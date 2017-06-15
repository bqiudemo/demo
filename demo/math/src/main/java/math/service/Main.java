package math.service;

import ch.qos.logback.classic.Level;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import math.util.base.Service;

public class Main implements Daemon, Service {
    
    static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private MathService math;
    public static void setShutdownHook(final Service service) {
        // EXIT Task
        Runnable exitTask = new Runnable() {
            @Override
            public void run() {
                try {
                    service.stop();
                } catch (Exception e) {
                    LOG.error("", e);
                }
            }
        };

        // shutdown hook.
        Runtime.getRuntime().addShutdownHook(new Thread(exitTask, "SHUTDOWN-HOOK"));
    }

    public static void main(String[] args) throws Exception {
        
        try {
            Main main = new Main();
            setShutdownHook(main);
            main.init();
            main.start();
            
        } catch (Exception e) {
            LOG.error("Exception occured during execution", e);
            throw e;
        }
    }

    @Override
    public void init() throws Exception {

        LOG.info("Initializing math service");
        math = new MathService();

        // setLogLevel(options.getLogLevel());
        math.init();
    }

    @Override
    public void init(DaemonContext context) throws DaemonInitException, Exception {
        init();
    }

    @Override
    public void start() throws Exception {
        math.start();
    }

    @Override
    public void stop() throws Exception {
        try {
            math.stop();
        } catch (Exception e) {
            LOG.info("Error ocuured during shutdown", e);
        }
    }

    @Override
    public void destroy() {
    	try {
			stop();
		} catch (Exception e) {
			LOG.info("Error ocuured during shutdown", e);
		}
    }
    
    private void setLogLevel(Level newLevel) {
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(newLevel);
    }
}
