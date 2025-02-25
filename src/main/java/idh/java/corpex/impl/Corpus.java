package idh.java.corpex.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import idh.java.corpex.CorpusDocument;
import idh.java.corpex.ICorpus;
import idh.java.corpex.ITokenizer;
import idh.java.corpex.Result;
import idh.java.corpex.Token;

public class Corpus implements ICorpus {

    String name;

    Map<String, Set<Result>> casedIndex = new HashMap<String, Set<Result>>();
    Map<String, Set<Result>> uncasedIndex = new HashMap<String, Set<Result>>();

    ITokenizer tokenizer = new Tokenizer();

    public Corpus(String name) {
	this.name = name;
    }

    @Override
    public void add(CorpusDocument document) {
	List<Token> tokens = tokenizer.tokenize(document.getTextContent());
	for (Token t : tokens) {
	    if (!casedIndex.containsKey(t.getCoveredText()))
		casedIndex.put(t.getCoveredText(), new HashSet<Result>());
	    casedIndex.get(t.getCoveredText()).add(new Result(t.getBegin(), t.getEnd(), document));

	    if (!uncasedIndex.containsKey(t.getCoveredText().toLowerCase()))
		uncasedIndex.put(t.getCoveredText().toLowerCase(), new HashSet<Result>());
	    uncasedIndex.get(t.getCoveredText().toLowerCase()).add(new Result(t.getBegin(), t.getEnd(), document));
	}
    }

    @Override
    public Set<Result> search(String query, boolean caseInsensitive) {
	if (caseInsensitive)
	    return uncasedIndex.get(query.toLowerCase());
	else
	    return casedIndex.get(query);
    }

    @Override
    public String getName() {
	return this.name;
    }

    @Override
    public void setName(String name) {
	this.name = name;
    }

}
