package test.designpattern.templatemethod.bean;

public class Inner extends Document
{
	private String attributeB;

	public Inner(String doc_no, String doc_ver, String updatetime)
	{
		// TODO Auto-generated constructor stub
		super(doc_no, doc_ver, updatetime);
	}

	public String getAttributeB()
	{
		return attributeB;
	}

	public void setAttributeB(String attributeB)
	{
		this.attributeB = attributeB;
	}

}
