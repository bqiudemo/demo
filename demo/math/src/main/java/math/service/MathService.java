package math.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import math.util.base.Service;
import math.service.rest.DefaultHttpService;
import math.service.rest.HttpService;
import math.service.Fibonacci;

public class MathService implements Service {

    static final Logger LOG = LoggerFactory.getLogger(MathService.class);
    private final HttpService httpService;

    public MathService() throws Exception {
        this.httpService = new DefaultHttpService(this);;
    }

    @Override
    public void init() throws Exception {
        LOG.info("Initializing math service...");
        httpService.init();
    }

    @Override
    public void start() throws Exception {
        LOG.info("Starting math service...");
        httpService.start();
    }

    @Override
    public void stop() throws Exception {
        LOG.info("Stopping math service...");
        httpService.stop();
    }
    
    /* 
     * calculate the fibonacci sequence
     * 
     */
    public String caculateFibonacci(long number) {
    	String ret = Fibonacci.caculateFibonacci(number);
    	return ret;
    }
}
