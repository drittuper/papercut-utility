# papercut-utility
Syncs information about domain users from Active Directory with corresponding fields in the managed print software solution "PaperCut".

The real life problem which spawned this application was the issue that "PaperCut" synced new information about its existing users only whilst importing new users. However, after importing new users I had to spend about half an hour deleting various system accounts and disabled users (people that were no longer employees of the client) due to the fact that they were imported along with the active users (the real issue here was a messy Active Directory). Coupled with the fact that the software is licensed on a per-user basis, this was a major problem.

In order to save myself the trouble, I wanted to update department, office and email info only for existing users. I did this by cross-referencing user information from Active Directory and PaperCut.

This application uses PaperCut's proprietary API wrapper (ServerCommandProxy.java) and a 3rd party LDAP library (unboundid-ldapsdk-se.jar). The GUI was made with the WindowBuilder plugin. The rest I wrote myself.
