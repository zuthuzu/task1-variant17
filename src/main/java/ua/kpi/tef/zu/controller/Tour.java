package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.model.Countries;
import ua.kpi.tef.zu.model.Food;
import ua.kpi.tef.zu.model.Transport;
import ua.kpi.tef.zu.model.TravelGoals;

/**
 * Created by Anton Domin on 2020-02-15
 */

public class Tour {
	private Countries country;
	private TravelGoals goal;
	private Transport transport;
	private Food food;
	private int price;

	public Tour(Countries country, TravelGoals goal, Transport transport, Food food, int price) {
		this.country = country;
		this.goal = goal;
		this.transport = transport;
		this.food = food;
		this.price = price;
	}

	public Countries getCountry() { return country; }

	public TravelGoals getGoal() { return goal; }

	public Transport getTransport() { return transport; }

	public Food getFood() { return food; }

	public int getPrice() { return price; }
}
