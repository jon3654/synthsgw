ARGS = -d build -verbose -cp build -g src/*/*.java
JC = javac
VIEW = src/view/fxml/
MAINCLASS = com.github.synthsgw.SynthsGW
.SUFFIXES: .java .class

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
