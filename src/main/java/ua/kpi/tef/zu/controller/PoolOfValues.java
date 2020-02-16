package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.model.Tour;
import ua.kpi.tef.zu.view.View;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Anton Domin on 2020-02-16
 */

public class PoolOfValues {
	private ArrayList<String> availableValues = new ArrayList<>();

	public void setAvailableValues(String[] values) {
		availableValues.clear();
		Collections.addAll(availableValues, values);
	}

	public void sortInChosenLanguage(View view) {
		if (availableValues.isEmpty()) {
			return;
		}

		String temporaryPointer;
		String localViewPrevious;
		String localViewCurrent;
		boolean hasSwapped;

		do {
			hasSwapped = false;
			for (int i = 1; i < availableValues.size(); i++) {
				localViewPrevious = view.getLocalizedText(availableValues.get(i-1));
				localViewCurrent = view.getLocalizedText(availableValues.get(i));

				if (localViewPrevious.compareTo(localViewCurrent) > 0) {
					temporaryPointer = availableValues.get(i - 1);
					availableValues.set(i - 1, availableValues.get(i));
					availableValues.set(i, temporaryPointer);
					hasSwapped = true;
				}
			}
		} while (hasSwapped);
	}


	public int size() { return availableValues.size(); }

	public String get(int index) { return availableValues.get(index); }

	public String[] values() { return availableValues.toArray(new String[0]); }
}
