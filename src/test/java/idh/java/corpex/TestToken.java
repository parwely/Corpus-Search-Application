package idh.java.corpex;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import idh.java.corpex.ex.InvalidTokenBoundariesException;

public class TestToken {
	Token token;
	
	@Test
	public void testObjectCreationWithKnownText() {
		String testSentence = "this is a sentence.";
		
		assertDoesNotThrow(() -> new Token(testSentence, 0, 4));
		assertThrows(InvalidTokenBoundariesException.class, () -> new Token(testSentence, 20, 24));
		assertThrows(InvalidTokenBoundariesException.class, () -> new Token(testSentence, -10, -5));
		assertThrows(InvalidTokenBoundariesException.class, () -> new Token(testSentence, 5, 2));
	}
	
	@Test
	public void testGetCoveredTextKnownText() throws InvalidTokenBoundariesException {
		String testSentence = "this is a sentence.";
		
		token = new Token(testSentence, 0, 4);
		assertEquals("this", token.getCoveredText());
		
		token = new Token(testSentence, 5, 7);
		assertEquals("is", token.getCoveredText());

		token = new Token(0,4);
		assertEquals("XXXX", token.getCoveredText());
	}
	
	@Test
	public void testToStringWithKnownText() throws InvalidTokenBoundariesException {
		String testSentence = "this is a sentence.";
		
		token = new Token(testSentence, 0, 4);
		assertEquals("[0,4:this]", token.toString());

		token = new Token(testSentence, 5, 7);
		assertEquals("[5,7:is]", token.toString());

	}
	
	@Test
	public void testObjectCreationWithoutText() {
		
		assertDoesNotThrow(() -> new Token(0, 4));
		assertThrows(InvalidTokenBoundariesException.class, () -> new Token(-10, -5));
		assertThrows(InvalidTokenBoundariesException.class, () -> new Token(5, 2));
	}

	
	@Test
	public void testGetCoveredTextUnknownText() throws InvalidTokenBoundariesException {
		token = new Token(0, 4);
		assertEquals("XXXX", token.getCoveredText());
		
		token = new Token(5, 7);
		assertEquals("XX", token.getCoveredText());
	}
}
