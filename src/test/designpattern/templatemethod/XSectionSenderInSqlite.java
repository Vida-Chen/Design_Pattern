package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class XSectionSenderInSqlite extends DocumentsInterfaceInSqlite
{
	XSectionSenderInSqlite()
	{
		this(null);
		System.out.println("XSectionSender 未指定待處理的物件.");
	}

	XSectionSenderInSqlite(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing() throws Exception
	{
		System.out.println(obj.getDoc_no() + " 處理XSection特殊資料.");
		ps = conn.prepareStatement(instSql_RefDocs);
		ps.setString(1, obj.getDoc_no());
		ps.setString(2, obj.getDoc_ver());
		ps.setString(3, "A12345-0");
		ps.setString(4, obj.getUpdatetime());
		ps.executeUpdate();
	}

	@Override
	boolean checkIsSpecialType()// Hook method, override super class definition
	{
		return true;
	}
}
