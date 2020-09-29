package ua.kpi.iasa.IASA_Organiser;

import org.junit.Assert;
import org.junit.Test;
import ua.kpi.iasa.IASA_Organiser.controller.Controller;
import ua.kpi.iasa.IASA_Organiser.view.ConsoleManager;


public class ControllerTest {

    private Controller controller = new Controller(new ConsoleManager());

    @Test
    public void creationTesting() {
//        boolean res = controller.createNewEvent();
//        Assert.assertTrue(res);
    }
}
