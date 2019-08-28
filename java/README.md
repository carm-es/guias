
# Proyectos Java

Este documento es una guía para el desarrollo de proyectos Java para la CARM. En él se describen una serie de recomendaciones técnicas a tener en cuenta a la hora de trabajar en este tipo de proyectos.


## Estilo del código fuente


Lea la [Guía de estilo para código Java](Guia-codigo-java.md) en la que se explica cómo deben escribirse los ficheros ```.java``` y qué herramientas debe usar para ello:

1. Instalar [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/packages/)
2. **Configurar la [plantilla de estilo de código Java de Google](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml) para cada proyecto** de la CARM
3. **Habilitar el [plugin SonarLint](https://www.sonarlint.org/eclipse/)** por proyecto

## Control de versiones

Lea la guía de [Uso del Control de Versiones (SCM)](Guia-SCM.md) en la que se explica cómo deben gestionarse todos los ficheros de un proyecto, y qué herramientas usar:

1. Cliente Git a su elección
2. Cliente Git de Eclipse
3. Operaciones más habituales en Git
4. Cuenta de acceso a [GitLab de la CARM](https://gitlab.carm.es) o [GitHub](https://github.com/carm-es/)
5. Cómo **usar correctamente el control de versiones**.


## Código bajo control de versiones

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


## Política de versionado

La política de versionado se describe en el documento [Políticas de versionado](../Politicas-de-versionado.md) siguiendo el patrón ```[Mayor].[Menor].[Parche]-[SNAPSHOT]```.