


## Guía de estilo para código Java

La guía de código son un conjunto de normas de estilo que aplicar a la hora de escribir código fuente, que dependen del lenguaje de programación al que van dirigidas. Estas normas pretenden crear estructuras de código fáciles de entender, no sólo para sí mismo sino también para cualquier programador, hacer más eficiente el proceso de desarrollo, y conseguir que los programas sean más robustos y fáciles de mantener.

Durante la fase de codificación de un programa, el uso de una guía de código nos evitará tener problemas en:

1. **La calidad del programa**: Un programa mal escrito obliga a invertir mucho tiempo en descrubir qué es lo que está pasando y darle solución: Usar un estilo facilitará la lectura y la corrección rápida de errores.
2. **Mantenimiento del programa**: En las tareas de mantenimiento, la mayor parte del tiempo se pierde en leer y comprender el código existente, algo que parecía obvio en el momento de escribirse, pero que tiende a ser confuso cuando se vuelve a revisar, y aún más cuando el código lo escribió otra persona. Usar guías de código asegura que los programas sean más comprensibles.
3. **Comunicación entre programadores**: Dentro de un equipo de desarrollo es necesario utilizar un estilo común en el código que escriban, para mantener la homogeneidad y garantizar la comunicación entre sus miembros.

Como cada programador tenemos nuestro propio estilo, es necesario establecer unos criterios comunes mínimos que todos nos comprometemos a respetar, en los proyectos de desarrollo de la CARM, y no es algo que hagamos nosotros solamente,

* [Mozilla](https://www.mozilla.org/es-ES/) tiene la suya propia [https://developer.mozilla.org/en-US/docs/Mozilla/Developer_guide/Coding_Style](https://developer.mozilla.org/en-US/docs/Mozilla/Developer_guide/Coding_Style)
* [GNU](http://www.gnu.org/) publica la suya en [http://www.gnu.org/prep/standards/](http://www.gnu.org/prep/standards/)
* Hasta [Google](https://www.google.es/) tiene la suya [https://google.github.io/styleguide/javaguide.html](https://google.github.io/styleguide/javaguide.html)

Como todas están en inglés, puedes leer un resumen en castellano en el documento de *[Buenas-Practicas-de-codificacion.md](Buenas-Practicas-de-codificacion.md)*, y en el artículo *[¿Que es una guía de código?](https://codigofacilito.com/articulos/guia_codigo)* explican en detalle el por qué de muchas de estas normas.


### El resumen del resumen

Si no tuvieras tanto de tiempo para leer, procura no dejar [de ojear este extracto en inglés](https://github.com/thoughtbot/guides/tree/master/style), pero si es que no tuvieras tanta paciencia y pasa que estás deseando empezar a escribir líneas de código, ten siempre presente las siguientes reglas de oro, a modo *resumen del resumen del extracto*


Sobre el **formato** de los ficheros que escribas:

1. Evita los comentarios en línea (```//```) en medio de los métodos, de las clases... *no es necesario que nos expliques en detalle tus ideas, prográmalas para que las entendamos cualquiera*
2. Líneas que no más de 80 caracteres, *para que podamos leer tu código los carcas que aún usamos el vi*
3. Evita los espacios al final de las linea, *se ven muy mal luego en git, afean tu trabajo*
4. Procura escribir bien, sin muchas faltas de ortografía, *no hablarán bien de tu nivel educativo*
5. Usa una línea en blanco para separar bloques de código, *los que tenemos presbicia y usamos vi, se nos amontona todo en pantalla*
6. Usa espacios en blanco entre los operadores lógicos en la condiciones, excepto en los operadores unarios, *la presbicia...tú ya sabes...*
7. Usa el fin de línea *Unix-style* (```\n```), *los carcas seguimos usando el linux de cuando estábamos en la facultad... nos hace sentirnos modernos*

Sobre el **nombrado** de los clases, métodos, variables... que escribas:

1. No uses abreviaturas,
2. No pongas el tipo en el nombre *(user_array, email_method CalculatorClass, ReportModule)*
3. Indica el patrón que implementas después del nombre *( Guest vs NullUser, CachedRequest vs RequestDecorator)*
4. Nombra los miembros de una colección siempre en singular
5. Procura que el nombre de la clase, variable o método revele para qué sirve
6. Trata los acrónimos como palabras independientes *(XmlHttpRequest y NO XMLHTTPRequest)*


Sobre la **organización** de los métodos en la clase:

1. Ordena los métodos de manera que los que son llamados, estén antes que los realizan las llamadas
2. Ordena los métodos para que el que es llamado y el que llama estén lo más cerca posible.



### Tu IDE te ayuda

Todas estas normas se pueden descargar en [un único fichero xml](https://google.github.io/styleguide/) e instalar en tu IDE favorito, para que se apliquen cada vez que guardes el fichero.

* Si es Eclipse, usa [eclipse-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml) como indican [en este post](http://www.practicesofmastery.com/post/eclipse-google-java-style-guide/)
* Si es Intellij IDEA, usa [intellij-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) como indican [en este post](https://gerrit-review.googlesource.com/Documentation/dev-intellij.html#_recommended_settings


Con esto, tendrás cubierto todas las normas de más bajo nivel que afectan al contenido del fichero (ordenación de métodos, tabuladores, fin de línea, etc), pero para las normas de alto nivel que tienen que ver con los nombres que usas, el contenido de los métodos, etc, necesitarás instalar [el plugin SonarLint](https://www.sonarlint.org/eclipse/) y activarlo como explican [en este post](https://www.adictosaltrabajo.com/2014/10/08/eclipse-sonar-qube/). Esto hará que te vaya sugiriendo cambios, conforme vayas escribiendo código.

La versión de [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/packages/) ya trae este plugin instalado, sólo tendrás que activarlo en cada proyecto de la CARM.



