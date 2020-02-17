package ua.kpi.tef.zu.model.tourfactory;

import ua.kpi.tef.zu.view.View;

/**
 * Created by Anton Domin on 2020-02-15
 */

public class Tour extends AbstractTour{

	public Tour(String goal) {
		setGoal(goal);
	}

	@Override
	public void setSpecials(String[] tourSpecials) {

	}

	@Override
	public String getLocalizedSpecials(View view) {
		return view.getLocalizedText(View.USER_TOURS_SPECIALS_DEFAULT);
	}
}