package idh.java.corpex.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

import idh.java.corpex.CorpEx;
import idh.java.corpex.CorpusDocument;
import idh.java.corpex.ICorpus;
import idh.java.corpex.Result;

public class TestCorpus {
    ICorpus corpus;

    @BeforeEach
    public void setUp() throws Exception {
	corpus = new CorpEx().getCorpus("testcorpus");
	assertNotNull(corpus);

	CorpusDocument doc = mock(CorpusDocument.class);
	when(doc.getId()).thenReturn("Test Id");
	when(doc.getTextContent()).thenReturn("\n" + "\n" + "some title\n" + "\n" + "This is a text.\n");
	corpus.add(doc);
    }

    @Test
    public void testMetaData() {
	assertNotNull(corpus);
	assertNotNull(corpus.getName());
	assertEquals("testcorpus", corpus.getName());
    }

    @Test
    public void testRegularCSSearch() {
	assertNotNull(corpus);

	Set<Result> res = corpus.search("This", false);
	assertNotNull(res);
	assertEquals(1, res.size());

	Result r = res.stream().findFirst().get();
	assertNotNull(r);
	assertEquals(14, r.getBegin());
	assertEquals(18, r.getEnd());
    }

    @Test
    @EnabledIf("optionalTask")
    public void testRegularCISearch() {
	Set<Result> res;
	Result r;
	assertNotNull(corpus);

	res = corpus.search("This", true);
	assertNotNull(res);
	assertEquals(1, res.size());

	r = res.stream().findFirst().get();
	assertNotNull(r);
	assertEquals(14, r.getBegin());
	assertEquals(18, r.getEnd());

	res = corpus.search("this", true);
	assertNotNull(res);
	assertEquals(1, res.size());

	r = res.stream().findFirst().get();
	assertNotNull(r);
	assertEquals(14, r.getBegin());
	assertEquals(18, r.getEnd());

    }

    boolean optionalTask() {
	return false;
    }
}
