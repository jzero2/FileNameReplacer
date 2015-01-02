package searchaction;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 파일명을 찾은 다음 특정 작업을 실행합니다. 
 * Regular Expression 을 이용하였습니다.
 * 
 * @author jzero2
 * Created on 2006. 1. 10
 */
public class FileSearcher {

	private File searchFile = null;
	private Pattern searchPattern = null;
	private FileAction action = null;
	
	
	public FileSearcher() {}
	
	
	public FileSearcher(final File file, final Pattern pattern, FileAction action) {
		setSearchFile(file);
		setSearchPattern(pattern);
		setAction(action);
	}
	
	
	private void checkNull() throws Exception {
		if (searchPattern == null) throw new NullPointerException(" pattern is null ");
		if (searchFile == null) throw new NullPointerException(" file is null ");
		if (action == null) throw new NullPointerException(" action is null ");
	}
	
	
	public final int doProc() throws Exception {
		int count = 0;
		checkNull();
		
		if (searchFile.isDirectory()) {
			//Directory 도 검색해야 하고 조건에 맞는다면 해당 Action 
			//Directory 는 검색하지 않기로 하자..
			File[] fList = searchFile.listFiles();
			for (int i = 0; i < fList.length; i++) {
				File f = fList[i];
				FileSearcher fs = new FileSearcher(f, searchPattern, action);
				count += fs.doProc();
			}
		} // Directory
		else if (searchFile.isFile()) {
			String fileName = searchFile.getName();
			Matcher m = searchPattern.matcher(fileName);
			if (m.find()) {
				String[] arrMatchGroup = new String[m.groupCount()];
				for (int idx=0; idx< m.groupCount(); idx++) {
					arrMatchGroup[idx] = m.group(idx+1);
				}
				count += action.doAction(searchFile, arrMatchGroup);
			}
			m.reset();
			clear();
		} // File
			
		return count;
	}
	
	
	private void clear() {
		this.searchFile = null;
		this.searchPattern = null;
	}
	
	
	public void setSearchFile(final File file) {
		this.searchFile = file;
	}

	
	public void setSearchPattern(final String patternStr) {
		if (patternStr != null) {
			this.searchPattern = Pattern.compile(patternStr);
		}
	}
	
	
	public void setSearchPattern(final Pattern pattern) {
		this.searchPattern = pattern;
	}

	
	public void setAction(FileAction action) {
		this.action = action;
	}


	
}
