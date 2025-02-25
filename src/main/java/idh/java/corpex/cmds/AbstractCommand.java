package idh.java.corpex.cmds;

import idh.java.corpex.CorpEx;

public abstract class AbstractCommand implements ICommand {

	CorpEx mainApplication;
	
	public AbstractCommand(CorpEx mainApp) {
		this.mainApplication = mainApp;
	}

	public CorpEx getMainApplication() {
		return this.mainApplication;
	}
	
	protected void log(String s) {
		System.out.println(s.replaceAll("\\n", "\\\\n"));
	};

}
