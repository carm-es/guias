# Configuración de las aplicaciones

La forma de configurar las aplicaciones **dependerá del modo en el que se desplieguen**:

* Para aplicaciones que se desplieguen **con Jenkins, se usará la configuración de la rama `config`** tal como se describe en la [guía de configuración para Jenkins](Guia-Config-CI.md).
* Para aquellas aplicaciones que se desplieguen **automáticamente con GitLab-CD, se usará la configuración del `VAULT`** tal como se describe en la [guía de configuración para GitLab-CD](Guia-Config-CD.md).



Independientemente de la forma en la que se configure la aplicación, existen una serie de requisitos que deben cumplirse en cualquier caso:

* Aquellos **parámetros que dependan del entorno** donde se ejecute la aplicación *(endpoints, credenciales, keystores, certificados, valores, textos, ...)*  debe **extraerse a ficheros de configuración fuera del artefacto _(`.war`, `.ear`, `docker`,...)_**
* Los artefactos generados **no deben tener dependencias del sistema operativo** _(linux, windows,...)_.
* La configuración de **los logs de la aplicación se debe externalizar** a fichero, fuera del artefacto y de la lógica de la aplicación.
* **Evite incluir en el repositorio parámetros que comprometan la seguridad** _(keystores, claves de acceso, tokens, api-keys, login, contraseñas...)_


En general tenga siempre en cuenta que un mismo artefacto _(`docker`, `.war`, `.ear`)_  **se debe poder desplegar en cualquier entorno** _(desarrollo, pruebas, producción)_  **y sobre cualquier sistema operativo**, simplemente configurándolo correctamente.

