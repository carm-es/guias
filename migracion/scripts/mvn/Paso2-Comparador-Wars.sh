#!/bin/bash
#

 # Comprobar argumentos de consola del script
 if [ "" = "$2" ]
 then
    echo "ERROR: Debes indicar War-Origen y War-Destino" >&2
    exit 2
 fi	 

 # Comprobar si nos han puesto rutas absolutas o relativas...
 origDir=$(pwd)
 cd /tmp

 if [ ! -e "$1" ]
 then
    echo "ERROR: La ruta '$1' no parece ser absoluta" >&2
    exit 2
 fi

 if [ ! -e "$2" ]
 then
    echo "ERROR: La ruta '$2' no parece ser absoluta" >&2
    exit 2
 fi
   	 


 # Para los temporales...
 tmpFile=/tmp/cmp02_Wars_$$_$RANDOM
 
 # Crear directorio Temp
 mkdir $tmpFile.ex

 # Descomprimir el original...
 cd $tmpFile.ex
 jar xf "$1" 

 # Obtener los sum de cada libreria...
 cd WEB-INF/lib 
 for i in $(ls * | sort ) 
 do
    s=$(sum $i)
    printf "%50s  # %s #\n" $i "$s"
 done > $tmpFile.Antiguo
 rm -fr $tmpFile.ex


 # Y ahora hacer lo mismo con el nuevo
 cd $origDir
 mkdir $tmpFile.ex

 # Descomprimir el original...
 cd $tmpFile.ex
 jar xf "$2" 

 # Obtener los sum de cada libreria...
 cd WEB-INF/lib 
 for i in $(ls * | sort ) 
 do
    s=$(sum $i)
    printf "%50s  # %s #\n" $i "$s"
 done > $tmpFile.Nuevo
 rm -fr $tmpFile.ex

 cd $origDir

 # Mostrar diferencias...
 echo "====== Antiguo ================="
 cat $tmpFile.Antiguo
 echo -e "\n\n====== Nuevo ==================="
 cat $tmpFile.Nuevo
 echo -e "\n\n====== Diferencias ============="
 diff -Nru $tmpFile.Antiguo $tmpFile.Nuevo

 # Borrar temporales...
 rm -f $tmpFile.*
 
