# Cómo deben ser los commits
Un *commit* es la actividad de guardar o subir los archivos que has modificado al repositorio en la rama en la que estás trabajando. **Las buenas prácticas invitan a hacer commit temprano y seguido**, siempre que:

1. El código no esté roto (que siempre compile)
2. Que el código no rompa las pruebas unitarias
3. Que no interfiera con el código de otro compañero

Esto permite volver a varios estados previos en caso de un bug difícil de rastrear. 

* Si confías en que puedes entregar los cambios correspondientes a toda la especificación sin problemas ya que la tarea no es muy compleja, entonces bastará con un solo commit.
* Si la tarea es demasiado compleja, divide la tarea en varias subtareas de modo que puedas asociar cada paso particular de la construcción a un commit específico.
* Si la tarea es simple, pero sin embargo consideras que te conviene más realizar varios commits por el motivo que sea, por ejemplo porque quieres entender mejor los cambios o te parece más ordenado, entonces hazlo de esa manera, no hay ningún problema. Si más tarde consideras que has hecho muchos commits para para algo muy simple, júntalos en uno solo.

Lo más importante es que **cada commit debe contener cambios funcionalmente completos** sin que rompan el código, los test unitarios ni generen conflictos.

Como ya sabrás, un commit están formado por los siguientes elementos:
* Ficheros que se han creado, modificado, renombrado o eliminado
* Un título de comentario 
* Un cuerpo del comentario

La [guía del *"Uso del Control de Versiones (SCM)"*](Guia-SCM.md#reglas-de-uso-del-control-de-versiones) ya establecía una serie recomendaciones sobre cómo debían ser los commits pero además, se debe añadir el hecho que sean *cambios funcionalmente completos*, como el conjunto aislado de modificaciones que implementan por sí solos una especificación concreta *(corregir un problema de visualización en FireFox, implementar un listado, añadir una nueva pantalla... )*.

> *El hecho de que los commits no contengan cambios funcionalmente completos, nos dice que estás usando el SCM como servidor de backup de tu directorio de trabajo...*

## Mensaje de los commits
Como sucede con el nombre de las ramas o el estilo del código fuente, existen diferentes criterios para escribir los mensajes de los commits:

* [Guía de estilo para Git](https://github.com/jeko2000/git-style-guide#commits)
* [Commits Convencionales](https://www.conventionalcommits.org/es/v1.0.0-beta.3/)
* [Buenas Prácticas en Commits de Git](https://codigofacilito.com/articulos/buenas-practicas-en-commits-de-git)

Para el desarrollo de proyectos en Java unificamos el criterio, de manera que **el mensaje del commit se ajuste al siguiente formato**:

```
tipo(ámbito): Resumen

Cuerpo del mensaje  [Opcional]

Pie del mensaje [Opcional]
```
Si quieres ajustarte a la norma tendrás que **usar un editor para escribir los mensajes de los commits y NO usar la consola ```git commit -m "blah, blah..."```**.

### El título del mensaje
La primera línea del mensaje del commit debe ser descriptiva y concisa. Idealmente, esta línea **NO debe tener más de 50 caracteres**. Debe hacer uso correcto de mayúsculas y también **debe estar escrita en infinitivo**. 

* bien:  *"Marcar registros como obsoletos"*, *"Corregir visualización en Internet Explorer"*
* mal: *"Arreglo ActiveModel::Mensajes"*, *"Se entrega la documentación"*

El **tipo** podrá ser alguno de los siguientes:

* **```feat```**: Una nueva característica.
* **```fix```**:  Los cambios solucionan un bug.
* **```docs```**:  Las modificaciones introducen cambios en la documentación.
* **```style```**:  Se aplica formato, comas y puntos faltantes, etc; Sin cambios en el código.
* **```refactor```**:  Refactorización del código: Algo que ya está funcionando se reescribe para optimizarlo, simplificarlo, etc...
* **```test```**:  Se añaden o modifican pruebas; Sin cambios en el codigo de la aplicación.
* **```chore```**:  Actualización de tareas de build, configuraciones, etc; Sin cambios en el código de la aplicación.

El **ámbito** es opcional y vendrá referido a un módulo del programa. *Ejemplos: (api-rest), (lang), (css)...* 

### El cuerpo del mensaje
Como no todos los commits son lo suficientemente complejos como para necesitar de una explicación y contextualización, **esta parte es opcional**. 

Después del título, **debe ir una línea en blanco seguida por** una descripción más a fondo del commit, **el cuerpo del mensaje** que se utilizará para **explicar el ¿Por qué? de un commit y no el ¿Cómo?**. 

Cada línea **deberá tener 72 caracteres o menos** y explicará la razón por la cual el cambio era necesario, y no cómo se ha hecho, que eso ya lo vemos en el diff del commit.

### El pie del mensaje
El pie es opcional, al igual que el cuerpo, y se usa para poder hacer un seguimiento a los Issues (a través de menciones) u otros enlaces de interés que resulten de interés para comprender porqué se han aplicado estos cambios.

*Ejemplos:*
```
Closes: #56, #78
Véase también: #12, #34
Fuente http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html
```
Para esto puede servir de ayuda: [Trucos en mensajes de los commits para GitHub](https://platzi.com/blog/trucos-en-mensajes-de-los-commits-para-github/)

## Mensaje de los pull/merge request
