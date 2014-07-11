/**
 * 
 */
package searchaction;

import java.io.File;

/**
 * @author jzero2
 *
 */
public interface FileAction {
	public int doAction(File sourceFile, String[] matchGroup);
}
