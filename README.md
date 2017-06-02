# synthsgw
Dependencies: Java Runtime Environment 8 (JRE 8), make.

Download JRE 8 [here](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

Get make for [OSX by installing XCode](https://developer.apple.com/xcode/), for [Windows](http://gnuwin32.sourceforge.net/packages/make.htm), or for Linux [here](https://ftp.gnu.org/gnu/make/) if your distro doesn't already have it in its repositories.

To run the program from the command line:

  ```
  git clone https://github.com/jon3654/synthsgw.git
  cd synthsgw
  make
  make run
  ```

Alternatively, without make, run:

```
  git clone https://github.com/jon3654/synthsgw.git
  cd synthsgw
  mkdir build
  javac -d build -cp build -g src/*/*.java
  java -classpath build synthgw.SynthGW
  ```
  
  Will later include a guide on how to use SynthsGW as a jar file.
