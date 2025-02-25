package idh.java.corpex.cmds;

import java.util.Collection;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import idh.java.corpex.CorpEx;
import idh.java.corpex.CorpusDocument;
import idh.java.corpex.Result;

public class SearchCommand extends AbstractCommand {
    public static String commandTerm = "search";

    public SearchCommand(CorpEx mainApp) {
	super(mainApp);
    }

    @Override
    public boolean verify(String[] args) {
	if (args.length < 2 || args.length > 3)
	    return false;
	if (!args[0].equalsIgnoreCase(commandTerm))
	    return false;
	if (args.length == 3)
	    if (!args[2].equals("ci"))
		return false;
	if (getMainApplication().getCurrentCorpus() == null)
	    return false;
	return true;
    }

    protected Set<Result> getSearchResults(String[] args) {
	boolean ci = (args.length == 3 && args[2].equals("ci") ? true : false);
	Set<Result> results = getMainApplication().getCurrentCorpus().search(args[1], ci);

	if (results == null) {
	    log("Search term not found.");
	    return new HashSet<Result>();
	}
	return results;
    }

    protected void printResults(Collection<Result> results) {
	int rCounter = 1;
	int context = getMainApplication().getContextSize();

	for (Result result : results) {
	    StringBuilder b = new StringBuilder();

	    CorpusDocument foundDocument = result.getCorpusDocument();

	    if (printResultsOnSingleLine()) {
		b.append(fixLength(foundDocument.getTitle(), 15)).append(' ');
	    } else {
		b.append("== Result ").append(rCounter++).append(" of ").append(String.valueOf(results.size()))
			.append(':').append(' ').append(foundDocument.getTitle()).append(" ==").append('\n');

	    }
	    for (int i = 0; i < context - result.getBegin(); i++)
		b.append(' ');
	    int beg = Math.max(result.getBegin() - context, 0);
	    int end = Math.min(result.getEnd() + context, foundDocument.getTextContent().length());
	    b.append(foundDocument.getTextContent().substring(beg, end));
	    b.append('\n');
	    log(b.toString());

	}
    }

    @Override
    public void execute(String[] args) {
	Set<Result> results = this.getSearchResults(args);

	if (results.isEmpty()) {
	    log("Search term not found.");
	    return;
	}

	printResults(results);

    }

    protected boolean printResultsOnSingleLine() {
	return Boolean
		.valueOf(getMainApplication().getProperties().getProperty(CorpEx.KEY_RESULTS_ON_SINGLE_LINE, "true"));
    }

    protected String fixLength(String s, int length) {
	if (s.length() < length)
	    return StringUtils.rightPad(s, length);
	else
	    return StringUtils.abbreviate(s, length);
    }

}
