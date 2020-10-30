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
			Class.forName("org.sqlite.JDBC");// ���UDriver��@����
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
			System.out.println("�B�z���� ->" + this.obj.getDoc_type()
					+ " ,�O�_�i��S��B�z?" + isSpecialProcesString);

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

	final void processing()// ���[�t��k
	{
		if (obj != null)
		{
			try
			{
				conn.setAutoCommit(false);

				sendBasicInfo();

				if (checkIsSpecialType())// Hook method,���w�]�B�z���e,�l���O�i�����poverride
				{
					doSpecialProcessing();// �l���O������@����H��k
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
		System.out.println(obj.getDoc_no() + " �߰e��󸹽X�B�����B�ק������򥻸��.");
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
		System.out.println(obj.getDoc_no() + " �̩R�W�W�h���]�U���ɮצ�FTP server.");
	}

	abstract void doSpecialProcessing() throws Exception;
}
