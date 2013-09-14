import com.google.inject.{Guice, AbstractModule}
import play.api.{Play, GlobalSettings}
import services.{ProdModule, DevModule, WelcomeTextGenerator, TextGenerator}

import play.api.Play.current

/**
 * Set up the Guice injector and provide the mechanism for return objects from the dependency graph.
 */
object Global extends GlobalSettings {

  /**
   * Bind types such that whenever TextGenerator is required, an instance of WelcomeTextGenerator will be used.
   *
   * This needs to be lazy so it is initalized after the application is started.
   */
  private lazy val injector =  {
    Play.isProd match {
      case true => Guice.createInjector(new ProdModule)
      case false => Guice.createInjector(new DevModule)
    }
  }

  /**
   * Controllers must be resolved through the application context. There is a special method of GlobalSettings
   * that we can override to resolve a given controller. This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = injector.getInstance(controllerClass)
}
