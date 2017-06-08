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

move:
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


run: 
	java -classpath build synthgw.SynthGW

jar:
	jar cvfe SynthsGW.jar App -C build .
