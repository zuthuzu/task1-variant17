package ua.kpi.tef.zu;

import java.util.Locale;

public enum SupportedLanguages {
	ENGLISH ("To select English, enter '1'."),
	RUSSIAN ("Чтобы выбрать русский язык, введите '2'.");

	public static final String INPUT_OPTIONS = "[1-2]";

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

	@SuppressWarnings("DuplicateBranchesInSwitch")
	public static SupportedLanguages getSupportedLanguage(int value) {
		switch (value) {
			case 1:
				return ENGLISH;
			case 2:
				return RUSSIAN;
			default:
				return ENGLISH;
		}
	}
}
