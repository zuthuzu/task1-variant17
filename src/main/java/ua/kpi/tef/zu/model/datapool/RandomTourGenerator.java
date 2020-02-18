package ua.kpi.tef.zu.model.datapool;

import ua.kpi.tef.zu.controller.TourProperties;
import ua.kpi.tef.zu.model.tourfactory.AbstractTour;
import ua.kpi.tef.zu.model.tourfactory.TourFactory;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Anton Domin on 2020-02-18
 * This is a temporary solution for demonstration purposes. Please don't look at the code.
 */

public class RandomTourGenerator {
	public static AbstractTour[] generateTours() {
		ArrayList<AbstractTour> activeTours = new ArrayList<>();

		int totalTourAmount = 300;
		int randomPropertyValue;
		int priceFloor = 2;
		int priceCeiling = 150;

		for (int i = 0; i < totalTourAmount; i++) {
			String[] totalPool = getTotalPropertyPool(TourProperties.TRAVEL_GOALS);
			randomPropertyValue = (int) (Math.random() * totalPool.length);
			AbstractTour sampleTour = TourFactory.createTourFromFactory(TravelGoals.values()[randomPropertyValue]);

			for (TourProperties property : TourProperties.values()) {
				if (property == TourProperties.TRAVEL_GOALS) {
					fillSpecials(sampleTour);
				} else {
					totalPool = getTotalPropertyPool(property);
					randomPropertyValue = (int) (Math.random() * totalPool.length);
					sampleTour.setProperty(property, totalPool[randomPropertyValue]);
				}
			}

			randomPropertyValue = (int) (Math.random() * (priceCeiling - priceFloor) + priceFloor);
			sampleTour.setPrice(randomPropertyValue * 100);

			activeTours.add(sampleTour);
		}

		return activeTours.toArray(new AbstractTour[0]);
	}

	/**
	 * A temporary demonstration method, complementary to random generator above.
	 * <br><br>
	 * Returns all possible values for a given property, reading them from the corresponding enum, or just guessing.
	 *
	 * @param property Property which we need to analyze
	 * @return Array of its' allowed values.
	 */
	private static String[] getTotalPropertyPool(TourProperties property) {
		ArrayList<String> result = new ArrayList<>();

		switch (property) {
			case COUNTRIES:
				for (Countries value : Countries.values()) {
					result.add(value.toString());
				}
				break;
			case TRANSPORT:
				for (Transport value : Transport.values()) {
					result.add(value.toString());
				}
				break;
			case TRAVEL_GOALS:
				for (TravelGoals value : TravelGoals.values()) {
					result.add(value.toString());
				}
				break;
			case FOOD:
				for (Food value : Food.values()) {
					result.add(value.toString());
				}
				break;
			case DAYS:
				for (int i = 1; i <= 12; i++) {
					result.add(String.format("%02d", i));
				}
				break;
		}

		return result.toArray(new String[0]);
	}

	private static void fillSpecials(AbstractTour tour) {
		HashSet<String> result = new HashSet<>();
		String tourGoal = tour.getProperty(TourProperties.TRAVEL_GOALS);
		int numberOfOffers;
		int randomPropertyValue;

		numberOfOffers = (int) (Math.random() * 7 + 1); //1..6 special offers potentially, but they might double up

		for (int i = 0; i < numberOfOffers; i++) {
			if (tourGoal.equals(TravelGoals.SHOPPING.toString())) {
				randomPropertyValue = (int) (Math.random() * Vendors.values().length);
				result.add(Vendors.values()[randomPropertyValue].toString());
			} else if (tourGoal.equals(TravelGoals.CRUISE.toString())) {
				randomPropertyValue = (int) (Math.random() * Countries.values().length);
				result.add(Countries.values()[randomPropertyValue].toString());
			}
		}

		tour.setSpecials(result.toArray(new String[0]));
	}
}
