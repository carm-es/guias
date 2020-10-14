# Configuración de las aplicaciones

La configuración de cada aplicación **se almacena en su repositorio de código fuente bajo el directorio ```/configuracion```**. En él se tendrá un subdirectorio por cada entorno en el que pueda desplegarse la aplicación:

* ```/configuracion/produccion/``` con la configuración de la aplicación que deberá desplegarse en producción,
* ```/configuracion/pruebas/``` con la configuración que desplegar en el entorno de pruebas,
* ```/configuracion/desarrollo/``` con la configuración que debería desplegarse en el entorno de desarrollo, **aunque no exista para la aplicación**. 

En el proceso de despliegue de la aplicación, **Jenkins copia el contenido del directorio ```/configuracion/XXXX/``` en ```$CATALINA_HOME/conf/YYYY``` de los servidores que ejecutarán la aplicación**, donde:

* ```XXXX``` es el entorno en el que se va a desplegar y tendrá uno de los siguientes valores: ```produccion```, ```pruebas``` o ```desarrollo```.
*  ```YYYY``` es el nombre de la aplicación *(ejemplo: tramel, sandra, etc...)*


## Requisitos de la configuración

Con carácter general,  los **parámetros de configuración cuyo valor dependa del entorno** donde se ejecute la aplicación *(endpoints, credenciales, keystores, certificados, valores, textos, ...)* **deberán llevarse a ficheros *(.properties, .xml, ...)* en los directorios  ```/configuracion/XXX``` fuera del ```.war```**.

Además, deberá tenerse en cuenta:

1. El  **```context.xml``` de la aplicación debe estar embebido en el propio ```.war```**: El equipo de operaciones no manipula estos ficheros.
2. El ```.war``` de la aplicación **no debe tener ninguna dependencia del sistema operativo** *(Linux, Windows,...)* **ni del entorno** *(desarrollo, producción)* donde se ejecute. Cualquier dependencia se parametrizará mediante ficheros de configuración en ```/configuracion/XXX``` .
3. La **configuración de los logs de la aplicación se externalizará en ```/configuracion/XXX```** y no se embeberá dentro del propio  ```.war```.
4. Los parámetros de configuración en los ficheros  ```/configuracion/XXX``` deberán documentarse generosamente en línea.

Tenga en cuenta que con la configuración de la aplicación trabajan el equipo de desarrollo *(es quien implementa estas configuraciones, quien propone)* y el de operaciones *(es quien las consume, quien dispone)*: Cuánto más legibles  y documentados dejemos nuestros ficheros de configuración, más fácil resultará a operaciones aplicar cambios y depurar problemas.

## Configuraciones sensibles (secretos)

En todas las configuraciones suele haber **parámetros de configuración que comprometen la seguridad de las aplicaciones** en producción en caso de quedar expuestos: 

* Contraseñas de acceso a los servidores de bases de datos,
* Certificados y keyStores con claves privadas,
* ApiKeys de consulta de servicios,
* Tokens de autorización,
* ...y en general: cualquier login y contraseña

Almacenar los valores de estos parámetros en ```/configuracion/XXXX``` dentro del repositorio, los expondría a la mirada curiosa de cualquiera que podría usarlos con fines ajenos al desarrollo de la aplicación *(phishing, accesos no autorizados, ...)*.

Además de este tipo de secretos, también existen **otros parámetros de configuración que dependen del entorno y/o equipo** donde se ejecuta la aplicación como:

* El Número de puerto donde escucha nuestra aplicación,
* El Path del directorio donde se monta un volumen NFS
* Ruta NetBIOS de acceso a un recurso
* ...y en general: todo lo que tiene que ver con rutas de directorios

Por lo general, el valor de todos estos parámetros los conoce el equipo de operaciones según el entorno donde se despliegue la aplicación, pero **es el equipo de desarrollo quien debe indicarle al equipo de operaciones qué parámetros debe instanciar en el despliegue**. 

Para ello, se creará un fichero en ```/configuracion/desarrollo/``` por cada archivo ```AAAA.EXT``` de configuración con propiedades sensibles llamado:

<h2 style="text-align: center"<code>AAAA.concatenar.EXT</code></h2>

