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
public class SearchActionImpl implements FileAction {

	public SearchActionImpl() { }

	@Override
	public int doAction (File sourceFile, final String[] matchGroup) {
		System.out.println("[" + sourceFile.getPath() + "] �� ã�ҽ��ϴ�.");
		return 1 ;
	}
	

}
