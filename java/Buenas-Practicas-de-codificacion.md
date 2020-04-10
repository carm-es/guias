> *Documento copiado desde [https://github.com/kosme10/standards/wiki/Buenas-Practicas-de-codificacion](https://github.com/kosme10/standards/wiki/Buenas-Practicas-de-codificacion)*


## Contenido
- [Organización del código](#organización-del-código)
    - [Sentencias Package e Import](#sentencias-package-e-import)
    - [Declaraciones de Clases e Interfaces](#declaraciones-de-clases-e-interfaces)
- [Estructura del Código](#estructura-del-código)
	- [Encabezados de la Clase](#encabezados-de-la-clase)
	- [Encabezados del Método](#encabezados-del-método)
	- [Indentación](#indentación)
	- [Espacios en Blanco](#espacios-en-blanco)
	- [Llaves y saltos de línea](#llaves-y-saltos-de-línea)
	- [Alineación de Sentencias de Asignación](#alineación-de-sentencias-de-asignación)
	- [Longitudes de Línea y Saltos de Línea](#longitudes-de-línea-y-saltos-de-línea)
	- [Clases Anónimas](#clases-anónimas)
- [Comentarios](#comentarios)
	- [Comentarios de implementación](#comentarios-de-implementación)
    - [Comentarios de Documentación](#comentarios-de-documentación)
- [Hábitos de programación](#hábitos-de-programación)
	- [Visibilidad (Alcance)](#visibilidad-alcance)
	- [Granularidad del código (Tamaño del Método)](#granularidad-del-código-tamano-del-método)
	- [Variables](#variables)
	- [Referencias a variables y métodos de clase](#referencias-a-variables-y-métodos-de-clase)
	- [Constantes](#constantes)
	- [Condicionales](#condicionales)
	- [Bucles](#bucles)
	- [Switches](#switches)
	- [Hábitos varios](#hábitos-varios)
	- [Notas sobre las Palabras Claves y Construcciones Específicas](#notas-sobre-las-palabras-claves-y-construcciones-específicas)

### Organización del código

Una clase contiene secciones que deben estar separadas por líneas en blanco y comentarios opcionales que identifican cada sección.

Deben evitarse los archivos de más de 2000 líneas ya que son incómodos.

Cada clase Java contiene una única clase o interfaz pública. Cuando algunas clases o interfaces privadas están asociadas a una clase pública, pueden ponerse en el mismo archivo que la clase pública. La clase o interfaz pública debe ser la primera clase o interfaz del archivo.

Los clases Java deben tener la siguiente ordenación:

* Sentencia package
* Sentencias import
* Declaraciones de clases e interfaces

### Sentencias Package e Import

La primera línea no-comentario de los archivos Java es la sentencia package. La convención es escribir todos los grupos en minúsculas.

Después de esta, pueden seguir varias sentencias import.

Por ejemplo:

```java
package com.redhat.nombreproyecto;

import com.redhat.nombreproyecto.OtraClase;
```

En la sección import, enumere cada módulo importado de forma explícita.

Ejemplo:

##### Estándar

```java
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.applet.AppletContext;
```
##### No estándar

```java
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
```

Deben respetarse las siguientes reglas en la declaración de imports:

* No se debe utilizar el * para importar todo un paquete
* No deben existir sentencias imports redundantes, en resumen:
  * Sentencias duplicadas de otro import previamente realizado en la misma clase
  * Imports de clases del package java.lang
  * La clase importada es del mismo package que la clase contenedora del import

### Declaraciones de Clases e Interfaces

La siguiente tabla describe las partes de la declaración de una clase o interfaz en el orden en el que deberían aparecer.

Orden | Partes de la declaración | Notas
--- | --- | ---
1 | Comentario de documentación de la clase o Interfaz | Ver [Comentarios de Documentacion](#comentarios-de-documentacion)
2 | Sentencia class o interface |
3 | Comentario de implementación de la clase o interfaz si fuera necesario (/\*...\*/) | Este comentario debe contener cualquier información aplicable a toda la clase o interfaz que no fuera apropiada para estar en los comentarios de documentación de la clase o interfaz.
4 | Variables de clase (static) | Primero las variables de clase pública, después las protegidas, después las de nivel de paquete (sin modificador de acceso), y después las privadas.
5 | Variables de instancia | Primero las públicas, después las protegidas, después las de nivel de paquete (sin modificador de acceso), y después las privadas.
6 | Bloques estáticos | Si bloques estáticos deben utilizarse fuera de los métodos, los mismos debe utilizarse para la inicialización de variables estáticas y estos mismos deben ubicarse antes de la declaración de constructores.
7 | Constructores |
8 | Comentario de documentación del método (/\*\*...\*/) | [Comentarios de Documentación](#comentarios-de-documentación)
9 | Métodos | Estos métodos se deben agrupar por funcionalidad más que por visión o accesibilidad. Por ejemplo, un método de clase privado puede estar entre dos métodos públicos de instancia. El objetivo es hacer el código más legible y comprensible.

# Estructura del Código

Una buena estrategia de estructuración debe representar con precisión y consistentemente la estructura lógica del código, debe hacer el código legible y fácil de mantener. Las reglas de esta sección están ideadas para cumplir con esos criterios.

##### Encabezados de la Clase

* Escribir encabezados de clase en una única línea si hubiera espacio disponible.
* De lo contrario, aplique un salto de línea antes de extends e implements. Indente las líneas sucesivas.
* Si el encabezado de la clase está en una única línea, coloque la llave de apertura al final de esa línea.
* Si el encabezado de la clase necesita líneas múltiples, coloque la llave de apertura alineada a la izquierda en la siguiente línea.

##### Encabezados del Método

* Cada método debe tener como máximo 5 parámetros, preferentemente agrupar parámetros en objetos.
* Escribir encabezados de método en una única línea si hubiera espacio disponible.
* De lo contrario, aplique un salto de línea inmediatamente después de la apertura del paréntesis. Esto deja a todos los parámetros en la misma línea.
* Si aún no hubiera espacio suficiente, coloque cada parámetro en su propia línea.
* Si el encabezado del método está en una única línea, coloque la llave de apertura al final de esa línea.
* Si el encabezado del método necesita líneas múltiples, coloque la llave de apertura alineada a la izquierda en siguiente línea.

```java
public void setName(String aNewName) {
    this.name = aNewName;
}
public void executeAction(
    HttpServletRequest aHttpServletRequest, Task aTask)
{
    ...
}
```

##### Indentación

* La indentación estándar debe ser de 4 espacios. En realidad, la indentación es un tabulador, que debería estar definido para visualizar cuatro espacios.
* Utilice los tabuladores para la indentación solamente. Cualquier espacio en blanco luego del nivel de indentación deben ser espacios reales, de modo que el formateo será razonable independientemente de la cantidad de espacios a los que un tabulador sea equivalente.
* Si el uso de indentación de 4 espacios no ayuda en la lectura del código, utilice la regla de 8 espacios para indentación en la cabecera de bloque o en la declaración de métodos (ver [Longitudes de Línea y Saltos de Línea](#longitudes-de-línea-y-saltos-de-línea))

##### Espacios en Blanco

El espacio en blanco habitualmente mejora la legilibilidad.

Agregue un espacio en los siguientes lugares:

* Entre operadores
* Luego de la coma en las declaraciones e invocaciones de método
* Luego de punto y coma en bucles for
* Antes y después del operador de asignación
* No coloque espacios en los siguientes lugares:
  * Entre el nombre de un método y el paréntesis de apertura
  * Alrededor de los paréntesis de apertura y cierre en una declaración o invocación de función
  * Alrededor de corchetes de apertura y cierre en la declaración o referencia de arrays
* Utilice las líneas en blanco para separar "párrafos" de líneas de código relacionados.

```java
if (!comboValid) {
    this.accounts.removeAll();
    for (int i = 0; i < accountList.size(); ++ i) {
        this.accounts.addItem(accountList.get(i).toString())
    }
    final String accountID = ContextManager.query(SOME_ID);
    int index = this.getAccountIndex(accountID);
    this.accounts.select(Math.max(0, index));
    this.comboValid = true;
}
...
private String titles[] = null; // array of strings
client.height = size.height – this.insets.top – this.insets.bottom
- this.title.height;
...
public String getItem(int aRow, int aColumn) {
    return (String) this.list[aColumn].elementAt(aRow);
}
```
##### Llaves y saltos de línea

Siempre utilice llaves, incluso para los bloques que contienen solo una sentencia. Esto elimina una fuente común de bugs y facilita el mantenimiento:

* Puede insertar o eliminar sentencias dentro de un bloque sin preocuparse por agregar o quitar llaves
* Nunca tendrá dificultades para relacionar las cláusulas else con las cláusulas `if`.

Ejemplo:

**Correcto:**

```java
if (bottom < index) {
    this.topRow = index – this.rows;
} else if (index < this.topRow) {
    this.topRow = index;
}
```

**Incorrecto:**

```java
if (bottom < index)
    this.topRow = index – this.rows;
else if (index < this.topRow)
    this.topRow = index;
```

Esta regla se aplica a las siguientes construcciones:

* Bucles for, while y do-while
* Sentencias if-else
* cláusulas try, catch y finally
* bloques synchronized

Observe que la llave de apertura está al final de la primera línea, incluso para definiciones de clase y método. La única excepción es si la expresión necesita un salto de línea; en ese caso, la legibilidad se logrará mejor colocando la llave de apertura en la siguiente línea.

##### Alineación de Sentencias de Asignación

* **Alinear** las sentencias de asignación relacionadas (=). Esto las agrupa y muestra claramente que están relacionadas.
* **No Alinear** las sentencias de asignación no relacionadas. Dicha alineación brinda una impresión errónea de la relación.

Ejemplo:

**Correcto:**

```java
panelWidth = 90;
panelHeight = 30;
selectedIndex = 0;
lastIndex = 12;
```

**Incorrecto:**

```java
panelWidth      = 90;
panelHeight     = 30;
selectedIndex   = 0;
lastIndex       = 12;
```
##### Longitudes de Línea y Saltos de Línea

* Una sentencia por línea.
* Trate de mantener la longitud de la línea en 80 caracteres máximo. Si debe aplicar un salto de línea, aplique la indentación en la(s) línea(s) de continuación.
* Si debe aplicar un salto de línea, hágalo de manera notoria finalizando la primera línea con algo que necesite una continuación:
  * Asignaciones de salto de línea luego del operador de asignación.
  * Aplique el salto en expresiones aritméticas y lógicas antes de un operador.
  * Aplique el salto de línea para enfatizar sub-expresiones importantes.
  * Aplique los saltos en las invocaciones de método luego del paréntesis de apertura. Si la lista del parámetro aún no encaja, aplique un salto entre cada parámetro.
  * Aplique los saltos a las declaraciones de método de la misma manera, y coloque la llave de apertura en la línea siguiente, sin indentar.
  * Si necesita aplicar un salto a las expresiones condicionales (por ejemplo, en sentencias if o while), siga las reglas mencionadas anteriormente y coloque la llave de apertura en la línea siguiente, sin indentar.

Ejemplos de Saltos de Línea:

* Salto de línea en expresiones aritméticas
  ```java
  longName1 = longName2 * (longName3 + longName4 - longName5)
  + 4 * longname6; // BIEN
  longName1 = longName2 * (longName3 + longName4
  - longName5) + 4 * longname6; // EVITAR
  ```

* Salto de línea en declaración de métodos

  ```java
  //INDENTACION CONVENCIONAL
  public void someMethod(int anArg, Object anotherArg, String yetAnotherArg,
                    Object andStillAnother) {
    ...
  }
  //INDENTACION DE 8 ESPACIOS PARA EVITAR INDENTACIONES PROFUNDAS
  private static synchronized horkingLongMethodName(int anArg,
        Object anotherArg, String yetAnotherArg,
        Object andStillAnother) {
    ...
  }
  ```

* Saltos de línea en sentencias `if`

  ```java
  //INDENTACION DE 4 ESPACIOS NO RECOMENDABLE PARA ESTE CASO
  if ((condition1 && condition2)
    || (condition3 && condition4)
    || !(condition5 && condition6)) {
    doSomethingAboutIt(); //LA LINEA DE CODIGO SE PIERDE
  }
  //USE LA INDENTACIONDE 8 ESPACIOS
  if ((condition1 && condition2)
        || (condition3 && condition4)
        || !(condition5 && condition6)) {
    doSomethingAboutIt();
  }
  //O ESTE FORMATO
  if ((condition1 && condition2) || (condition3 && condition4)
      || !(condition5 && condition6)) {
    doSomethingAboutIt();
  }
  ```

* Salto de línea en operadores ternarios

Los siguientes formatos son aceptables para aplicar saltos de líneas en operadores ternarios

    ```java
    alpha = (aLongBooleanExpression) ? beta : gamma; //NO REQUIERE SALTO
    alpha = (aLongBooleanExpression) ? beta
                                     : gamma;
    alpha = (aLongBooleanExpression)
        ? beta
        : gamma;
    ```

##### Clases Anónimas

Una clase anónima es una forma especial de clases internas – una innovación de Java 1.1.

Es una construcción lo suficientemente curiosa de la cual brindamos dos ejemplos sobre cómo formatearla.

Si usted utiliza una clase más de una vez, asigne una instancia a una variable:

```java
ActionListener actionListener = new ActionListener() {
    public void processActionEvent(ActionEvent e) {
        ...
    }
};
this.comboBox.addActionListener(actionListener);
this.button.addActionListener(actionListener);
```

A menudo la clase anónima es un listener diseñado para manejar eventos de solo un widget (componente de interfaz gráfica de usuario) específico. En este caso, defina e instancie la clase directamente en el código, de la siguiente manera:

```java
this.comboBox.addActionListener(new ActionListener() {
    public void processActionEvent(ActionEvent e) {
        ...
    }
} );
```

Otro uso común de clases anónimas es la implementación de un adapter. A continuación se muestra la forma de filtrar los archivos de un directorio a través de la clase File y con una clase anónima como filtro:

```java
File f = new File("/src"); // Directorio a listar

// Invocar el método list() con un argumento FilenameFilter
// Definir e instanciar una implementación anónima de
// FilenameFilter
// como parte de la expresión de invocación del método.

String[] filelist = f.list(new FilenameFilter() { public boolean accept(File f, String s) {
        return s.endsWith(".java");
    }
});
// No olvide los paréntesis y el punto y como al final de la llamada al método!
```

# Comentarios

Los programas Java pueden tener dos tipos de comentarios: comentarios de implementación y comentarios de documentación. Los comentarios de implementación son aquellos que también se encuentran en C++, delimitados por `/*...*/`, y `//`. Los comentarios de documentación (conocidos como "doc comments") existen solo en Java, y se limitan por `/**...*/`.

Los comentarios de documentación se pueden exportar a archivos HTML con la herramienta javadoc.

Los comentarios de implementación son para comentar nuestro código o para comentarios acerca de una implementación particular. Los comentarios de documentación son para describir la especificación del código, libre de una perspectiva de implementación, y para ser leídos por desarrolladores que pueden no tener el código fuente a mano.

Se deben usar los comentarios para dar descripciones de código y facilitar información adicional que no es legible en el código mismo. Los comentarios deben contener solo información que es relevante para la lectura y el entendimiento del programa. Por ejemplo, la información sobre cómo se construye el paquete correspondiente o en qué directorio reside no debe ser incluida como comentario.

Son apropiadas las discusiones sobre decisiones de diseño no triviales o no obvias, pero debe evitar duplicar información que está presente (de forma clara) en el código, ya que es fácil que los comentarios redundantes se queden desfasados. En general, evitar cualquier comentario que pueda quedar desfasado a medida que el código evoluciona.

Nota: La frecuencia de comentarios a veces refleja una pobre calidad del código. Cuando se sienta obligado a escribir un comentario considere re-escribir el código para hacerlo más claro.

Los comentarios no deben encerrarse en grandes cuadrados dibujados con asteriscos u otros caracteres.

Los comentarios nunca deben incluir caracteres especiales como retroceso (backspace).

##### Comentarios de implementación

Los programas pueden tener cuatro estilos de comentarios de implementación: de bloque, de una línea, de remolque y de fin de línea.

###### Comentarios de bloque

Los comentarios de bloque se usan para dar descripciones de archivos, métodos, estructuras de datos y algoritmos. Los comentarios de bloque se podrán usar al comienzo de cada fichero o antes de cada método. También se pueden usar en otros lugares, tales como el interior de los métodos. Los comentarios de bloque en el interior de una función o método deben ser indentados al mismo nivel que el código que describen.

Un comentario de bloque debe ir precedido por una línea en blanco que lo separe del resto del código.

```java
/*
* Aqui hay un comentario de bloque.
*/
```

Los comentarios de bloque pueden comenzar con `/*-`, que es reconocido por **indent(1)** como el comienzo de un comentario de bloque que no debe ser reformateado. Ejemplo:

```java
/*-
* Aqui tenemos un comentario de bloque con cierto
* formato especial que quiero que ignore indent(1).
*
* uno
*   dos
*       tres
*/
```

**Nota**: Si no se usa **indent(1)**, no se tiene que usar `/*-` en el código o hacer cualquier otra concesión a la posibilidad de que alguien ejecute **indent(1)** sobre él.

Vea también [Comentarios de Documentación](#comentarios-de-documentación)

###### Comentarios de una línea

Pueden aparecer comentarios cortos de una única línea al nivel del código que siguen. Si un comentario no se puede escribir en una línea, debe seguir el formato de los comentarios de bloque. (ver [Comentarios de implementación](#comentarios-de-implementación)). Un comentario de una sola línea debe ir precedido de una línea en blanco. Aquí mostramos un ejemplo de comentario de una sola línea en código Java (ver también [Comentarios de Documentación](#comentarios-de-documentación)):

```java
if (condicion) {
    /* Código de la condicion. */
    ...
}
```

###### Comentarios Finales

Pueden aparecer comentarios muy pequeños en la misma línea que describen, pero deben ser movidos lo suficientemente lejos para separarlos de las sentencias. Si más de un comentario corto aparece en el mismo trozo de código, deben ser indentados con la misma profundidad.

Aquí un ejemplo de comentario final:

```java
if (a == 2) {
    return TRUE;        /* caso especial */
} else {
    return isPrime(a);  /* caso general */
}
```

Como regla, **no se recomiendan estos comentarios** debido a la dificultad de mantenerlos ordenados –bien formateados- a largo plazo. Si los mismos no se mantienen adecuadamente pueden confundir más que ayudar.

###### Comentarios de fin de línea

El delimitador de comentario `//` puede convertir en comentario una línea completa o una parte de la misma. No debe ser usado para hacer comentarios de varias líneas consecutivas; sin embargo, puede usarse en líneas consecutivas para comentar secciones de código.

Ejemplos de los tres estilos:

```java
if (foo > 1) {
    // Hacer algo.
    ...
}
else {
    return false; // Explicar aquí por qué.
}
//if (bar > 1) {
//// Hacer algo.
//...
//}
//else {
// return false;
//}
```

##### Comentarios de Documentación

**Nota**: Vea "Ejemplo de archivo fuente Java" para conocer los ejemplos de los formatos de comentarios descritos aquí.

Para más detalles acerca de los comentarios de documentación y javadoc, visitar el sitio web de javadoc: http://download.oracle.com/javase/1.5.0/docs/guide/javadoc/index.html

Los comentarios de documentación describen clases Java, interfaces, constructores, métodos y atributos. Cada comentario de documentación se encierra con los delimitadores de comentarios `/**...*/`, con un comentario por clase, interfaz o miembro (método o atributo). Este comentario debe aparecer justo antes de la declaración:

```java
/**
* La clase Ejemplo ofrece ...
*/
public class Ejemplo { ...}
```

Darse cuenta de que las clases e interfaces de alto nivel no están indentadas, mientras que sus miembros lo están. La primera línea de un comentario de documentación `(/**)` para clases e interfaces no está indentada, las líneas subsiguientes tienen cada una un espacio de indentación (para alinear verticalmente los asteriscos). Los miembros, incluidos los constructores, tienen cuatro espacios para la primera línea y 5 para las siguientes.

Si se necesita dar información sobre una clase, interfaz, variable o método que no es apropiada para la documentación, utilice un comentario de implementación de bloque o de una línea (ver [Comentarios de implementación](#comentarios-de-implementación)) para comentarlo inmediatamente después de la declaración. Por ejemplo, los detalles de implementación de una clase deben ir en un comentario de implementación de bloque siguiendo a la sentencia class, no en el comentario de documentación de la clase.

Los comentarios de documentación no deben colocarse en el interior de la definición de un método o constructor, ya que Java asocia los comentarios de documentación con la primera declaración después del comentario.

Utilice los comentarios Javadoc para todas las clases, los métodos y las constantes. Como regla general, las variables (campos) son privadas y no necesitan los comentarios Javadoc. Si los campos públicos o protegidos son requeridos por alguna razón en especial, estos deben estar “documentados con java”.

###### Encabezados de Clase

Utilice el siguiente encabezado al principio de cada clase:

```java
/**
* [Detalle el objetivo de la clase.
*
* Incorpore indicaciones de uso de la clase para facilitar su comprensión.
* ej.:
*
*   EstaClase clase = new EstaClase(arg, arg1, ..., argN);
*   clase.setUnaPropiedad(valor);
*   ValorDeRespuesta valor = clase.execute();
* ]
*
* @version [Ingrese el número de versión con el siguiente formato: VS.CM.cm AAAA-mm-dd
*           "VS" indica la versión actual del sistema,
*           "CM" indica un refactoring de la clase,
*           "cm" indica un cambio menor en alguna sección de la misma
*
*           ej: 4.02.003 2011-08-01, La clase corresponde a la versión 4 del sistema,
*               la misma ha sufrido 2 refactorings durante la versión
*               se realizaron 3 cambios menores luego del segundo refactoring
*               el último cambio fue realizado el 1 de agosto de 2011]
*
* @author [Ingrese nombre, apellido y correo electrónico del autor.
*           ej: Fulano DeTal – fulano.detal@swissmedical.com.ar]
*
* @since [Ingrese desde qué versión del sistema está presente la clase]
*
*/
```

###### Encabezados de Método

```java
/**
* [Detalle el objetivo del método.
*
* Incorpore indicaciones de uso del mismo para facilitar su comprensión.
* ej.:
*
*   ...;
*   clase.unaLogicaDeNegocioDeterminada(valor1, valor2);
*   ValorDeRespuesta valor = clase.execute();
* ]
*
* @param arg
* @param arg2
* @return
* @throws Exception
*
* @author [Ingrese nombre, apellido y correo electrónico del autor.
*            ej: Fulano DeTal – fulano.detal@swissmedical.com.ar]
*
* @since [Ingrese desde qué versión del sistema está presente el método]
*
* @see [Indique si existe código que deba ser consultado como material de apoyo a este código
* Para consultar sobre el uso de este tag, visite la siguiente dirección
* <a href="http://download.oracle.com/javase/1.5.0/docs/tooldocs/windows/javadoc.html#@see">javadoc - The Java API Documentation Generator</a>]
*
* @deprecated [Indique si el método es obsoleto, a partir de cuándo será discontinuado
*               y proporcione un link al, o los, método que lo reemplazará.
*               ej: {@link #remove(int)} and {@link #removeAll()}
*               Para consultar sobre el uso del tag link, visite la siguiente dirección
*               <a href="http://download.oracle.com/javase/1.5.0/docs/tooldocs/windows/javadoc.html#{@link}">javadoc - The Java API Documentation Generator</a>
*               ATENCION!: BORRE ESTE TAG SI NO APLICA!!!!!.]
*
*/
```

###### Etiquetas (TAGS) Javadoc

Javadoc analiza las etiquetas especiales cuando están incorporadas en un comentario Javadoc. Estas etiquetas de documentación le permiten autogenerar una API completa y correctamente formada desde su código fuente. Las etiquetas comienzan con un símbolo "at" (@) y diferencian mayúsculas de minúsculas – deben tipearse con las letras mayúsculas y minúsculas como se muestra. Una etiqueta debe comenzar al principio de la línea (luego de cualquier espacio que encabece y un asterisco opcional) o es tratada como texto normal. Por convención, las etiquetas con el mismo nombre se agrupan todas juntas. Por ejemplo, coloque todas las etiquetas @see juntas.

Ejemplos de Etiquetas

```java
@author
@deprecated
@exception
@param
@return
@throws
@version
```

**Descripción de Etiquetas**

`@author name-text (@author texto-nombre)`

Agrega una entrada "Author" con el name-text especificado a los documentos generados cuando se utiliza la opción -author. Un comentario de documentación puede contener múltiples etiquetas `@author`. Puede especificar un nombre por etiqueta `@author` o múltiples nombres por etiqueta. En el primer caso, Javadoc inserta una coma (,) y espacio entre los nombres. En el segundo caso, el texto completo simplemente es copiado al documento generado sin ser analizado. Por lo tanto, utilice múltiples nombres por línea si desea un separador de nombres localizado que no sea una coma.

`@deprecated deprecated-text (@deprecated texto)`
Agrega un comentario indicando que esta API no debe seguir siendo utilizada (aunque pudiera continuar funcionando). Javadoc traslada el deprecated-text delante de la descripción, colocándolo en cursiva y precediéndolo con una advertencia en negrita: "Deprecated" (Inválida).

La primera sentencia de deprecated-text debe al menos informar al usuario cuándo la API fue invalidada y qué utilizar en su reemplazo. Javadoc copia solo la primera sentencia a la sección del resumen y el índice. Las sentencias subsiguientes también pueden explicar la razón de la invalidación. Debe incluir una etiqueta `{@link}` (para Javadoc 1.2 o superior), que señala el reemplazo de la API:

Para Javadoc 1.2, utilice una etiqueta `{@link}`. Esto crea el link secuenciado donde usted lo desea.

Por ejemplo:

```java
/**
*@deprecated A partir de #setBounds(int,int,int,int)}
*/
```

`@exception class-name description`

La etiqueta `@exception` es un sinónimo de `@throws`.

`@param parameter-name description`

Agrega un parámetro a la sección “Parameters”. La descripción puede continuar en la siguiente línea.

`@return description`

Agrega una sección "Returns" con el texto description. Este texto debe describir el tipo de retorno y el rango permisible de valores.

`@throws class-name description`

Las etiquetas `@throws` y `@exception` son sinónimas. Agrega un sub-encabezado "Throws" a la documentación generada con el texto class-name y description. El class-name es el nombre de la excepción que podría ser disparada por el método.

`@version version-text`

Agrega un sub-encabezado "Version" con el texto version especificado a los documentos generados cuándo se utiliza la opción -version. El texto no tiene una estructura interna especial. Un comentario de documentación puede contener como máximo una etiqueta `@version`. La versión generalmente se refiere a la versión del software (tal como el JDK) que contiene esta clase o miembro.

Las etiquetas `@author` múltiples deben estar enumeradas en orden cronológico. El creador de la clase debe encabezar la lista.

Las etiquetas `@param` múltiples deben estar enumeradas en orden de argumento-declaración. Esto facilita acomodar visualmente la lista en la declaración.

Las etiquetas `@exception` múltiples (también conocidas como @throws) deben estar enumeradas en orden alfabético por los nombres de excepción.

Para más información sobre los tags de Javadoc, visite la siguiente dirección http://download.oracle.com/javase/1.5.0/docs/tooldocs/windows/javadoc.html

###### Anotaciones

Las anotaciones son una mejora incorporada al lenguaje Java a partir de la versión 1.5. El objetivo de las anotaciones es facilitar la utilización de código existente para exponerlo como objetos EJB, servicios web, etc.

Asimismo, las anotaciones sirven para implementar aspectos y facilitar la incorporación de funcionalidad para el negocio, funciones de control (logueo, cálculo de métricas) o configuración (inyección de lógica, conexión a bases de datos, colas de mensajes, etc).

Algunos de los beneficios que se obtienen del uso de anotaciones son

* Simplify Configuration
* To define requirements
* To enforce local policies
* Avoiding unneeded interfaces (with care)
* Avoiding marker interfaces (no methods)
* Replace Naming Convention Based Development
* To avoid boilerplate code
* To make meta-data about code available at runtime

# Hábitos de programación

### Visibilidad (Alcance)

* En general, el alcance debe ser lo más pequeño posible.
* Todos los campos y las variables de clases deben ser privadas.
* Si se necesitara acceso externo a dichos campos, utilice los métodos de acceso (a.k.a. “getters y setters”).

Ejemplo:

```java
class Person {
    private String name;
    public void setName(String aName) {
        this.name = aName;
    }
    public String getName() {
        return this.name;
    }
    ...
}
```
* Siempre que sea posible, acceda a los campos utilizando “getters” aún dentro de métodos en la misma clase. Esto simplifica de manera significativa el mantenimiento y la lectura.

Ejemplo:

**Correcto**
```java
class Circle {
    ...
    int getRadius() {
        return this.radius;
    }
    int getArea() {
        return (this.getRadius() * this.getRadius()) * Math.PI;
    }
}
```

**Incorrecto**
```java
class Circle {
    ...
    int getRadius() {
        return this.radius;
    }
    int getArea() {
        return (this.radius * this.radius) * Math.PI;
    }
}
```

### Granularidad del código (Tamaño del Método)

* Una cantidad de líneas razonable para un método depende de su complejidad. Un módulo que consista de sentencias secuenciales puede ser más largo que un método que contenga bucles y condiciones complejas. Si el código secuencial es repetitivo, como una inicialización de estructura índice por índice, el método puede ser tan largo como requiera. (Debería, sin embargo, pensar dos veces en el diseño. Tal vez exista una mejor manera de hacerlo)
* Como regla general, evitar métodos con contenido superior a 35 líneas de código “reales” (es decir, sin contar las líneas en blanco que se agregan para mayor legibilidad y los comentarios).
* Preferentemente, un método debería realizar una sola cosa (cohesión), y el nombre del método debería reflejar esto en forma precisa. Si realiza más de una cosa, asegúrese de que esto esté reflejado en el nombre del método. Si esto resulta en un nombre feo para el método, vuelva a considerar la estructura de su código. Si tuviera una función llamada **initPanelManagerAndReadAccountList**, el código probablemente se beneficiaría si se separa en métodos llamados **initPanelManager** y **readAccountList**.

### Variables

* Utilice solamente una declaración de variable por línea. Esto mejora la lectura y facilita el mantenimiento:

**Correcto**

```java
private int myWidth = 150;
private int myHeight = 50;
```

**Incorrecto**

```java
private int myWidth = 150,
            myHeight = 50;
```

* Todos los campos y variables de clase deben ser privadas.

##### Asignaciones de Variables

No asigne el mismo valor a varias variables en la misma sentencia. Es difícil de leer.

Ejemplo:

```java
fooBar.fChar = barFoo.lchar = 'c'; // EVITAR
```

No usar asignación embebida como un intento de mejorar el rendimiento en tiempo de ejecución. Ese es el trabajo del compilador.

Ejemplo:

```java
d = (a = b + c) + r; // EVITAR!
```

debe escribir:

```java
a = b + c;
d = a + r;
```
##### Inicialización

* Todas las variables, incluidas las variables de campos y clases, deben inicializarse, si fuera posible, en su misma declaración. A pesar de que todas las declaraciones de Java tienen valores de inicialización determinados (0, nulo, falso), descríbalo detallada y explícitamente.

* Java permite la inicialización de arrays usando la misma sintaxis que C y C++, al incluir un conjunto de valores delimitados por una coma y entre llaves. Se permite una coma luego del valor final. Utilice esta alternativa, ya que facilita el mantenimiento: es más fácil agregar valores adicionales o quitarlos del final de la lista.

* Desde Java 1.1, se permiten bloques inicializadores entre las declaraciones. Un bloque de inicialización es una sección de un código entre llaves. Hay dos tipos de bloques de inicialización: estática y de instancia.

**Los bloques inicializadores estáticos** se ejecutan la primera vez que se instancia una clase. Durante la inicialización estática (inicialización de clases), las cosas ocurren en el siguiente orden:


* Se lleva a cabo la inicialización de clase de la superclase, a menos que se haya realizado anteriormente.
* Se inicializan las variables estáticas y se ejecutan los bloques inicializadores estáticos.

Esto ocurre en el orden descrito, de arriba hacia abajo. Las variables de instancia, los bloques inicializadores de instancia y los métodos no figuran dentro de esto.

Observe que los bloques inicializadores estáticos y de instancia son permitidos desde Java 1.1. Los bloques inicializadores estáticos se ejecutan en orden cuando la clase se instancia inicialmente; los bloques inicializadores de instancia se ejecutan en orden después de que se ejecute el constructor de superclase pero antes de que se ejecute el constructor de clase.

Los bloques inicializadores de instancia se ejecutan cuando una clase se instancia. Durante la inicialización del objeto (inicialización de instancia), las cosas ocurren en el siguiente orden:

* Si esta es la primera vez que se instancia una clase, entonces se realiza toda la inicialización de clase (static).
* Ingresamos un constructor. Si no hemos especificado un constructor, el compilador brinda automáticamente un constructor predeterminado sin argumentos.
* Se llama al constructor superclase. Si su constructor no invoca explícitamente un constructor de superclase, el constructor superclase predeterminado (sin argumento) se invoca de todas maneras.
* Todas las variables de instancias se inicializan y se ejecutan los bloques inicializadores de instancia. Esto ocurre en el orden enumerado, de arriba hacia abajo. Las variables de clase, los bloques inicializadores de clase y los métodos no figuran dentro de esto.

Utilice los bloques inicializadores para realizar cualquier inicialización que no pueda hacerse a través de la inicialización de variables directa; coloque cada bloque de inicialización inmediatamente después de la variable en cuestión. En los ejemplos de abajo, observe que el array puede inicializarse sin utilizar un bloque de inicialización, mientras que el objeto vector requiere uno debido a los procesos de llamadas al método addElement.

Ejemplos:

```java
private Vector listofSomething = new Vector();
{ // Instance initializer block
    listofSomething.addElement(someObject);
    listofSomething.addElement(anotherObject);
}
private static int[] multipliers = {
    5, 4, 3, 2, 7, 6, 5, 4, 3, 2,
};
private static Screen screen = new Screen();
static {
    // Static initializer block
    screen.setValue(someValue);
}
```

##### Uso de Variables
Utilice siempre una variable para un único propósito. A veces es tentador volver a usar una variable existente; evite esta tentación:

**Incorrecto**

```java
int i;
...
for (i = 0; i < accountList.size(); ++ i) {
    ...
}
...
// Swap elements:
i = someArray[0];
someArray[0] = someArray[1];
someArray[1] = i;
...
```

Los dos usos de i arriba no tienen nada que ver entre ellos. La creación de variables únicas para cada propósito hace que su código sea más legible.

### Referencias a variables y métodos de clase

Evite usar un objeto para acceder a una variable o método de clase (static). Use el nombre de la clase en su lugar. Por ejemplo:

```java
UnaClase.metodoDeClase(); //OK
unObjeto.metodoDeClase(); //EVITAR!
```

### Constantes

Las constantes numéricas (literales) no se deben codificar directamente, excepto -1, 0, 1 y 2 que puedan aparecer en un bucle for como contadores.

### Condicionales

Las condiciones complejas pueden ser difíciles de leer y entender. Una manera de facilitar esto es usando variables booleanas adicionales. En el primer fragmento de abajo, el significado de la prueba no es obvio; en el segundo, está clarísimo:

**Confuso**

```java
if (element < 0 || MAX_ELEMENTS < element ||
    element == lastElement )
{
    ...
}
```

**Claro**

```java
final boolean finished = (element < 0 || MAX_ELEMENTS < element);
final boolean repeatedEntry = (element == lastElement);
if (finished || repeatedEntry) {
    ...
}
```

Este enfoque simplifica y documenta las expresiones complejas, facilita su programación sin errores y su mantenimiento.
* Si hay duda sobre la precedencia del operador, use paréntesis. Puede ser que no sean necesarios pero no cuestan nada y evitan errores de interpretación futuros al mantener el código.
* Si usted codifica una cadena de sentencias if – then codifique primero los casos más comunes.
* Utilice un número máximo de 3 branches (caminos distintos) en su código. Siempre que tenga que ocuparse de un caso especial, tómese un momento para considerar si es posible manejar el problema de un modo más general. El código lineal es mucho más fácil de probar.
* Compare los valores boolean con verdadero o falso implícitamente, no explícitamente.

**Correcto**

```java
if (valid) {
    ...
}
if (!valid) {
    ...
}
```

**Incorrecto**

```java
if (valid == true) {
    ...
}
if (valid == false) {
    ...
}
```

### Bucles

* Opte por un bucle for cada vez que sea posible. Las ventajas del bucle for es que reúne el control del bucle en un solo lugar, y permite que usted determine una variable de control del bucle que no es accesible desde fuera del mismo.

Ejemplo:

```java
for (int i = 0; i < vector.size(); ++i ) {
    ...
}
```

* Nunca modifique la variable de control de bucle dentro del bucle for. Si es necesario, opte por un bucle while. Considere el ejemplo anterior: Si el propósito del bucle fuera borrar los ítems seleccionados del vector, sería inapropiado usar un bucle for ya que no incrementaría consistentemente la variable de control del bucle:

**Incorrecto**

```java
for (int i = 0; i < vector.size(); ++ i ) {
    Person person = (Person) vector.elementAt(i);
    if (person.isOldAndTired()) {
        vector.removeElementAt(i);
        -- i; // Loop control is off limits!
    }
}
```

**Correcto**

```java
int i = 0;
while (i < vector.size()) {
    Person person = (Person) vector.elementAt(i);
    if (person. isOldAndTired()) {
        vector.removeElementAt(i);
    } else {
        ++ i;
    }
}
```

* Opte por los bucles que prueban las condiciones de salida en la parte superior e inferior. Si esto no se puede lograra fácilmente, re-escriba el bucle como un bucle infinito “while(true)”con una prueba en el medio. Si fuera posible, utilice una sentencia de un solo salto para salir del bucle.

* Si fuera posible, haga bucles que sean lo suficientemente cortos como para ver todo a la vez. Esto es especialmente importante si el cuerpo del bucle es complejo. Si el código del bucle es mayor a las 15 líneas, considere la posibilidad de reestructurar el código.

* Limite la agrupación a tres niveles.

### Switches

* Nunca permita que el control de flujo “pase” de un case al siguiente omitiendo la sentencia break. Si usted siente la urgencia de hacerlo debido a un código común, considere la posibilidad de sacar el código común a un nuevo método de ayuda.
* Utilize siempre un case default. De esta manera el código está protegido contra cambios futuros, como por ejemplo la introducción de nuevos tipos en un tipo enumerativo

### Hábitos varios

##### Paréntesis

En general es una buena idea usar paréntesis en expresiones que implican distintos operadores para evitar problemas con el orden de precedencia de los operadores. Incluso si parece claro el orden de precedencia de los operadores, podría no ser así para otros, no se debe asumir que otros programadores conocen el orden de precedencia.

```java
if (a == b && c == d) // EVITAR!
if ((a == b) && (c == d)) // CORRECTO
```
##### Valores de Retorno

Intentar hacer que la estructura del programa se ajuste a su intención.

Ejemplo:

```java
if (expresionBooleana) {
    return true;
} else {
    return false;
}
```

en su lugar se debe escribir

```java
return expressionBooleana;
```

##### Expresiones antes de '?' en el operador condicional

Si una expresión contiene un operador binario antes de ? en el operador ternario ?: , se debe colocar entre paréntesis.

Ejemplo:

```java
(x >= 0) ? x : -x;
```

### Notas sobre las Palabras Claves y Construcciones Específicas

##### Final
La palabra clave final tiene relación con la palabra clave const de C++ (aunque no sea lo mismo). Aplique esta a las clases, métodos y todos los tipos de variables:

Una clase final no puede ser subclasificada.

Un método final no puede ser sobrescrito (overridden).

Una variable final no puede ser modificada.

Usar final en una clase o método también puede tener un efecto de optimización. El compilador puede realizar una conexión secuenciada (inlining) o en tiempo de compilación en lugar de una conexión dinámica en el tiempo de ejecución. Por este motivo aplique final a todas las clases y métodos que no tienen la finalidad de ser subclasificados o anulados (esto no quiere decir que todos las clases o métodos no-finales son subclasificados o anulados). No obstante, tenga cuidado con que, en el futuro, los requisitos de sub clasificación podrían cambiar.

Como regla, decida que algo es final sólo si está seguro de que nadie necesitará subclasificarlo en el futuro.

De forma similar, analice el código y verifique qué variables (incluidos los parámetros de función), que, por su naturaleza de uso en la lógica, deben ser finales y agregue el modificador final a las mismas.

##### Return

Un método que retorne un valor debe tener un único return al final del método.

Si el cumplimiento de esta regla hace su código innecesariamente complejo, utilize variables locales para ir almacenando temporalmente el valor a devolver y luego coloque esta variable como valor de retorno.

El objetivo general es la legibilidad y el mantenimiento.

Si considera que es muy complicado cumplir con esta regla, tómese un minuto para considerar si podría existir un diseño alternativo, tal vez aliviando parte del trabajo de los métodos utilizando métodos de ayuda.

##### Transient

Esta palabra clave se aplica a los elementos de datos que no deben ser serializados.

Considere la clase Customer como un ejemplo: tiene una variable de tipo Thread que se usa para descargar en el background la lista de cuentas de clientes. Un Thread no es serializable, entonces el Thread se declara transient.

##### Constructores

Normalmente debería haber solo un constructor “principal” en una clase. Se pueden definir constructores de conveniencia adicional, pero deberían ser implementados en términos del constructor principal. El objetivo de esto es evitar código duplicado:

**Constructor Principal**

```java
public MultiLineLabel(
    String aLabel,
    int aMarginWidth,
    int aMarginHeight,
    int aTextAlignment,
    int aFixedSize)
{
    this.breakLabel(aLabel);
    this.marginWidth = aMarginWidth;
    this.marginHeight = aMarginHeight;
    this.textAlignment = aTextAlignment;
    this.fixedWidth = aFixedSize;
}
```

**Constructor de conveniencia incorrecto (repite código del anterior)**

```java
public MultiLineLabel(String aLabel) {
    this.breakLabel(aLabel);
    this.marginWidth = 0;
    this.marginHeight = 0;
    this.textAlignment = Alignment.LEFT;
    this.fixedWidth = 0;
}
```

**Constructor de conveniencia correcto**

```java
public MultiLineLabel(String aLabel) {
    this(aLabel, 0, 0, Alignment.LEFT, 0);
}
```

##### Threads

La depuración y la creación de perfiles es significativamente más efectivo nombrando todos los Threads explícitamente. Por lo tanto, asegúrese siempre de usar los constructores de Threads que toman un parámetro de nombre, ejemplo: use Thread(String aName) en lugar de Thread().

Asimismo, esto facilita la lectura de logs que imprimen el nombre de cada hilo; tal como se puede hacer con la librería de logueo Log4j.
