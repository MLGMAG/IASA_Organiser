package ua.kpi.iasa.IASA_Organiser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ua.kpi.iasa.IASA_Organiser.controller.Controller;
import ua.kpi.iasa.IASA_Organiser.view.View;

@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.controller", "ua.kpi.iasa.IASA_Organiser.view"})
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Controller controller = (Controller) context.getBean("controller");
        View view = (View) context.getBean("consoleManager");
        controller.init(view);
    }
}
