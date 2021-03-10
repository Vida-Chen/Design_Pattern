package test.designpattern.strategypattern;

public class ToDownstreamSender
{
	IFileProcessor fileProcessor;

	public ToDownstreamSender(IFileProcessor fileProcessor)
	{
		this.fileProcessor = fileProcessor;
	}

	public void processing()
	{
		this.fileProcessor.sendFile();
		sendData();
		sendMail();
	}

	private void sendData()
	{
		System.out.println("Send data to interface table.");
	}

	private void sendMail()
	{
		System.out.println("Send inform mail.");
	}

	public void setFileProcessingWay(IFileProcessor fileProcessor)
	{
		this.fileProcessor = fileProcessor;
	}
}
