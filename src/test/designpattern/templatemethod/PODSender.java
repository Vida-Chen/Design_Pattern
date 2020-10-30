package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class PODSender extends DocumentsInterface
{
	PODSender()
	{
		this(null);
		System.out.println("PODSender �����w�ݳB�z������.");
	}

	PODSender(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing()
	{
		System.out.println(obj.getDoc_no() + " �B�zPOD�S����.");
	}
}
