package es.carm.tests.ejemplos;

import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;


import es.carm.tests.Configurator;
import es.carm.tests.Utils;

import java.util.stream.Stream;

public abstract class AbstractContainersTest {

  public static BrowserWebDriverContainer<?> chrome;
  public static BrowserWebDriverContainer<?> firefox;
  public static BrowserWebDriverContainer<?> edge;


  static {
    // Inicializar la configuración
    Configurator.initAll();

    // Configurar el docker de Chrome (que grabe video solo cuando falle)
    chrome = Utils.getChrome(false, Utils.CertificadoCliente.Interesado);

    // Configurar el docker de firefox (que siempre grabe video)
    firefox = Utils.getFirefox(true, Utils.CertificadoCliente.Interesado);

    // Configurar el docker de EDGE (que grabe video solo cuando falle)
    edge = Utils.getEdge(false, Utils.CertificadoCliente.Interesado);


    // Arrancar los dockers
    Stream.of(AbstractContainersTest.chrome, AbstractContainersTest.firefox,
        AbstractContainersTest.edge).parallel().forEach(GenericContainer::start);

  }
}
