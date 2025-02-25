package idh.java.corpex;

import idh.java.corpex.ex.InvalidTokenBoundariesException;

/**
 * This class represents a token within a longer sequence of characters (= a
 * String). A token here is identified by two integer numbers (begin and end)
 * and the entire sequence (which is a reference and therefore not wasteful).
 * 
 * The text that is covered by a token can be extracted with the method
 * {@link String#substring(int, int)}:
 * <code>entireString.substring(begin, end)</code>, as also exemplified in the
 * method {@link #getCoveredText()}.
 * 
 * It is possible to create and use a Token-object that has no reference to the
 * entire String. In this case, the covered text cannot be extracted.
 *
 *
 * <br/>
 * Please note that the end position is actually the position of the next
 * character. In the example below, the token "the" would be represented by
 * <code>begin=0</code> and <code>end=3</code>. This follows from the way
 * {@link String#substring(int, int)} works.
 * 
 * <table>
 * <tr>
 * <td>t</td>
 * <td>h</td>
 * <td>e</td>
 * <td></td>
 * <td>d</td>
 * <td>o</td>
 * <td>g</td>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>1</td>
 * <td>2</td>
 * <td>3</td>
 * <td>4</td>
 * <td>5</td>
 * <td>6</td>
 * </tr>
 * </table>
 * 
 */
public class Token {

    int begin;
    int end;
    String entireString;

    /**
     * Create a new Token object with reference to a full String.
     * 
     * @param entireString The full String
     * @param begin        begin position
     * @param end          end position
     * @throws InvalidTokenBoundariesException
     */
    public Token(String entireString, int begin, int end) throws InvalidTokenBoundariesException {
	if (begin < 0 || begin > entireString.length())
	    throw new InvalidTokenBoundariesException();
	if (end < 0 || end > entireString.length())
	    throw new InvalidTokenBoundariesException();
	if (end == begin)
	    throw new InvalidTokenBoundariesException();
	if (end < begin)
	    throw new InvalidTokenBoundariesException();
	this.begin = begin;
	this.end = end;
	this.entireString = entireString;
    }

    /**
     * Creates a new token without reference to the entire string.
     * 
     * @param begin
     * @param end
     * @throws InvalidTokenBoundariesException
     */
    public Token(int begin, int end) throws InvalidTokenBoundariesException {
	if (begin < 0 || end < 0)
	    throw new InvalidTokenBoundariesException();
	if (end == begin)
	    throw new InvalidTokenBoundariesException();
	if (end < begin)
	    throw new InvalidTokenBoundariesException();
	this.begin = begin;
	this.end = end;
	this.entireString = null;
    }

    /**
     * Returns a String representation of the Token. Please note that this is
     * <b>not</b> the text surface of the token, but includes the actual begin/end
     * numbers as well as the text surface. It is thus longer than the text surface.
     * 
     * E.g., the token <code>new Token(0,3,"the dog barks")</code> would be
     * represented as <code>[0,3:the]</code>. To access the surface text of the
     * token, please use {@link #getCoveredText()}.
     */
    @Override
    public String toString() {
	StringBuilder b = new StringBuilder();
	b.append('[').append(String.valueOf(begin));
	b.append(',').append(String.valueOf(end));
	if (this.entireString != null)
	    b.append(':').append(entireString.substring(begin, end));
	b.append(']');
	return b.toString();
    }

    public int getBegin() {
	return begin;
    }

    public int getEnd() {
	return end;
    }

    /**
     * Returns the surface text of the token. If there is no reference to the entire
     * text, the object has no way of knowing what the token is. It therefore
     * returns a sequence of "X" characters in the length of the token.
     * 
     * @return
     */
    public String getCoveredText() {
	if (this.entireString != null)
	    return this.entireString.substring(begin, end);
	else
	    return "X".repeat(end - begin);
    }

}
