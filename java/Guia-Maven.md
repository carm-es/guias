# Construcción automatizada (build)

Una vez hemos realizado y entregado nuestros cambios al repositorio de código fuente que conforma nuestra aplicación, necesitaremos convertir esas instrucciones en lenguaje Java a código binario que un ordenador sea capaz de interpretar y ejecutar. Habitualmente llamamos a este proceso **compilación** o **build**. 

Para ello, se requieren de herramientas informáticas *(compiladores)* y de otros componentes y frameworks de terceros, que necesitará nuestra aplicación durante su construcción o más tarde, cuando sea ejecutada por nuestros usuarios *(dependencias)*. Por estos requisitos, es habitual que el proceso de construcción se realice en el equipo del programador que es, quien en última instancia conoce todos esos requisitos, y requiera además, realizar tareas manuales para conseguirlo. Pero entonces, nos encontramos con: 

1. El equipo y la configuración de cada desarrollador, podría dar origen a binarios  diferentes para un mismo código fuente
2. Cualquier tarea manual, es siempre susceptible de incluir errores humanos.

Es necesario **asegurar que un build, siempre que se realice de la misma forma, genere el mismo resultado sin depender de quién ni cómo lo lleve a cabo**. Así, cuando cambien los desarrolladores o sus equipos de trabajo, siempre puedan construir una versión ejecutable de su aplicación que poder distribuir e instalar.

La solución consiste en **utilizar una herramienta de build** que, a partir de una serie de instrucciones incluidas en el repositorio de la aplicación junto a su código fuente, ejecute con apenas un único comando, todo el proceso de construcción del software. Estas instrucciones las escribirá el desarrollador de la aplicación, y modelan todos los pasos que deben aplicarse de forma automática, sintetizando así su conocimiento de cómo se construye la aplicación.

## Maven
La **construcción automatizada**, consiste 
en **crear secuencias de comandos para automatizar las tareas 
que los desarrolladores de software realizan para construir el binario** de su aplicación, el: *.exe, .war, .ear, .jar...*

Estas tareas suelen incluir como mínimo:

* Descarga y gestión de **dependencias**
* **Compilado** del código fuente a código binario.
* **Empaquetado** de ese código binario.
* Ejecución de pruebas **(tests)** a la aplicación

La última de las tareas que suele realizarse es el **despliegue**, que permite llevar estos paquetes que incluyen los binarios a un repositorio desde el que los usuarios de la aplicación pueden descargar e instalar la aplicación. 

Por lo general, las herramientas que nos permiten implementar esta construcción
automatizada, utilizan un **lenguaje específico con el que desarrollador programará 
las secuencias de comandos que automatizan el *build***.

* Sintaxis basada en la definición de propiedades *(make, cmake)*
* Sintaxis basada en XML *(ant, maven, msbuild)*

