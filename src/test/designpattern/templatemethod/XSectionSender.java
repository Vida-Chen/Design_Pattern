package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class XSectionSender extends DocumentsInterface
{
	XSectionSender()
	{
		this(null);
		System.out.println("XSectionSender 未指定待處理的物件.");
	}

	XSectionSender(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing()
	{
		System.out.println(obj.getDoc_no() + " 處理XSection特殊資料.");
	}

	@Override
	boolean checkIsSpecialType()// Hook method, override super class definition
	{
		return true;
	}
}
