# synthsgw
##Progress Videos
Click on the following link to see the progress for Sprint 2

https://www.youtube.com/watch?v=_tLChjeKR1c

## Dependencies
Java SE Runtime Environment 8 (JRE 8) if you want to only use the jar or Java SE Development Kit 8 if you want to compile SynthsGW yourself, make (optional but makes compilation simpler), git.

Download JRE 8 [here](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
or 
Download JDK 8 [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Get make for [OSX by installing XCode](https://developer.apple.com/xcode/), for [Windows](http://gnuwin32.sourceforge.net/packages/make.htm), or for Linux [here](https://ftp.gnu.org/gnu/make/) if your distro doesn't already have it in its repositories.

Download git [here](https://git-scm.com/downloads).

## Cloning the repository

### Download synthsGW by running the command:

```
 git clone https://github.com/jon3654/synthsgw.git
```
#### Before runnning the following commands, ensure that you're in the synthsgw directory by running:
```
cd synthsgw
```
## Using the included jar file
### To run SynthsGW without compiling it yourself, simply run:
```
java -jar SynthsGW.jar
```

## Building SynthsGW
### Build SynthsGW with make by running:
```
make
```
### Build SynthsGW without make by running:
```
mkdir build
javac -d build -cp build -g src/*/*.java
```
#### Before running the following commands, ensure that SynthsGW is already built.
## Running SynthsGW after its built
### Run the program using make:
  ```
make run
  ```

### Alternatively, without make:
```
java -classpath build synthsgw.SynthsGW
```
### Or make the jar yourself
### To compress what you've built into a jar file with make:
#### Note that this will overwrite the already included jar file
```
make jar
```
### To compress what you've built into a jar file yourself without make:
#### Note that this will overwrite the already included jar file
```
jar cvfe SynthsGW.jar synthsgw.SynthsGW -C build .
```
