package idh.java.corpex.cmds;

import java.io.File;

import idh.java.corpex.CorpEx;
import idh.java.corpex.CorpusDocument;
import idh.java.corpex.IFileImporter;

public class ImportCommand extends AbstractCommand {

    public ImportCommand(CorpEx mainApp) {
	super(mainApp);
    }

    public static String commandTerm = "import";

    @Override
    public boolean verify(String[] args) {
	// check general things
	if (args.length < 2 || args.length > 3)
	    return false;
	if (!args[0].equalsIgnoreCase(commandTerm))
	    return false;

	// check argument 1
	String a1 = args[1];
	File file = new File(a1);
	if (!file.isDirectory())
	    return false;
	if (!file.canRead())
	    return false;

	// everything okay
	return true;
    }

    @Override
    public void execute(String[] args) {
	File corpusDirectory = new File(args[1]);
	String corpusName = corpusDirectory.getName();
	if (args.length == 3)
	    corpusName = args[2];
	int counter = 0;
	IFileImporter[] importers = getMainApplication().getFileImporter();
	for (File file : corpusDirectory.listFiles()) {
	    for (int i = 0; i < importers.length; i++) {
		try {
		    if (importers[i].canImport(file)) {
			CorpusDocument cd = importers[i].importFile(file);
			getMainApplication().getImportedCorpus(corpusName).add(cd);
			counter++;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
	log("Imported " + String.valueOf(counter) + " files in corpus " + corpusName + ".");
    }

}
