package es.carm.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Configurator {


  private static final Logger log = LoggerFactory.getLogger(Configurator.class);

  public static final String FLV_PATH = "./target/videos/";

  private static String configGlobalURL = null;
  private static String configGlobalVersion = null;

  public static void initAll() {
    File videoDir = new File(FLV_PATH);
    videoDir.mkdirs();

    configGlobalURL = System.getProperty("AppURL");
    configGlobalVersion = System.getProperty("AppVersion");

    log.debug(String.format("\n\nSe usara como URL de la aplicacion [%s] y version=[%s]\n\n",
        getApplicationUrl(), getApplicationVersion()));
  }

  public static String getApplicationUrl() {
    return configGlobalURL;
  }

  public static String getApplicationVersion() {
    return configGlobalVersion;
  }


}
