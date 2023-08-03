package es.carm.tests.ejemplos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.lifecycle.TestDescription;

import es.carm.tests.Utils;



/**
 *
 * Este clase permite ir autenticarse en PASE-PRODUCCION usando certificado SSL de usuario con
 * FIREFOX
 *
 */

public class Caso8Test extends AbstractContainersTest {
  private static final Logger log = LoggerFactory.getLogger(Caso8Test.class);

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
      firefox.afterTest(toTestDescription("FFOX", description), Optional.of(e));
    };

    protected void succeeded(Description description) {
      firefox.afterTest(toTestDescription("FFOX", description), Optional.empty());
    };

  };

  @BeforeClass
  public static void setup() {
    // Asegurarse que está arrancado el contenedor...
    assertTrue(firefox.isRunning());

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

    ProfilesIni profileReader = new ProfilesIni();
    FirefoxProfile profile = profileReader.getProfile("default-release");
    profile.setAcceptUntrustedCertificates(true);
    profile.setAssumeUntrustedCertificateIssuer(false);

    FirefoxOptions ffoxCap = new FirefoxOptions(capabilities);
    ffoxCap.setAcceptInsecureCerts(true);
    ffoxCap.setProfile(profile);

    // Obtener acceso al docker con selenium
    driver = new RemoteWebDriver(firefox.getSeleniumAddress(), ffoxCap);

    // Fijar resolución
    driver.manage().window().setSize(new Dimension(1024, 768));
  }

  @Test
  public void testPase03() {

    driver.get("https://pase.carm.es/pase/login");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    // Click en acceso con certificado
    driver.findElement(By.id("accesoCertificadoClaveBotonId")).click();

    // Esperar unos segundos de redirecciones hasta estar delante de cl@ve
    Utils.esperar(2);

    // Scroll hacia abajo
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("window.scrollBy(0,500)");

    // Click en acceso con certificado
    driver
        .findElement(By.xpath("/html/body/main/div[3]/section/div/ul/li[2]/article/div[4]/button"))
        .click();

    // Esperar unos segundos de redirecciones, hasta estar delante de la página de vuelta de PASE
    Utils.esperar(2);

    // Se supone que estamos en la página de vuelta de PASE: Verificamos que aparezca en el texto
    // el NIF del certificado con el que nos hemos autenticado
    String texto = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/p[1]")).getText();


    // Mostrar el texto que se lee de la página...
    log.info("\n\nEncontró el texto=[" + texto + "]\n\n");

    assertNotNull(texto);
    assertTrue(texto.contains("99999999R"));

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
