package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class PODSenderInSqlite extends DocumentsInterfaceInSqlite
{
	PODSenderInSqlite()
	{
		this(null);
		System.out.println("PODSender 未指定待處理的物件.");
	}

	PODSenderInSqlite(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing() throws Exception
	{
		System.out.println(obj.getDoc_no() + " 處理POD特殊資料.");
	}
}
