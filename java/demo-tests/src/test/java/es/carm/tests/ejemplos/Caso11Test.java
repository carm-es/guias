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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.TestDescription;



/**
 *
 * Este clase permite dar de alta un procedimiento genérico 1609 en la sede de la CARM, usando
 * certificado SSL de representante, con FIREFOX (en realidad es el mismo TEST de Caso10Test pero
 * usando firefox en vez de EDGE)
 *
 */

public class Caso11Test {

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
      navegador.afterTest(toTestDescription("FFOX", description), Optional.of(e));
    };

    protected void succeeded(Description description) {
      navegador.afterTest(toTestDescription("FFOX", description), Optional.empty());
    };

  };

  static {

    Configurator.initAll();

    navegador = Utils.getFirefox(true, Utils.CertificadoCliente.Representante);
    Stream.of(navegador).parallel().forEach(GenericContainer::start);
  }


  @BeforeClass
  public static void setup() {
    // Asegurarse que está arrancado el contenedor...
    assertTrue(navegador.isRunning());


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
    driver = new RemoteWebDriver(navegador.getSeleniumAddress(), ffoxCap);

    // Fijar resolución
    driver.manage().window().setSize(new Dimension(1024, 768));

  }

  @Test
  public void testRepresentante03() {

    driver.get("https://sede.carm.es/genericos/formularios/F.SOLICITUD?proc=1609");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    driver.manage().window().maximize();

    JavascriptExecutor executor = (JavascriptExecutor) driver;

    // Esperar que cargue la página :(
    Utils.esperar(5);

    // Seleccionar organo destino
    driver.findElement(By.xpath(
        "/html/body/app-root/app-page-formularios/div/div/app-formulario/div/app-formulario-formly/div/div[2]/div[2]/div/form/formly-form/formly-field[6]/formly-wrapper-fieldset/div/formly-wrapper-label/formly-wrapper-description/formly-wrapper-validation-messages/app-select-dynamic-options/ng-select/div/div/div[2]/input"))
        .click();

    driver.findElement(By.xpath("/html/body/ng-dropdown-panel/div/div[2]/div[10]/span")).click();


    // Escribir el NIF
    // driver.findElement(By.id("formly_14_input_nif_1")).click();
    driver.findElement(By.id("formly_14_input_nif_1")).clear();
    driver.findElement(By.id("formly_14_input_nif_1")).sendKeys("A66721499");

    // esperar que valide el CIF
    Utils.esperar(2);

    // Escribir el nombre...
    WebElement nombre = driver.findElement(By.id("formly_14_input_nombre_2"));
    nombre.click();
    nombre.clear();
    nombre.sendKeys("org");
    nombre.sendKeys("-Nombre de la Organización");
    executor.executeScript("arguments[0].value='org-Nombre de la Organización';", nombre);

    Utils.esperar(2);

    // Scroll hacia abajo
    WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(0, 0);
    new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 400).perform();

    // Rellenar el primer TextArea
    WebElement textArea1 = driver.findElement(By.id("formly_15_textarea_expone_9"));
    textArea1.click();
    textArea1.clear();
    // executor.executeScript("arguments[0].value='Estamos implementando un test';", textArea1);
    textArea1.sendKeys("Estamos implementando un test");

    // Scroll
    new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 800).perform();

    // Completar el siguiente TextArea
    WebElement textArea2 = driver.findElement(By.id("formly_16_textarea_solicita_11"));
    textArea2.click();
    textArea2.clear();
    // executor.executeScript("arguments[0].value='Sea ignorado';", textArea2);
    textArea2.sendKeys("Sea ignorado");


    // Pinchar fuera del TextArea
    driver.findElement(By.xpath("/html/body/app-root/app-page-formularios/div/div")).click();

    // Scroll a tope abajo
    new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 1400).perform();

    // Esperar unos segundos a que se active el botón de continuar
    Utils.esperar(2);

    // Pulsar el botón de Continuar
    WebElement boton1 = driver.findElement(By.id("formly_18_button_defaultKey-13_13"));
    executor.executeScript("arguments[0].click();", boton1);

    try {
      // Y via clásica (unas veces funciona y otras no)
      boton1.click();
    } catch (Exception x) {
      // Ignorar ... a ver si avanza!
    }


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

    // Esperar a la redirección a PASE
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


    // Se supone que estamos en la página de vuelta, en PRESENTADOR
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@noreply.es");

    // Esperar unos segundos a que valide el email...
    Utils.esperar(3);

    // Scroll hasta abajo
    executor.executeScript("window.scrollBy(0,1000)");


    // Click en el botón de CONTINUAR (ya para presentar)
    driver.findElement(By.id("submitFormSol")).click();

    // Se supone que estamos en el paso2, ya para firmar...
    String texto = driver.findElement(By.xpath("/html/body/div[6]/div[2]/form/div[1]/p")).getText();

    assertNotNull(texto);
    assertTrue(texto.contains("finalizar el procedimiento es necesario que firme"));
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
