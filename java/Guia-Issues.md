# Gestión de issues con Git

**El término Issues se traduce al castellano como Cuestiones**. Las *"cuestiones"* referidas a un proyecto de desarrollo de una aplicación, pueden ser: *preguntas de los usuarios sobre cómo se hace determinada cosa, sugerencias de mejora, errores detectados, un hilo de discusión entre desarrolladores para decidir cómo, y si  implementar tal cosa o cuándo publicar la siguiente versión*. 
Registrarlas de forma centralizada para tratar de dar respuesta a todas, permite:

* disponer de una base documental adicional sobre determinadas funcionalidades de la aplicación,
* poder realizar un seguimiento de las tareas de desarrollo: implementación de mejoras *(features)* y corrección de errores *(bugs)*,
* tener una perpestiva histórica de la evolución de la aplicación a lo largo del tiempo, 

**El registro de todas estas cuestiones es lo que permite gestionar el proyecto**, y delegar tareas. Para gestionar de manera eficiente un proyecto, cualquier actividad dentro del proyecto debe estar integrada con el resto de conceptos del proyecto, y  al hablar de proyectos de desarrollo de software los conceptos no son sólo bugs o features, son: preguntas de los usuarios, versiones de la aplicación, líneas paralelas de trabajo, manuales, compilación, commits, calidad del código, equipos de desarrollo o el rol de cada persona... 

