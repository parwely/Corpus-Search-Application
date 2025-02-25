package idh.java.corpex.impl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import idh.java.corpex.CorpusDocument;
import idh.java.corpex.IFileImporter;
import idh.java.corpex.ex.InvalidOperationException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HTMLFileImporter implements IFileImporter {

    public boolean canImport(File file) {
        return file.getName().toLowerCase().endsWith(".html") || file.getName().toLowerCase().endsWith(".htm");
    }

 @Override
    public CorpusDocument importFile(File file) throws IOException {
     
     if (file == null || !file.exists() || !file.canRead()) {
         throw new IOException("File does not exist or cannot be read: " + (file != null ? file.getAbsolutePath() : "null"));
     }

     if (!canImport(file)) {
         throw new IllegalArgumentException("Unsupported file format: " + file.getName());
     }

     // Lies die Datei explizit mit UTF-8
     String htmlContent = Files.readString(Path.of(file.getAbsolutePath()), StandardCharsets.UTF_8);
     Document document = Jsoup.parse(htmlContent);

  // Extrahiere den Body-Text unter Beibehaltung der Zeilenumbrüche
     Element body = document.body();
     String extractedText = (body != null) ? body.wholeText() : document.wholeText();

  // Entferne unnötige führende und abschließende Leerzeichen
     extractedText = extractedText.stripTrailing();

     // Stelle sicher, dass der Text mit einem \n endet, falls nötig
     if (!extractedText.isEmpty() && !extractedText.endsWith("\n")) {
         extractedText += "\n";}
     
     CorpusDocument cd = new CorpusDocument();
        try {
	    cd.setId(file.getCanonicalPath());
	
       cd.setTitle(file.getName()); 
        cd.setTextContent(extractedText);
        } catch (InvalidOperationException | IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
       
         return cd;
    }
}

	