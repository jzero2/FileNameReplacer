/**
 * 
 */
package searchaction;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MP3 파일의 파일명을 쉽게 변경 하기 위하여 만들었습니다.
 * Regular Expression 을 이용하였습니다.
 * 
 * @author jzero2
 * Created on 2006. 1. 10
 */
public class ReplaceActionImpl implements FileAction {

	private String replaceStr = null;
	private final String REPLACE_PREFIX_CHAR = "\\\\";
	private final String REPLACE_FIND_INDEX = "\\d{1}";
	
	public ReplaceActionImpl() { }

	@Override
	public int doAction (File sourceFile, final String[] matchGroup) {
		
		String[] str = replaceStr.split(this.REPLACE_PREFIX_CHAR);
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i<str.length;i++) {
			Pattern replacePattern = Pattern.compile(this.REPLACE_FIND_INDEX);
			Matcher m = replacePattern.matcher(str[i]);
			if (m.lookingAt()) {
				int idx = Integer.parseInt(String.valueOf(str[i].charAt(0)));
				sb.append(matchGroup[idx-1]).append(str[i].substring(1));
			} else {
				sb.append(str[i]);
			}
		}
		
		File destFile = new File(sourceFile.getParent(), sb.toString());
		System.out.println("[" + sourceFile.getPath() + "] -> [" + destFile + "]");
		
		return (sourceFile.renameTo(destFile))? 1 : 0 ;
	}
	
	
	public void setReplacePattern(final String replaceStr) {
		this.replaceStr = replaceStr;
	}
	



}
