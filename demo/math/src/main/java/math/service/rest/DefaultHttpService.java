package math.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import math.service.MathService;
import math.util.rest.EmbeddedRestServer;
import math.util.rest.JacksonJson;
import math.util.rest.jetty.JettyEmbeddedRestServer;

public class DefaultHttpService implements HttpService {

    private static final Logger LOG = LoggerFactory.getLogger(HttpService.class);
    private final EmbeddedRestServer restServer;
    private final MathService mathService;

    public DefaultHttpService(MathService mathService) throws Exception { 
    	this.mathService = mathService;
        this.restServer = new JettyEmbeddedRestServer(8080, "/demo", "/api/*");
    }

    @Override
    public void init() throws Exception {

        LOG.info("Initializing DefaultHttpService...");
        restServer.addProvider(JacksonJson.class);
        restServer.setAttribute(MathService.class.getName(), mathService);
        restServer.addRestService("math.service.rest.resources");
    }

    @Override
    public void start() throws Exception {
        LOG.info("Starting DefaultHttpService...");
        restServer.start();
    }

    @Override
    public void stop() throws Exception {
        LOG.info("Stopping DefaultHttpService...");
        restServer.stop();
    }
}
