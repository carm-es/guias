#!/bin/bash
#

 # Comprobar argumentos de consola del script
 if [ "" = "$2" ]
 then
    echo "ERROR: Debes indicar War-Origen y War-Destino" >&2
    exit 2
 fi	 

 # Para los temporales...
 tmpFile=/tmp/cmp01_Wars_$$_$RANDOM
 
 # Extraer contenido de fichero...
 jar tf "$1" | sort -u > $tmpFile.Antiguo
 jar tf "$2" | sort -u > $tmpFile.Nuevo

 # Mostrar diferencias...
 diff -Nru $tmpFile.Antiguo $tmpFile.Nuevo

 # Borrar temporales...
 rm -f $tmpFile.*
 
