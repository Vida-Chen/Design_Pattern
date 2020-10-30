package test.designpattern.templatemethod.tester;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.designpattern.templatemethod.bean.Document;
import test.designpattern.templatemethod.bean.Inner;
import test.designpattern.templatemethod.bean.POD;
import test.designpattern.templatemethod.bean.XSection;

public class TestProgramInSqlite
{
	private static String dbUrl = "jdbc:sqlite:test.sqlite";
	private static String createTb_BasicInfo = "CREATE TABLE IF NOT EXISTS BASICINFO("
			+ "DOC_NO TEXT NOT NULL, DOC_VER TEXT NOT NULL,"
			+ "DOC_TYPE TEXT NOT NULL, ADDITIONAL TEXT, UPDATETIME TEXT NOT NULL,"
			+ "PRIMARY KEY (DOC_NO,DOC_VER))";
	private static String createTb_RefDocs = "CREATE TABLE IF NOT EXISTS REFDOCS("
			+ "DOC_NO TEXT NOT NULL, DOC_VER TEXT NOT NULL,"
			+ "REF TEXT, UPDATETIME TEXT NOT NULL,PRIMARY KEY (DOC_NO,DOC_VER))";
	private static String clearTb_BasicInfoString = "DELETE FROM BASICINFO";
	private static String clearTb_RefDocsString = "DELETE FROM REFDOCS";

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

	public static void main(String[] args)
	{
		doPreparings();

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(new Date());
		// System.out.println("nowTime:" + nowTime);

		// �إߴ��եΪ���������
		List<Document> docs = new ArrayList<Document>();
		Inner inner = new Inner("A12345", "0", nowTime);
		inner.setAttributeB("Inner attribute data.");
		docs.add(inner);
		docs.add(new POD("AAA11223", "A", nowTime));
		docs.add(new XSection("AAJ11111", "0", nowTime));

		// foreach�̾ڶǤJ����������, �Q�ΤϮg(reflection)����������߰e����
		for (Document doc : docs)
		{
			try
			{
				Class clz = Class.forName("test.designpattern.templatemethod."
						+ doc.getDoc_type() + "SenderInSqlite");
				// ���w�Ѽƫ��A
				Class[] params = { Document.class };
				// Constructor constructor = clz.getConstructor(params);
				Constructor constructor = clz.getDeclaredConstructor(params);
				constructor.setAccessible(true);
				// ���w�ѼƤ��e
				Object[] paramObjs = { doc };
				// ��Ҥ�
				Object obj = constructor.newInstance(paramObjs);

				// Method method = clz.getMethod("processing");
				Method method = clz.getSuperclass().getDeclaredMethod(
						"processing");
				method.setAccessible(true);
				method.invoke(obj);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void doPreparings()
	{
		try
		{
			Connection conn = DriverManager.getConnection(dbUrl);
			Statement st = conn.createStatement();
			st.executeUpdate(createTb_BasicInfo);
			st.executeUpdate(createTb_RefDocs);
			st.executeUpdate(clearTb_BasicInfoString);
			st.executeUpdate(clearTb_RefDocsString);
		}
		catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
