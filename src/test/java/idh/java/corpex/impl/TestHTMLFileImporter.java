package idh.java.corpex.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.corpex.CorpEx;
import idh.java.corpex.CorpusDocument;
import idh.java.corpex.IFileImporter;
import idh.java.corpex.ex.InvalidFileException;

public class TestHTMLFileImporter {
    IFileImporter importer;
    File file;

    @BeforeEach
    public void setUp() throws Exception {
	// Stelle sicher, dass CorpEx.App nicht null ist;; SPÄTER RAUSLÖSCHEN!!!
        CorpEx.App = new CorpEx();
	for (IFileImporter imp : CorpEx.App.getFileImporter()) {
	    if (imp.canImport(new File("bla.html")))
		importer = imp;
	}
    }

    /**
     * Tests whether a valid XML file is read correctly.
     * 
     * @throws InvalidFileException
     * @throws IOException
     */
    @Test
    public void testFile1() throws IOException, InvalidFileException {
	String textContent = "\n" + "First Title" + "\n" + "This is a sentence\n";

	file = new File("src/test/resources/html/test1.html");
	assertNotNull(importer);
	CorpusDocument cd = importer.importFile(file);
	assertNotNull(cd);
	assertEquals("test1.html", cd.getTitle());
	assertEquals(textContent, cd.getTextContent());
    }

    /**
     * Tests whether IOException is thrown if the file does not exist
     */
    @Test
    public void testFileDoesNotExist() {
	file = new File("src/test/resources/html/test2.html");
	assertNotNull(importer);
	assertThrows(IOException.class, () -> importer.importFile(file));

    }

    @Test
    public void testFile3() throws InvalidFileException, IOException {
	assertNotNull(importer);
	file = new File("src/test/resources/html/test3.html");
	CorpusDocument cd = importer.importFile(file);
	assertNotNull(cd);
	assertEquals("test3.html", cd.getTitle());

	// Because the text content is very long, we test several segments
	String expected = "\n" + "		\n" + "		\n" + "		\n" + "";
	assertEquals(expected, cd.getTextContent().substring(0, 10));

	expected = "Wechseln zu";
	assertEquals(expected, cd.getTextContent().substring(95, 106));

	expected = "nach obenzu";
	assertEquals(expected, cd.getTextContent().substring(199, 210));
    }

    @Test
    public void testCanImport() {
	File f;
	f = new File("src/test/resources/test.htm");
	assertTrue(importer.canImport(f));
	f = new File("src/test/resources/test.html");
	assertTrue(importer.canImport(f));
	f = new File("src/test/resources/test.ht");
	assertFalse(importer.canImport(f));
	f = new File("src/test/resources/test.htmlx");
	assertFalse(importer.canImport(f));
    }

}
