package services

import com.tzavellas.sse.guice.ScalaModule


class ProdModule extends ScalaModule {
  def configure() {
    bind[TextGenerator].to[WelcomeTextGenerator]
  }
}

class DevModule extends ScalaModule {
  def configure() {
    bind[TextGenerator].to[DevTextGenerator]
  }
}