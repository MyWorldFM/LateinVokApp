==== Installation ====

- TortoiseGit

==== �nderungen einchecken ====

Ins Verzeichnis gehen --> Rechtsklick

Wenn neue Dateien hinzugef�gt wurden:
"Git Add All files now" - damit werden alle Dateien zum Index, also zur Versionierung hinzugef�gt.

Git commit -> "master" - damit wird die �nderung eingecheckt.

Damit die �nderung nun auch bei den anderen verf�gbar ist, muss diese noch gepusht, also hochgeladen werden.
Rechtsklick --> TortoiseGit --> Push...

Folgendes ausw�hlen:
 - Local: "master"
 - Remote: "master"

Dann auf "Ok" klicken.

==== �nderungen von anderen herunterladen ====

Damit man selbst auch die �nderungen der anderen hat, also auf dem aktuellen Stand ist, muss man diese �nderungen herunterladen, dies nennt man pull.

Rechtsklick --> TortoiseGit --> Pull...

Dann "Remote" ausw�hlen, Remote Branch auf "master" setzen und auf "Ok" klicken.

==== Ein vorhandenes Verzeichnis versionieren ====

# git init
# git add *
# git commit -m "master commit"

# git status