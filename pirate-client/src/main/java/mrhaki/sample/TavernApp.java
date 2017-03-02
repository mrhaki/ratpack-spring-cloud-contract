package mrhaki.sample;

import ratpack.server.RatpackServer;

public class TavernApp {

    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .handlers(chain -> chain
                        .post("bartender/ask", new BartenderHandler())
                        .get("bartender/story", new StoryHandler())
                )
        );
    }

}
