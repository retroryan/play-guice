package mockSettings


import com.google.inject.Guice
import play.api.GlobalSettings
import services.TextGenerator
import com.tzavellas.sse.guice.ScalaModule
import org.specs2.mock.Mockito

import play.api.test.FakeApplication

/**
 * Set up the Guice injector and provide the mechanism for return objects from the dependency graph.
 */
object MockGlobal extends GlobalSettings {

  lazy val mockFakeApp = FakeApplication(withGlobal = Option(MockGlobal))

  /**
   * Test binding of types such that whenever TextGenerator is required, a mocked instance of TextGenerator will be used.
   */
  private lazy val injector = Guice.createInjector(new TestModule)

  /**
   * Controllers must be resolved through the application context. There is a special method of GlobalSettings
   * that we can override to resolve a given controller. This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = injector.getInstance(controllerClass)
}

/**
 * It doesn't seem right to have Mockito define here, but not sure of a better way to mock the response?
 */
class TestModule extends ScalaModule with Mockito {
  def configure() {
    val textGenerator = mock[TextGenerator]
    textGenerator.welcomeText returns "Mock Welcome Text."

    bind[TextGenerator].toInstance(textGenerator)
  }
}