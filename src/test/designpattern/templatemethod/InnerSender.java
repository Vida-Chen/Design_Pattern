package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class InnerSender extends DocumentsInterface
{
	InnerSender()
	{
		this(null);
		System.out.println("InnerSender 未指定待處理的物件.");
	}

	InnerSender(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing()
	{
		System.out.println(obj.getDoc_no() + " 處理Inner特殊資料.");
	}
}
