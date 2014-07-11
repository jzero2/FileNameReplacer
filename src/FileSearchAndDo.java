

import java.io.File;

import searchaction.*;

/*
 * 정규식을 사용하여 파일을 찾거나 변경시키기 위해 사용.
 * 
 * Created on 2006. 1. 10
 * jzero2 test 
 */
public class FileSearchAndDo {

	public static final String CMD_SEARCH = "-s";
	public static final String CMD_REPLACE = "-r";
	public static final String CMD_DELETE = "-d";
	public static final String CMD_COPY = "-c";
 
	
	public static void usage() {
		System.out.println("\n 이 프로그램은 파일명을 변경할 때 사용합니다.");
		System.out.println("() 를 이용해 그룹화 하고 ^순번 을 이용하여 파일명을 변경합니다.\n ");
		System.out
				.println("[usage]FileSearchAndDo [command] [path] [Regular Expressions] [replace String]");
		System.out.println("");
		System.out.println("[command search ] -s ");
		System.out.println("[command replace] -r ");
		System.out.println("[command delete ] -d ");
		//System.out
		//		.println("[command copy ] -c filename length replaceFrom replaceTo unique");
		System.out.println("");
		System.out.println("[ex]FileSearchAndDo -s /WRB2B/Log 2005.*");
		System.out.println("[ex]FileSearchAndDo -r D:\\Music\\628 (\\d{3}\\ )(.*)(\\ \\-\\ )(.*)\\.mp3 ^2^3^4.mp3");
		System.out.println("[ex]FileSearchAndDo -d /WRB2B/Log 2005.*\\.log");

		System.out.println("\n -- 정규식 예제 --\n");
		System.out.println("\t. 	char 1");
		System.out.println("\t\\ 	escape");
		System.out.println("\t+ 	>=1");
		System.out.println("\t* 	>=0");
		System.out.println("\ta[1-3] a1~a3");
		System.out.println("\ta[13]  a1 or a3");
		System.out.println("\ta[^1]  a1 제외한 나머지");
		System.out.println("\t^ 시작");
		System.out.println("\t$ 끝");
	}
	
	
	public static FileAction getAction(String[] args) {
		
		FileAction action = null;
		
		if (args[0].equalsIgnoreCase(CMD_SEARCH)) {
			action = new SearchActionImpl();
		} else if (args[0].equalsIgnoreCase(CMD_REPLACE)) {
			action = new ReplaceActionImpl();
			((ReplaceActionImpl)action).setReplacePattern(args[3]); //replaceString
		} else if (args[0].equalsIgnoreCase(CMD_DELETE)) {
			action = new DeleteActionImpl();
		}
		
		return action;
	}
	
	
	/**
	 * 
	 * @param args[0] : command
	 * @param args[1] : 찾아야 할 경로
	 * @param args[2] : 검색 패턴
	 * @param args[3] : command 가 -r 일 경우 변경될 패턴
	 *  	
	 */
	public static void main(String[] args) {

		if (args.length < 3) {
			usage();
			//System.exit(1);
			
			args = new String[4]; 
			args[0] = "-s";
			//args[1] = "D:\\Music\\628";
			args[1] = "D:\\BNSWorks\\workspace\\SmartWallet_Server\\SmartWallet_DBIF5";
			//args[2] = "(\\d{3}\\ )(.*)(\\ \\-\\ )(.*)\\.mp3";
			//args[2] = "(\\w\\d\\d\\-)(.*)(\\-)(.*)\\.mp3";
			args[2] = "\\.svn";
			args[3] = "^2^3^4.mp3";
			//args[3] = "^2 ^3 ^4.mp3";
		} 
		
		FileSearcher fnm = new FileSearcher();
		fnm.setSearchFile(new File(args[1])); //searchFile
		fnm.setSearchPattern(args[2]); //searchPattern
		fnm.setAction(getAction(args));
		
		int count = 0;
		try { count = fnm.doProc(); } catch (Exception e) { e.printStackTrace(); }
		
		System.out.println(count + " 건의 파일이 처리되었습니다.");
	}

	
}
