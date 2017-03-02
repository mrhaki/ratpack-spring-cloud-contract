package mrhaki.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.func.Action;
import ratpack.func.Function;
import ratpack.handling.Context;
import ratpack.handling.InjectionHandler;
import ratpack.http.HttpUrlBuilder;
import ratpack.http.client.HttpClient;
import ratpack.http.client.ReceivedResponse;
import ratpack.http.client.RequestSpec;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class BartenderHandler extends InjectionHandler {

    public void handle(
            final Context ctx,
            final HttpClient client,
            final URL url,
            final ObjectMapper objectMapper) throws URISyntaxException {

        client.request(createServiceURI(url), createRequest())
              .map(transformServiceResponse(objectMapper))
              .then(pirateResponse -> ctx.render(pirateResponse));
    }

    private Function<ReceivedResponse, String> transformServiceResponse(final ObjectMapper objectMapper) {
        return response -> objectMapper
                .readValue(response.getBody().getText(), PirateResponse.class)
                .getResponse();
    }

    private URI createServiceURI(final URL url) throws URISyntaxException {
        return HttpUrlBuilder.base(url.toURI()).path("drink").params("name", "hubert").build();
    }

    private Action<RequestSpec> createRequest() {
        return request -> request
                .headers(headers -> headers.add("Content-type", "application/json"))
                .get();
    }
}
