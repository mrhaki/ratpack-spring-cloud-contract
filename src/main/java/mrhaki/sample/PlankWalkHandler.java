package mrhaki.sample;


import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class PlankWalkHandler implements Handler {
    @Override
    public void handle(final Context ctx) throws Exception {
        ctx.parse(PlankWalk.class)
           .map(data -> String.format("Ay, matey, %s, walk the plank!", data.getName()))
           .then(response -> ctx.render(json(new PirateResponse(response))));
    }
}
