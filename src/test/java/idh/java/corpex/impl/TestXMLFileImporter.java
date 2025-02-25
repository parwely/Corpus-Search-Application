package idh.java.corpex.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.corpex.CorpusDocument;
import idh.java.corpex.IFileImporter;
import idh.java.corpex.ex.InvalidFileException;

public class TestXMLFileImporter {
    IFileImporter importer;
    File file;

    @BeforeEach
    public void setUp() throws Exception {
	importer = new XMLFileImporter();
    }

    /**
     * Tests whether a valid XML file is read correctly.
     * 
     * @throws InvalidFileException
     * @throws IOException
     */
    @Test
    public void test1() throws IOException, InvalidFileException {
	String textContent = "\n" + "\n" + "some title\n" + "\n" + "This is a text.\n";

	file = new File("src/test/resources/xml/test1.xml");
	assertNotNull(importer);
	CorpusDocument cd = importer.importFile(file);
	assertNotNull(cd);
	assertEquals("test1.xml", cd.getTitle());
	assertEquals(textContent, cd.getTextContent());
    }

    /**
     * Tests whether InvalidFileException is thrown if the file is not valid XML
     */
    @Test
    public void test2() {
	file = new File("src/test/resources/xml/test2.xml");
	assertNotNull(importer);
	assertThrows(InvalidFileException.class, () -> importer.importFile(file));
    }

    /**
     * Tests whether IOException is thrown if the file does not exist
     */
    @Test
    public void test3() {
	file = new File("src/test/resources/xml/test0.xml");
	assertNotNull(importer);
	assertThrows(IOException.class, () -> importer.importFile(file));

    }

    @Test
    public void testFile3() throws IOException, InvalidFileException {
	String textContent = "\n" + "\n" + "some title\n" + "\n" + "This is a text with some embedded tags.\n";
	assertNotNull(importer);
	file = new File("src/test/resources/xml/test3.xml");
	CorpusDocument cd = importer.importFile(file);
	assertNotNull(cd);
	assertEquals("test3.xml", cd.getTitle());
	assertEquals(textContent, cd.getTextContent());
    }

}
