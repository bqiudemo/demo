package math.util.base;

/*
 * Service APIs
 */
public interface Service {
	
    void init() throws Exception;

    void start() throws Exception;

    void stop() throws Exception;
}
