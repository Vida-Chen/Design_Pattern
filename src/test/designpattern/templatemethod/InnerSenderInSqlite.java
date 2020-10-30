package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class InnerSenderInSqlite extends DocumentsInterfaceInSqlite
{
	InnerSenderInSqlite()
	{
		this(null);
		System.out.println("InnerSender 未指定待處理的物件.");
	}

	InnerSenderInSqlite(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing() throws Exception
	{
		System.out.println(obj.getDoc_no() + " 處理Inner特殊資料.");
		ps = conn.prepareStatement(instSql_RefDocs);
		ps.setString(1, obj.getDoc_no());
		ps.setString(2, obj.getDoc_ver());
		ps.setString(3, "AAA11223-A");
		ps.setString(4, obj.getUpdatetime());
		ps.executeUpdate();
	}
}
