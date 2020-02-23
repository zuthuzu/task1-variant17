package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.SupportedLanguages;
import ua.kpi.tef.zu.model.Model;
import ua.kpi.tef.zu.model.tourfactory.AbstractTour;
import ua.kpi.tef.zu.view.View;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Anton Domin on 2020-02-14
 */

public class Controller {

	@SuppressWarnings("SpellCheckingInspection")
	private final static String INPUT_TOKENS = "1234567890ABCDE";
	private final static int HOW_MANY_TOP_TOURS_TO_DISPLAY = 10; //this number must not exceed the length of inputTokens
	private final static char CURRENCY = '\u20AC'; //euro

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

		fillValuePools();

		view.printAndEndLine(View.USER_WELCOME);

		coreInputLoop(sc);

		view.printAndEndLine(View.USER_BYE);
	}

	private void fillValuePools() {
		model.loadActiveTours();

		for (TourProperties property : TourProperties.values()) {
			property.getPool().setAvailableValues(model.getAvailableValues(property));
			property.getPool().sortInChosenLanguage(view);
		}
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
		int chosenMenuItem;

		do {
			showMainMenu();

			chosenMenuItem = numericalMenuChoice(groupChoiceLoop(sc,
					createChoiceRegex(TourProperties.values().length + 3, false, true)));

		} while (processMainMenuAndContinue(sc, chosenMenuItem));
	}

	private boolean processMainMenuAndContinue(Scanner sc, int chosenMenuItem) {
		boolean userWishesToContinue = true;
		int filtersInMenu = TourProperties.values().length;

		//after filters Main Menu currently has three additional options:
		//show tours ascending at the index chosenMenuItem == filtersInMenu,
		//show tours descending at the index chosenMenuItem == filtersInMenu + 1,
		//exit program at the index chosenMenuItem == filtersInMenu + 2 or chosenMenuItem == -1

		if (chosenMenuItem < 0 || chosenMenuItem == filtersInMenu + 2) {
			userWishesToContinue = false;
		} else if (chosenMenuItem == filtersInMenu) {
			selectTopTour(sc, true);
		} else if (chosenMenuItem == filtersInMenu + 1) {
			selectTopTour(sc, false);
		} else {
			filterProperty(sc, TourProperties.values()[chosenMenuItem]);
		}
		return userWishesToContinue;
	}

	@SuppressWarnings("UnusedAssignment")
	private void showMainMenu() {
		view.printAndKeepLine(View.USER_TOURS_AVAILABLE);
		view.printAndEndLine(Integer.toString(model.conformsToFilter()));

		view.printAndEndLine(View.INPUT_MAIN_MENU);

		int menuItemNumber = 0;

		for (TourProperties property : TourProperties.values()) {
			view.printAndKeepLine(INPUT_TOKENS.charAt(menuItemNumber++) + ":");
			view.printAndEndLine((property.toString()));
		}

		view.printAndKeepLine(INPUT_TOKENS.charAt(menuItemNumber++) + ":");
		view.printAndEndLine(View.INPUT_VIEW_ASCENDING);

		view.printAndKeepLine(INPUT_TOKENS.charAt(menuItemNumber++) + ":");
		view.printAndEndLine(View.INPUT_VIEW_DESCENDING);

		view.printAndKeepLine(INPUT_TOKENS.charAt(menuItemNumber++) + ":");
		view.printAndEndLine(View.INPUT_EXIT);
	}

	private void filterProperty(Scanner sc, TourProperties property) {
		showPropertyMenu(property);

		String userInput = groupChoiceLoop(sc, createChoiceRegex(property.getPool().size(), true, true));

		model.applyFilter(property, convertInputIntoValues(userInput, property));
	}

	private void showPropertyMenu(TourProperties property) {
		view.printAndEndLine(property.toString());

		for (int i = 0; i < property.getPool().size(); i++) {
			view.printAndKeepLine(INPUT_TOKENS.charAt(i) + ":");
			view.printAndEndLine(view.cullLeadingZeroes(property.getPool().get(i)));
		}
		view.printAndEndLine(View.INPUT_DROP_FILTER);
		view.printAndEndLine(View.INPUT_RETURN);
	}

	private void selectTopTour(Scanner sc, boolean ascendingOrder) {
		AbstractTour[] topTours = model.getTopTours(ascendingOrder, HOW_MANY_TOP_TOURS_TO_DISPLAY);
		int chosenMenuItem;

		do {
			showTourMenu(topTours);

			chosenMenuItem = numericalMenuChoice(groupChoiceLoop(sc,
					createChoiceRegex(topTours.length, false, true)));

		} while (processTourAndContinue(sc, topTours, chosenMenuItem));
	}

	private void showTourMenu(AbstractTour[] topTours) {
		view.printAndEndLine(View.USER_TOURS_HEADER);

		int menuItemNumber = 0;

		for (AbstractTour topTour : topTours) {
			view.printAndKeepLine(INPUT_TOKENS.charAt(menuItemNumber++) + ":");
			view.printAndEndLine(topTour.getLocalizedBrief(view));
		}

		view.printAndEndLine(View.INPUT_RETURN);
	}

	private boolean processTourAndContinue(Scanner sc, AbstractTour[] tourMenu, int menuItem) {
		if (menuItem >= 0 && menuItem < tourMenu.length) {
			showTourDetails(sc, tourMenu[menuItem]);
			return true;
		} else {
			return false;
		}
	}

	private void showTourDetails(Scanner sc, AbstractTour tour) {
		view.printAndEndLine(tour.getLocalizedDetails(view, CURRENCY));

		view.printAndEndLine(View.USER_TOURS_DETAILS);
		view.printAndEndLine(View.INPUT_RETURN);

		numericalMenuChoice(groupChoiceLoop(sc, createChoiceRegex(1, false, true)));
	}

	private String[] convertInputIntoValues(String userInput, TourProperties property) {
		ArrayList<String> chosenValues = new ArrayList<>();

		for (char value : userInput.toCharArray()) {
			if (value == '-') {
				break; //according to regex there shouldn't be any other characters in userInput, but just to be sure
			} else if (value == '.') {
				return null;
			} else {
				chosenValues.add(property.getPool().get(numericalMenuChoice(value)));
			}
		}

		return chosenValues.toArray(new String[0]);
	}

	/**
	 * Explicit conversion of user selection into the menu index.
	 * <br><br>
	 * When user selects items off the menu, such as 'drop filters' or 'return to previous', method returns -1.
	 *
	 * @param value Character received taken the user input
	 * @return Number of menu item that this character represents
	 */
	private int numericalMenuChoice(char value) { return INPUT_TOKENS.indexOf(value); }

	private int numericalMenuChoice(String value) { return INPUT_TOKENS.indexOf(value.charAt(0)); }

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
		return "[" + INPUT_TOKENS.substring(0, numberOfChoices) + "]"
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
