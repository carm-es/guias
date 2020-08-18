# Despliegue de aplicaciones *(jenkins)*


El despliegue de una aplicación **consiste en instalar el artefacto** o paquete binario *(.war, .ear)* que construimos automáticamente con *Maven* y almacenamos en *Nexus* **en los servidores que ejecutarán la aplicación**. Para ello es necesario:

1. **Copiar el artefacto al servidor** que ejecutará la aplicación desde *Nexus*
2. **Copiar la configuración de la aplicación en el servidor** que ejecutará la aplicación
3. **Reiniciar el servicio** que sirve la aplicación, para que se apliquen los cambios.

Este último paso, ***el reinicio*, puede ser inmediato**, lo que provocará errores a todos los usuarios que en ese momento estén usando la aplicación, **o programado a la noche**, para hacerlo en la siguiente madrugada cuando el número de usuarios sea mínimo.


## Entornos de servidores
En el desarrollo de aplicaciones cliente-servidor no es recomendable trabajar  directamente sobre los servidores que ejecutan las aplicaciones a las que conectan los usuarios: *Si lo hiciéramos, continuamente se estaría torpedeando el trabajo de los usuarios y los datos no serían confiables*.

Para evitarlo y al mismo tiempo facilitar el trabajo a los desarrolladores de las aplicaciones, lo que se hace es duplicar los servidores donde se ejecutan las aplicaciones y todo lo que ello implica *(subredes, cortafuegos, bases de datos, servidores de ficheros...)* y agruparlos en lo que se llama entorno. Así, **siempre encontraremos al menos un entorno**: 

1. **Entorno de producción**: Este es el entorno *(servidores de aplicaciones/bases de datos/ficheros, subredes, cortafuegos, etc)* en el que se ejecutan las aplicaciones que utilizan los usuarios finales.

Como el desarrollo de aplicaciones pasa por diferentes fases, se suele  hablar  como mínimo de:

1. **Entorno de desarrollo**: Este es el entorno que utilizan los programadores mientras modifican las aplicaciones para añadirle nuevas características y corregir errores.
2. **Entorno de pruebas**: Este entorno se utiliza para poder realizar pruebas de funcionamiento de las aplicaciones antes de llevarlas a producción y comprobar su integración con el resto de aplicaciones. También sirve para que los responsables de las aplicaciones validen nuevas funcionalidades e incluso para los cursos de formación.

**Estos entornos se diferencian no sólo en el contexto de su uso, si no también en sus requerimientos**:  El entorno de producción tendrá unos requisitos de disponibilidad (24x7) y rendimiento máximos, mientras que el entorno de pruebas, aún siendo análogo al de producción, no tendrá sus mismos requisitos, ni tampoco el de desarrollo.


### Entornos en la CARM

En la CARM se dispone de **2 entornos**:

1. **Entorno de producción (*```*.carm.es```*)**. Características:

	Es el entorno donde se ejecutan las aplicaciones que usan los usuarios y ciudadanos.

	* Esta configurado en alta disponibilidad con elementos redundantes y balanceados
	* Está monitorizado 24x7
	* Red independiente (no accesible)
	* Los desarrolladores no tienen acceso a la consola de los servidores
  
2. **Entorno de pruebas (*```*-pru.carm.es```*)**. Características:

	Es el entorno donde se despliegan las aplicaciones para verificar su funcionamiento en los servidores CARM, su integración con el resto de aplicaciones, validación de los responsables de las aplicaciones y formación.

	* Esta configurado en alta disponibilidad con elementos redundantes y balanceados.
	* No está monitorizado
	* Red independiente (no accesible)
	* Los desarrolladores no tienen acceso a la consola de los servidores


El **entorno de desarrollo** se  asume que es el equipo de trabajo del desarrollador, aunque **aún existen algunos servicios configurados como *```*-des.carm.es```***, con las siguientes características:

* No están configurados en alta disponibilidad ni con elementos redundantes ni balanceados.
* No están monitorizado
* Red independiente (accesible a través de VPN)
* Los desarrolladores tienen acceso a la consola de los servidores (vía SSH)


## Jenkins
[Jenkins](https://www.jenkins.io/) es un servidor de integración continua, gratuito, open-source y actualmente uno de los más empleados para esta función.

Esta herramienta, proviene de otra similar llamada Hudson, ideada por Kohsuke Kawaguchi, que trabajaba en Sun. Después de que Oracle comprara Sun, la comunidad de Hudson decidió renombrar el proyecto a Jenkins y migrar [el código a Github](https://github.com/jenkinsci). 

A pesar que en la CARM [apostemos por GitLab-CI para la integración continua](Guia-CI.md),  decidimos **usar [Jenkins](https://jenkins.carm.es) para orquestar el despliegue de aplicaciones en los diferentes entornos**, y así disponer una consola de gestión de despliegues central (https://jenkins.carm.es) que unifica la forma en la que desplegar las aplicaciones. Además, se configuran autorizaciones diferentes a GitLab para poder controlar quién puede desplegar qué y sobre qué entornos.

Por tanto, Jenkins se encarga de copiar los artefactos desde Nexus a los servidores de aplicaciones, la configuración de las aplicaciones y reiniciar el servicio, con los siguientes **condicionantes**:

* En el entorno de **desarrollo sólo podrán desplegarse ```SNAPSHOTs```** de Nexus.
* En el entorno de **producción sólo podrán desplegarse ```RELEASEs```** de Nexus.
* En el entorno de **pruebas se podrán desplegar tanto ```SNAPSHOTs``` como ```RELEASEs```** de Nexus.


Para que una aplicación puede desplegarse desde https://jenkins.carm.es, se necesita **configurar una tarea de despliegue, que podrá solicitarse mediante GLPI de tipo tarea al grupo *"S. Aplicaciones Estándar y Herramientas IC"***.

![Jenkins](imagenes/GuiaCD-001.png)


