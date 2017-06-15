package math.util.rest;

import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;

public interface EmbeddedRestServer {

    /**
     * Start the server.
     * 
     * @throws Exception
     */
    public void start() throws Exception;

    /**
     * Stop the server.
     * 
     * @throws Exception
     */
    public void stop() throws Exception;

    /**
     * Waits for the server thread to die.
     * 
     * @throws Exception
     */
    public void join() throws Exception;

    /**
     * Add a package containing JAX-RS REST services. This must be done before
     * the server is started.
     * 
     * @param resourcePackageName the package name containing JAX-RS rest service classes
     */
    public void addRestService(String resourcePackageName);

    /**
     * Add a package containing JAX-RS REST services. This must be done before
     * the server is started.
     * 
     * @param resourcePackage the package containing JAX-RS rest service classes
     */
    public void addRestService(Package resourcePackage);

    /**
     * Add a class containing a JAX-RS REST services. This must be done before
     * the server is started.
     * 
     * @param resourceClass the Class containing the JAX-RS rest service
     */
    public void addRestService(Class<?> resourceClass);

    /**
     * Add a class containing a JAX-RS Providers. This must be done before
     * the server is started.
     * 
     * @param providerClass the Class containing JAX-RS provider services
     */
    public void addProvider(Class<?> providerClass);

    /**
     * Add a Servlet Class to the server with init parameters.
     * 
     * @param servletClass
     * @param pathSpec
     * @param initParams
     */
    public void addServlet(Class<? extends Servlet> servletClass, String pathSpec, Map<String, String> initParams);

    /**
     * Add a Servlet Class to the server.
     * 
     * @param servletClass
     * @param pathSpec
     */
    public void addServlet(Class<? extends Servlet> servletClass, String pathSpec);

    /**
     * Add a ServletContextListener to the server.
     * 
     * @param listener the ServletContextListener to add
     */
    public void addServletContextListener(ServletContextListener listener);

    /**
     * Add an attribute to the server context.
     * 
     * @param name
     * @param value
     */
    public void setAttribute(String name, Object value);

    /**
     * Add an init parameter to the server context.
     * 
     * @param name
     * @param value
     */
    public void setInitParameter(String name, String value);
}
