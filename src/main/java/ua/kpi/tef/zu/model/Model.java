package ua.kpi.tef.zu.model;

import ua.kpi.tef.zu.controller.TourProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by Anton Domin on 2020-02-14
 */

public class Model {
	private char currency = '\u20AC'; //euro

	private ArrayList<Tour> activeTours = new ArrayList<>();
	private ArrayList<Tour> filteredTours = new ArrayList<>();

	private HashSet<String> filterCountry = new HashSet<>();
	private HashSet<String> filterTransport = new HashSet<>();
	private HashSet<String> filterGoal = new HashSet<>();
	private HashSet<String> filterFood = new HashSet<>();
	private HashSet<String> filterDays = new HashSet<>();

	public Model() {
		loadActiveTours();
	}

	/**
	 * In full implementation this method is supposed to load active tours from some external source.
	 * <br><br>
	 * At the current stage it generates a bunch of tours randomly, for demonstration purposes.
	 */
	private void loadActiveTours() {
		generateRandomTours();
		applyFilters();
	}

	private void generateRandomTours() {
		int totalTourAmount = 300;
		int randomPropertyValue;
		int priceFloor = 200;
		int priceCeiling = 15000;

		for (int i = 0; i < totalTourAmount; i++) {
			Tour sampleTour = new Tour();

			for (TourProperties property : TourProperties.values()) {
				String[] totalPool = getTotalPropertyPool(property);
				randomPropertyValue = (int) (Math.random() * totalPool.length);
				sampleTour.setProperty(property, totalPool[randomPropertyValue]);
			}

			randomPropertyValue = (int) (Math.random() * (priceCeiling - priceFloor) + priceFloor);
			sampleTour.setPrice(randomPropertyValue);

			activeTours.add(sampleTour);
		}
	}

	/**
	 * A temporary demonstration method, complementary to random generator above.
	 * <br><br>
	 * Returns all possible values for a given property, reading them from the corresponding enum, or just guessing.
	 *
	 * @param property Property which we need to analyze
	 * @return Array of its' allowed values.
	 */
	private String[] getTotalPropertyPool(TourProperties property) {
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

	public String[] getAvailableValues(TourProperties property) {
		TreeSet<String> valuePool = new TreeSet<>();

		for (Tour tour : activeTours) {
			valuePool.add(tour.getProperty(property));
		}

		return valuePool.toArray(new String[0]);
	}

	public void applyFilter(TourProperties property, String[] values) {
		if (values == null) {
			return;
		}

		switch (property) {
			case COUNTRIES:
				setFilterCountry(values);
				break;
			case TRANSPORT:
				setFilterTransport(values);
				break;
			case TRAVEL_GOALS:
				setFilterGoal(values);
				break;
			case FOOD:
				setFilterFood(values);
				break;
			case DAYS:
				setFilterDays(values);
				break;
		}

		applyFilters();
	}

	private void setFilterCountry(String[] values) {
		filterCountry.clear();
		filterCountry.addAll(Arrays.asList(values));
	}

	private void setFilterTransport(String[] values) {
		filterTransport.clear();
		filterTransport.addAll(Arrays.asList(values));
	}

	private void setFilterGoal(String[] values) {
		filterGoal.clear();
		filterGoal.addAll(Arrays.asList(values));
	}

	private void setFilterFood(String[] values) {
		filterFood.clear();
		filterFood.addAll(Arrays.asList(values));
	}

	private void setFilterDays(String[] values) {
		filterDays.clear();
		filterDays.addAll(Arrays.asList(values));
	}

	private void applyFilters() {
		filteredTours.clear();

		for (Tour currentTour : activeTours) {
			if ((filterCountry.size() == 0 || filterCountry.contains(currentTour.getCountry())) &&
					(filterTransport.size() == 0 || filterTransport.contains(currentTour.getTransport())) &&
					(filterGoal.size() == 0 || filterGoal.contains(currentTour.getGoal())) &&
					(filterFood.size() == 0 || filterFood.contains(currentTour.getFood())) &&
					(filterDays.size() == 0 || filterDays.contains(currentTour.getDays()))) {
				filteredTours.add(currentTour);
			}
		}
	}

	public int conformsToFilter() {
		return filteredTours.size();
	}

	public Tour[] getTopTours(boolean ascendingOrder, int howMany) {
		sortFilteredTours();

		int howManyInFact = Math.min(howMany, filteredTours.size());

		if (ascendingOrder) {
			return filteredTours.subList(0, howManyInFact).toArray(new Tour[0]);
		} else {
			return filteredTours.subList(filteredTours.size() - howManyInFact, filteredTours.size()).toArray(new Tour[0]);
		}
	}

	private void sortFilteredTours() {
		if (filteredTours.isEmpty()) {
			return;
		}

		Tour temporaryPointer;
		boolean hasSwapped;

		do {
			hasSwapped = false;
			for (int i = 1; i < filteredTours.size(); i++) {
				if (filteredTours.get(i - 1).getPrice() > filteredTours.get(i).getPrice()) {
					temporaryPointer = filteredTours.get(i - 1);
					filteredTours.set(i - 1, filteredTours.get(i));
					filteredTours.set(i, temporaryPointer);
					hasSwapped = true;
				}
			}
		} while (hasSwapped);
	}

	public char getCurrency() { return currency; }

}
