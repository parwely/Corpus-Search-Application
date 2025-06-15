# 📚 Corpus Search Application

Ein Java-Projekt zur performanten Suche und Analyse in Textkorpora – entwickelt als Abschlussprojekt der Programmierprüfung im Rahmen des Studiums an der Universität zu Köln.

## 🧩 Projektbeschreibung

Die **Corpus Search Application** ermöglicht die Analyse und Exploration großer Textkorpora durch die Implementierung modularer Schnittstellen für Dateiexport, Tokenisierung und Ergebnis-Sortierung. Das Projekt wurde im Rahmen der Abschlussprüfung im Fach Programmierung (Sommersemester 2024) unter Beachtung objektorientierter Prinzipien, Interface-Design und sauberer Architektur entwickelt.

## ⚙️ Funktionen

- **Import verschiedener Dateiformate** (z. B. `.txt`, `.html`)
- **Extraktion von Text aus HTML-Dokumenten** (nur `<body>`-Inhalt)
- **Flexible Tokenisierung** mit Positions-Tracking
- **Volltextsuche** im Korpus
- **Kontextbasierte Ergebnisanzeige**
- **Sortierung der Treffer** auf Basis des Kontextes
- **Modular erweiterbar** durch Interface-basierte Architektur

## 🧱 Architektur

Das System basiert auf folgenden zentralen Interfaces:

- `IFileImporter`: Definiert das Einlesen und Parsen von Dateien
- `ITokenizer`: Regelt die Aufbereitung von Rohtexten in Tokens mit Metadaten
- `IResultSorter`: Bietet anpassbare Sortierlogiken für Suchergebnisse

Die Hauptklasse `CorpEx` dient als Einstiegspunkt und Koordinator der einzelnen Komponenten.

## 📂 Projektstruktur

