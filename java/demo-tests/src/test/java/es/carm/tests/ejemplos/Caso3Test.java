package es.carm.tests.ejemplos;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

/**
 * Este clase permite ejecutar el mismo test en diferentes navegadores
 */

@RunWith(Parameterized.class)
public class Caso3Test extends AbstractContainersTest {


  @Parameters
  public static Iterable<RemoteWebDriver> getDriver() {
    return Arrays.asList(new RemoteWebDriver(chrome.getSeleniumAddress(), new ChromeOptions()),
        new RemoteWebDriver(firefox.getSeleniumAddress(), new FirefoxOptions()),
        new RemoteWebDriver(edge.getSeleniumAddress(), new EdgeOptions()));
  }

  private RemoteWebDriver driver;

  public Caso3Test(RemoteWebDriver driverParam) {
    this.driver = driverParam;
  }


  @Test
  public void testSuccessRica() {

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
        .contains("Interconexión de Registros"));

    try {
      driver.quit();
    } catch (Exception x) {
      // Ignorar
    }
  }


}
