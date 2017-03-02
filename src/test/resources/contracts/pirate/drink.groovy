package contracts.pirate

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        urlPath '/drink', {
            queryParameters {
                parameter 'name': $(consumer(regex('[a-zA-z]+')), producer('mrhaki'))
            }
        }
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body([response: "Hi-ho, ${value(consumer('mrhaki'), producer(regex('[a-zA-z]+')))}, ye like to drink some spiced rum!"])
        headers {
            contentType(applicationJson())
        }
    }
}
