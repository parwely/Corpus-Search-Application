package idh.java.corpex.cmds;

import java.util.Set;

import idh.java.corpex.CorpEx;
import idh.java.corpex.ILeftRightResultSorter;
import idh.java.corpex.Result;

public class SortCommand extends AbstractCommand {

    public static String commandTerm = "sort";

    SearchCommand searchCommand;

    public SortCommand(CorpEx mainApp) {
	super(mainApp);
    }

    @Override
    public boolean verify(String[] args) {
	if (getMainApplication().getCurrentCorpus() == null)
	    return false;
	if (!args[0].equalsIgnoreCase(commandTerm))
	    return false;
	if (getMainApplication().getSorter() instanceof ILeftRightResultSorter) {
	    if (args.length != 2)
		return false;
	    if (!(args[1].equalsIgnoreCase("left") || args[1].equalsIgnoreCase("right")))
		return false;
	} else {
	    if (args.length != 1)
		return false;
	}

	// Verify that last command was a search command
	searchCommand = new SearchCommand(getMainApplication());
	if (!searchCommand.verify(getMainApplication().getLastSearchCommand()))
	    return false;
	return true;
    }

    @Override
    public void execute(String[] args) {
	Set<Result> results = searchCommand.getSearchResults(this.getMainApplication().getLastSearchCommand());

	if (getMainApplication().getSorter() instanceof ILeftRightResultSorter) {
	    if (args[1].equalsIgnoreCase("left")) {
		searchCommand.printResults(((ILeftRightResultSorter) getMainApplication().getSorter()).sortLeft(results));
	    } else if (args[1].equalsIgnoreCase("right")) {
		searchCommand.printResults(((ILeftRightResultSorter) getMainApplication().getSorter()).sort(results));
	    }
	} else {
	    searchCommand.printResults(getMainApplication().getSorter().sort(results));
	}
    }

}
