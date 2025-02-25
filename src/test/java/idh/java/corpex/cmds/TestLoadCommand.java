package idh.java.corpex.cmds;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;

import idh.java.corpex.CorpEx;
import idh.java.corpex.ICorpus;

public class TestLoadCommand {
    static String LOAD = "load";

    LoadCommand cmd;
    CorpEx corpex;
    ICorpus corpus;

    @BeforeEach
    void setUp() {
	corpex = mock(CorpEx.class);
	corpus = mock(ICorpus.class);
	when(corpex.getImportedCorpus(anyString())).thenReturn(corpus);
	cmd = new LoadCommand(corpex);
    }

    @Test
    void testVerify() {
	assertFalse(cmd.verify(new String[] {}), "Empty array");
	assertFalse(cmd.verify(new String[] { "" }), "Empty command name");
	assertFalse(cmd.verify(new String[] { "bla" }), "Wrong command name");
	when(corpex.getCorpusNames()).thenReturn(new HashSet<String>());
	assertFalse(cmd.verify(new String[] { LOAD, "NotExistingCorpus" }), "Load corpus that doesn't exist");
	when(corpex.getCorpusNames()).thenReturn(Sets.newSet("TEST"));
	assertFalse(cmd.verify(new String[] { LOAD, "NotExistingCorpus" }), "Load corpus that doesn't exist");
	assertTrue(cmd.verify(new String[] { LOAD, "TEST" }), "Load corpus that does exist");
    }

    @Test
    /**
     * This test produces console output ...
     */
    void testExecute() {
	when(corpex.getCorpusNames()).thenReturn(Sets.newSet("TEST"));
	cmd.execute(new String[] { LOAD, "TEST" });
	verify(corpex, times(1)).setCurrentCorpus("TEST");
    }
}
