package idh.java.corpex.impl;
import java.util.*;

import idh.java.corpex.CorpusDocument;
import idh.java.corpex.IResultSorter;
import idh.java.corpex.Result;
/*
 * Vorgehensweise:

    Den gesamten Text aus CorpusDocument holen:
    Über result.getCorpusDocument().getTextContent().
    Den Kontext bestimmen:
    Der relevante Kontext beginnt direkt nach result.getEnd(), d.h., wir nehmen den Substring ab dieser Position.
    Sortieren nach diesem Kontext:
    Falls kein Kontext mehr vorhanden ist (z.B. result.getEnd() ist am Ende des Dokuments), sortieren 
 * 
 * 
 */

public class ResultSorter implements IResultSorter {
    @Override
    public Collection<Result> sort(Collection<Result> results) {
        List<Result> sortedList = new ArrayList<>(results);
        
        sortedList.sort(Comparator.comparing(this::getSortingKey));
        
        return sortedList;
    }

    private String getSortingKey(Result result) {
	    CorpusDocument doc = result.getCorpusDocument();
	    String fullText = doc.getTextContent();

	    //explizit sicherstellen, dass der Sorting Key keine Sonderzeichen oder unnötigen Whitespaces enthält:
	    if (fullText == null || result.getEnd() >= fullText.length()) {
	        return ""; // Falls kein Kontext vorhanden ist
	    }
	    
	    //Abfolge die das Result bereinigt von Whitespaces und Lower/UpperCase, sowie  alle Zeichen, die nicht Buchstaben (a-zA-Z) oder Ziffern (0-9) sind, durch Leerzeichen.
	
	    return fullText.substring(result.getEnd()).stripLeading().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ");
	}
    
    /*
     * 
     * Analyse:

    Die Result-Objekte markieren Positionen im Text.
    Die beiden Dokumente enthalten ähnliche Inhalte:
        doc1: "This is some text."
        doc2: "This is a text."
    Die Result-Objekte beginnen beide bei Index 20 und enden bei 22.
    → Das bedeutet, dass sie beide das Wort "a" oder "some" markieren.
    Der relevante Kontext ist also das, was nach diesem Wort kommt:
        doc1: " text."
        doc2: " text."
    Alphabetisch ist " text." bei doc2 vor doc1, da "a" vor "some" kommt.
     */
}
