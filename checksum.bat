@echo off

REM Checksum of jar file
echo TIME   - %date% %time% > F:\IdeaProjects\checksumJar.txt
certutil -hashfile "F:\IdeaProjects\out\artifacts\IdeaProjects_jar\IdeaProjects.jar" SHA1 >> F:\IdeaProjects\checksumJar.txt
certutil -hashfile "F:\IdeaProjects\out\artifacts\IdeaProjects_jar\IdeaProjects.jar" SHA256 >> F:\IdeaProjects\checksumJar.txt
certutil -hashfile "F:\IdeaProjects\out\artifacts\IdeaProjects_jar\IdeaProjects.jar" SHA384 >> F:\IdeaProjects\checksumJar.txt
certutil -hashfile "F:\IdeaProjects\out\artifacts\IdeaProjects_jar\IdeaProjects.jar" SHA512 >> F:\IdeaProjects\checksumJar.txt
certutil -hashfile "F:\IdeaProjects\out\artifacts\IdeaProjects_jar\IdeaProjects.jar" MD5 >> F:\IdeaProjects\checksumJar.txt

REM Checksum of RP zip
echo SHA1 - >> F:\IdeaProjects\checksumZip.txt
certutil -hashfile "F:\IdeaProjects\IdeaPluginsRP.zip" SHA1 >> F:\IdeaProjects\checksumZip.txt
