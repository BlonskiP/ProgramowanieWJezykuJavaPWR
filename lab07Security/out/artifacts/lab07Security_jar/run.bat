@ECHO OFF
java -jar C:\Programming\ProgramowanieWJezykuJavaPWR\lab07Security\out\artifacts\lab07Security_jar\lab07Security.jar crypt
ECHO Z policy file
java -Djava.security.policy==C:\Programming\ProgramowanieWJezykuJavaPWR\lab07Security\knapsackPolicyFile.policy -jar C:\Programming\ProgramowanieWJezykuJavaPWR\lab07Security\out\artifacts\lab07Security_jar\lab07Security.jar crypt -Djava.security.manager 
PAUSE 