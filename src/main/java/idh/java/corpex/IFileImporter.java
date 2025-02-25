package idh.java.corpex;

import java.io.File;
import java.io.IOException;

import idh.java.corpex.ex.InvalidFileException;

public interface IFileImporter {

	/**
	 * This method gets a {@link File}-object as argument, and returns a object of
	 * the class {@link CorpusDocument}.
	 * 
	 * An implementation of this method <b>must</b> check that the file exists and
	 * is readable. If not, it throws an IOException. Ideally, this check occurs
	 * before any time-consuming reading attempts are done.
	 * 
	 * <br/>
	 * The method further employs an XML parser to extract the text content of the
	 * input file. Any XML parser can be used here, as long as it's able to return
	 * the <b>entire</b> textual content of the document. The textual content of the
	 * document consists of all characters that are not part of an XML tag. E.g., 
	 * the textual content of <code>&lt;bla number="7"&gt;blubb&nbsp;&nbsp;blobb&lt;/bla&gt;</code> 
	 * would be <code>blubb&nbsp;&nbsp;blobb</code> (note the two space characters in the middle.
	 * 
	 * @see CorpusDocument
	 * 
	 * @param inputFile
	 * @return
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	CorpusDocument importFile(File inputFile) throws IOException, InvalidFileException;

	/**
	 * For each file to be imported, the importer is asked if it is able to import
	 * it. Importers may decide this based on the file extension (i.e.,
	 * XMLFileImporter returns true if the file ends on ".xml").
	 * 
	 * @param file
	 * @return
	 */
	boolean canImport(File file);
}
