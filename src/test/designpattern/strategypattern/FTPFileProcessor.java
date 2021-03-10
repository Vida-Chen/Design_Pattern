package test.designpattern.strategypattern;

public class FTPFileProcessor implements IFileProcessor
{
	public void sendFile()
	{
		System.out.println("Send file by ftp!");
	}
}
