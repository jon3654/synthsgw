ARGS = -d build -cp build -g -Xlint:unchecked -Xlint:deprecation
JC = javac
MAINCLASS = com.github.synthsgw.App
VIEW = src/view/fxml
.SUFFIXES: .java .class

make:
	if [ -d build ] ; \
		then rm -r build;  \
	fi; \
	mkdir build;

	find src -name "*.java" > sources.tmp
	$(JC) $(ARGS) @sources.tmp

	cp -r $(VIEW) build

clean:
	if [ -d build ] ; \
		then rm -r build;  \
	fi; \
	if [ -e sources.tmp ] ; \
		then rm sources.tmp; \
	fi

run:
	java -classpath build $(MAINCLASS)

jar:
	if [ -e SynthsGW.jar ] ; \
		then rm SynthsGW.jar;  \
	fi; \
	jar cvfe SynthsGW.jar $(MAINCLASS) -C build .

