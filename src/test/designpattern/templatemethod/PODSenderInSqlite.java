package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class PODSenderInSqlite extends DocumentsInterfaceInSqlite
{
	PODSenderInSqlite()
	{
		this(null);
		System.out.println("PODSender �����w�ݳB�z������.");
	}

	PODSenderInSqlite(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing() throws Exception
	{
		System.out.println(obj.getDoc_no() + " �B�zPOD�S����.");
	}
}
