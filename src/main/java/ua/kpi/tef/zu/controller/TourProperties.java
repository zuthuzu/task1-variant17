package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.model.Countries;
import ua.kpi.tef.zu.model.Food;
import ua.kpi.tef.zu.model.Transport;
import ua.kpi.tef.zu.model.TravelGoals;

/**
 * Created by Anton Domin on 2020-02-16
 * <br><br>
 * Currently PoolOfValues for each property gets filled based on the corresponding Enum.
 * <br><br>
 * In full implementation those pools won't be known in advance, they'll have to be determined via query to the model.
 */

public enum TourProperties implements RequestingPool{
	COUNTRIES ("property.country") {
		@Override
		public PoolOfValues getPool() {
			return new PoolOfValues(COUNTRIES, Countries.values());
		}
	},
	TRANSPORT ("property.transport") {
		@Override
		public PoolOfValues getPool() {
			return new PoolOfValues(TRANSPORT, Transport.values());
		}
	},
	TRAVEL_GOALS ("property.goal") {
		@Override
		public PoolOfValues getPool() {
			return new PoolOfValues(TRAVEL_GOALS, TravelGoals.values());
		}
	},
	FOOD ("property.food") {
		@Override
		public PoolOfValues getPool() {
			return new PoolOfValues(FOOD, Food.values());
		}
	};

	private String value;

	TourProperties(String value) {
		this.value = value;
	}

	@Override
	public String toString() { return value; }
}
