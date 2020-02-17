package ua.kpi.tef.zu.model.tourfactory;

import ua.kpi.tef.zu.model.datapool.TravelGoals;

/**
 * Created by Anton Domin on 2020-02-17
 */

public class TourFactory {
	public static AbstractTour createTourFromFactory(TravelGoals goal) {
		switch (goal) {
			case CRUISE:
				return new CruiseTour(goal.toString());
			case SHOPPING:
				return new ShoppingTour(goal.toString());
			default:
				return new Tour(goal.toString());
		}
	}
}
