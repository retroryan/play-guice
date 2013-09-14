package controllers

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import mockSettings.MockGlobal

/**
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationIT extends Specification {

  "Application" should {

    "send 404 on a bad request" in {
      running(MockGlobal.mockFakeApp) {
        route(FakeRequest(GET, "/boum")) must beNone
      }
    }

    "render the index page" in {
      running(MockGlobal.mockFakeApp) {
        val home = route(FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")
        contentAsString(home) must contain("Mock Welcome Text.")
      }
    }
  }
}