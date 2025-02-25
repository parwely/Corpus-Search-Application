package idh.java.corpex.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import idh.java.corpex.CorpusDocument;
import idh.java.corpex.IFileImporter;
import idh.java.corpex.ex.InvalidFileException;
import idh.java.corpex.ex.InvalidOperationException;

public class XMLFileImporter implements IFileImporter {

	@Override
	public CorpusDocument importFile(File inputFile) throws InvalidFileException, IOException {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(inputFile);

			CorpusDocument cd = new CorpusDocument();
			try {
				cd.setId(inputFile.getCanonicalPath());
				cd.setTextContent(doc.getDocumentElement().getTextContent());
			} catch (InvalidOperationException e) {
				e.printStackTrace();
				// this should not happen here, because fields are still null.
			}
			cd.setTitle(inputFile.getName());
			return cd;
		} catch (ParserConfigurationException | SAXException e) {
			throw new InvalidFileException();
		} 
	}

	@Override
	public boolean canImport(File file) {
	    return file.getName().endsWith(".xml");
	}
}
