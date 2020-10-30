package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class InnerSenderInSqlite extends DocumentsInterfaceInSqlite
{
	InnerSenderInSqlite()
	{
		this(null);
		System.out.println("InnerSender �����w�ݳB�z������.");
	}

	InnerSenderInSqlite(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing() throws Exception
	{
		System.out.println(obj.getDoc_no() + " �B�zInner�S����.");
		ps = conn.prepareStatement(instSql_RefDocs);
		ps.setString(1, obj.getDoc_no());
		ps.setString(2, obj.getDoc_ver());
		ps.setString(3, "AAA11223-A");
		ps.setString(4, obj.getUpdatetime());
		ps.executeUpdate();
	}
}
