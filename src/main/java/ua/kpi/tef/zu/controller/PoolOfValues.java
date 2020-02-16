package ua.kpi.tef.zu.controller;

/**
 * Created by Anton Domin on 2020-02-16
 */

public class PoolOfValues {
	private String[] availableValues;

	@SuppressWarnings("rawtypes")
	public PoolOfValues(TourProperties property, Enum[] availableValues) {
		this.availableValues = new String[availableValues.length];

		for (int i = 0; i < availableValues.length; i++) {
			this.availableValues[i] = availableValues[i].toString();
		}
	}

	public int size() {
		return availableValues.length;
	}

	public String get(int index) {
		return availableValues[index];
	}

	public String[] values() {
		return availableValues;
	}
}
