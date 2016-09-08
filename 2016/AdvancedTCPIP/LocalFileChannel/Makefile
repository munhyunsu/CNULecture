#
# define make variables
#
JCC = javac
JCCFLAGS = -g -Xlint:all
JCCDIR = -d ./bin/
JVM = java
JVMFLAGS =
JDOC = javadoc
JDOCFLAGS = -version -author
JDOCDIR = -d ./doc/


#
# for make classes
#
CLASSES = \
	Main.class

#
# Main
#
MAIN = Main

default : $(CLASSES)

Main.class :
	mkdir -p ./out/
	javac -d ./out/ ./src/org/cscnu/hobby/Main.java

run : $(CLASSES)
	java -cp .:./out/ org.cscnu.hobby.Main

doc : $(CLASSES)
	javadoc -version -author -d ./doc/

clean :
	rm -rf ./out/*
