package idh.java.corpex;

public interface IExtendedTokenizer extends ITokenizer {
    /**
     * This method is used to define a set of characters that delimits tokens in the
     * extended tokenizer.
     * 
     * For example, if the string to be tokenized is "the dog barks.", and we set
     * "eoa" as boundary characters, the tokenizer would produce the tokens "th", "
     * d", "g b" and "rks.".
     * 
     * 
     * 
     */
    void setBoundaryCharacters(String bs);
}
