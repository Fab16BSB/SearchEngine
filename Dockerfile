FROM openjdk:17.0.2-jdk

LABEL author="Fab.16" \
	  version="1.0.0" \
	  description="Image du projet Search Engine"

# Définition du répertoire de travail
WORKDIR /app/src/java

# Copie des dossiers 'src' contenant le code et 'hotel' contenant les données
COPY hotels/ ../../hotels/
COPY src/resources/ ../resources/
COPY src/java/ .

# Compiler les fichiers Java et placer les fichiers .class dans le répertoire 'bin'
RUN mkdir -p ../../bin && javac -d ../../bin *.java

# Exécution de l'application
CMD ["java", "-cp", "../../bin", "Main"]

