package es.carm.tests.ejemplos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.lifecycle.TestDescription;



/**
 * Este clase permite ejecutar un test en Chrome (en principio exitoso)
 */

public class Caso2Test extends AbstractContainersTest {
  private static final Logger log = LoggerFactory.getLogger(Caso2Test.class);

  private static RemoteWebDriver driver = null;


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
      chrome.afterTest(toTestDescription("CHROME", description), Optional.of(e));
    };

    protected void succeeded(Description description) {
      chrome.afterTest(toTestDescription("CHROME", description), Optional.empty());
    };

  };

  @BeforeClass
  public static void setup() {
    // Asegurarse que está arrancado el contenedor...
    assertTrue(chrome.isRunning());

    // Obtener acceso al docker con selenium
    driver = new RemoteWebDriver(chrome.getSeleniumAddress(), new ChromeOptions());
  }

  @Test
  public void testRica() {

    driver.get("https://rica.carm.es");
    assertNotNull(driver.getCurrentUrl());

    // Vamos hacer algún click por la web...
    driver.findElement(By.xpath(
        "//a[@title = \"Intranet de la Consejería de Economía, Hacienda y Administración Digital\"]"))
        .click();
    driver.findElement(By.xpath("//a[@title = \"Informática y Transformación Digital\"]")).click();
    driver.findElement(By.xpath("//a[@title = \"Integración de Aplicaciones Corporativas\"]"))
        .click();
    driver
        .findElement(By.xpath("//acronym[@title = \"Comunidad Autónoma de la Región de Murcia\"]"))
        .click();

    log.debug("\n\nESTO ES UN MENSAJE DE DEBUG\n\n");
    assertTrue(driver.findElement(By.id("margenZonaPrincipal")).getText()
        .contains("Interconexión de Registros"));
  }

  @AfterClass
  public static void cerrar() {
    log.info("\n\nESTO ES UN MENSAJE DE INFO\n\n");
    try {
      driver.quit();
    } catch (Exception x) {
      // Ignorar
    }
  }

}
