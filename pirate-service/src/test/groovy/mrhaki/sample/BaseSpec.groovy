package mrhaki.sample

import com.jayway.restassured.RestAssured
import ratpack.test.MainClassApplicationUnderTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

abstract class BaseSpec extends Specification {
    
    @Shared
    @AutoCleanup
    def app = new MainClassApplicationUnderTest(PirateApp)
    
    def setupSpec() {
        final URI address = app.address
        RestAssured.port = address.port
    }
}
