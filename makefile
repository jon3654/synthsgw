ARGS = -d build -verbose -cp build -g
JC = javac
.SUFFIXES: .java .class

make:
	if [ -d build ] ; \
	then \
                rm -r build;  \
	fi; \
	mkdir build;

	$(JC) $(ARGS) src/*/*.java
clean:
	rm -r build MANIFEST.MF SynthsGW.jar
run: 
	java -classpath build synthgw.SynthGW
jar:
	echo Main-Class: synthgw.SynthGW > MANIFEST.MF
	jar cvmf MANIFEST.MF SynthsGW.jar build/*/*.class

