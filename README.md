An applet loader, with a thin reflection wrapper.

Bytecode manipulation is used in places where reflection isn't possible/viable.
<br>
<br>
<br>
<br>
<b>How to use:</b>

<b>1)</b> Subclass ClientLoader (https://github.com/demmonic/ReflectionWrapper/blob/master/src/demmonic/rwrapper/user/ClientLoader.java)<br>
<b>2)</b> Register your commands (ClientLoader#Add(Command c))<br>
<b>3)</b> Export the loader to a jar, and drop it in a folder where the wrapper is named "loaders"
