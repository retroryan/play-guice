package controllers

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import mockSettings.MockGlobal

/**
 * A functional test will fire up a whole play application in a real (or headless) browser
 */
class ApplicationFT extends Specification {
  
  "Application" should {
    
    "work from within a browser" in {
      running(TestServer(3333, MockGlobal.mockFakeApp), HTMLUNIT) { browser =>

        browser.goTo("http://localhost:3333/")

        browser.pageSource must contain("Mock Welcome Text.")
       
      }
    }
    
  }
  
}