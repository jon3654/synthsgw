# synthsgw
Dependencies: Java Runtime Environment 8 (JRE 8), make (optional but makes compilation simpler), git.

Download JRE 8 [here](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

Get make for [OSX by installing XCode](https://developer.apple.com/xcode/), for [Windows](http://gnuwin32.sourceforge.net/packages/make.htm), or for Linux [here](https://ftp.gnu.org/gnu/make/) if your distro doesn't already have it in its repositories.

Download git [here](https://git-scm.com/downloads).

##Building and running SynthsGW

To run the program from the command line using make:

  ```
  git clone https://github.com/jon3654/synthsgw.git
  cd synthsgw
  make
  make run
  ```

Alternatively, without make:

```
  git clone https://github.com/jon3654/synthsgw.git
  cd synthsgw
  mkdir build
  javac -d build -cp build -g src/*/*.java
  java -classpath build synthgw.SynthGW
  ```
  
To run the program as a jar file using make:
```
  git clone https://github.com/jon3654/synthsgw.git
  cd synthsgw
  make
  make jar
  java -jar SynthsGW.jar
  ```
To run the program as a jar without make:
  ```
  git clone https://github.com/jon3654/synthsgw.git
  cd synthsgw
  jar cvfe SynthsGW.jar synthgw.SynthGW -C build .
  java -jar SynthsGW.jar
  ```
