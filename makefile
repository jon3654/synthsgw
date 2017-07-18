ARGS = -d build -verbose -cp build -g -Xlint:unchecked
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
	if [ -e SynthsGW.jar ] ; \
		then rm SynthsGW.jar;  \
	fi; \
	if [ -e sources.tmp ] ; \
		then rm sources.tmp; \
	fi

run:
	java -classpath build $(MAINCLASS)

jar:
	jar cvfe SynthsGW.jar $(MAINCLASS) -C build .

