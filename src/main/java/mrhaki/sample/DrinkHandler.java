package mrhaki.sample;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class DrinkHandler implements Handler {
    @Override
    public void handle(final Context ctx) throws Exception {
        final String name = ctx.getRequest().getQueryParams().get("name");
        final String response = String.format("Hi-ho, %s, ye like to drink some spiced rum!", name);
        final PirateResponse pirateResponse = new PirateResponse(response);
        ctx.render(json(pirateResponse));
    }
}
