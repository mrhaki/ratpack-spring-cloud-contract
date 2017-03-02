package mrhaki.sample

import ratpack.http.HttpUrlBuilder
import spock.lang.Specification
import spock.lang.Unroll

class HttpUrlBuilderSpec extends Specification {

    void 'create URI with http protocol'() {
        expect:
        HttpUrlBuilder.http()
                      .host('localhost')
                      .port(5050)
                      .build()
                      .toString() == 'http://localhost:5050'
    }

    @Unroll
    void 'create URI with path'() {
        given:
        final URI server = 'http://server:8080/'.toURI()

        expect:
        HttpUrlBuilder.base(server)
                      .maybePath(path)
                      .build()
                      .toString() == result


        where:
        path   | result
        null   | 'http://server:8080'
        ''     | 'http://server:8080'
        'user' | 'http://server:8080/user'
    }

    void 'create URI with parameters'() {
        expect:
        HttpUrlBuilder.https()
                      .host("localhost")
                      .path('users')
                      .params(page: 2, max: 100)
                      .params('details', 'true')
                      .build()
                      .toString() == 'https://localhost/users?page=2&max=100&details=true'
    }

}
