package net.thumbtack.web.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class HelloWorld {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);
    public static void main(String[] args) {
        get("/hello", (req, res) -> {
            LOGGER.info("Request received from {}", req.ip());
            return "Hello World";
        });
    }
}

