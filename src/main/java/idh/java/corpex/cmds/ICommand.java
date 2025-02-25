package idh.java.corpex.cmds;

import idh.java.corpex.CorpEx;

public interface ICommand {
	boolean verify(String[] args);
	
	void execute(String[] args);
	
	CorpEx getMainApplication();
}
