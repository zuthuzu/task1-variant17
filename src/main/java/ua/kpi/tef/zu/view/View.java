package ua.kpi.tef.zu.view;

import ua.kpi.tef.zu.SupportedLanguages;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Anton Domin on 2020-02-14
 */

@SuppressWarnings("SpellCheckingInspection")
public class View {
	private SupportedLanguages currentLanguage;
	private ResourceBundle bundle;
	private final String BUNDLE_NAME = "travel17";

	public static final String USER_WELCOME = "user.welcome";
	public static final String USER_BYE = "user.bye";
	public static final String USER_TOURS_AVAILABLE = "user.tours.available";
	public static final String USER_TOURS_HEADER = "user.tours.header";
	public static final String USER_TOURS_DETAILS = "user.tours.details";
	public static final String USER_TOURS_SPECIALS_DEFAULT = "user.tours.specials.default";
	public static final String USER_TOURS_SPECIALS_CRUISE = "user.tours.specials.cruise";
	public static final String USER_TOURS_SPECIALS_SHOPPING = "user.tours.specials.shopping";
	public static final String PROPERTY_PRICE = "property.price";
	public static final String WRONG_INPUT = "input.wrong";
	public static final String INPUT_MAIN_MENU = "input.mainmenu";
	public static final String INPUT_DROP_FILTER = "input.dropfilter";
	public static final String INPUT_RETURN = "input.return";
	public static final String INPUT_VIEW_ASCENDING = "input.view.ascending";
	public static final String INPUT_VIEW_DESCENDING = "input.view.descending";
	public static final String INPUT_EXIT = "input.exit";
	public static final String SEPARATOR = ", ";
	public static final String TERMINATOR = ".";

	public View() {
		currentLanguage = SupportedLanguages.ENGLISH;
		setLocalization(currentLanguage);
	}

	public void setLocalization(SupportedLanguages lang) {
		currentLanguage = lang;
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, SupportedLanguages.determineLocale(lang));
	}

	public void printAndKeepLine(String message) { System.out.print(getLocalizedText(message) + " "); }

	public void printAndEndLine(String message) { System.out.println(getLocalizedText(message)); }

	public String getLocalizedText(String token) {
		return bundle.keySet().contains(token) ? bundle.getString(token) : token;
	}

	public String cullLeadingZeroes(String value) {
		int zeroes = 0;

		while (zeroes < value.length() && value.charAt(zeroes) == '0')
			zeroes++;

		return value.substring(zeroes);
	}

	public void sortInChosenLanguage(ArrayList<String> values) {
		if (values.isEmpty()) {
			return;
		}

		String temporaryPointer;
		String localViewPrevious;
		String localViewCurrent;
		boolean hasSwapped;

		do {
			hasSwapped = false;
			for (int i = 1; i < values.size(); i++) {
				localViewPrevious = getLocalizedText(values.get(i-1));
				localViewCurrent = getLocalizedText(values.get(i));

				if (localViewPrevious.compareTo(localViewCurrent) > 0) {
					temporaryPointer = values.get(i - 1);
					values.set(i - 1, values.get(i));
					values.set(i, temporaryPointer);
					hasSwapped = true;
				}
			}
		} while (hasSwapped);
	}
}
