package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.SupportedLanguages;
import ua.kpi.tef.zu.model.Model;
import ua.kpi.tef.zu.view.View;

import java.util.Scanner;

/**
 * Created by Anton Domin on 2020-02-14
 */

public class Controller {

	// Constructor
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void startTravelOffers() {
		Scanner sc = new Scanner(System.in);

		selectLanguage(sc);

		view.printAndEndLine(View.USER_WELCOME);

	}

	/**
	 * Prompts the scanner for the user choice about the preferred language.<br>
	 * <br>
	 * Available languages are pulled automatically from SupportedLanguages enum in main package.<br>
	 *
	 * @param sc Scanner from which the choice is obtained
	 */
	public void selectLanguage(Scanner sc) {
		SupportedLanguages selectedLanguage;

		for (SupportedLanguages option : SupportedLanguages.values()) {
			view.printAndEndLine(option.getUserPrompt());
		}

		selectedLanguage = SupportedLanguages.getSupportedLanguage(groupSelectionLoop(sc, SupportedLanguages.INPUT_OPTIONS));

		view.setLocalization(selectedLanguage);
	}

	private int groupSelectionLoop(Scanner sc, String regexAllowed) {
		int inputValue;

		inputValue = inputIntValueWithScanner(sc);

		while (!Integer.toString(inputValue).matches(regexAllowed)) {
			view.printAndEndLine(View.WRONG_INPUT);
			inputValue = inputIntValueWithScanner(sc);
		}

		sc.nextLine(); //to avoid ghost input further on

		return inputValue;
	}

	private int inputIntValueWithScanner(Scanner sc) {
		while (!sc.hasNextInt()) {
			view.printAndEndLine(View.WRONG_INPUT);
			sc.next();
		}
		return sc.nextInt();
	}

}
