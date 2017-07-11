# synthsgw
## Progress Videos
Click on the following links to see the progress for Sprint 2

 * https://www.youtube.com/watch?v=_tLChjeKR1c

 * https://www.youtube.com/watch?v=VREWVBDSXEY
 
## Diagrams
* [Use Case 1](https://github.com/jon3654/synthsgw/blob/master/diagrams/Use%20Case%201.pdf)

* [Use Case 2](https://github.com/jon3654/synthsgw/blob/master/diagrams/Use%20Case%202.pdf)

* [Use Case 3](https://github.com/jon3654/synthsgw/blob/master/diagrams/Use%20Case%203.pdf)

* [Use Case 4](https://github.com/jon3654/synthsgw/blob/master/diagrams/Use%20Case%204.pdf)

* [Class Diagram](https://github.com/jon3654/synthsgw/blob/master/diagrams/Class%20Diagram.pdf)

## Tests

* [Manual Tests](https://github.com/jon3654/synthsgw/blob/master/Tests/Manual%20Tests.pdf)

* [Unit Tests](https://github.com/jon3654/synthsgw/blob/master/Tests/com/github/synthsgw/tests/JUnitTest.java)

## Burndown Chart and Backlogs

* [Burndown Chart](https://github.com/jon3654/synthsgw/blob/master/Burndown_Chart.pdf)

* [Product Backlog](https://github.com/jon3654/synthsgw/blob/master/Product_Backlog.pdf)

* [Sprint Backlog](https://github.com/jon3654/synthsgw/blob/master/Sprint_Backlog.pdf)

## Dependencies
Java SE Runtime Environment 8 (JRE 8) if you want to only use the jar or Java SE Development Kit 8 if you want to compile SynthsGW yourself, make (optional but makes compilation simpler), git.

Download JRE 8 [here](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
or 
Download JDK 8 [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Get make for [OSX by installing XCode](https://developer.apple.com/xcode/), for [Windows](http://gnuwin32.sourceforge.net/packages/make.htm), or for Linux [here](https://ftp.gnu.org/gnu/make/) if your distro doesn't already have it in its repositories.

Download git [here](https://git-scm.com/downloads).

## Downloading Source Files
* Using git bash, run this command
```
 git clone https://github.com/jon3654/synthsgw.git
```
* Alternatively, you can download a zip file with all of the source code inside from this main GitHub page

* #### Before runnning the following commands, ensure that you're in the synthsgw directory by running:
```
cd synthsgw
```
## Running synthsGW Program
### The included jar can be expecuted by running the following command from the git bash command line:
```
java -jar SynthsGW.jar
```

## Building & Running SynthsGW
### Build and Run synthsGW with make:
* #### Run the following command from git bash line to build the program:
```
make
```
* #### After it has been built, you can run synthsGw by running this command:
```
make run
```

### Build and Run synthsGW without make:
* #### The following makes a build directory and puts built code inside:
```
mkdir build
javac -d build -cp build -g src/*/*.java
```

* #### After the code has been built, run it with this command:
```
java -classpath build synthsgw.SynthsGW
```
### Or make the jar yourself
* #### To compress what you've built into a jar file with make (Note that this will overwrite the already included jar file):
```
make jar
```
### To compress what you've built into a jar file yourself without make:
* #### Note that this will overwrite the already included jar file
```
jar cvfe SynthsGW.jar synthsgw.SynthsGW -C build .
```
