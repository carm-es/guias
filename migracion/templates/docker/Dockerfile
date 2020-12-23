

# Imagen de referencia de Tomcat que gestiona Sistemas
FROM docker-registry.carm.es/carm/cicd/dockers/srv/tomcat-8.5:latest

ENV TZ=Europe/Madrid
# ---------
# Copiar la configuración del Tomcat para Tramel
#
ADD conf/*.xml /usr/local/tomcat/conf/
ADD bin/*.sh /usr/local/tomcat/bin/
RUN chmod a+x /usr/local/tomcat/bin/*.sh

# ---------
# Requiere saber cuáles son las siguientes direcciones IP 
COPY hosts/host.aliases /etc/
ENV HOSTALIASES=/etc/host.aliases

# ---------
# Copiar la configuración de la aplicación
ADD target/conf  /usr/local/tomcat/conf/XXXXXX/

# ---------
# Copiar las librerías
COPY target/libs/*.jar /usr/local/tomcat/lib/

# ---------
# Copiar los WARS
COPY target/wars/*.war /usr/local/tomcat/webapps/

