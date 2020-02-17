package ua.kpi.tef.zu.model;

import ua.kpi.tef.zu.controller.TourProperties;
import ua.kpi.tef.zu.model.datapool.RandomTourGenerator;
import ua.kpi.tef.zu.model.tourfactory.AbstractTour;

import java.util.*;

/**
 * Created by Anton Domin on 2020-02-14
 */

public class Model {
	private ArrayList<AbstractTour> activeTours = new ArrayList<>();
	private ArrayList<AbstractTour> filteredTours = new ArrayList<>();

	private HashSet<String> filterCountry = new HashSet<>();
	private HashSet<String> filterTransport = new HashSet<>();
	private HashSet<String> filterGoal = new HashSet<>();
	private HashSet<String> filterFood = new HashSet<>();
	private HashSet<String> filterDays = new HashSet<>();

	public Model() {
	}

	/**
	 * In full implementation this method is supposed to load active tours from some external source.
	 * <br><br>
	 * At the current stage it generates a bunch of tours randomly, for demonstration purposes.
	 */
	public void loadActiveTours() {
		Collections.addAll(activeTours, RandomTourGenerator.generateTours());
		applyFilters();
	}

	public void addNewTour(AbstractTour tour) {
		activeTours.add(tour);
	}

	public String[] getAvailableValues(TourProperties property) {
		TreeSet<String> valuePool = new TreeSet<>();

		for (AbstractTour tour : activeTours) {
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

		for (AbstractTour currentTour : activeTours) {
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

	public AbstractTour[] getTopTours(boolean ascendingOrder, int howMany) {
		sortFilteredTours();

		int howManyInFact = Math.min(howMany, filteredTours.size());

		if (ascendingOrder) {
			return filteredTours.subList(0, howManyInFact).toArray(new AbstractTour[0]);
		} else {
			return filteredTours.subList(filteredTours.size() - howManyInFact, filteredTours.size()).toArray(new AbstractTour[0]);
		}
	}

	private void sortFilteredTours() {
		if (filteredTours.isEmpty()) {
			return;
		}

		AbstractTour temporaryPointer;
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
}
