package es.carm.tests.ejemplos;


import es.carm.tests.Configurator;
import es.carm.tests.Utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.TestDescription;



/**
 *
 * Este clase permite dar de alta un procedimiento genérico 1609 en la sede de la CARM, usando
 * certificado SSL de representante, con Chrome
 *
 */

public class Caso9Test {
  private static final Logger log = LoggerFactory.getLogger(Caso9Test.class);

  private static RemoteWebDriver driver = null;

  public static BrowserWebDriverContainer<?> navegador;

  @Rule
  public TestWatcher testWatcher = new TestWatcher() {

    private TestDescription toTestDescription(String nav, Description description) {
      return new TestDescription() {
        public String getTestId() {
          return description.getDisplayName();
        }

        public String getFilesystemFriendlyName() {
          return nav + "-" + description.getClassName() + "-" + description.getMethodName();
        }
      };
    }

    protected void failed(Throwable e, Description description) {
      navegador.afterTest(toTestDescription("CHROME", description), Optional.of(e));
    };

    protected void succeeded(Description description) {
      navegador.afterTest(toTestDescription("CHROME", description), Optional.empty());
    };

  };

  static {
    Configurator.initAll();

    navegador = Utils.getChrome(true, Utils.CertificadoCliente.Representante);
    Stream.of(navegador).parallel().forEach(GenericContainer::start);
  }


  @BeforeClass
  public static void setup() {
    // Asegurarse que está arrancado el contenedor...
    assertTrue(navegador.isRunning());

    ChromeOptions chromeCap = new ChromeOptions();
    chromeCap.setAcceptInsecureCerts(true);

    // Obtener acceso al docker con selenium
    driver = new RemoteWebDriver(navegador.getSeleniumAddress(), chromeCap);

  }

  @Test
  public void testRepresentante01() {

    driver.get("https://sede.carm.es/genericos/formularios/F.SOLICITUD?proc=1609");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    driver.manage().window().maximize();


    // Seleccionar organo destino
    driver.findElement(By.xpath(
        "/html/body/app-root/app-page-formularios/div/div/app-formulario/div/app-formulario-formly/div/div[2]/div[2]/div/form/formly-form/formly-field[6]/formly-wrapper-fieldset/div/formly-wrapper-label/formly-wrapper-description/formly-wrapper-validation-messages/app-select-dynamic-options/ng-select/div/div/div[2]/input"))
        .click();

    driver.findElement(By.xpath("/html/body/ng-dropdown-panel/div/div[2]/div[10]/span")).click();
    driver.findElement(By.xpath("/html/body/app-root/app-page-formularios")).click();


    // Escribir el NIF
    driver.findElement(By.id("formly_14_input_nif_1")).click();
    driver.findElement(By.id("formly_14_input_nif_1")).clear();
    driver.findElement(By.id("formly_14_input_nif_1")).sendKeys("34823143Q");
    driver.findElement(By.xpath("/html/body/app-root/app-page-formularios")).click();

    // Escribir el nombre...
    driver.findElement(By.id("formly_14_input_nombre_2")).click();
    driver.findElement(By.id("formly_14_input_nombre_2")).clear();
    driver.findElement(By.id("formly_14_input_nombre_2")).sendKeys("ignacio");
    driver.findElement(By.xpath("/html/body/app-root/app-page-formularios")).click();

    Utils.esperar(3);

    // Escribir el apellido1...
    driver.findElement(By.id("formly_14_input_apellido1_3")).click();
    driver.findElement(By.id("formly_14_input_apellido1_3")).clear();
    driver.findElement(By.id("formly_14_input_apellido1_3")).sendKeys("barrancos");
    driver.findElement(By.xpath("/html/body/app-root/app-page-formularios")).click();

    // Escribir el apellido2...
    driver.findElement(By.id("formly_14_input_apellido2_4")).click();
    driver.findElement(By.id("formly_14_input_apellido2_4")).clear();
    driver.findElement(By.id("formly_14_input_apellido2_4")).sendKeys("martinez");
    driver.findElement(By.xpath("/html/body/app-root/app-page-formularios")).click();


    driver.findElement(By.id("formly_15_textarea_expone_9")).clear();
    driver.findElement(By.id("formly_15_textarea_expone_9"))
        .sendKeys("Estamos implementando un test");

    driver.findElement(By.id("formly_16_textarea_solicita_11")).clear();
    driver.findElement(By.id("formly_16_textarea_solicita_11")).sendKeys("Sea ignorado");
    driver.findElement(By.xpath("/html/body/app-root/app-page-formularios")).click();

    // Esperar unos segundos a que se active el botón de continuar
    Utils.esperar(2);

    // Pulsar el botón de Continuar

    // Este bloque sustituye el click del botón (que no hay manera de que funcione)
    WebElement element = driver.findElement(By.id("formly_18_button_defaultKey-13_13"));
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    // Scroll hasta abajo
    executor.executeScript("window.scrollBy(0,1000)");

    log.info("Botón seleccionado [" + element.isEnabled() + "/" + element.getText() + "]");
    // Click en el botón (via javascript)
    executor.executeScript("arguments[0].click();", element);
    try {
      // Y via clásica (unas veces funciona y otras no)
      element.click();
    } catch (Exception x) {
      // Ignorar ... a ver si avanza!
    }


    log.info("Botón emergente [" + driver.findElement(By.xpath(
        "/html/body/app-root/app-page-formularios/div/div/app-formulario/div/app-formulario-formly/div/div[2]/div[2]/div/form/formly-form/formly-field[13]/formly-modal-panel/div/div/div/div[3]/div/div/button[2]"))
        .getText() + "]");

    // Click en el emergente que aparece
    driver.findElement(By.xpath(
        "/html/body/app-root/app-page-formularios/div/div/app-formulario/div/app-formulario-formly/div/div[2]/div[2]/div/form/formly-form/formly-field[13]/formly-modal-panel/div/div/div/div[3]/div/div/button[2]"))
        .click();

    // Esperar a que cargue presentador, que también tarda lo suyo...
    Utils.esperar(5);

    // Decir que es representante
    driver.findElement(By.id("rbRepresentante")).click();

    // Hacer click en Identificarse
    driver.findElement(By.id("formSolIdent")).click();

    // Esperar a la redirección a pase
    Utils.esperar(3);

    // Click en acceso con certificado (en pase)
    driver.findElement(By.id("accesoCertificadoClaveBotonId")).click();

    // Esperar unos segundos de redirecciones hasta estar delante de cl@ve
    Utils.esperar(3);

    // Scroll hacia abajo
    executor.executeScript("window.scrollBy(0,500)");

    // Click en acceso con certificado
    driver
        .findElement(By.xpath("/html/body/main/div[3]/section/div/ul/li[2]/article/div[4]/button"))
        .click();

    // Esperar unos segundos de redirecciones, hasta estar delante de la página de vuelta
    Utils.esperar(3);


    // Se supone que estamos en la página de vuelta y PRESENTADOR nos dará un error!
    String texto = driver.findElement(By.xpath("/html/body/div[4]/div/label[1]")).getText();


    // Mostrar el texto que se lee de la página...
    log.info("\n\nEncontró el texto=[" + texto + "]\n\n");

    assertNotNull(texto);
    assertTrue(texto.contains("COD032"));
  }

  @AfterClass
  public static void cerrar() {
    try {
      driver.quit();
    } catch (Exception x) {
      // Ignorar
    }
  }

}
