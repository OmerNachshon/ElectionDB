package id209006535_id315671552;

import java.util.InputMismatchException;

import model.GraphicUI;
import model.MassageAble;
import model.NotValidIdExspetion;
import model.TooYoungExsepction;


public class MainProgram {

	public static void main(String[] args) throws InputMismatchException, NotValidIdExspetion, TooYoungExsepction {

		MassageAble ui = new GraphicUI();

		int choice = 0;
		do {
			try {
				choice = Integer.parseInt(ui.getString(
						"Please choose which view you want \nFor:\n1)Console view press (1).\n2)UI view press (2).\n3)Java-FX (3).\n4)Exit press (4)."));
				switch (choice) {
				case 1:
					ui.showMassage("You Choose console view");
					Program.main(args);
					break;

				case 2:
					ui.showMassage("You Choose UI view");
					ProgramUI.main(args);
					break;
				case 3:
					ui.showMassage("You Choose Java-FX view");
					ProgramMVC.main(args);
					break;
				case 4:
					ui.showMassage("You Choose exit");
					break;
				}

			} catch (NumberFormatException e) {
				ui.showMassage("Wrong input please try again");
			}
		} while (choice != 4);

	}

}