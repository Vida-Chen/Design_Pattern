package test.designpattern.templatemethod.bean;

public class POD extends Document
{
	private String attributeA;

	public POD(String doc_no, String doc_ver, String updatetime)
	{
		// TODO Auto-generated constructor stub
		super(doc_no, doc_ver, updatetime);
	}

	public String getAttributeA()
	{
		return attributeA;
	}

	public void setAttributeA(String attributeA)
	{
		this.attributeA = attributeA;
	}
}
