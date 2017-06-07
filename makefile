ARGS = -d build -verbose -cp build -g src/*/*.java
JC = javac
VIEW = src/view/fxml/
.SUFFIXES: .java .class

make:
	if [ -d build ] ; \
	then \
                rm -r build;  \
	fi; \
	mkdir build;

	$(JC) $(ARGS)

	# Moves fxml file(s) to build directory so that `make jar` and `make run`
	# work correctly.
	cp -r $(VIEW) build

clean:
	if [ -d build ] ; \
	then \
                rm -r build;  \
	fi; \

	if [ -d SynthsGW.jar ] ; \
	then \
                rm SynthsGW.jar;  \
	fi; \


run: make
	java -classpath "`pwd`/build" App

jar: make
	jar cvfe SynthsGW.jar App -C build .
