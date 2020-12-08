package ua.kpi.iasa.IASA_Organiser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.kpi.iasa.IASA_Organiser.controller.Controller;
import ua.kpi.iasa.IASA_Organiser.view.View;

@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "ua.kpi.iasa.IASA_Organiser.controller",
        "ua.kpi.iasa.IASA_Organiser.view",
        "ua.kpi.iasa.IASA_Organiser.config"
})
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Controller controller = context.getBean("controller", Controller.class);
        View view = context.getBean("consoleManager", View.class);
        controller.init(view);
    }
}
