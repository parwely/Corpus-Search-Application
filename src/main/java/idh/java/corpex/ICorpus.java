package idh.java.corpex;

import java.util.Set;

public interface ICorpus {

	/**
	 * Returns the name of the corpus. The currently loaded corpus is also displayed
	 * in the command line user interface of CorpEx.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the name of the corpus. Should only be called once.
	 * @param n
	 */
	void setName(String n);
	
	/**
	 * Adds a single new {@CorpusDocument}-object to the corpus. This method in
	 * particular also registers the document in the search index.
	 * 
	 * @param document
	 */
	void add(CorpusDocument document);

	/**
	 * This method executes a search within the corpus. In contrast to a
	 * document-search, we are searching for specific portions of the corpus, 
	 * which are represented as objects of type {@link Result}.
	 * 
	 * The query can be a single token. The parameter <code>ci</code> controls, 
	 * whether the search pays attention to upper- and lower-casing. If set to 
	 * <code>true</code>, a search for "dog" returns "dog", "Dog", "DOG", etc. 
	 * Please note that the implementation of the case-insensitive search is optional.
	 * 
	 * If there are no positive results, the returned set of results is empty.
	 * 
	 * @param query 
	 * @param ci
	 * @return
	 */
	Set<Result> search(String query, boolean ci);
}
