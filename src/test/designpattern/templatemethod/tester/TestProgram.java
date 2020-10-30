package test.designpattern.templatemethod.tester;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import test.designpattern.templatemethod.bean.Document;
import test.designpattern.templatemethod.bean.Inner;
import test.designpattern.templatemethod.bean.POD;
import test.designpattern.templatemethod.bean.XSection;

public class TestProgram
{
	public static void main(String[] args)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(new Date());
		// System.out.println("nowTime:" + nowTime);

		// 建立測試用的假物件資料
		Document[] docs = { new Inner("A12345", "0", nowTime),
				new POD("AAA11223", "A", nowTime),
				new XSection("AAJ11111", "0", nowTime) };

		// foreach依據傳入的物件類型, 利用反射(reflection)執行對應的拋送機制
		for (Document doc : docs)
		{
			try
			{
				Class clz = Class.forName("test.designpattern.templatemethod."
						+ doc.getDoc_type() + "Sender");
				// 指定參數型態
				Class[] params = { Document.class };
				// Constructor constructor = clz.getConstructor(params);
				Constructor constructor = clz.getDeclaredConstructor(params);
				constructor.setAccessible(true);
				// 指定參數內容
				Object[] paramObjs = { doc };
				// 實例化
				Object obj = constructor.newInstance(paramObjs);

				// Method method = clz.getMethod("processing");
				Method method = clz.getSuperclass().getDeclaredMethod(
						"processing");
				method.setAccessible(true);
				method.invoke(obj);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
