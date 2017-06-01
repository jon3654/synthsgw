ARGS = -d build -g
JC = javac
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
