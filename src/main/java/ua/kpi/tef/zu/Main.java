package ua.kpi.tef.zu;

import ua.kpi.tef.zu.controller.Controller;
import ua.kpi.tef.zu.model.Model;
import ua.kpi.tef.zu.view.View;

/**
 * Created by Anton Domin on 2020-02-14
 */

public class Main
{
    public static void main(String[] args) {
        // Initialization
        Controller controller = new Controller(new Model(), new View());
        // Run
        controller.startTravelOffers();
    }
}