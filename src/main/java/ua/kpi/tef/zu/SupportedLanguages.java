package ua.kpi.tef.zu;

import java.util.Locale;

/**
 * Created by Anton Domin on 2020-02-11
 */

public enum SupportedLanguages {
	ENGLISH ("To select English, enter '1'."),
	RUSSIAN ("Чтобы выбрать русский язык, введите '2'.");

	private String userPrompt;

	SupportedLanguages(String userPrompt) { this.userPrompt = userPrompt; }

	public String getUserPrompt() {
		return userPrompt;
	}

	@SuppressWarnings("DuplicateBranchesInSwitch")
	public static Locale determineLocale(SupportedLanguages lang) {
		switch (lang) {
			case ENGLISH:
				return new Locale("en", "US");
			case RUSSIAN:
				return new Locale("ru", "RU");
			default:
				return new Locale("en", "US");
		}
	}
}