Aunque la tendencia actual sea usar un lenguajes de scripts real para escribir estas secuencias de compilación *(como lua para [premake](https://premake.github.io/), y groovy para [gradle](https://gradle.org/))*,
en la **CARM seguimos apostando y soportando [Maven 3](http://maven.apache.org/) para la construcción automatizada de aplicaciones Java**.

![Workflow](imagenes/GuiaMaven-001.png)

Si necesitas saber más de maven, hay suficiente documentación en Internet pero siempre puedes empezar por:
* [Qué es maven](https://www.genbeta.com/desarrollo/que-es-maven)
* [Maven en 10 minutos](https://www.javiergarzas.com/2014/06/maven-en-10-min.html)
* [Diferencias entre Maven 2 y 3](http://www.juntadeandalucia.es/servicios/madeja/contenido/recurso/681)
* [Diferencias entre make, maven, rake, gradle](https://justcodeit.io/build-automation-tools-make-cmake-rake-gradle-maven/)
  

El principal motivo de escoger [Maven frente a Gradle](https://dzone.com/articles/gradle-vs-maven) en la CARM, se debe a que **estamos en pleno proceso de homogenización del desarrollo de proyectos Java y necesitamos limitar la flexibilidad para acotar el soporte técnico**: *No es lo mismo ofrecer ayuda y soporte a programas groovy, que encontrar errores en las declaraciones de un fichero XML con una sintaxis mínima*.

Además, el [**ciclo de vida** que propone Maven para la construcción automatizada](https://www.tutorialspoint.com/maven/maven_build_life_cycle.htm) de aplicaciones **cubre todas nuestras necesidades actuales** y es ampliamente usado en la comunidad Java:

* Permite **compilar** el código fuente Java
* Permite **generar paquetes de distribución** *(.jar, .war, .ear)*
* Es capaz de **generar la documentación integrada** *(Javadoc, Doxygen)*
* Soporta la **ejecución de pruebas automatizadas**:  unitarias, análisis estático y de integración.
* Genera **informes** para el equipo de desarrollo **con las advertencias y errores ocurridos** durante la compilación
* Permite el **despliegue automático** de paquetes de distribución **a un repositorio central *(Nexus)***
* Implementa un **sistema de versionado automático** para los diferentes paquetes de distribución de versiones incrementales de nuestra aplicación
* Permite **gestionar las dependencias externas** de cada aplicación.


### Reglas para construir los ```pom.xml```

Para cumplir con el requisito de que cualquier proyecto Java debe poder construirse de forma automática sin depender del equipo del desarrollador, este deberá escribir un fichero ```pom.xml``` **en el directorio raíz del repositorio de su aplicación** que cumpla las siguientes reglas:


1. Debe ser **compatible con Maven 3**.
2. El  ```groupId``` deberá hacer **mención explícita al nombre de la aplicación o del proyecto**

	Ejemplos: 

	```xml
	<groupId>es.carm.javato.sso</groupId>, 
	<groupId>es.carm.wsindustria.ccp</groupId>,
	<groupId>es.carm.borm.firma-ws</groupId>
	```

3. El tag  ```version``` deberá **incluir siempre ```-SNAPSHOT```**

	Ejemplos: 

	```xml
	<version>1.0-SNAPSHOT</version>, 
	<version>5.6.0-SNAPSHOT</version>
	```

4. **Incluirá el tag ```scm```** con la configuración del repositorio de código fuente

	Ejemplos:

	```xml
	<scm>
	  <url>https://svn.carm.es/APLICACION/trunk</url>
	  <connection>scm:svn:https://svn.carm.es/APLICACION/trunk</connection>  
	  <developerConnection>scm:svn:https://svn.carm.es/APLICACION/trunk</developerConnection>
	</scm>

	<scm>
	  <url>https://gitlab.carm.es/SIAC/tramel</url>
	  <connection>scm:git:https://gitlab.carm.es/SIAC/tramel.git</connection>
	  <developerConnection>scm:git:[push=]https://gitlab.carm.es/SIAC/tramel.git[fetch=]https://gitlab.carm.es/SIAC/tramel.git</developerConnection>
	</scm>
	```

5. **No incluirá configuración para el tag ```distributionManagement```** a excepción que se use la rama ```mvn-repo``` para declarar dependencias no estándard.
6. **Incluirá el tag ```name```** con el nombre común por el que se conoce la aplicación en la CARM *(que servirá como nombre del proyecto en SonarQube)*.

	Ejemplos:

	```xml
	<name>tramel</name>
	<name>inside-carm</name>
	<name>idecri</name>
	```

7. **Incluirá el tag ```description```** con una mínima descripción del proyecto de no más de 10 palabras

	Ejemplos:

	```xml
	<description>Plataforma de Tramitación Electrónica de Expedientes (TRAMEL)</description>
	<description>Gestión del expediente electrónico</description>
	<description>IDEntidad digital corporativa del CRI</description>
	```

8. Para aplicaciones web *(que generen un ```.war``` o ```.ear```)* deberá **incluir el tag ```url```** con el link a la aplicación en producción

	Ejemplos:

	```xml
	<url>https://tramel.carm.es</url>
	<url>https://inside.carm.es</url>
	<url>https://idecri.carm.es</url>
	```


El ```pom.xml``` que se construya **deberá funcionar correctamente sin errores para la ejecución de los comandos**:

* **```mvn deploy```**: 
que construirá todos los artefactos de la aplicación y los instalará en el directorio ```.m2``` del usuario, con la versión ```X.Y.Z-SNAPSHOT``` configurada en el propio ```pom.xml```

* **```mvn release:prepare release:perform -Darguments="-Dmaven.javadoc.skip=true"```**:
que actualizará la versión de los ficheros ```pom.xml```, creará una nueva etiqueta/tag en el repositorio y construirá todos los artefactos e instalará en ```.m2``` para la versión ```X.Y.Z```.


### Configuración de Maven en la CARM

En la guía sobre el [Repositorio de artefactos](Guia-Nexus.md),  se describe cómo se organizan todos los artefactos que se generan en el proceso de construcción y sus dependencias externas [en un servidor Nexus interno](https://nexus.carm.es).

Es por ello, que habrá que **configurar Maven para decirle la URL desde la que encontrar las dependencias CARM**, editando el fichero ```$M2_HOME/conf/settings.xml``` y añadiendo un nuevo perfil (dentro del tag ```<profiles>```):

```xml
<profile>
   <id>carm-repos</id>
   <repositories>       
     <repository>
       <id>carm-public</id>
       <url>https://nexus.carm.es/nexus/content/groups/public</url>
       <layout>default</layout>
       <releases>
          <enabled>true</enabled>
       </releases>
       <snapshots>
          <enabled>true</enabled>
       </snapshots>
     </repository>
     <repository>
       <id>carm-thirdparty</id>
       <url>https://nexus.carm.es/nexus/content/repositories/thirdparty/</url>
       <layout>default</layout>
       <releases>
          <enabled>true</enabled>
       </releases>
       <snapshots>
          <enabled>true</enabled>
       </snapshots>
     </repository>
   </repositories>
</profile>
```

Y **activarlo por defecto**, añadiendo al final del fichero:

```xml
<activeProfiles>
    <activeProfile>carm-repos</activeProfile>
</activeProfiles>
```


