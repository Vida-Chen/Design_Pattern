package test.designpattern.templatemethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import test.designpattern.templatemethod.bean.Document;

public abstract class DocumentsInterfaceInSqlite
{
	static
	{
		try
		{
			Class.forName("org.sqlite.JDBC");// 註冊Driver實作物件
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private String dbUrl = "jdbc:sqlite:test.sqlite";
	protected final Document obj;
	protected Connection conn;
	protected PreparedStatement ps;
	protected String instSql_BasicInfo = "INSERT INTO BASICINFO (DOC_NO,DOC_VER,DOC_TYPE,ADDITIONAL,UPDATETIME) VALUES (?,?,?,?,?)";
	protected String instSql_RefDocs = "INSERT INTO REFDOCS (DOC_NO,DOC_VER,REF,UPDATETIME) VALUES (?,?,?,?)";

	DocumentsInterfaceInSqlite(Document obj)
	{
		this.obj = obj;
		if (obj != null)
		{
			String isSpecialProcesString = checkIsSpecialType() == true ? "Y"
					: "N";
			System.out.println("處理類型 ->" + this.obj.getDoc_type()
					+ " ,是否進行特殊處理?" + isSpecialProcesString);

			try
			{
				conn = DriverManager.getConnection(dbUrl);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	final void processing()// 骨架演算法
	{
		if (obj != null)
		{
			try
			{
				conn.setAutoCommit(false);

				sendBasicInfo();

				if (checkIsSpecialType())// Hook method,有預設處理內容,子類別可視情況override
				{
					doSpecialProcessing();// 子類別必須實作的抽象方法
				}

				ftpFiles();

				conn.commit();
			}
			catch (Exception e)
			{
				if (conn != null)
				{
					try
					{
						conn.rollback();
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			finally
			{
				if (conn != null)
				{
					try
					{
						conn.close();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	void sendBasicInfo() throws Exception
	{
		ps = conn.prepareStatement(instSql_BasicInfo);
		ps.setString(1, obj.getDoc_no());
		ps.setString(2, obj.getDoc_ver());
		ps.setString(3, obj.getDoc_type());
		ps.setString(4, obj.getAdditional());
		ps.setString(5, obj.getUpdatetime());
		ps.executeUpdate();
		System.out.println(obj.getDoc_no() + " 拋送文件號碼、版本、修改日期等基本資料.");
	}

	boolean checkIsSpecialType()
	{
		if ("Inner".equalsIgnoreCase(obj.getDoc_type())
				|| "BD".equalsIgnoreCase(obj.getDoc_type()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	void ftpFiles()
	{
		System.out.println(obj.getDoc_no() + " 依命名規則打包各類檔案至FTP server.");
	}

	abstract void doSpecialProcessing() throws Exception;
}
