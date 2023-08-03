package es.carm.tests;

import java.io.File;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.utility.DockerImageName;

public class Utils {

  private static final String PATH_DOCKERS = "docker-registry.carm.es/carm/cicd/dockers/tests/";

  public enum CertificadoCliente {
    Interesado, Representante
  };

  public static void esperar(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (Exception x) {
      // Ignorar...
    }
  }

  private static String getContainerSuffix(CertificadoCliente tipo) {
    if (CertificadoCliente.Representante == tipo) {
      return "-repre";
    }
    return "";
  }

  @SuppressWarnings("unchecked")
  private static BrowserWebDriverContainer<?> configure(
      @SuppressWarnings("rawtypes") BrowserWebDriverContainer browser, boolean recordAlways,
      Logger log) {
    if (recordAlways) {
      browser.withRecordingMode(VncRecordingMode.RECORD_ALL, new File(Configurator.FLV_PATH));
    } else {
      browser.withRecordingMode(VncRecordingMode.RECORD_FAILING, new File(Configurator.FLV_PATH));
    }
    if (null != log) {
      browser.withLogConsumer(new Slf4jLogConsumer(log));
    }
    return browser;
  }


  public static BrowserWebDriverContainer<?> getChrome(boolean recordAlways,
      CertificadoCliente tipo, Logger log) {
    @SuppressWarnings("resource")
    BrowserWebDriverContainer<?> retVal = new BrowserWebDriverContainer<>(
        DockerImageName.parse(PATH_DOCKERS + "chrome" + getContainerSuffix(tipo) + ":latest")
            .asCompatibleSubstituteFor("selenium/standalone-chrome"))
        .withCapabilities(new ChromeOptions()).withImagePullPolicy(PullPolicy.alwaysPull());

    retVal.setShmSize(2147483648L); // 2GBytes

    return configure(retVal, recordAlways, log);
  }

  public static BrowserWebDriverContainer<?> getChrome(boolean recordAlways,
      CertificadoCliente tipo) {
    return getChrome(recordAlways, tipo, null);
  }



  public static BrowserWebDriverContainer<?> getEdge(boolean recordAlways, CertificadoCliente tipo,
      Logger log) {
    @SuppressWarnings("resource")
    BrowserWebDriverContainer<?> retVal = new BrowserWebDriverContainer<>(
        DockerImageName.parse(PATH_DOCKERS + "edge" + getContainerSuffix(tipo) + ":latest")
            .asCompatibleSubstituteFor("selenium/standalone-edge"))
        .withCapabilities(new EdgeOptions()).withImagePullPolicy(PullPolicy.alwaysPull());

    return configure(retVal, recordAlways, log);
  }

  public static BrowserWebDriverContainer<?> getEdge(boolean recordAlways,
      CertificadoCliente tipo) {
    return getEdge(recordAlways, tipo, null);
  }



  public static BrowserWebDriverContainer<?> getFirefox(boolean recordAlways,
      CertificadoCliente tipo, Logger log) {
    @SuppressWarnings("resource")
    BrowserWebDriverContainer<?> retVal = new BrowserWebDriverContainer<>(
        DockerImageName.parse(PATH_DOCKERS + "firefox" + getContainerSuffix(tipo) + ":latest")
            .asCompatibleSubstituteFor("selenium/standalone-firefox"))
        .withCapabilities(new FirefoxOptions()).withImagePullPolicy(PullPolicy.alwaysPull());

    return configure(retVal, recordAlways, log);
  }

  public static BrowserWebDriverContainer<?> getFirefox(boolean recordAlways,
      CertificadoCliente tipo) {
    return getFirefox(recordAlways, tipo, null);
  }


}
