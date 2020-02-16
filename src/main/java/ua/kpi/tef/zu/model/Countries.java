package ua.kpi.tef.zu.model;

/**
 * Created by Anton Domin on 2020-02-15
 */

public enum Countries {
	RUSSIA ("country.russia"),
	POLAND ("country.poland"),
	BULGARIA ("country.bulgaria"),
	CHERNOGORIA ("country.chernogoria"),
	GREECE ("country.greece"),
	ITALY ("country.italy"),
	FRANCE ("country.france"),
	GERMANY ("country.germany");

	private String value;

	Countries(String value) {
		this.value = value;
	}

	@Override
	public String toString() { return value; }
}
