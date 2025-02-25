package idh.java.corpex;

import idh.java.corpex.ex.InvalidOperationException;

/**
 * This class represents a single document within our corpus. A document
 * consists of a text (basically a string), a title and an id value. The id
 * value <b>must be unique</b> within our corpus.
 *
 */
public class CorpusDocument {
	String title;
	String id = null;
	String textContent = null;

	/**
	 * The title of the document. 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the document.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the id value of the document.
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get the text content of the document.
	 * @return
	 */
	public String getTextContent() {
		return textContent;
	}

	/**
	 * Sets the text content of the document. This can only be done once.
	 * 
	 * @param textContent
	 * @throws InvalidOperationException If a text content has already been set.
	 */
	public void setTextContent(String textContent) throws InvalidOperationException {
		if (this.textContent != null)
			throw new InvalidOperationException("Once set, text content field cannot be reset.");
		this.textContent = textContent;
	}

	/**
	 * Sets the id of the document.  This can only be done once.
	 * @param id
	 * @throws InvalidOperationException If an id has already been set.
	 */
	public void setId(String id) throws InvalidOperationException {
		if (this.id != null)
			throw new InvalidOperationException("Once set, id field cannot be reset.");
		this.id = id;
	}
}
