package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

public class PODSender extends DocumentsInterface
{
	PODSender()
	{
		this(null);
		System.out.println("PODSender 未指定待處理的物件.");
	}

	PODSender(Document obj)
	{
		super(obj);
	}

	@Override
	void doSpecialProcessing()
	{
		System.out.println(obj.getDoc_no() + " 處理POD特殊資料.");
	}
}
