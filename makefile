ARGS = -d build -verbose -cp build -g
JC = javac
MAIN = ./build/synthgw/SynthGW
JVM= java
.SUFFIXES: .java .class

make:
	if [ -d build ] ; \
	then \
     		rm -r build;  \
		mkdir build; \
	else \
		mkdir build; \
	fi;

	$(JC) $(ARGS) src/synthgw/*.java src/userInterface/*.java
clean:
	rm -r build
run: 
	java -classpath build synthgw.SynthGW


