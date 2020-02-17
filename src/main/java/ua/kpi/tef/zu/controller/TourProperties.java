package ua.kpi.tef.zu.controller;

/**
 * Created by Anton Domin on 2020-02-16
 * <br><br>
 * Currently PoolOfValues for each property gets filled based on the corresponding Enum.
 * <br><br>
 * In full implementation those pools won't be known in advance, they'll have to be determined via query to the model.
 */

public enum TourProperties implements RequestingPool {
	COUNTRIES("property.country", new PoolOfValues()) {
		@Override
		public PoolOfValues getPool() {
			return COUNTRIES.poolOfValues;
		}
	},
	TRANSPORT("property.transport", new PoolOfValues()) {
		@Override
		public PoolOfValues getPool() {
			return TRANSPORT.poolOfValues;
		}
	},
	TRAVEL_GOALS("property.goal", new PoolOfValues()) {
		@Override
		public PoolOfValues getPool() {
			return TRAVEL_GOALS.poolOfValues;
		}
	},
	FOOD("property.food", new PoolOfValues()) {
		@Override
		public PoolOfValues getPool() {
			return FOOD.poolOfValues;
		}
	},
	DAYS("property.days", new PoolOfValues()) {
		@Override
		public PoolOfValues getPool() {
			return DAYS.poolOfValues;
		}
	};

	private String value;
	private PoolOfValues poolOfValues;

	TourProperties(String value, PoolOfValues poolOfValues) {
		this.value = value;
		this.poolOfValues = poolOfValues;
	}

	@Override
	public String toString() { return value; }
}
