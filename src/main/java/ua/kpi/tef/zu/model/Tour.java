package ua.kpi.tef.zu.model;

import ua.kpi.tef.zu.controller.TourProperties;

/**
 * Created by Anton Domin on 2020-02-15
 */

public class Tour {
	private String country;
	private String goal;
	private String transport;
	private String food;
	private int price;

	public Tour() {
	}

	public Tour(String country, String goal, String transport, String food, int price) {
		this.country = country;
		this.goal = goal;
		this.transport = transport;
		this.food = food;
		this.price = price;
	}

	public void setProperty(TourProperties property, String value) {
		switch (property) {
			case COUNTRIES:
				setCountry(value);
				break;
			case TRANSPORT:
				setTransport(value);
				break;
			case TRAVEL_GOALS:
				setGoal(value);
				break;
			case FOOD:
				setFood(value);
				break;
		}
	}

	public void setCountry(String country) { this.country = country; }

	public void setGoal(String goal) { this.goal = goal; }

	public void setTransport(String transport) { this.transport = transport; }

	public void setFood(String food) { this.food = food; }

	public void setPrice(int price) { this.price = price; }

	public String getProperty(TourProperties property) {
		switch (property) {
			case COUNTRIES:
				return getCountry();
			case TRANSPORT:
				return getTransport();
			case TRAVEL_GOALS:
				return getGoal();
			case FOOD:
				return getFood();
		}

		return "";
	}

	public String getCountry() { return country; }

	public String getGoal() { return goal; }

	public String getTransport() { return transport; }

	public String getFood() { return food; }

	public int getPrice() { return price; }

	@Override
	public String toString() {
		return "Tour{" +
				"country='" + country + '\'' +
				", goal='" + goal + '\'' +
				", transport='" + transport + '\'' +
				", food='" + food + '\'' +
				", price=" + price +
				'}';
	}
}
