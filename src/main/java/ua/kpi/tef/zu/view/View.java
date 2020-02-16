package ua.kpi.tef.zu.view;

import ua.kpi.tef.zu.SupportedLanguages;

import java.text.DateFormat;
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
	public static final String PROPERTY_PRICE = "property.price";
	public static final String WRONG_INPUT = "input.wrong";
	public static final String INPUT_MAIN_MENU = "input.mainmenu";
	public static final String INPUT_DROP_FILTER = "input.dropfilter";
	public static final String INPUT_RETURN = "input.return";
	public static final String INPUT_VIEW_ASCENDING = "input.view.ascending";
	public static final String INPUT_VIEW_DESCENDING = "input.view.descending";
	public static final String INPUT_EXIT = "input.exit";

	public View() {
		currentLanguage = SupportedLanguages.ENGLISH;
		setLocalization(currentLanguage);
	}

	public void setLocalization(SupportedLanguages lang) {
		currentLanguage = lang;
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, SupportedLanguages.determineLocale(lang));
	}

	public SupportedLanguages getCurrentLanguage() { return currentLanguage; }

	public void printAndKeepLine(String message) { System.out.print(getLocalizedText(message) + " "); }

	public void printAndKeepLine(String message, String separator) { System.out.print(getLocalizedText(message) + separator); }

	public void printAndEndLine(String message) { System.out.println(getLocalizedText(message)); }

	public String getLocalizedText(String token) {
		return bundle.keySet().contains(token) ? bundle.getString(token) : token;
	}
}
