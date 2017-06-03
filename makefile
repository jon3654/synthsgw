ARGS = -d build -verbose -cp build -g src/*/*.java
JC = javac
.SUFFIXES: .java .class

make:
	if [ -d build ] ; \
	then \
                rm -r build;  \
	fi; \
	mkdir build;

	$(JC) $(ARGS)

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
	jar cvfe SynthsGW.jar synthgw.SynthGW -C build .
