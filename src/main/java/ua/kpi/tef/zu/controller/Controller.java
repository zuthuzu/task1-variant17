package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.SupportedLanguages;
import ua.kpi.tef.zu.model.Model;
import ua.kpi.tef.zu.view.View;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Anton Domin on 2020-02-14
 */

public class Controller {

	@SuppressWarnings("SpellCheckingInspection")
	private String inputTokens = "1234567890ABCDE";

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

		coreInputLoop(sc);

		view.printAndEndLine(View.USER_BYE);
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

		selectedLanguage = SupportedLanguages.values()[numericalMenuChoice(groupChoiceLoop(sc,
				createChoiceRegex(SupportedLanguages.values().length, false, false)))];

		view.setLocalization(selectedLanguage);
	}

	private void coreInputLoop(Scanner sc) {
		int filterMenuSize = TourProperties.values().length;
		int chosenMenuItem;
		boolean userWishesToExit = false;

		do {
			showMainMenu(filterMenuSize);

			chosenMenuItem = numericalMenuChoice(groupChoiceLoop(sc,
					createChoiceRegex(filterMenuSize + 1, false, true)));

			if (chosenMenuItem >= 0 && chosenMenuItem < filterMenuSize) {
				filterProperty(sc, TourProperties.values()[chosenMenuItem]);
			} else {
				userWishesToExit = true;
			}
		} while (!userWishesToExit);
	}

	private void showMainMenu(int filterMenuSize) {
		view.printAndEndLine(View.INPUT_MAIN_MENU);

		for (int i = 0; i < filterMenuSize; i++) {
			view.printAndKeepLine(inputTokens.charAt(i) + ":");
			view.printAndEndLine((TourProperties.values()[i].toString()));
		}

		view.printAndKeepLine(inputTokens.charAt(filterMenuSize) + ":");
		view.printAndEndLine(View.INPUT_EXIT);
	}

	private void filterProperty(Scanner sc, TourProperties property) {
		int poolSize = property.getPool().size();

		for (int i = 0; i < poolSize; i++) {
			view.printAndKeepLine(inputTokens.charAt(i) + ":");
			view.printAndEndLine(property.getPool().get(i));
		}

		ArrayList<String> chosenValues = new ArrayList<>();

		for (char value : groupChoiceLoop(sc, createChoiceRegex(poolSize, true, true)).toCharArray()) {
			if (value == '-') {
				break; //according to regex there shouldn't be any other characters in userInput, but just to make sure
			} else if (value == '.') {
				return;
			} else {
				chosenValues.add(property.getPool().get(numericalMenuChoice(value)));
			}
		}

		model.applyFilter(property, chosenValues.toArray(new String[0]));
	}

	/**
	 * Explicit conversion of user selection into the menu index.
	 * <br><br>
	 * When user selects items off the menu, such as 'drop filters' or 'return to previous', method returns -1.
	 *
	 * @param value Character received taken the user input
	 * @return Number of menu item that this character represents
	 */
	private int numericalMenuChoice(char value) { return inputTokens.indexOf(value); }

	private int numericalMenuChoice(String value) { return inputTokens.indexOf(value.charAt(0)); }

	/**
	 * Automatically creates a regex for filtering user input in response to menus.
	 * <br><br>
	 * Scales depending on how many items are on the menu.
	 * <br><br>
	 * Optional support for navigational inputs.
	 *
	 * @param numberOfChoices how many items are on the menu
	 * @param multipleChoice  allows '-' for explicit selection of 'none of the above', aka 'drop the filter'
	 * @param allowExit       allows one or several '.' characters for exiting the menu without making any changes
	 * @return Regular expression that will match only inputs with correct group options
	 */
	private String createChoiceRegex(int numberOfChoices, boolean multipleChoice, boolean allowExit) {
		return "[" + inputTokens.substring(0, numberOfChoices) + "]"
				+ (multipleChoice ? "+|-" : "") + (allowExit ? "|\\.+" : "");
	}

	private String groupChoiceLoop(Scanner sc, String regexAllowed) {
		String inputValue;

		inputValue = sc.nextLine();

		while (!inputValue.matches(regexAllowed)) {
			view.printAndEndLine(View.WRONG_INPUT);
			inputValue = sc.nextLine();
		}

		return inputValue;
	}

}
