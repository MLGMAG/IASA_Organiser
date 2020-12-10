package ua.kpi.iasa.IASA_Organiser.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.config"})
public class MainApplication implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        var context = new AnnotationConfigWebApplicationContext();
        context.register(MainApplication.class);

        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("App", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }

}
