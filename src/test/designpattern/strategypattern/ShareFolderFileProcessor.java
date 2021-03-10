package test.designpattern.strategypattern;

public class ShareFolderFileProcessor implements IFileProcessor
{
	public void sendFile()
	{
		System.out.println("Send file by share folder!");
	}
}
