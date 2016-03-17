package medvedev.ilya.example.async.spring;

import medvedev.ilya.example.async.spring.config.ApplicationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

public class Main {
    private static final int PORT = 8080;
    private static final String PATH = "/";

    public static void main(String[] args) throws Exception {
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationConfiguration.class);

        final Servlet servlet = new DispatcherServlet(context);
        final ServletHolder holder = new ServletHolder(servlet);

        final ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(holder, PATH);

        final Server server = new Server(PORT);
        server.setHandler(handler);
        server.start();
    }
}
