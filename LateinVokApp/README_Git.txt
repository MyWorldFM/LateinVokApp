==== Installation ====

- TortoiseGit

==== Änderungen einchecken ====

Ins Verzeichnis gehen --> Rechtsklick

Wenn neue Dateien hinzugefügt wurden:
"Git Add All files now" - damit werden alle Dateien zum Index, also zur Versionierung hinzugefügt.

Git commit -> "master" - damit wird die Änderung eingecheckt.

Damit die Änderung nun auch bei den anderen verfügbar ist, muss diese noch gepusht, also hochgeladen werden.
Rechtsklick --> TortoiseGit --> Push...

Folgendes auswählen:
 - Local: "master"
 - Remote: "master"

Dann auf "Ok" klicken.

==== Änderungen von anderen herunterladen ====

Damit man selbst auch die Änderungen der anderen hat, also auf dem aktuellen Stand ist, muss man diese Änderungen herunterladen, dies nennt man pull.

Rechtsklick --> TortoiseGit --> Pull...

Dann "Remote" auswählen, Remote Branch auf "master" setzen und auf "Ok" klicken.

==== Ein vorhandenes Verzeichnis versionieren ====

# git init
# git add *
# git commit -m "master commit"

# git status