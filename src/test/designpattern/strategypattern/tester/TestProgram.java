package test.designpattern.strategypattern.tester;

import test.designpattern.strategypattern.FTPFileProcessor;
import test.designpattern.strategypattern.ToDownstreamSender;

public class TestProgram
{
	public static void main(String[] args)
	{
		// �ݯu����@�ɤ~�M�w��@����k����? (���ҥi��ܥHftp or sharefolder�覡, �߸�Ƶ��U��t��)
		ToDownstreamSender sender = new ToDownstreamSender(
				new FTPFileProcessor());
		// sender.setFileProcessingWay(new ShareFolderFileProcessor());
		sender.processing();
	}
}
