package contracts.pirate

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        urlPath '/walk'
        body([name: $(consumer(regex('[a-zA-z]+')), producer('mrhaki'))])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body([response: "Ay, matey, ${value(consumer('mrhaki'), producer(regex('[a-zA-z]+')))}, walk the plank!"])
        headers {
            contentType(applicationJson())
        }
    }
}
