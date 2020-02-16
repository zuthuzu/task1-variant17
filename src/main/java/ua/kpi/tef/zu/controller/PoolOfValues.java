package ua.kpi.tef.zu.controller;

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

	public int size() { return availableValues.size(); }

	public String get(int index) { return availableValues.get(index); }

	public String[] values() { return availableValues.toArray(new String[0]); }
}
