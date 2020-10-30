package test.designpattern.templatemethod.bean;

public class XSection extends Document
{
	private String attributeC;

	public XSection(String doc_no, String doc_ver, String updatetime)
	{
		// TODO Auto-generated constructor stub
		super(doc_no, doc_ver, updatetime);
	}

	public String getAttributeC()
	{
		return attributeC;
	}

	public void setAttributeC(String attributeC)
	{
		this.attributeC = attributeC;
	}
}
