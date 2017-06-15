package math.util.rest.jetty;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import math.util.rest.EmbeddedRestServer;

/**
 * This class implements EmbeddedResetServer by creating an embedded Jetty instance and
 * utilizes Jersey 2.x for JAX-RS support.
 */
public class JettyEmbeddedRestServer implements EmbeddedRestServer {

    private Server server;
    private ServletContextHandler servletContext;
    private ServletHolder jerseyServlet;

    /**
     * Create an instance with the HTTP server on the specified port.
     * 
     * @param port the port to listen for HTTP requests on
     * @param contextPath the root context path for the HTTP server, null = "/"
     * @param apiPath the path for the REST services, null = "/api/*"
     * @throws Exception
     */
    public JettyEmbeddedRestServer(int port, String contextPath, String apiPath) throws Exception {

        server = new Server(port);
        servletContext = new ServletContextHandler();
        servletContext.setContextPath(contextPath != null ? contextPath : "/");
        server.setHandler(servletContext);
        jerseyServlet = servletContext.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, 
                apiPath != null ? apiPath : "/api/*");
        jerseyServlet.setInitOrder(0);
    }

    @Override
    public void start() throws Exception {
        server.start();
    }

    @Override
    public void stop() throws Exception {
        server.stop();
    }

    @Override
    public void join() throws Exception {
        server.join();
    }

    @Override
    public void setAttribute(String name, Object value) {
        servletContext.setAttribute(name, value);
    }

    @Override
    public void setInitParameter(String name, String value) {
        servletContext.setInitParameter(name, value);
    }

    @Override
    public void addRestService(String resourcePackageName) {

        String currentPackages = jerseyServlet.getInitParameter("jersey.config.server.provider.packages");
        StringBuilder packages = new StringBuilder(currentPackages == null ? "" : currentPackages);
        if (packages.length() > 0)
            packages.append(",");

        packages.append(resourcePackageName);

        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", packages.toString());
        jerseyServlet.setInitParameter("jersey.config.server.provider.scanning.recursive", "false");
    }

    @Override
    public void addRestService(Package resourcePackage) {

        String currentPackages = jerseyServlet.getInitParameter("jersey.config.server.provider.packages");
        StringBuilder packages = new StringBuilder(currentPackages == null ? "" : currentPackages);
        if (packages.length() > 0)
            packages.append(",");

        packages.append(resourcePackage.getName());

        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", packages.toString());
        jerseyServlet.setInitParameter("jersey.config.server.provider.scanning.recursive", "false");
    }

    @Override
    public void addRestService(Class<?> resourceClass) {

        String currentClasses = jerseyServlet.getInitParameter("jersey.config.server.provider.classnames");
        StringBuilder classes = new StringBuilder(currentClasses == null ? "" : currentClasses);
        if (classes.length() > 0)
            classes.append(",");

        classes.append(resourceClass.getCanonicalName());
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", classes.toString());
    }

    @Override
    public void addProvider(Class<?> providerClass) {
        addRestService(providerClass);
    }

    @Override
    public void addServlet(Class<? extends Servlet> servletClass, String pathSpec, Map<String, String> initParams) {

        if (initParams == null || initParams.isEmpty()) {
            servletContext.addServlet(servletClass, pathSpec);
        } else {

            ServletHolder servletHolder = new ServletHolder(servletClass);
            for (Entry<String, String> entry : initParams.entrySet())
                servletHolder.setInitParameter(entry.getKey(), entry.getValue());

            servletContext.addServlet(servletHolder, pathSpec);
        }
    }

    @Override
    public void addServlet(Class<? extends Servlet> servletClass, String pathSpec) {
        addServlet(servletClass, pathSpec, null);
    }

    @Override
    public void addServletContextListener(ServletContextListener listener) {
        servletContext.addEventListener(listener);
    }
}
