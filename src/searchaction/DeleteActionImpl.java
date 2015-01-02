/**
 * 
 */
package searchaction;

import java.io.File;

/**
 * MP3 파일의 파일명을 쉽게 변경 하기 위하여 만들었습니다.
 * Regular Expression 을 이용하였습니다.
 * 
 * @author jzero2
 * Created on 2006. 1. 10
 */
public class DeleteActionImpl implements FileAction {

	public DeleteActionImpl() {	}

	@Override
	public int doAction (File sourceFile, final String[] matchGroup) {
		
		boolean result = sourceFile.delete();
		
		if (result) System.out.println("[" + sourceFile.getPath() + "] deleted.");
		else System.out.println("Did not delete [" + sourceFile.getPath() + "] !! ");
		
		return (result)? 1 : 0 ;
	}
	
	
	

}