...donde:

* ```AAAA``` es el nombre del fichero de configuración
* ```EXT``` es la extensión que tuviera el fichero de configuración *(ejemplo. ```.xml```, ```.properties```)*
* ```concatenar``` es una marca que indica al equipo de operaciones que el fichero  ```AAAA.EXT``` tiene propiedades sensibles declaradas en ```AAAA.concatenar.EXT```, que deben instanciar.


Ilustremos todo esto con un ejemplo. Imaginemos que nuestra aplicación tiene el siguiente fichero de configuración llamado  ```mi-app.properties```, que en nuestro equipo y en el entorno de desarrollo funciona perfectamente:

```
# Mensaje bienvenida
bienvenida_text=Entorno desarrollo

# Color de fondo para distinguir el entorno
bienvenida_color=rojo

# Máximo numero de usuarios
max_users=10

# A quien enviar las estadisticas
notificacion=pedrito@empresa.com

# Servidor SMTP
smtp_server=192.168.2.40
stmp_user=mi-app
stmp_password=pokemon
```

En el fichero identificamos parámetros de configuración: 

1. Podrían ser  **comunes a todos los entornos**: ```max_users```.  *Estos valores se repetirán en todos los ficheros ```/configuracion/XXXX/mi-app.properties```*
2. Serán **distintos según el entorno pero NO comprometen la seguridad**: ```bienvenida_text```, ```bienvenida_color``` y ```notificacion```. *Estos valores se configurarán en cada uno de los ```/configuracion/XXXX/mi-app.properties```*
3. Serán **distintos según el entorno y SÍ comprometen la seguridad**, o al menos su valor solo lo conoce el equipo de operaciones: ```smtp_server```, ```stmp_user``` y ```stmp_password```. **Estos valores solo se configurarán en  ```/configuracion/desarrollo/mi-app.concatenar.properties```**

Así, lo que finalmente tendremos que crear en ```/configuracion/produccion/``` será un único fichero llamado ```mi-app.properties``` con el siguiente contenido:

```
# Mensaje bienvenida
bienvenida_text=Entorno produccion

# Color de fondo para distinguir el entorno
bienvenida_color=azul

# Máximo numero de usuarios
max_users=10

# A quien enviar las estadisticas
notificacion=aplicaciones@listas.carm.es
```

Para el entorno de pruebas habrá que crear en ```/configuracion/pruebas/``` un único fichero ```mi-app.properties``` con el siguiente contenido:

```
# Mensaje bienvenida
bienvenida_text=Entorno pruebas

# Color de fondo para distinguir el entorno
bienvenida_color=naranja

# Máximo numero de usuarios
max_users=10

# A quien enviar las estadisticas
notificacion=pedrito@empresa.com
```

Y por último, para el entorno de desarrollo *(**aunque no exista para nuestra aplicación**)* habrá que **crear dos ficheros**:

1. ```/configuracion/desarrollo/mi-app.properties``` con el siguiente contenido:

	```
	# Mensaje bienvenida
	bienvenida_text=Entorno desarrollo
	
	# Color de fondo para distinguir el entorno
	bienvenida_color=rojo
	
	# Máximo numero de usuarios
	max_users=10
	
	# A quien enviar las estadisticas
	notificacion=pedrito@empresa.com
	```

2. ```/configuracion/desarrollo/mi-app.concatenar.properties``` con el siguiente contenido:

	```
	# Servidor SMTP
	smtp_server=192.168.2.40
	stmp_user=mi-app
	stmp_password=pokemon
	```

En resumen: Llevar todos los parámetros de configuración *"sensibles"* del fichero  ```mi-app.properties```  *(```AAAA.EXT```)*  a  ```mi-app.concatenar.properties```  *(```AAAA.concatenar.EXT```)* pero solo para ```/configuracion/desarrollo/``` , y en general, hacerlo con todos los ficheros de configuración que incluyan configuración sensible.


Luego, al configurar la tarea de despliegue en Jenkins para la aplicación, el equipo de operaciones implementará que, después de copiar la configuración de la aplicación en el servidor, resuelva el contenido de todos los ficheros ```*.concatenar.*``` y los concatene a sus respectivos ```*.*``` .
