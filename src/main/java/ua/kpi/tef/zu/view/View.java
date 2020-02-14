package ua.kpi.tef.zu.view;

import ua.kpi.tef.zu.SupportedLanguages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class View {
	private SupportedLanguages currentLanguage;
	private ResourceBundle bundle;
	private final String BUNDLE_NAME = "travel17";

	public static final String USER_WELCOME = "user.welcome";
	public static final String WRONG_INPUT = "input.wrong";

	private DateFormat dateFormat;

	public View() {
		currentLanguage = SupportedLanguages.ENGLISH;
		setLocalization(currentLanguage);
	}

	public void setLocalization(SupportedLanguages lang) {
		currentLanguage = lang;
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, SupportedLanguages.determineLocale(lang));
		dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	}

	public SupportedLanguages getCurrentLanguage() { return currentLanguage; }

	public void printAndKeepLine(String message) { System.out.print(getLocalizedText(message) + " "); }

	public void printAndEndLine(String message) { System.out.println(getLocalizedText(message)); }

	public String getLocalizedText(String property) {
		return bundle.keySet().contains(property) ? bundle.getString(property) : property;
	}

	public String getLocalizedDate(Date date) {
		return dateFormat.format(date);
	}
}