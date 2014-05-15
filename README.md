An applet loader, with a thin reflection wrapper.

Bytecode manipulation is used in places where reflection isn't possible/viable.




How to use:

1:) Subclass ClientLoader (https://github.com/demmonic/ReflectionWrapper/blob/master/src/demmonic/rwrapper/user/ClientLoader.java)

2:) Register your commands (ClientLoader#Add(Command c))

3:) Export the loader to a jar, and drop it in a folder where the wrapper is named "loaders"
