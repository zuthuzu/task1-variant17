package ua.kpi.tef.zu.model.tourfactory;

import ua.kpi.tef.zu.controller.TourProperties;
import ua.kpi.tef.zu.view.View;

import java.text.DecimalFormat;

/**
 * Created by Anton Domin on 2020-02-17
 */

public abstract class AbstractTour {
	private String country;
	private String goal;
	private String transport;
	private String food;
	private String days;
	private int price;

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
			case DAYS:
				setDays(value);
				break;
		}
	}

	public void setCountry(String country) { this.country = country; }

	public void setGoal(String goal) { this.goal = goal; }

	public void setTransport(String transport) { this.transport = transport; }

	public void setFood(String food) { this.food = food; }

	public void setDays(String days) { this.days = days; }

	public void setPrice(int price) { this.price = price; }

	public abstract void setSpecials(String[] tourSpecials);

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
			case DAYS:
				return getDays();
		}

		return "";
	}

	public String getCountry() { return country; }

	public String getGoal() { return goal; }

	public String getTransport() { return transport; }

	public String getFood() { return food; }

	public String getDays() { return days; }

	public int getPrice() { return price; }

	public String getLocalizedBrief(View view) {
		return view.getLocalizedText(getCountry()) + ", " +
				view.getLocalizedText(getGoal()) + ", " +
				view.getLocalizedText(TourProperties.DAYS.toString()) + ": " +
				view.getLocalizedText(view.cullLeadingZeroes(getDays())) + ", " +
				view.getLocalizedText(TourProperties.TRANSPORT.toString()) + ": " +
				view.getLocalizedText(getTransport()) + ".";
	}

	public String getLocalizedDetails (View view, char currency) {
		DecimalFormat moneyFormat = new DecimalFormat("0.00");

		return view.getLocalizedText(TourProperties.COUNTRIES.toString()) + ": " + view.getLocalizedText(getCountry()) + "\n"
				+ view.getLocalizedText(TourProperties.DAYS.toString()) + ": " + view.cullLeadingZeroes(getDays()) + "\n"
				+ view.getLocalizedText(TourProperties.TRANSPORT.toString()) + ": " + view.getLocalizedText(getTransport()) + "\n"
				+ view.getLocalizedText(TourProperties.FOOD.toString()) + ": " + view.getLocalizedText(getFood()) + "\n"
				+ getLocalizedSpecials(view) + "\n"
				+ view.getLocalizedText(View.PROPERTY_PRICE) + ": " + currency + moneyFormat.format(getPrice());
	}

	public abstract String getLocalizedSpecials(View view);

	@Override
	public String toString() {
		return "Tour{" +
				"country='" + country + '\'' +
				", goal='" + goal + '\'' +
				", transport='" + transport + '\'' +
				", food='" + food + '\'' +
				", days='" + days + '\'' +
				", price=" + price +
				'}';
	}
}
