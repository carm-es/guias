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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.lifecycle.TestDescription;



/**
 * Este clase permite ejecutar un test en Chrome que fallará 
 */

public class Caso4Test extends AbstractContainersTest {

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

    ChromeOptions chromeCap = new ChromeOptions();
    chromeCap.setCapability("headlesss", true);
    chromeCap.setCapability("acceptInsecureCerts", true);
    chromeCap.setCapability("acceptSslCerts", true);
    chromeCap.setCapability("headless", true);
    chromeCap.setCapability("window-size=1920,1080", true);


    // Obtener acceso al docker con selenium
    driver = new RemoteWebDriver(chrome.getSeleniumAddress(), chromeCap);

    // Fijar resolución
    driver.manage().window().setSize(new Dimension(1600, 900));
  }

  @Test
  public void testSimpleFailRica() {

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

    assertTrue(driver.findElement(By.id("margenZonaPrincipal")).getText()
        .contains("Este texto no existe!"));
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
