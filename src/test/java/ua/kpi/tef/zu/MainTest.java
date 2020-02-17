package ua.kpi.tef.zu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ua.kpi.tef.zu.controller.TourProperties;
import ua.kpi.tef.zu.model.*;
import ua.kpi.tef.zu.model.datapool.Countries;
import ua.kpi.tef.zu.model.datapool.Food;
import ua.kpi.tef.zu.model.datapool.Transport;
import ua.kpi.tef.zu.model.datapool.TravelGoals;
import ua.kpi.tef.zu.model.tourfactory.AbstractTour;
import ua.kpi.tef.zu.model.tourfactory.TourFactory;

/**
 * Unit test for simple App.
 */
public class MainTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testModelFilters()
    {
        Model model = new Model();
        AbstractTour tour = TourFactory.createTourFromFactory(TravelGoals.SHOPPING);

        tour.setCountry(Countries.ITALY.toString());
        tour.setTransport(Transport.PLANE.toString());
        tour.setFood(Food.BREAKFAST.toString());
        tour.setDays("10");

        model.addNewTour(tour);

        model.applyFilter(TourProperties.COUNTRIES, new String[]{Countries.ITALY.toString()});
        model.applyFilter(TourProperties.TRANSPORT, new String[]{Transport.PLANE.toString()});
        model.applyFilter(TourProperties.TRAVEL_GOALS, new String[]{TravelGoals.SHOPPING.toString()});
        model.applyFilter(TourProperties.FOOD, new String[]{Food.BREAKFAST.toString()});
        model.applyFilter(TourProperties.DAYS, new String[]{"10"});

        assertEquals(1, model.conformsToFilter());
    }
}
