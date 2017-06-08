ARGS = -d build -verbose -cp build -g src/*/*.java
JC = javac
MAINCLASS = com.github.synthsgw.SynthsGW
.SUFFIXES: .java .class

# FX testing variables
ARGSFX = -d build -verbose -cp build -g src.bak/*/*.java
MAINFX = com.github.synthsgw.view.App
VIEW = src.bak/view/fxml/

make:
	if [ -d build ] ; \
	then \
                rm -r build;  \
	fi; \
	mkdir build;

	$(JC) $(ARGS)

move:
	cp -r $(VIEW) build

clean:
	if [ -d build ] ; \
	then \
                rm -r build;  \
	fi; \

	if [ -e SynthsGW.jar ] ; \
	then \
                rm SynthsGW.jar;  \
	fi; \


run: 
	cd build; \
	java $(MAINCLASS)

jar:
	jar cvfe SynthsGW.jar $(MAINCLASS) -C build .

# JavaFX build targets

makefx: clean
	mkdir build;
	$(JC) $(ARGSFX)
	cp -r $(VIEW) build

runfx: makefx
	cd build; \
	java -classpath . $(MAINFX)

