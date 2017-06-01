JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
make:
	$(JC) $(JFLAGS) src/synthgw/*.java src/userInterface/*.java
