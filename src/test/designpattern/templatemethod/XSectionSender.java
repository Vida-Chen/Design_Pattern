package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class XSectionSender extends DocumentsInterface
{
	XSectionSender()
	{
		this(null);
		System.out.println("XSectionSender �����w�ݳB�z������.");
	}

	XSectionSender(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing()
	{
		System.out.println(obj.getDoc_no() + " �B�zXSection�S����.");
	}

	@Override
	boolean checkIsSpecialType()// Hook method, override super class definition
	{
		return true;
	}
}
