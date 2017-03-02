package mrhaki.sample;

import ratpack.server.RatpackServer;

public class PirateApp {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .handlers(chain -> chain
                        .post("walk", new PlankWalkHandler())
                        .get("drink", new DrinkHandler())));
    }
}
