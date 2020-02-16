package ua.kpi.tef.zu.model;

import ua.kpi.tef.zu.controller.*;

import java.util.ArrayList;

/**
 * Created by Anton Domin on 2020-02-14
 */

public class Model {
	private ArrayList<Tour> activeTours = new ArrayList<>();

	public Model() {
		loadActiveTours();
	}

	/**
	 * In full implementation this method is supposed to load active tours from some external source.
	 * <br><br>
	 * At the current stage it generates a bunch of tours randomly, for demonstration purposes.
	 */
	private void loadActiveTours() {
		int totalTourAmount = 300;

		for(int i = 0; i < totalTourAmount; i++) {



			//activeTours.add(freshTour);
		}
	}

	public void applyFilter(TourProperties property, String[] values) {

	}

}
