package mrhaki.sample

import org.junit.ClassRule
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule
import ratpack.impose.ImpositionsSpec
import ratpack.impose.UserRegistryImposition
import ratpack.registry.Registry
import ratpack.test.MainClassApplicationUnderTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class BartenderSpec extends Specification {

    @ClassRule
    @Shared
    private StubRunnerRule mockServer =
            new StubRunnerRule()
                    .downloadStub('mrhaki.ratpack.pirate.service', 'pirate-service', '0.0.2', 'stubs')
                    .workOffline(true)

    @AutoCleanup
    @Shared
    private app = new MainClassApplicationUnderTest(TavernApp) {
        @Override
        protected void addImpositions(final ImpositionsSpec impositions) {
            final mockUrl = mockServer.findStubUrl('mrhaki.ratpack.pirate.service', 'pirate-service')
            impositions.add(UserRegistryImposition.of(Registry.of { registry -> registry.add(URL, mockUrl)}))
        }
    }

    void 'ask for a drink'() {
        when:
        def response = app.httpClient.post('bartender/ask')

        then:
        response.statusCode == 200
        response.body.text == 'Hi-ho, mrhaki, ye like to drink some spiced rum!'
    }
    
    void 'tell story'() {
        when:
        def response = app.httpClient.get('bartender/story')

        then:
        response.statusCode == 200
        response.body.text == 'Ay, matey, mrhaki, walk the plank!'
    }
}
