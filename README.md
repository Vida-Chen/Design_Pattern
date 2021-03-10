## Design Pattern - Some implementations.
參閱Head First Design Pattern/深入淺出設計模式一書後，發現過往一些寫code的習慣可再精進，
故此Repository主要是嘗試以設計模式的方法改寫以往的應用實例，皆以java jdk 1.6為主要程式語言開發，
日後還會陸續放上各類設計模式的相關實作，一方面也藉此機會學習git & github相關控管:smile:

### Package程式結構與命名：
 - test.designpattern.XXX pattern - 該種design pattern的主程式
 - test.designpattern.XXX pattern.bean - data bean
 - test.designpattern.XXX pattern.tester - 該種design pattern的測試程式

### 1. 樣板方法模式(Template Method)
 	應用說明：將各類文件(e.g. Inner、POD、XSection)的基本屬性 or檔案資料...等外拋給有需要的單位，無論何種文件類型，大部份處理流程皆一致(外拋basic data,額外資訊,檔案資料等)，故採用此樣板方法模式將主流程以final包裝起來，搭配掛鉤(hook)機制及子類別實作抽象方法的方式可再細項差異化處理各類型文件外拋資料。
 
 * test.designpattern.templatemethod.DocumentsInterface - concept algorithm
 * test.designpattern.templatemethod.DocumentsInterfaceInSqlite - 除concept algorithm外，佐以寫入資料至sqlite，故需載入額外的sqlite-jdbc jar.
 
### 2. 策略模式(Strategy Method)
> 應用說明：外拋資料給下游單位，處理三件事 - 拋檔案、拋資料、寄通知信件，
> 其中拋檔案尚未確定以何種方式進行(e.g. ftp or share folder...etc)，
> 故將拋檔行為定抽出到interface(IFileProcessor, sendFile方法)，
> 實際執行內容交由各別processor實作(FTPFileProcessor、ShareFolderFileProcessor)，
> 保留彈性給後來確定實作機制時才動態決定真正的實作方法。

IFileProcessor interface
```sh
public interface IFileProcessor
{
	public void sendFile();
}
```

FTPFileProcessor

```sh
public class FTPFileProcessor implements IFileProcessor
{
	public void sendFile()
	{
		System.out.println("Send file by ftp!");
	}
}
```

ShareFolderFileProcessor

```sh
public class ShareFolderFileProcessor implements IFileProcessor
{
	public void sendFile()
	{
		System.out.println("Send file by share folder!");
	}
}
```

TestProgram

```sh
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
```

ToDownstreamSender

```sh
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
	...以下省略
}
```