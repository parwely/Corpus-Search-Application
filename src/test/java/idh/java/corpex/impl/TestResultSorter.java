package idh.java.corpex.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.corpex.CorpEx;
import idh.java.corpex.CorpusDocument;
import idh.java.corpex.IResultSorter;
import idh.java.corpex.Result;

public class TestResultSorter {
    IResultSorter sorter;

    Collection<Result> resultSet;

    CorpusDocument doc1, doc2;

    @BeforeEach
    public void setUp() throws Exception {
	sorter = new CorpEx().getSorter();
	assertNotNull(sorter);

	doc1 = mock(CorpusDocument.class);
	doc2 = mock(CorpusDocument.class);
	when(doc1.getId()).thenReturn("Doc 1");
	when(doc1.getTextContent()).thenReturn("\n\nsome title\n\nThis is some text.\n");
	when(doc2.getId()).thenReturn("Doc 2");
	when(doc2.getTextContent()).thenReturn("\n\nsome title\n\nThis is a text.\n");

	resultSet = new ArrayList<Result>();
	resultSet.add(new Result(20, 22, doc1));
	resultSet.add(new Result(20, 22, doc2));


    }

    @Test
    public void testBeforeSort() {
	Collection<Result> sorted = resultSet;
	Iterator<Result> iter = sorted.iterator();

	Result r;
	r = iter.next();
	assertEquals(doc1, r.getCorpusDocument());

	r = iter.next();
	assertEquals(doc2, r.getCorpusDocument());

    }

    @Test
    public void testSort() {
	Collection<Result> sorted = sorter.sort(resultSet);
	Iterator<Result> iter = sorted.iterator();

	Result r;
	r = iter.next();
	assertEquals(doc2, r.getCorpusDocument());
	
	r = iter.next();
	assertEquals(doc1, r.getCorpusDocument());

    }



}
