
## Tests de ejemplo

Este directorio contiene una serie de clases Java de ejemplo que implementan diferentes 
Tests de aceptación del usuario _(UAT)_, 
basados en **[Selenium Webdriver](https://www.selenium.dev/documentation/webdriver/)** 
ejecutándose sobre 
_contenedores Docker_ 
gracias a **[TestContainers](https://testcontainers.com/)**. 


Para poder ejecutarlos en su equipo, ejecute:

```bash
mvn clean test -DAppURL=https://fedoramagazine.org/ -DAppVersion=1.0
```


### Caso1Test

Este clase permite ejecutar varios test en Chrome, a partir de la URL que se le pasa por línea de comandos _(se supone: `https://fedoramagazine.org/`)_



### Caso2Test

En este caso se navega por la URL https://rica.carm.es en Chrome, y se extrae un texto de la web, después de haber hecho click en diferentes enlaces. 


### Caso3Test

Este caso es el mismo que el Caso2Test, pero se ejecuta en tres navegadores diferentes: Chrome, Edge y Firefox. Para Firefox, además se grabará un vídeo con todo el proceso, en `target/videos/`, mientras que en Chrome y Edge sólo se grabará cuando el test falle.



### Caso4Test

Similar al Caso2Test, pero en tratando de encontrar un texto que nunca existirá, y que por tanto fallará y grabará un vídeo en `target/videos/`



### Caso5Test

Este test accede a https://expired.badssl.com/ con Chrome, y lee un texto de la página. La gracia está en que el certificado está expirado, y lanzamos el test ignorando este tipo de advertencias.



### Caso6Test

Este test accede a https://pase.carm.es/ con Chrome y se autentica usando certificado de persona física.


### Caso7Test

Similar a Caso6Test, pero usando Edge.


### Caso8Test

Similar a Caso6Test, pero usando Firefox.


### Caso9Test

Este test accede a https://sede.carm.es/genericos/formularios/F.SOLICITUD?proc=1609 con Chrome, se autentica usando certificado de persona física que actúa como representante, y realiza una presentación online para el procedimiento 1609, a falta de firma.


### Caso10Test

Similar a Caso9Test, pero usando Edge.


### Caso11Test

Similar a Caso9Test, pero usando Firefox.

