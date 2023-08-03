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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.lifecycle.TestDescription;



/**
 *
 * Este clase permite ir a una URL que tiene un certificado SSL expirado, que habitualmente nos
 * pararía el test
 *
 */

public class Caso5Test extends AbstractContainersTest {
  private static final Logger log = LoggerFactory.getLogger(Caso5Test.class);

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
    chromeCap.setAcceptInsecureCerts(true);

    // Obtener acceso al docker con selenium
    driver = new RemoteWebDriver(chrome.getSeleniumAddress(), chromeCap);

    // Fijar resolución
    driver.manage().window().setSize(new Dimension(800, 600));
  }

  @Test
  public void testExpired() {

    driver.get("https://expired.badssl.com/");
    assertNotNull(driver.getCurrentUrl());

    String texto = driver.findElement(By.xpath("/html/body/div[1]/h1")).getText();

    log.info("\n\nEncontró el texto=[" + texto + "]\n\n");

    assertNotNull(texto);
    assertTrue(0 < texto.length());
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
