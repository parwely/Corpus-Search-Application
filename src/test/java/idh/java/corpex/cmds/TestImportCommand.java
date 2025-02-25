package idh.java.corpex.cmds;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.corpex.CorpEx;
import idh.java.corpex.CorpusDocument;
import idh.java.corpex.ICorpus;
import idh.java.corpex.IFileImporter;
import idh.java.corpex.ex.InvalidFileException;

public class TestImportCommand {
    static String IMPORT = "import";

    ImportCommand cmd;
    CorpEx corpex;
    ICorpus corpus;
    IFileImporter importer;

    @BeforeEach
    void setUp() throws IOException, InvalidFileException {
	corpex = mock(CorpEx.class);
	corpus = mock(ICorpus.class);
	importer = mock(IFileImporter.class);
	when(importer.importFile(any(File.class))).thenReturn(mock(CorpusDocument.class));
	when(importer.canImport(any(File.class))).thenReturn(true);
	when(corpex.getImportedCorpus(anyString())).thenReturn(corpus);
	when(corpex.getFileImporter()).thenReturn(new IFileImporter[] { importer });
	cmd = new ImportCommand(corpex);
    }

    @Test
    void testVerify() {
	assertFalse(cmd.verify(new String[] {}), "Empty array");
	assertFalse(cmd.verify(new String[] { "bla" }), "Wrong command name");
	assertFalse(cmd.verify(new String[] { IMPORT, "NotExistingFilename" }), "Import from file that does not exist");
	assertFalse(cmd.verify(new String[] { IMPORT, "pom.xml" }), "Import from something that's not a directory");
	assertTrue(cmd.verify(new String[] { IMPORT, "target" }), "Successful verification");
    }

    @Test
    /**
     * This test produces console output.
     */
    void testExecute() throws IOException, InvalidFileException {
	cmd.execute(new String[] { IMPORT, "src/test/resources/xml" });
	verify(importer, times(4)).importFile(any(File.class));
	verify(corpus, times(4)).add(any(CorpusDocument.class));
    }
}
