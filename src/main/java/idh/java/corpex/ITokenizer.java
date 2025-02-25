package idh.java.corpex;

import java.util.List;

public interface ITokenizer {
	/**
	 * This method takes a text in form of a {@link String} and returns a list of
	 * {@link Token}s (please refer to the {@link Token} documentation for details).
	 * 
	 * The tokenization rules are as follows:
	 * <ul>
	 * <li>Only printable characters can be (part of) a token. Whitespace of any
	 * kind is never in a token.</li>
	 * <li>Tokens are separated by one or more boundary characters. Boundary
	 * characters are <code>' '</code>, <code>'\t'</code> (tab stop), and
	 * <code>'\n'</code> (newline).
	 * <li>Whitespace characters between tokens are not conflated:
	 * <table>
	 * <tr>
	 * <td>t</td>
	 * <td>h</td>
	 * <td>e</td>
	 * <td></td>
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
	 * <td>7</td>
	 * </tr>
	 * </table>
	 * 
	 * The example above consists of two tokens, the first one being "the", the
	 * second one "dog". As there are two whitespace characters between these two,
	 * the second token begins at position 5.</li>
	 * </ul>
	 * 
	 * If the text is an empty string, the method returns an empty list.
	 * 
	 * While not strictly necessary given the nature of the tokens, the returned
	 * list should contain the tokens <i>in ascending order</i>, i.e., the token
	 * that comes first in the text is first in the list.
	 * 
	 * @see Token
	 * @param text
	 * @return
	 */
	List<Token> tokenize(String text);
}
