# Marco de trabajo para aplicaciones web
En los siguientes apartados intentaremos explicar el por qué se han escogido las tecnologías **Angular y SpringBoot** para conformar el marco de trabajo para el desarrollo de aplicaciones web. Además, encontrarás una breve comparación con otros marcos tecnológicos así como las herramientas y utilidades se utilizan.

Hemos estudiado las tendencias del mercado y analizado cuáles son las tecnologías más populares, comprobando que las tecnologías escogidas siguen estando entre las más populares y si siguen estando de actualidad, lo cual facilitará el mantenimiento futuro de las apliaciones, siendo fácil encontrar técnicos cualificados tanto dentro de la organización como fuera de ella.

Para la elección de las tecnologías hemos tenido en cuenta que dentro de los distintos departamentos de informática de la CARM se han llevado a cabo multitud de proyectos en java por lo que muchos de los técnicos tanto de la CARM como de las empresas que han trabajado para la CARM tienen formación en dicho lenguaje y se sienten cómodos con él.

## ¿Por qué utilizar Angular/SpringBoot?
En este [artículo](razonamientofrmw.md) podrás encontrar algunas de las razones por las que hemos escogido utilizar Angular/SpringBoot.

## ¿Cómo empezar con Angular/SpringBoot ?
Para comenzar lo primero que tenemos que hacer es crear un proyecto, podríamos crear el proyecto o bien directamente desde la página online de Spring [https://start.spring.io/](https://start.spring.io/) y ya trabajar con la herramienta que a cada uno le guste o bien otra opción y para nosotros la más interesante es instalarse Spring Tool que no es más que un plugin que se instala en el IDE, existe tanto para eclipse como para Visual Studio Code.

Para instalarse Spring Tool hay que ir a la web [https://spring.io/tools](https://spring.io/tools) y descargarse la versión adecuada tanto para el IDE como para el sistema operativo que cada uno tenga.

![Spring Tool](imagenes/springtool_14.png)

## ¿Se podría utilizar algún Asistente RAD?
Pues SI!. Hemos visto varios proyectos para generar código a partir de la Base de Datos o de Modelos de Datos como g9 Database Import, hibernate tools (Indigo), jHipster pero esos no nos han convencido. Sin embargo, nos ha gustado mucho Telosys Code Generator, creemos que sería una buena herramienta para agilizar el desarrollo de aplicaciones.

**Telosys Code Generator**

Telosys [https://www.telosys.org/](https://www.telosys.org/) utiliza plantillas velocity para generar código. Hemos encontrado varios proyectos en gitHub.com donde se pueden encontrar plantillas para generar código:

- **springBoot** [https://github.com/anicetkeric/spring-boot-telosys-template](https://github.com/anicetkeric/spring-boot-telosys-template): **Nos ha gustado tanto que hemos creado un fork de este proyecto y lo hemos modificado para SpringBoot 3.1.0** puedes descargarte las plantillas de **[https://github.com/carm-es/spring-boot-telosys-template](https://github.com/carm-es/spring-boot-telosys-template)**

  En el siguiente artículo [https://boottechnologies-ci.medium.com/quick-spring-boot-microservice-api-using-telosys-generator-bc29327c06ab](https://boottechnologies-ci.medium.com/quick-spring-boot-microservice-api-using-telosys-generator-bc29327c06ab) encontramos un tutorial para generar código a partir de los objetos de la base de datos utilizando las plantillas telosys.

- **Angular** (https://github.com/telosys-templates-v3/angular4-rest-frontend).

Telosys tiene plugin tanto para eclipse como para visual studio code

En eclipse (Spring Tool Suite):

![Telosys](imagenes/plugtelosys_15.png)

En Visual Studio Code:

![Telosys](imagenes/plugtelosys_16.png)





