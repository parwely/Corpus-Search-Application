package idh.java.corpex;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import idh.java.corpex.cmds.ICommand;
import idh.java.corpex.cmds.ImportCommand;
import idh.java.corpex.cmds.LoadCommand;
import idh.java.corpex.cmds.SearchCommand;
import idh.java.corpex.cmds.SortCommand;
import idh.java.corpex.impl.Corpus;
import idh.java.corpex.impl.HTMLFileImporter;
import idh.java.corpex.impl.ResultSorter;
import idh.java.corpex.impl.Tokenizer;


import idh.java.corpex.impl.XMLFileImporter;

public class CorpEx {

    public static CorpEx App;

    /**
     * Constants to store property names
     */
    public static String KEY_CONTEXT_SIZE = "KEY_CONTEXT_SIZE";
    public static String KEY_RESULTS_ON_SINGLE_LINE = "KEY_RESULTS_ON_SINGLE_LINE";

    String[] lastSearchCommand = null;

    /**
     * The store for different corpora
     */
    Map<String, ICorpus> corpora = new HashMap<String, ICorpus>();

    /**
     * The currently loaded corpus
     */
    ICorpus currentCorpus = null;

    /**
     * Configuration options for the whole application
     */
    Properties properties = new Properties();

    /**
     * Returns a (new) instance of ITokenizer
     * 
     * @return
     */
    public ITokenizer getTokenizer() {
	Tokenizer t = new Tokenizer();
	return t;
    }

    public IResultSorter getSorter() {
	return new ResultSorter();
    }

    /**
     * Returns a (new) instance of IFileImporter
     * 
     * @return
     */
    public IFileImporter[] getFileImporter() {
	return new IFileImporter[] { new XMLFileImporter(), new HTMLFileImporter() };
    }

    /**
     * Returns a (new) instance of ICorpus, with the provided name as corpus name.
     * 
     * @param corpusName The name of the new corpus.
     * @return
     */
    public ICorpus getCorpus(String corpusName) {
	return new Corpus(corpusName);
    }

    /**
     * Retrieves a corpus that has already been imported. If it doesn't exist, the
     * method creates a new corpus using {@link #getCorpus(String)}.
     * 
     * @param corpusName The name of the corpus. Can be any string.
     * @return
     */
    public ICorpus getImportedCorpus(String corpusName) {
	if (!this.corpora.containsKey(corpusName))
	    this.corpora.put(corpusName, this.getCorpus(corpusName));
	return this.corpora.get(corpusName);
    }

    /**
     * Main method to start the application.
     * 
     * @param args No arguments accepted.
     */
    public static void main(String[] args) {
	CorpEx.App = new CorpEx();
	CorpEx.App.run();
    }

    /**
     * Interprets the command entered by a user. The method expects the command to
     * be split and provided as argument args.
     * 
     * @param args
     */
    private void command(String[] args) {
	ICommand subProgram = null;

	// go over the different possible commands
	if (args[0].equalsIgnoreCase(ImportCommand.commandTerm)) {
	    subProgram = new ImportCommand(this);
	} else if (args[0].equalsIgnoreCase(SearchCommand.commandTerm)) {
	    subProgram = new SearchCommand(this);
	} else if (args[0].equalsIgnoreCase(LoadCommand.commandTerm)) {
	    subProgram = new LoadCommand(this);
	} else if (args[0].equalsIgnoreCase(SortCommand.commandTerm)) {
	    subProgram = new SortCommand(this);
	}

	// if the command thinks the user input is valid, we execute it
	if (subProgram != null && subProgram.verify(args)) {
	    subProgram.execute(args);
	    if (subProgram instanceof SearchCommand)
		lastSearchCommand = args;
	}
    }

    /**
     * Main UI loop. Shows a prompt to the user and retrieves their input. Splits
     * the input on white space and passes the result to {@link #command(String[])}.
     */
    private void run() {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	while (true) {
	    try {
		StringBuilder b = new StringBuilder();
		if (getCurrentCorpus() != null)
		    b.append(getCurrentCorpus().getName());
		b.append("> ");
		System.out.print(b.toString());
		String command = br.readLine();
		command(command.split(" +"));
	    } catch (Exception e) {
		e.printStackTrace();
		break;
	    }
	}
    }

    /**
     * 
     * @return access to the properties of the whole application
     */
    public Properties getProperties() {
	return properties;
    }

    /**
     * 
     * @return A reference to the currently loaded corpus
     */
    public ICorpus getCurrentCorpus() {
	return currentCorpus;
    }

    /**
     * 
     * @return A set of all imported corpus names
     */
    public Set<String> getCorpusNames() {
	return this.corpora.keySet();
    }

    /**
     * Change the currently loaded corpus
     * 
     * @param currentEngine
     */
    public void setCurrentCorpus(ICorpus currentEngine) {
	this.currentCorpus = currentEngine;
    }

    /**
     * Change the currently loaded corpus
     * 
     * @param corpusName
     */
    public void setCurrentCorpus(String corpusName) {
	this.currentCorpus = this.getImportedCorpus(corpusName);
    }

    public String[] getLastSearchCommand() {
	return this.lastSearchCommand;
    }

    public int getContextSize() {
	return (Integer) getProperties().getOrDefault(CorpEx.KEY_CONTEXT_SIZE, 20);
    }

}
