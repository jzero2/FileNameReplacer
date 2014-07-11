/**
 * 
 */
package searchaction;

import java.io.File;

/**
 * MP3 ������ ���ϸ��� ���� ���� �ϱ� ���Ͽ� ��������ϴ�.
 * Regular Expression �� �̿��Ͽ����ϴ�.
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
