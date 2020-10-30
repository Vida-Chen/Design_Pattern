package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

/**
 * @author Vida
 * 
 *         應用：將各類文件的基本屬性 or檔案資料...等外拋給有需要的單位
 * 
 *         Design Pattern - Template Method(樣板方法模式)
 * 
 *         定義：將一個演算法的骨架定義在一個方法中，而演算法本身會用到的一些方法
 *         ，則定義在次類別中。樣板方法讓次類別可以在不改變演算法架構的情況下，重新定義演算法中的某些步驟。
 * 
 *         要點1、：將樣板方法(骨架)定義在父類別中，通常可以宣告此方法為final，如此可避免子類別override其中的演算法
 *         要點2、：父類別為抽象類別，通常用「繼承」的方式來封裝演算法，這點與策略模式使用的「合成」方式不同
 *         要點3、：可定義一些掛鉤(Hook)方法於父類別中，用來作為條件控制，讓子類別可選擇要不要影響父類別中的演算法流程
 * 
 *         補充1、：策略模式(Strategy Pattern)定義 -
 *         定義了演算法家族，個別封裝起來，讓它們之間可以互相替換，此模式讓演算法的變動不會影響到使用演算法的程式。
 * 
 *         References:Head First Design Pattern/深入淺出設計模式
 */
abstract class DocumentsInterface
{
	protected final Document obj;

	DocumentsInterface(Document obj)
	{
		this.obj = obj;
		if (obj != null)
		{
			String isSpecialProcesString = checkIsSpecialType() == true ? "Y"
					: "N";
			System.out.println("處理類型 ->" + this.obj.getDoc_type()
					+ " ,是否進行特殊處理?" + isSpecialProcesString);
		}
	}

	final void processing()// 骨架演算法
	{
		if (obj != null)
		{
			sendBasicInfo();

			if (checkIsSpecialType())// Hook method,有預設處理內容,子類別可視情況override
			{
				doSpecialProcessing();// 子類別必須實作的抽象方法
			}

			ftpFiles();
		}
	}

	void sendBasicInfo()
	{
		System.out.println(obj.getDoc_no() + " 拋送文件號碼、版本、修改日期等基本資料.");
	}

	boolean checkIsSpecialType()
	{
		if ("Inner".equalsIgnoreCase(obj.getDoc_type())
				|| "BD".equalsIgnoreCase(obj.getDoc_type()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	void ftpFiles()
	{
		System.out.println(obj.getDoc_no() + " 依命名規則打包各類檔案至FTP server.");
	}

	abstract void doSpecialProcessing();
}
