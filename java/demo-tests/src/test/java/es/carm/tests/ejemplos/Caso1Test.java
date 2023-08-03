package es.carm.tests.ejemplos;

import es.carm.tests.Configurator;

import static org.junit.Assert.assertEquals;
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
 * Este clase permite ejecutar varios test en Chrome, a partir de la URL que se le pasa por línea de
 * comandos
 *
 */


public class Caso1Test extends AbstractContainersTest {

  private static final Logger log = LoggerFactory.getLogger(Caso1Test.class);

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
  public void testArgLine() {

    // Cargar en selenium la URL que nos han pasado...
    driver.get(Configurator.getApplicationUrl());
    assertNotNull(driver.getCurrentUrl());

  }

  @Test
  public void testContainHtml() {

    // Leer algo de la URL que se supone hemos cargado
    String content = driver.findElement(By.id("search-form")).getAttribute("action");

    assertNotNull(content);
    assertEquals(content, Configurator.getApplicationUrl());

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
