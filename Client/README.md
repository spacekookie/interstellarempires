# CLIENT MAVEN README

Q: The project isn't building. WHY?
A: Did you go into the Framework project and run 'mvn install' on it? Otherwise try cleaning the Framework first, then compiling it to see if it builds and then installing it.

Q: I can launch the built .jar but it's crashing on Launch with a NPE.
A: You need to include Command-line arguments. Just make one up if you don't want to use one.

Q: I can launch the built .jar but it's crashing when trying to initiate LWJGL. Can't find a library.
A: Go to your directory: /var/folders/sm/cdyrc3t1475cs4hpd_fvgg1h0000gn/T/libgdx*USERNAMEHERE*/75260a42 and rename 'liblwjgl.jnilib' to 'liblwjgl.dylib'. That fixes it C:
