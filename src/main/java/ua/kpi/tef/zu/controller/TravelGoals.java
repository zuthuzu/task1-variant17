package ua.kpi.tef.zu.controller;

/**
 * Created by Anton Domin on 2020-02-15
 */

public enum TravelGoals {
	RELAX("goal.relax"),
	EXCURSIONS("goal.excursions"),
	HEALING("goal.healing"),
	SHOPPING("goal.shopping"),
	CRUISE("goal.cruise");

	private String value;

	TravelGoals(String value) {
		this.value = value;
	}

	@Override
	public String toString() { return value; }
}
