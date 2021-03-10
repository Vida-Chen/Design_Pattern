package test.designpattern.strategypattern.tester;

import test.designpattern.strategypattern.FTPFileProcessor;
import test.designpattern.strategypattern.ToDownstreamSender;

public class TestProgram
{
	public static void main(String[] args)
	{
		// 待真正實作時才決定實作的方法為何? (此例可選擇以ftp or sharefolder方式, 拋資料給下游系統)
		ToDownstreamSender sender = new ToDownstreamSender(
				new FTPFileProcessor());
		// sender.setFileProcessingWay(new ShareFolderFileProcessor());
		sender.processing();
	}
}
