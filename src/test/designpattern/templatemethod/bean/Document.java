package test.designpattern.templatemethod.bean;

import java.lang.reflect.Method;

public class Document
{
	private String doc_no, doc_ver, doc_type, updatetime, additional;

	public Document()
	{
		// TODO Auto-generated constructor stub
	}

	public Document(String doc_no, String doc_ver, String updatetime)
	{
		// TODO Auto-generated constructor stub
		setDoc_no(doc_no);
		setDoc_ver(doc_ver);
		setUpdatetime(updatetime);
	}

	public String getDoc_no()
	{
		return doc_no;
	}

	public void setDoc_no(String doc_no)
	{
		this.doc_no = doc_no;
	}

	public String getDoc_ver()
	{
		return doc_ver;
	}

	public void setDoc_ver(String doc_ver)
	{
		this.doc_ver = doc_ver;
	}

	public String getUpdatetime()
	{
		return updatetime;
	}

	public void setUpdatetime(String updatetime)
	{
		this.updatetime = updatetime;
	}

	public String getDoc_type()
	{
		this.doc_type = this.getClass().getSimpleName();
		return doc_type;
	}

	// public void setDoc_type(String doc_type)
	// {
	// this.doc_type = doc_type;
	// }

	public String getAdditional()
	{
		try
		{
			for (Method method : this.getClass().getMethods())
			{
				if (method.getName().indexOf("getAttribute") > -1)
				{
					// System.out.println("Class Name:" + this.getClass()
					// + " ,getAdditional attName:" + method.getName());
					additional = method.invoke(this) != null ? method.invoke(
							this).toString() : additional;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return additional;
	}
}
