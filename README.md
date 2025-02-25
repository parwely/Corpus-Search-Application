For Modulprüfung Programmierung (Softwaretechnologie)
Studiengänge: B.A. Informationsverarbeitung, B.A. Medieninformatik in Java mit MavenDependencies und JUNIT Tests

## Beschreibung der Anwendung

Bei dem Programm **CorpEx** handelt es sich um einen textbasierten Korpus-Explorer. Es dient dazu, größere Korpora, die in verschiedenen Dateiformaten vorliegen können, effizient durchsuchen zu können. Als Ergebnis der Suche werden sog. *keyword in context*-Listen (KWIC) angezeigt, die das Suchwort sowie links und rechts davon den Kontext der Fundstelle anzeigen.

Startet man das Programm, kann auf der Kommandozeile ein Befehl eingegeben werden, der je nachdem auch Parameter entgegennimmt. 

Mit dem Befehl `import` kann ein Korpus importiert werden. Der Befehl erwartet ein Argument, und zwar einen Verzeichnisnamen. Alle Dateien innerhalb des Verzeichnisses, die auf `.xml`, `.htm` oder `.html` enden, werden dann eingelesen. Optional kann ein zweites Argument angegeben werden, nämlich ein Name für das Korpus. Wird kein Name angegeben, verwendet das Programm den Verzeichnisnamen.

Mit dem Befehl `load` kann ein Korpus geladen werden. Ist ein Korpus geladen, wird der Korpusname vor dem Prompt angezeigt. 

Der Befehl `search` erlaubt eine token-basierte Suche. D.h. es werden ausschließlich ganze Tokens gesucht, dieses aber sehr schnell. Als Ausgabe erscheint dann die KWIC-Liste.

Sobald eine Suche erfolgt ist, kann diese mit dem Kommando `sort` sortiert werden. Die Einträge werden dabei alphabetisch aufsteigend nach dem auf den Suchbegriff folgenden Kontext sortiert.

### 1. Import von HTML & XML-Dateien

### 2. Tokenisierung des Textinhalts

### 3. Sortierung einer Suche

Beispiel:

```
bundesrat> search wirtschaft
Bundesrat_20...  auch die schweizer wirtschaft profitiert vom kauf\n
Bundesrat_20... auch im bereich der wirtschaft (0.3)
\n         
\n \n
Bundesrat_20...         °h oder der wirtschaft nicht ausreichen
\n \n
Bundesrat_20...             und die wirtschaft weniger beschäftigt\n
Bundesrat_20... lle bevölkerung und wirtschaft einen beitrag leist\n
Bundesrat_20...  damit verliert die wirtschaft wertvolle fachkräft\n
Bundesrat_20...  °hh der anteil der wirtschaft am gesamtertrag bet\n
Bundesrat_20...   das stärkt unsere wirtschaft (0.28)
\n         
\n\n
Bundesrat_20...   °hh er stärkt die wirtschaft trägt zu unserem wo\n
bundesrat> sort right
Bundesrat_20...   das stärkt unsere wirtschaft (0.28)
\n         
\n\n
Bundesrat_20... auch im bereich der wirtschaft (0.3)
\n         
\n \n
Bundesrat_20...  °hh der anteil der wirtschaft am gesamtertrag bet\n
Bundesrat_20... lle bevölkerung und wirtschaft einen beitrag leist\n
Bundesrat_20...         °h oder der wirtschaft nicht ausreichen
\n \n
Bundesrat_20...  auch die schweizer wirtschaft profitiert vom kauf\n
Bundesrat_20...   °hh er stärkt die wirtschaft trägt zu unserem wo\n
Bundesrat_20...             und die wirtschaft weniger beschäftigt\n
Bundesrat_20...  damit verliert die wirtschaft wertvolle fachkräft\n
```
