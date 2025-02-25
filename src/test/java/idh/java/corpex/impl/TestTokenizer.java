package idh.java.corpex.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

import idh.java.corpex.CorpEx;
import idh.java.corpex.IExtendedTokenizer;
import idh.java.corpex.ITokenizer;
import idh.java.corpex.Token;

public class TestTokenizer {
    ITokenizer tokenizer;

    @BeforeEach
    public void setUp() {
	tokenizer = new CorpEx().getTokenizer();
    }

    @Test
    public void testNormalText() {
	String text = "this is a test.";
	assertNotNull(tokenizer);
	List<Token> tokenList = tokenizer.tokenize(text);

	Iterator<Token> tokenIter = tokenList.iterator();
	Token token;
	assertTrue(tokenIter.hasNext());
	token = tokenIter.next();
	assertEquals("this", text.substring(token.getBegin(), token.getEnd()));
	assertTrue(tokenIter.hasNext());
	token = tokenIter.next();
	assertEquals("is", text.substring(token.getBegin(), token.getEnd()));
	assertTrue(tokenIter.hasNext());
	token = tokenIter.next();
	assertEquals("a", text.substring(token.getBegin(), token.getEnd()));
	assertTrue(tokenIter.hasNext());
	token = tokenIter.next();
	assertEquals("test.", text.substring(token.getBegin(), token.getEnd()));
	assertFalse(tokenIter.hasNext());
    }

    @Test
    public void testEmptyText() {
	String text = "";
	assertNotNull(tokenizer);
	List<Token> tokenList = tokenizer.tokenize(text);
	assertTrue(tokenList.isEmpty());
    }

    @Test
    public void testTextWithWhitespace() {
	String text = " test ";
	assertNotNull(tokenizer);
	List<Token> tokenList = tokenizer.tokenize(text);
	assertFalse(tokenList.isEmpty());
	assertEquals(1, tokenList.size());
	assertEquals("test", tokenList.get(0).getCoveredText());

	text = "    		 test  		 ";
	tokenList = tokenizer.tokenize(text);
	assertFalse(tokenList.isEmpty());
	assertEquals(1, tokenList.size());
	assertEquals("test", tokenList.get(0).getCoveredText());
    }

    @Test
    @EnabledIf("optionalTask")
    public void testExNormalTextOnlySpace() {
	String text;
	List<Token> tokenList;
	assertNotNull(tokenizer);
	assertTrue(tokenizer instanceof IExtendedTokenizer);
	IExtendedTokenizer tok = (IExtendedTokenizer) tokenizer;
	tok.setBoundaryCharacters(" ");
	text = "    		 test  		 ";
	tokenList = tokenizer.tokenize(text);
	assertFalse(tokenList.isEmpty());
	assertEquals(3, tokenList.size());
	assertEquals("\t\t", tokenList.get(0).getCoveredText());
	assertEquals("test", tokenList.get(1).getCoveredText());
	assertEquals("\t\t", tokenList.get(2).getCoveredText());
    }

    @Test
    @EnabledIf("optionalTask")
    public void testExNormalTextOnlyTab() {
	String text;
	List<Token> tokenList;

	assertNotNull(tokenizer);
	assertTrue(tokenizer instanceof IExtendedTokenizer);
	IExtendedTokenizer tok = (IExtendedTokenizer) tokenizer;
	tok.setBoundaryCharacters("\t");
	text = "    		 test  		 ";
	tokenList = tokenizer.tokenize(text);
	assertFalse(tokenList.isEmpty());
	assertEquals(3, tokenList.size());
	assertEquals("    ", tokenList.get(0).getCoveredText());
	assertEquals(" test  ", tokenList.get(1).getCoveredText());
	assertEquals(" ", tokenList.get(2).getCoveredText());
    }

    boolean optionalTask() {
	return false;
    }

}
