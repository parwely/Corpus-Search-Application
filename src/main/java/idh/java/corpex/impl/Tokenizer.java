package idh.java.corpex.impl;

import java.util.ArrayList;

import java.util.List;
import idh.java.corpex.ITokenizer;
import idh.java.corpex.Token;
import idh.java.corpex.ex.InvalidTokenBoundariesException;

public class Tokenizer implements ITokenizer {

    @Override
    public List<Token> tokenize(String text) {
        List<Token> tokens = new ArrayList<>();
        int currentPosition = 0;

        while (currentPosition < text.length()) {
            // Überspringe Whitespaces
            while (currentPosition < text.length() && Character.isWhitespace(text.charAt(currentPosition))) {
                currentPosition++;
            }
            if (currentPosition >= text.length()) {
                break;
            }

            int start = currentPosition;

            // Suche das Ende des Tokens (bis zum nächsten Whitespace)
            while (currentPosition < text.length() && !Character.isWhitespace(text.charAt(currentPosition))) {
                currentPosition++;
            }
            int end = currentPosition;

            try {
                tokens.add(new Token(text, start, end));
            } catch (InvalidTokenBoundariesException e) {
                e.printStackTrace();
            }
        }
        return tokens;
    }
}
