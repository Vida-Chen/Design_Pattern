package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class InnerSender extends DocumentsInterface
{
	InnerSender()
	{
		this(null);
		System.out.println("InnerSender �����w�ݳB�z������.");
	}

	InnerSender(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing()
	{
		System.out.println(obj.getDoc_no() + " �B�zInner�S����.");
	}
}
