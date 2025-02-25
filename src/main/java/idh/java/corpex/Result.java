package idh.java.corpex;

public class Result {

	int begin;
	int end;
	CorpusDocument corpusDocument;

	public Result(int begin, int end, CorpusDocument corpusDocument) {
		super();
		this.begin = begin;
		this.end = end;
		this.corpusDocument = corpusDocument;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public CorpusDocument getCorpusDocument() {
		return corpusDocument;
	}

	public void setCorpusDocument(CorpusDocument corpusDocument) {
		this.corpusDocument = corpusDocument;
	}
}
