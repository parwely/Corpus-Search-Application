package idh.java.corpex.cmds;

import idh.java.corpex.CorpEx;

public class LoadCommand extends AbstractCommand {
    public static String commandTerm = "load";

    public LoadCommand(CorpEx mainApp) {
	super(mainApp);
    }

    @Override
    public boolean verify(String[] args) {
	// check general things
	if (args.length != 2)
	    return false;
	if (!args[0].equalsIgnoreCase(commandTerm))
	    return false;

	// verify that corpus exists
	if (!getMainApplication().getCorpusNames().contains(args[1]))
	    return false;
	return true;
    }

    @Override
    public void execute(String[] args) {
	getMainApplication().setCurrentCorpus(args[1]);
	log("Loaded corpus " + args[1] + ".");
    }

}
