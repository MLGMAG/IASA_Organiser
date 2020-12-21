package ua.kpi.iasa.IASA_Organiser;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {
    private static final int PORT = 8081;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        if (args.length != 0) {
            tomcat.setPort(Integer.parseInt(args[0]));
        } else {
            tomcat.setPort(PORT);
        }
        tomcat.getConnector();
        tomcat.addWebapp("", new File(".").getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }

}
