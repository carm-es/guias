# Gestión de Logs
Una vez que hemos adoptado el modelo de [Integración continua](Guia-CI.md) y ajustado la [Entrega y despliegue de aplicaciones](Guia-CD.md) nuestra aplicación será desplegada en un contenedor. Los contenedores se consideran por naturaleza entidades "efimeras". Necesitamos una forma de procesar y almacenar para consulta el log de la aplicación. 

Usaremos [OpenSearch](https://opensearch.org/) para almacen y consulta logs 

## ¿Qué hacen las aplicaciones?
 - Envian el log por la salida estándar.
 - Establecen un patrón por defecto
 - Ya no se ocupan de la rotación de logs

### Ejemplo
1. Adjuntamos un ejemplo de configuración para [log4j](https://logging.apache.org/log4j/2.x/) 

```xml 
<?xml version="1.0" encoding="UTF-8"?> 
<Configuration status="{${loglevel}}"> 
   <Appenders> 
     <Console name="Console" target="SYSTEM_OUT"> 
	     <PatternLayout pattern="&lt;servapp_out&gt;%d{yyyy-MM-dd HH:mm:ss,SSS ZZZ} | %p | %c | %m&lt;/finmensaje&gt;%n"/> 
     </Console> 
   </Appenders> 
   <Loggers> 
     <Root level="{${loglevel}}"> 
       <AppenderRef ref="Console"/> 
     </Root> 
   </Loggers> 
</Configuration> 

```
2. Si su aplicación usa [logback](https://logback.qos.ch/)
```xml 
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender"> 
<layout class="ch.qos.logback.classic.PatternLayout"> 
<Pattern> 
&lt;servapp_out&gt;%d{“yyyy-MM-dd HH:mm:ss,SSS ZZZ”} | %p | %c | %m&lt;/finmensaje&gt;%n 
</Pattern> 
</layout> 
</appender> 
```
## Consulta de logs 
 #### Entorno de pruebas
- [https://logs-panel-nopro.carm.es/](https://logs-panel-nopro.carm.es/app/dashboards)
 #### Entorno de producción
- [https://logs-panel.carm.es/](https://logs-panel-nopro.carm.es/app/dashboards)

# Políticas de retención
 -  Por compatibilidad algunas aplicaciones que los soliciten tendrán temporalmente los logs disponibles [Corelog](https://corelog.carm.es)
 -  La caducidad de los indices pertenecientes a la aplicación será de **30 días**.
 -  La caducidad de los indices en opensearch que contengan logs en modo *DEBUG* será de **5 días** 
