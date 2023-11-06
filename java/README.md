# Proyectos Java

Este documento es una guía para el desarrollo de proyectos Java para la CARM. En él se describen una serie de recomendaciones técnicas a tener en cuenta a la hora de trabajar en este tipo de proyectos.


## Estilo del código fuente *(los .java)*

Lea la [Guía de estilo para código Java](Guia-codigo-java.md) en la que se explica cómo deben escribirse los ficheros ```.java``` y qué herramientas debe usar para ello:

1. Instalar [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/packages/)
2. **Configurar la [plantilla de estilo de código Java de Google](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml) para cada proyecto** de la CARM
3. **Habilitar el [plugin SonarLint](https://www.sonarlint.org/eclipse/)** por proyecto


## Control de versiones *(el Git)*

Lea la guía de [Uso del Control de Versiones (SCM)](Guia-SCM.md) en la que se explica cómo deben gestionarse todos los ficheros de un proyecto y qué herramientas usar:

1. Cliente Git a su elección
2. Cliente Git de Eclipse
3. Operaciones más habituales en Git
4. Cuenta de acceso a [GitLab de la CARM](https://gitlab.carm.es) o [GitHub](https://github.com/carm-es/)
5. Cómo **usar correctamente el control de versiones**.


## Gestión del cambio *(los Issues)*

Lea la guía [Gestión de issues con Git](Guia-Issues.md) para comprender cuál es el punto de partida que nos mueve a cambiar el código fuente de un proyecto y cómo debe **quedar registrado** para resultar eficaz. Después de ello sabrá, 

1. Cómo y cuando **crear Issues**,
2. Cómo clasificarlos mediante **Etiquetas**,
3. Cómo referenciarlos mediante **menciones**,
4. Cómo **agruparlos** para planificar su trabajo 


## Flujo de trabajo  *(las ramas)*

Lea la guía de [El flujo de trabajo](Guia-Workflow.md) para conocer el método que hemos establecido para, a partir de un Issue, aplicar cambios en el código fuente de los proyectos Java. Descubrirá:

1. Las **ramas** que tendrá el proyecto
2. El **ciclo de vida** de las ramas y su relación con los Issues
3. Cómo deben **nombrarse**: ```{tipo}/{usuario o grupo}/{2-3 palabras resumen}-{mención al issue}``` 
4. Cuánto **deben durar** (2 semanas)


## Entrega de código *(los commits)*

Lea la guía [Cómo deben ser los commits](Guia-Commits.md), que describe cómo debe realizarse la actividad de entrega de código dentro del flujo de trabajo del proyecto. Conocerá:

1. **Qué debe contener** un commit
2. **Cuándo** hacer un commit
3. Cómo debe **escribir los mensajes de los commits**
4. Cómo escribir los **mensajes en los pull/merge request**


## Números de versión

Lea la guía sobre los [Números de versión](Guia-Versiones.md) para 
saber cómo y cuándo numerar las distintas versiones de su aplicación:

1. **Usamos ```[Major].[Minor].[Patch]```** asociado a *`[Año].[Nº Sprint].[Nº Feature/Hotfix]`*
2. Los cambios se aplican: al **aceptar los *MergeRequest* y al comenzar un nuevo sprint**
3. Cómo **planificar la nueva versión**, mediante los *hitos* y *tableros* de GitLab.


## Licencia del software *(libre)*

Lea la guía [Licencias de software](Guia-Licencias.md) para conocer las 
licencias software que admitimos para tu código cuando uses
recursos materiales o personales de la CARM.


## Construcción automatizada *(maven)*

Lea la guía para la [Construcción automatizada *(build)*](Guia-Maven.md), que describe cómo transformar el código fuente
de su aplicación en un paquete distribuible para instalar. Además, encontrará: 

1. Qué debe **incluir en el ```pom.xml```** de su proyecto
2. Cómo **configurar maven** para poder compilar proyectos de la CARM
3. Qué **mínimos debe cubrir** su construcción automatizada


## Repositorio de artefactos *(nexus)*

Lea la guía sobre el [Repositorio de artefactos](Guia-Nexus.md) para 
descubrir dónde acaban todos los paquetes y contenedores _docker_ que se construyen automáticamente, y

1. Qué hacer cuando **necesite un ```.jar``` que no está en Internet**
1. Qué hacer cuando necesite **usar un `docker` de internet**
1. Cómo **ejecutar en su equipo la aplicación** que se despliega en los servidores de la CARM

## Calidad del código *(sonarqube)*

Lea la guía [Calidad del Software](Guia-Sonar.md)
para conocer los criterios mínimos de calidad que exigimos en las
entregas de código fuente, y

1. Quién, cómo y cuándo **actualiza estos criterios**,
2. Cuándo **rechazaremos su código** fuente,
3. Cómo **autoevaluar su proyecto** antes de entregar

## Integración continua *(gitlab-ci)*

Lea la guía sobre [Integración continua](Guia-CI.md) para saber cómo hemos automatizado todo el proceso de publicación 
de una versión con cada cambio en el
repositorio de código fuente y,

1. **Cómo escribir ```.gitlab-ci.yml```** en su proyecto, para poder usarlo

## Entrega y despliegue de aplicaciones *(gitlab-cd)*

Lea la guía sobre la [Entrega y despliegue de aplicaciones](Guia-CD.md), para saber cómo instalar una versión de su aplicación en nuestros servidores, y además:

1. De **cuántos entornos** disponemos *(pruebas y producción)*
2. Qué es **Jenkins** y por qué lo usamos
3. Cómo **automatizar el despliegue** de aplicaciones
4. Cómo trabajar con **despliegues concurrentes** en el entorno de pruebas
5. Qué **tipo de artefactos de Nexus** puede desplegar en cada entorno


## Configuración de las aplicaciones

Lea la guía [Configuración de las aplicaciones](Guia-Configuracion.md) para saber cómo y dónde
debe escribir la configuración de su aplicación dependiendo del
entorno donde se despliegue:

1. Cómo diferenciar la **configuración por entornos**
2. Cómo **declarar parámetros *"sensibles"*** a la seguridad *(secretos)*
3. Qué **configuraciones puede incluir en el ```.war```** 
4. Qué es **`vault`** y por qué lo usamos


## Tests software

Lea la guía sobre [Cómo escribir tests](Guia-Tests.md) para las aplicaciones Java y conozca:

1. Cómo crear y ejecutar **test unitarios** en su proyecto,
2. Cómo crear y ejecutar **test de integración y aceptación** _(solo gitlab-cd)_

## Configuración de logs

Lea la guía para la [Configuración de logs ](Guia-Logs.md) asi conocerá:
 
1. Cómo configurar el log de su aplicación 
2. Cómoo visualizar el log en la plataforma de [Gestión de Logs](https://logs-panel-nopro.carm.es). 


## Marco de trabajo para aplicaciones web
Lea la guía [Marco de trabajo para aplicaciones web](./framework/framework.md) para conocer el marco de trabajo recomendado para el desarrollo ágil de aplicaciones web en java.
1.	Por qué se ha escogido el marco tecnológico propuesto
2.	Breve comparación con otros marcos tecnológicos
3.	Qué tecnologías, herramientas y utilidades se utilizan

### ...y todavía faltaría...
> Esta guía **está aún está en desarrollo** y en el documento *"[Qué falta para terminar](Guia-AunLeFalta.md)"* se explica lo que está pendiente de publicar.
