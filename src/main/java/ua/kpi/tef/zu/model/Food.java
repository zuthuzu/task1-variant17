package ua.kpi.tef.zu.model;

/**
 * Created by Anton Domin on 2020-02-15
 */

public enum Food {
	NONE ("food.none"),
	BREAKFAST ("food.breakfast"),
	ALLINCLUSIVE ("food.allinclusive");

	private String value;

	Food(String value) {
		this.value = value;
	}

	@Override
	public String toString() { return value; }
}
