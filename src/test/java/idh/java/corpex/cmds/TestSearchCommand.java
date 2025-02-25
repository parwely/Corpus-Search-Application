package idh.java.corpex.cmds;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.corpex.CorpEx;
import idh.java.corpex.ICorpus;

public class TestSearchCommand {
    static String SEARCH = "search";

    SearchCommand cmd;
    CorpEx corpex;
    ICorpus corpus;

    @BeforeEach
    void setUp() {
	corpex = mock(CorpEx.class);
	corpus = mock(ICorpus.class);
	when(corpex.getProperties()).thenReturn(new Properties());

	cmd = new SearchCommand(corpex);
    }

    @Test
    void testVerify() {
	assertFalse(cmd.verify(new String[] {}), "Empty array");
	assertFalse(cmd.verify(new String[] { "" }), "Empty command name");
	assertFalse(cmd.verify(new String[] { "bla" }), "Wrong command name");

	when(corpex.getCurrentCorpus()).thenReturn(corpus);
	assertTrue(cmd.verify(new String[] { SEARCH, "bla" }), "regular search");
	assertTrue(cmd.verify(new String[] { SEARCH, "bla", "ci" }), "ci search");

	when(corpex.getCurrentCorpus()).thenReturn(null);
	assertFalse(cmd.verify(new String[] { SEARCH, "bla" }), "regular search, w/o loaded corpus");
	assertFalse(cmd.verify(new String[] { SEARCH, "bla", "ci" }), "ci search, w/o loaded corpus");
    }

    @Test
    void testExecute() {
	when(corpex.getCurrentCorpus()).thenReturn(corpus);
	cmd.execute(new String[] { SEARCH, "test" });
	verify(corpus, times(1)).search("test", false);
	cmd.execute(new String[] { SEARCH, "test", "ci" });
	verify(corpus, times(1)).search("test", true);
    }
}
