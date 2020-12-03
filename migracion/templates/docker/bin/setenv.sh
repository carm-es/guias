#!/bin/sh



export JAVA_OPTS="$JAVA_OPTS -Xmx768M "

##export JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.trustStore=/usr/local/tomcat/conf/XXXXXX/truststore.jks -Djavax.net.ssl.trustStorePassword=password "

# Para el enrutado de la sesión
export JVMROUTE=`hostname`
export JAVA_OPTS="$JAVA_OPTS -DjvmRoute=jvm$JVMROUTE "

export CATALINA_OPTS="$CATALINA_OPTS" 

