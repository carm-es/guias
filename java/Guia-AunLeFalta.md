# Qué falta para terminar
> Esta guía **aún está en proceso de desarrollo**, y todo esto es lo que le falta.


## El objetivo de la guía
El desarrollo de esta guía tiene como objetivo **elaborar una serie de recomendaciones de buenas prácticas que sirvan como marco de referencia para la gestión de proyectos software en la CARM**, al menos para aplicaciones Java, *y quién sabe si pudiera servir de inspiración a otro tipo de proyectos como Node.js, PHP,...*

Otras comunidades autónomas ya han hecho algo similar:

1. El *Gobierno de Canarias*, en su perfil del contratante tienen publicado [AGRIOTP_ManualProveedor_v1_4.pdf](http://www.gobiernodecanarias.org/perfildelcontratante/apipublica/documento.html?documento=128468&anuncio=133460)

2. El *Govern de les Illes Balears* publica también las [recomendaciones técnicas](http://governib.github.io/) a seguir en el desarrollo de aplicaciones en [GitHUB](https://github.com/GovernIB/)

3. El *Gobierno de Cantabria* tiene publicados sus [Estándares de desarrollo](https://amap.cantabria.es/amap/bin/view/Main/) en una Web propia 

4. Y, por supuesto, la Junta de Andalucía en su MArco de DEsarrollo de la Junta de Andalucía: *[MADEJA](http://www.juntadeandalucia.es/servicios/madeja/contenido)* 


En los cuatro casos encontraremos directrices comunes sobre cómo gestionar el control de versiones y organizar el código en él (ramas/trunk/ tags), cómo construir los artefactos o qué documentación se debe elaborar y se describe el marco de trabajo y las herramientas que se usarán para ello. No hay que insistir en la **necesidad e importancia de este tipo de recomendaciones** en un entorno que cada vez es más dinámico, hay más contratación externa y los equipos cuentan con una alta rotación laboral y su continuidad se limita a meses: *Es necesario definir claramente las reglas del juego para todos y difundirlas*.


Si se leen las recomendaciones de cada una de estas instituciones y se profundiza en documentos similares en Internet, se podrán detectar unos puntos comunes que son los que describe la guía:

* La elección de un Sistema de Control de Versiones,
* La gestión y organización de los proyectos en el control de versiones,
* El estilo a usar en la escritura del código fuente,
* La gestión de tickets asociados al desarrollo y mantenimiento de aplicaciones,
* La construcción del software desde el código fuente,
* La gestión de la calidad del software,
* La gestión de la construcción y despliegue de las aplicaciones,

...y, para todo ello, se describen las herramientas que se usarán en cada una de las instituciones.

## Septiembre 2022: ¿en qué punto estamos?

Cuando comenzamos a desarrollar esta guía de buenas prácticas, nos fijamos como principal objetivo adaptar las prácticas que propone la integración y entrega continua *(CI/CD)* a la realidad del desarrollo Java en la CARM con las herramientas de las que disponíamos: Desde entonces **hemos estado trabajando en modelar el proceso**:

![CI/CD](imagenes/TODO-04.png)

En **la parte de desarrollo *(dev)***, tenemos:

* Totalmente **acabada y documentada**: ```code```, ```build```. Los proyectos que la usan no reportan incidencias, son autónomos y es plenamente funcional. 
* Por **acabar de implantar**: ```plan```. *Estamos trabajando en su difusión, cada vez son más los proyectos que adoptan este estilo de planificación.*
* Estamos **en ello**: ```test```. *Estamos trabajando en un proyecto en maximizar la cobertura de test unitarios (con Junit5) y la integración de tests con Selenium en el pipeline, que nos ayude  definir el modelo.*

En **la parte de operaciones *(ops)***, disponemos:

* Totalmente **acabada y documentada**: ```release```, ```deploy```. Los proyectos que la usan no reportan incidencias, son autónomos y es plenamente funcional.  El ```deploy```: 
	* En el entorno de pruebas es automático _(con GitLab)_
	* En el entorno de producción es manual  _(tanto con GitLab como con Jenkins)_

En **la parte de seguridad *(sec)***,  tenemos integrado el escaneo de vulnerabilidades de todos los artefactos, _tanto Java como docker_.




En el verano de 2022, se empieza a trabajar en:

1. **Actualizar la numeración del versionado** de los artefactos por un modelo propio
2. **Integrar tests** tanto unitarios como de aceptación e integridad (con Selenium)
3. **Mejorar los pipelines** para controlar más aspectos de los que contempla la guía _(numero de ramas en los proyectos, gestión automatizada de etiquetas de los issues, etc)_




## ¿Y qué le falta a la guía?

Aparte de lo que ya se ha comentado en el estado actual de la guía, sobre lo que ya tenemos implantado consideramos que aún nos faltaría:

1. **El ChangeLog**, como un registro por proyecto de los cambios que se introducen en cada versión, de manera automática basado en los issues que conforman cada _Hito/Milestone_. 
2. **Documentación técnica y GitLab-Pages**. Para la documentación técnica de los proyectos estamos escribiendo la documentación en Markdown en una rama específica del proyecto, pero aún no está automatizado el proceso de renderizado a HTML, ni configurado GitLab para [soportar GitLab-Pages](https://docs.gitlab.com/ee/user/project/pages/).


Quizás que **lo más importante de lo que queda por hacer, es extender estas buenas prácticas a más proyectos en la CARM**:  Aunque cada vez son más los proyectos que integran el pipeline-CI, aún son pocos los que usan el pipeline-CD.



### ¿Cómo se desarrolla la guía?

**Se parte de un hito o una necesidad en el proceso de desarrollo**:
1. Se **recopilan links y referencias** que expliquen cómo solucionarla dentro del marco de la integración continua y de las tecnologías ágiles. 
2. Se **leen estas referencias y se comparten** entre la gente predispuesta que se haya encontrado con esa necesidad/hito, se llega a un consenso de cuál es mejor adoptar.
3. Se difunde lo suficiente como para **empezar a ponerla en marcha en algún proyecto**, de forma rápida y ajustada a la realidad de las herramientas,  personal y formación del que disponemos.
4. Pasados **un par de meses** en los que la solución se haya rodado, se evalúa si la experiencia es buena, aceptada por los participantes del proyecto, no genera incidencias y la gente interioriza los cambios. Mientras no se consiga se sigue con el ciclo: leer, compartir, implantar...
5. Una vez que la solución funciona bien y nos ahorra tiempo, incidencias y problemas, es el momento en el que **ya se puede documentar y publicar en la guía**. 

Al final, **el objetivo de esta guía es el de presentar los acuerdos técnicos que hemos alcanzado en la CARM para trabajar en los proyectos de desarrollo**, y para ello es necesario enumerar en lo que nos hemos basado, qué hemos probado, y de todo ello, con qué nos quedamos.
