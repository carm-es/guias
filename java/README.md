
## Proyectos Java

Este documento es una guía para el desarrollo de proyectos Java para la CARM. En él se describen la serie de recomendaciones técnicas que deberán tenerse siempre en cuenta a la hora de trabajar en este tipo de proyectos:


### Estilo del código fuente

La codificación de clases Java, se realizará **siguiendo el [documento de buenas prácticas](Buenas-Practicas-de-codificacion.md)**.

Para poder cumplir estas recomendaciones de una manera cómoda, es deseable que instale en [plantilla la Guía de estilo de Google para Java](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml) en su IDE de desarrollo y el [plugin de SonarLint](https://www.sonarlint.org/eclipse/): La versión de [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/packages/) suele incluirla.


### Código bajo control de versiones

Las entregas de código fuente de cualquier proyecto deberán estar **siempre justificadas en un ticket GLPI o un *issue* de GitLab/GitHub**, y además [deberá respetarse](https://www.campusmvp.es/recursos/post/los-10-mandamientos-del-control-de-codigo-fuente.aspx):

1. El código del proyecto **siempre estará bajo un control de versiones**, con la siguiente prioridad:
* [GitLab de la CARM](https://gitlab.carm.es) para nuevos desarrollos propios de la CARM
* [GitHub](https://github.com/carm-es/) para desarrollos que lo permita la licencia, compartidos con otras administraciones o basados en personalizaciones de proyectos Open Source
* [Subversion de la CARM](https://vcs.carm.es) para proyectos antiguos en mantenimiento: *No se usará para los nuevos desarrollos*
2. **SIEMPRE deberá escribir un mensaje** de texto en el commit, que explique brevemente en qué consisten los cambios.
3. El texto del mensaje del commit deberá hacer referencia al ticket GLPI (para repositorios SVN) o al issue (para repositorios GIT) usando ```#NN``` (donde NN es el número de issue).
4. **Nunca trabajar directamente sobre las ramas trunk/master**, siempre sobre ramas independientes que luego mediante ```pull request``` se integrarán.
5. Se monitorizará [la calidad del código mediante sonar](https://sonarqube-pru.carm.es/projects), y cada commit evitará empeorar los indicadores del proyecto.

El flujo de trabajo [utilizará la estrategia OneFlow](https://www.endoflineblog.com/oneflow-a-git-branching-model-and-workflow), siguiendo las pautas que se indican en la misma y optando la variante (**```develop``` + ```master```**).


### Política de versionado

La política de versionado se describe en el documento [Políticas de versionado](../Politicas-de-versionado.md) siguiendo el patrón ```[Mayor].[Menor].[Parche]-[SNAPSHOT]```.