Tanto [GitLab](https://gitlab.carm.es) como [GitHub](https://github.com/carm-es) ofrecen el **concepto de *"Issue"*, un registro centralizado de las cuestiones que rodean al proyecto que, como gran valor añadido permite vincularlas de forma directa con un commit o una rama de desarrollo** y responder a la pregunta: *"¿Por qué se ha hecho este commit?"*, es decir: *cuál es el bug o la incidencia que está relacionado con este o estos commits,  para saber el motivo por el cual se ha hecho ese cambio en el código*. **Este es el gran valor diferencial de usar Git** frente a Subversion y a otras herramientas especializadas en gestión de tareas, bugs, o incluso del propio [GLPI](https://glpi.carm.es)  que usamos en la CARM: **Poder vincular cualquier cuestión al código fuente del programa en una misma aplicación**,  permite tener una trazabilidad completa de commits a un bug o incidencia concreta. [Un ejemplo en el desarrollo de Electron en GitHub](https://github.com/electron/electron/issues/19911) y [otro del desarrollo de Wget en GitLab](https://gitlab.com/gnuwget/wget2/issues).

Un issue en Git es como una conversación entre los desarrolladores (y usuarios) para definir o resolver algo relacionado con la aplicación, y puede tener dos estados: abierto o cerrado.  Además, permite anexar comentarios, propuestas de solución, designar a responsables de resolverlo, el tipo de solución que se aplicó y todo, llevando un seguimiento del autor y las fechas en las cuales sucede cada evento. 

Como añadido, permite **etiquetar los Issues** para indicar otros aspectos como a qué parte de la aplicación afecta, la urgencia de su resolución, si es un bug o una feature  *([ejemplo en GitHub](https://github.com/electron/electron/labels) y [en GitLab](https://gitlab.com/gnuwget/wget2/-/labels))*,  que permiten **desarrollar una planificación ágil del proyecto mediante [Kanbann](https://es.wikipedia.org/wiki/Kanban_(desarrollo))** en lo que se conoce como **[proyectos en GitHub](https://github.com/electron/electron/projects/21) o [Boards en GitLab](https://gitlab.com/gitlab-org/gitlab-ce/-/boards/280883)**.

Además, **los Issues se pueden agrupar en lo que se conoce como etapas o MileStones**: los issues son metas a corto plazo y los milestones son a largo plazo, y pueden ser un lanzamiento de una nueva versión, un módulo del proyecto, una revisión, un sprint de scrum, etc. Incluyen una descripción, una fecha tope, y las tareas que se necesitan cumplir para lograr ese objetivo. [Un ejemplo en GitHub](https://github.com/codenautas/backend-plus/milestones) y [en GitLab](https://gitlab.com/gitlab-org/gitlab-ce/-/milestones?sort=due_date_desc&state=closed)


## Normas en la gestión de issues
Como cada proyecto puede gestionar sus cambios como consideren sus responsables, es necesario establecer unos criterios comunes mínimos que todos nos comprometemos a respetar en los proyectos de desarrollo de la CARM, y tampoco es algo que hagamos nosotros solamente,

* [Maestría en Issues](https://github.com/ricval/Documentacion/blob/master/Guias/GitHub/issues.md),  traducción de [de la guía de GitHub: Mastering Issues](https://guides.github.com/features/issues/)
* [Issue Tracker (& Tasks Managers)](https://sites.google.com/site/practicadesarrollosoft/temario/issue-tracker)
* [Plantilla de Issues para el desarrollo de Masive-JS](https://gitlab.com/dmfay/massive-js/blob/master/.gitlab/issue_templates/Problem.md)
* [How we organize GitHub issues: A simple styleguide for tagging](https://robinpowered.com/blog/best-practice-system-for-organizing-and-tagging-github-issues)
* [What is the Issues Label Standard (WIP)](https://github.com/moimikey/issues-label-standard/)
* [GitHub Labels and Milestones](https://docs.saltstack.com/en/latest/topics/development/labels.html)
* [About milestones](https://help.github.com/en/articles/about-milestones)

Estas normas responden a las preguntas de cuándo y cómo crear un Issue y cómo gestionarlos a través de etiquetas y milestones.

### Issues
Las normas mínimas comunes para cualquier proyecto Java serán:

1. **Los issues son gratis**: Cada cambio que se considere aplicar sobre la aplicación  debe tener un Issue, no importa cuándo se vaya hacer el cambio.
2. **Cualquier issue debe tener un ÚNICO motivo**, que puede ser [una historia de usuario](https://es.wikipedia.org/wiki/Historias_de_usuario), una necesidad del proyecto, un requisito de sistemas, un bug, una excepción no contralada en los Logs... 
3. **Divide y vencerás**: Un issue NO es *"crear api-rest"*, ni *"disminuir la deuda técnica"*, eso son proyectos o milestones: El desarrollo de una nueva api conlleva muchas tareas de desarrollo, integración y documentación, igual que la disminución de la deuda técnica se divide en tareas de actualización de librerías, refactorización de paquetes...
4. Los issues deben **explicar qué se quiere y cómo se quiere**, y **cuando sea posible por qué**.
5. Cada vez que salgas de **una reunión de seguimiento del proyecto** con los responsables o con los usuarios, crea **un issue de discussion** donde se recoja todo lo hablado en la reunión (a modo de acta), e **issues individuales por cada tarea** que se haya acordado realizar.
6. **En caso de bugs**:
    * **Entorno** donde se produjo el error: Sistema Operativo, Navegador, ...
    * **Resumen** del problema, donde se explique cuál es el problema y cómo reproducirlo 
    * **Resultado** final,  ¿Qué paso cuando se disparó el bug?
    * Comportamiento **esperado**: ¿Qué debería de pasar si ese bug no existiera?
    * y cualquier detalle relevante que pueda ayudar 

> *Considera que en el primer issue que crees, vamos a saber cuánto has leído y cómo de en serio te tomas todo esto*

### Etiquetas
Las etiquetas mínimas serán las que proponen [GitHub](https://help.github.com/es/articles/about-labels)/[GitLab](https://docs.gitlab.com/ee/user/project/labels.html) de forma predeterminada:

* <span style="background-color:red; color: white;">&nbsp;bug </span>: Indica un problema inesperado o un comportamiento no intencionado
* <span style="background-color:red; color: white;">&nbsp;confirmed </span>: Indica que el bug se puede reproducir como indica el Issue
* <span style="background-color:red; color: white;">&nbsp;critical </span>: Por culpa de ese issue, los usuarios no pueden trabajar
* <span style="background-color:blue; color: white;">&nbsp;discussion </span>: Actas de reunión, debates o hilos de conversación donde se debate el rumbo de cambios que debe tomar la aplicación
* <span style="background-color:blue; color: white;">&nbsp;suggestion </span>: Propuesta de cambio de mejora para la aplicación, una nueva funcionalidad
* <span style="background-color:green; color: white;">&nbsp;enhancement </span>: Mejora o feature por implementar, nuevas funcionalidades 
* <span style="background-color:gray; color: white;">&nbsp;documentation </span>: Indica una necesidad de mejoras o adiciones a la documentación
* <span style="background-color:gray; color: white;">&nbsp;support </span>: Preguntas sobre el uso de la aplicación, dudas... 

Estas se ampliarán cuando el responsable lo considere, siguiendo el **código de colores** que propone  *[What is the Issues Label Standard (WIP)](https://github.com/moimikey/issues-label-standard/)*:

* **Amarillo**: Cambios sin impacto aparente
* **Naranja**: Cambios que podrían tener impacto en la aplicación, como refactorizaciones, cambios en la configuración para mejorar el rendimiento, actualizaciones de librerías... 
* **Rojo**: Errores que hacen que la aplicación no funcione como se espera
* **Violeta**: Libre para asignar a criterio del responsable
* **Azul**: Libre para asignar a criterio del responsable
* **Verde**: Mejoras que aplicar en la aplicación, nuevas funcionalidades
* **Gris**: Cambios que no tienen que ver con el código, si no con la documentación, ayuda, issues duplicados, inválidos, etc...
* **Negro**: Usar para sólo para los cambios de versión.

En general, el **etiquetado de issues** se puede hacer en cualquier momento mientras el **issue esté abierto**, pero que debería estar acabado cuando se cierre.

 > *Considera que en el primer issue que cierres, vamos a saber cuánto has leído y cómo de en serio te tomas todo esto*

### Menciones
Usa las menciones siempre que puedas:

* Mención a **usuarios**: *Ejemplos: @login, @admin...*
* Mención a **otros issues**: *Ejemplos: #14, #45...*
* Mención a **etiquetas**: *Ejemplos: ~bug, ~documentation...*

