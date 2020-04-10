> *Documento copiado desde [https://github.com/kosme10/standards/wiki/Buenas-Practicas-de-codificacion](https://github.com/kosme10/standards/wiki/Politicas-de-versionado.md)*

# Políticas de versionado

## Desarrollo de una nueva funcionalidad
Toda nueva funcionalidad se desarrollará sobre la carpeta trunk correspondiente. Cuando se desarrolle una nueva funcionalidad de una versión en producción o mantenimiento, se hará un branch a partir de la rama correspondiente: ```master``` o ```develop```.

### Reglas de versionamiento

Tomando como base los conceptos expresados en el documento "[Semantic Versioning](http://semver.org/)" de Tom Preston-Werner, se propone los siguientes lineamientos para la definición de versiones para los artefactos generados.

Estructura |[Mayor].[Menor].[Parche]-[Calificador].[Incremental]
-|-
Regex | [ 0-9 ].[ 0-9 ].[ 0-9 ]-[ SNAPSHOT / ALPHA / BETA / CR / GA ].[0-9]


**Versión Mayor**
- Cambios radicales en los requisitos y funcionalidad (cambios disruptivos)
- Cambios de frameworks de base
- Cambios en el núcleo del proyecto
- Cambios en la API pública del proyecto
- Cambios de plataforma
- Si se incrementa vuelve los valores de versión menor y parche a cero
- Regex: [ 0-9 ]
- Ejemplo: **X.y.z**

**Versión Menor**
- Nueva funcionalidad
- Cambios incrementales no-disruptivos
- Si se incrementa vuelve los valores de versión parche a cero
- Regex: [ 0-9 ]
- Ejemplo: **x.Y.z**

**Versión Parche**
- Arreglo (FIX) sobre una versión concreta
- Regex: [ 0-9 ]
- Ejemplo: **x.y.Z**

**Calificador**
- Indica el nivel de madurez en función del “Roadmap” del producto.
- El Product Owner define en qué estado de madurez se encuentra el proyecto en función de las funcionalidades cubiertas y los resultados de pruebas en QA.
- Se utilizaran los siguientes calificadores:
  - SNAPSHOT: Indica que el código está en pleno desarrollo.
  - ALPHA (opcional): Pruebas de integración entre desarrolladores.
  - BETA: Listo para pasar a QA. Listo para iniciar pruebas funcionales.
  - CR (Candidate Release): Incluye todas las correcciones solicitadas por QA.
  - GA (General Availibility): Listo para despliegue productivo luego que se verificó que la versión de CR en QA no presenta errores.
- Regex: [ SNAPSHOT | ALPHA | BETA | CR | GA ]
- Ejemplos:
  - x.y.z-ALPHA
  - x.y.z-BETA
  - x.y.z-SNAPSHOT
  - x.y.z-GA

**Incremental**
- Indica iteraciones de arreglos de bugs y funcionalidad basados en las correcciones solicitadas por QA entre los distintos estadios de madurez.
- Regex: [ 0-9]
- Ejemplos:
  - x.y.z-BETA.1
  - x.y.z-BETA.2
  - x.y.z-CR.1
  - x.y.z-CR.2

### Creación de TAGs (etiquetas)
Git (al igual que otros VCS) permite etiquetar (tag) puntos específicos en la historia como importantes. Generalmente la gente usa esta funcionalidad para marcar puntos donde se ha lanzado alguna versión.

#### Listar etiquetas
Listar las etiquetas disponibles en Git es sencillo, Simplemente escribe git tag.

`$ git tag`

También se puede buscar etiquetas de acuerdo a un patrón en particular.

`$ git tag -l 'v1.4.2.*'`

#### Crear etiquetas
Git usa dos tipos principales de etiquetas: ligeras y anotadas. Una etiqueta ligera es muy parecida a una rama que no cambia (un puntero a una confirmación específica). Sin embargo, las etiquetas anotadas son almacenadas como objetos completos en la base de datos de Git. Generalmente se recomienda crear etiquetas anotadas para disponer de toda esta información.

Para crear **etiquetas anotadas**
```
$ git tag -a v1.4 -m 'my version 1.4'
$ git tag
v0.1
v1.3
v1.4
```

Para crear **etiquetas ligeras**
```
$ git tag v1.4-lw
$ git tag
v0.1
v1.3
v1.4
v1.4-lw
v1.5
```

Se puede incluso etiquetar más tarde.
```
$ git log --pretty=oneline
15027957951b64cf874c3557a0f3547bd83b3ff6 Merge branch 'experiment'
a6b4c97498bd301d84096da251c98a07c7723e65 beginning write support
0d52aaab4479697da7686c15f77a3d64d9165190 one more thing
6d52a271eda8725415634dd79daabbc4d9b6008e Merge branch 'experiment'
```

Por ejemplo, si quiero tagear la versión de "one more thing".

`$ git tag -a v1.2 -m 'version 1.2' 0d52aaab4`
