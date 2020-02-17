package ua.kpi.tef.zu.model.tourfactory;

import ua.kpi.tef.zu.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Anton Domin on 2020-02-15
 */

public class ShoppingTour extends AbstractTour{
	private ArrayList<String> specials = new ArrayList<>();

	public ShoppingTour(String goal) {
		setGoal(goal);
	}

	@Override
	public void setSpecials(String[] tourSpecials) {
		Collections.addAll(specials, tourSpecials);
	}

	@Override
	public String getLocalizedSpecials(View view) {
		view.sortInChosenLanguage(specials);

		StringBuilder result = new StringBuilder();

		result.append(view.getLocalizedText(View.USER_TOURS_SPECIALS_SHOPPING)).append(": ");

		for (int i = 0; i < specials.size(); i++) {
			result.append(view.getLocalizedText(specials.get(i)));
			result.append((i == (specials.size() - 1)) ? View.TERMINATOR : View.SEPARATOR);
		}

		return result.toString();
	}
}