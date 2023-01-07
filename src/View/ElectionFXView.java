package View;

import java.util.InputMismatchException;
import java.util.Vector;

import javax.swing.JOptionPane;

import DataBase.DB_Connection;
import Listeners.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MyDate;
import model.NotValidIdExspetion;
import model.Party;
import model.Citizen;
import model.Contender;
import model.CoronaKalpi;
import model.SickSoldier;
import model.Soldier;
import model.TooYoungExsepction;
import model.CoronaPatient;
import model.Kalpi;
import model.MilitaryCoronaKalpi;
import model.MilitaryKalpi;

public class ElectionFXView implements AbstractElectionView {
	private Vector<ElectionUIListener> allListeners = new Vector<ElectionUIListener>();
	private Vector<RadioButton> allRButtonMenu = new Vector<RadioButton>();

	public ElectionFXView(Stage theStage) throws InputMismatchException, NotValidIdExspetion {
		allListeners = new Vector<ElectionUIListener>();

		theStage.setTitle("Elections in Corona ");
		VBox VBMenu = new VBox();
		Label lblChoise = new Label("Menu:");
		ToggleGroup TGRBMenu = new ToggleGroup();

		// ADD KALPI
		RadioButton RBAddKalpi = new RadioButton("Add kalpi: \nPlease enter kalpi address:");
		allRButtonMenu.add(RBAddKalpi);
		RBAddKalpi.setToggleGroup(TGRBMenu);
		RBAddKalpi.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage StageAddKalpi = new Stage();
				StageAddKalpi.setTitle("Add Kalpi");
				VBox VBAddKalpi = new VBox();
				Label LKalpiAddress = new Label("Add kalpi: \nPlease enter kalpi address:");
				TextField TFKalpiAddress = new TextField();
				TFKalpiAddress.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						String emptyAddress = "";
						if (TFKalpiAddress.getText().isEmpty()) {
							while (emptyAddress.isEmpty()) {
								emptyAddress = JOptionPane.showInputDialog("Please enter again kalpi address:");
							}
						}
						StageAddKalpi.close();
						KalpiType(emptyAddress);
					}
				});

				// SUMBIT
				Button btnSubmit = new Button("Submit");
				btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						TFEmpty(TFKalpiAddress, "Please enter again kalpi address:", StageAddKalpi);
						KalpiType(TextFieldToString(TFKalpiAddress));
						StageAddKalpi.close();
					}
				});

				// EXIT
				Button btnExit = new Button("EXIT");
				btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Stage stage = (Stage) btnExit.getScene().getWindow();
						stage.close();

					}

				});
				Label Instructions = new Label("Instructions:");
				Label notice = new Label(
						"To check if the input is correct-> press Enter\nIf input is deleted --> Invalid input!");
				notice.setTextFill(Color.RED);
				Label notice2 = new Label("When you're done, click Submit");
				notice2.setTextFill(Color.DARKBLUE);

				VBAddKalpi.setSpacing(15);
				VBAddKalpi.setPadding(new Insets(20));
				VBAddKalpi.setAlignment(Pos.CENTER_LEFT);
				VBAddKalpi.getChildren().addAll(LKalpiAddress, TFKalpiAddress, Instructions, notice, notice2, btnSubmit,
						btnExit);

				StageAddKalpi.setScene(new Scene(VBAddKalpi, 300, 330));
				StageAddKalpi.show();
			}
		});

		// ADD CITIZEN
		RadioButton RBAddCitizen = new RadioButton("Add citizen");
		allRButtonMenu.add(RBAddCitizen);
		RBAddCitizen.setToggleGroup(TGRBMenu);
		RBAddCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage StageAddCitizen = new Stage();
				StageAddCitizen.setTitle("Add citizen");
				VBox VBAddCitizen = new VBox();

				Label LaddCitizen = new Label("Add citizen: \nPlease enter the name of the citizen:");
				TextField TFCitiName = new TextField();
				Label LCitiID = new Label("Please enter the id of the citizen:");
				TextField TFCitiID = new TextField();
				TFCitiID.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						try {
							if (TextFieldToIntCount(TFCitiID) != 9) {
								throw new NotValidIdExspetion();
							}
						} catch (NotValidIdExspetion e) {
							TFCitiID.clear();
						} catch (NumberFormatException e) {
							TFCitiID.clear();
						} catch (InputMismatchException e) {
							TFCitiID.clear();
						}
					}
				});

				Label LCitiYear = new Label("Please enter the birth year of the citizen:");
				TextField TFCitiYear = new TextField();
				TFCitiYear.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (TextFieldToIntStage(TFCitiYear, StageAddCitizen) < 1900
								|| TextFieldToIntStage(TFCitiYear, StageAddCitizen) > 2020) {
							TFCitiYear.clear();
						}

					}
				});

				Label LCitiISolated = new Label("Please enter yes/no if isolated :");
				TextField TFCitiIsolated = new TextField();

				TFCitiIsolated.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						citi(TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiYear, StageAddCitizen),
								TextFieldToString(TFCitiIsolated), TextFieldToIntStage(TFCitiID, StageAddCitizen),
								StageAddCitizen);
					}

				});

				// SUMBIT
				Button btnSumbit = new Button("Submit");
				btnSumbit.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {

						// name+id+isolated+year option
						if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) != 9
								&& (!(TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no")))
								&& TFCitiName.getText().isEmpty()) {

							String tempName = GetTFEmpty(TFCitiName);
							int tempID = GetIdCheck(TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							int tempYear = GetYear(TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFCitiIsolated),
									TextFieldToString(TFCitiName), TextFieldToIntCountStage(TFCitiID, StageAddCitizen),
									TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (tempID == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020
											&& !tempName.isEmpty()) {

								citi(tempName, tempYear, tempIsolated, tempID, StageAddCitizen);

							}
						}

						// id+isolated+year option
						else if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) != 9
								&& (!(TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no")))) {
							int tempID = GetIdCheck(TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							int tempYear = GetYear(TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFCitiIsolated),
									TextFieldToString(TFCitiName), TextFieldToIntCountStage(TFCitiID, StageAddCitizen),
									TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (tempID == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020) {

								citi(TextFieldToString(TFCitiName), tempYear, tempIsolated, tempID, StageAddCitizen);
							}
						}

						// id+isolated option
						else if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) != 9
								&& (!(TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no")))) {
							int tempID = GetIdCheck(TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFCitiIsolated),
									TextFieldToString(TFCitiName), TextFieldToIntCountStage(TFCitiID, StageAddCitizen),
									TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (intToString(tempID).length() == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no")) {
								citi(TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiYear, StageAddCitizen),
										tempIsolated, tempID, StageAddCitizen);
							}
						}

						// id+year option
						else if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) != 9
								&& TextFieldToIntStage(TFCitiYear, StageAddCitizen) < 999
								|| TextFieldToIntStage(TFCitiYear, StageAddCitizen) > 2020) {
							int tempId = GetIdCheck(TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							int tempYear = GetYear(TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (intToString(tempId).length() == 9 && tempYear >= 999 && tempYear <= 2020) {

								citi(TextFieldToString(TFCitiName), tempYear, TextFieldToString(TFCitiIsolated), tempId,
										StageAddCitizen);
							}
						}

						// isolated+year option
						else if (!(TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no"))
								&& TextFieldToIntStage(TFCitiYear, StageAddCitizen) < 999
								|| TextFieldToIntStage(TFCitiYear, StageAddCitizen) > 2020) {

							int tempYear = GetYear(TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFCitiIsolated),
									TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiID, StageAddCitizen),
									TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020) {

								citi(TextFieldToString(TFCitiName), tempYear, tempIsolated,
										TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							}
						}

						// id+isolated option
						else if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) != 9
								&& (!(TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no")))
								&& TFCitiName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFCitiName);
							int tempID = GetIdCheck(TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFCitiIsolated),
									TextFieldToString(TFCitiName), TextFieldToIntCountStage(TFCitiID, StageAddCitizen),
									TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (intToString(tempID).length() == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && !tempName.isEmpty()) {
								citi(tempName, TextFieldToIntStage(TFCitiYear, StageAddCitizen), tempIsolated, tempID,
										StageAddCitizen);
							}
						}

						// name+id+year option
						else if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) != 9
								&& TextFieldToIntStage(TFCitiYear, StageAddCitizen) < 999
								|| TextFieldToIntStage(TFCitiYear, StageAddCitizen) > 2020
										&& TFCitiName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFCitiName);
							int tempId = GetIdCheck(TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							int tempYear = GetYear(TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (intToString(tempId).length() == 9 && tempYear >= 999 && tempYear <= 2020
									&& !tempName.isEmpty()) {

								citi(tempName, tempYear, TextFieldToString(TFCitiIsolated), tempId, StageAddCitizen);
							}
						}

						// name+isolated+year option
						else if (!(TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no"))
								&& TextFieldToIntStage(TFCitiYear, StageAddCitizen) < 999
								|| TextFieldToIntStage(TFCitiYear, StageAddCitizen) > 2020
										&& TFCitiName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFCitiName);
							int tempYear = GetYear(TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFCitiIsolated),
									TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiID, StageAddCitizen),
									TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (tempIsolated.equalsIgnoreCase("yes") || tempIsolated.equalsIgnoreCase("no")
									&& tempYear >= 999 && tempYear <= 2020 && !tempName.isEmpty()) {

								citi(tempName, tempYear, tempIsolated, TextFieldToIntStage(TFCitiID, StageAddCitizen),
										StageAddCitizen);
							}
						}

						// name+year option
						else if (TextFieldToIntStage(TFCitiYear, StageAddCitizen) < 999
								|| TextFieldToIntStage(TFCitiYear, StageAddCitizen) > 2020
										&& TFCitiName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFCitiName);
							int tempYear = GetYear(TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (tempYear >= 999 && tempYear <= 2020 && !tempName.isEmpty()) {
								citi(tempName, tempYear, TextFieldToString(TFCitiIsolated),
										TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							}
						}

						// id+name option
						else if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) != 9
								&& TFCitiName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFCitiName);
							int tempId = GetIdCheck(TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);

							if (intToString(tempId).length() == 9 && !tempName.isEmpty()) {
								citi(tempName, TextFieldToIntStage(TFCitiYear, StageAddCitizen),
										TextFieldToString(TFCitiIsolated), tempId, StageAddCitizen);
							}
						}
						// name+isolated option
						else if (!(TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no"))
								&& TFCitiName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFCitiName);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFCitiIsolated),
									TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiID, StageAddCitizen),
									TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && !tempName.isEmpty()) {
								citi(tempName, TextFieldToIntStage(TFCitiYear, StageAddCitizen), tempIsolated,
										TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							}
						}

						// id option
						else if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) != 9) {
							int tempId = GetIdCheck(TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);

							if (intToString(tempId).length() == 9) {
								citi(TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiYear, StageAddCitizen),
										TextFieldToString(TFCitiIsolated), tempId, StageAddCitizen);
							}
						}

						// isolated option
						else if (!(TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no"))) {
							String temp = GetIsolatedCheck(TextFieldToString(TFCitiIsolated),
									TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiID, StageAddCitizen),
									TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (temp.equalsIgnoreCase("yes") || temp.equalsIgnoreCase("no")) {
								citi(TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiYear, StageAddCitizen),
										temp, TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							}
						}

						// name option
						else if (TFCitiName.getText().isEmpty()) {
							String temp = GetTFEmpty(TFCitiName);

							if (!temp.isEmpty()) {
								citi(temp, TextFieldToIntStage(TFCitiYear, StageAddCitizen),
										TextFieldToString(TFCitiIsolated),
										TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							}
						}

						// year option
						else if (TextFieldToIntStage(TFCitiYear, StageAddCitizen) < 999
								|| TextFieldToIntStage(TFCitiYear, StageAddCitizen) > 2020) {
							int temp = GetYear(TextFieldToIntStage(TFCitiYear, StageAddCitizen), StageAddCitizen);

							if (temp >= 999 && temp <= 2020) {
								citi(TextFieldToString(TFCitiName), temp, TextFieldToString(TFCitiIsolated),
										TextFieldToIntStage(TFCitiID, StageAddCitizen), StageAddCitizen);
							}
						}

						// perfect option
						else if (TextFieldToIntCountStage(TFCitiID, StageAddCitizen) == 9
								&& (TextFieldToString(TFCitiIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFCitiIsolated).equalsIgnoreCase("no"))
								&& TextFieldToIntStage(TFCitiYear, StageAddCitizen) >= 999
								&& TextFieldToIntStage(TFCitiYear, StageAddCitizen) <= 2020) {
							citi(TextFieldToString(TFCitiName), TextFieldToIntStage(TFCitiYear, StageAddCitizen),
									TextFieldToString(TFCitiIsolated), TextFieldToIntStage(TFCitiID, StageAddCitizen),
									StageAddCitizen);
						}

					}

				});

				// EXIT
				Button btnExit = new Button("EXIT");
				btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Stage stage = (Stage) btnExit.getScene().getWindow();
						stage.close();

					}

				});

				Label Instructions = new Label("Instructions:");
				Label notice = new Label(
						"To check if the input is correct-> press Enter\nIf input is deleted --> Invalid input!");
				notice.setTextFill(Color.RED);
				Label notice2 = new Label("When you're done, click Submit");
				notice2.setTextFill(Color.DARKBLUE);

				VBAddCitizen.setSpacing(15);
				VBAddCitizen.setPadding(new Insets(20));
				VBAddCitizen.setAlignment(Pos.CENTER_LEFT);
				VBAddCitizen.getChildren().addAll(LaddCitizen, TFCitiName);
				VBAddCitizen.getChildren().addAll(LCitiID, TFCitiID);
				VBAddCitizen.getChildren().addAll(LCitiYear, TFCitiYear);
				VBAddCitizen.getChildren().addAll(LCitiISolated, TFCitiIsolated, Instructions, notice, notice2,
						btnSumbit, btnExit);

				StageAddCitizen.setScene(new Scene(VBAddCitizen, 300, 530));
				StageAddCitizen.show();
			}
		});

		// ADD PARTY
		RadioButton RBAddParty = new RadioButton("Add party");
		allRButtonMenu.add(RBAddParty);
		RBAddParty.setToggleGroup(TGRBMenu);
		RBAddParty.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage StageAddParty = new Stage();
				StageAddParty.setTitle("Add Party");
				VBox VBAddParty = new VBox();

				Label LAddParty = new Label("Add party: \nPlease enter the name of the party:");
				TextField TFPartyName = new TextField();
				Label LPArtyWing = new Label("Please enter the wing of the party(left,center,right):");
				TextField TFPartyWing = new TextField();

				TFPartyWing.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (!(TextFieldToString(TFPartyWing).equalsIgnoreCase("left")
								|| TextFieldToString(TFPartyWing).equalsIgnoreCase("center")
								|| TextFieldToString(TFPartyWing).equalsIgnoreCase("right"))) {
							TFPartyWing.clear();
						}
					}
				});

				Label LPArtyDate = new Label(
						"Please enter the date of the establishment of the party:\nPlease enter day:");
				TextField TFPartyDay = new TextField();
				TFPartyDay.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (TextFieldToInt(TFPartyDay) < 1 || TextFieldToInt(TFPartyDay) > 31) {
							TFPartyDay.clear();
						}

					}
				});

				Label LPArtyMonth = new Label("Please enter month:");
				TextField TFPartyMonth = new TextField();
				TFPartyMonth.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (TextFieldToInt(TFPartyMonth) < 1 || TextFieldToInt(TFPartyMonth) > 12) {
							TFPartyMonth.clear();
						}

					}
				});

				Label LPArtyYear = new Label("Please enter year:");
				TextField TFPartyYear = new TextField();
				TFPartyYear.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (TextFieldToInt(TFPartyYear) < 999 || TextFieldToInt(TFPartyYear) > 2020) {
							TFPartyYear.clear();
						}

					}
				});

				// SUMBIT
				Button btnSubmit = new Button("Submit");
				btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						for (ElectionUIListener l : allListeners) {

							// day+month+year option
							if (TextFieldToInt(TFPartyDay) < 1
									|| TextFieldToInt(TFPartyDay) > 31 && TextFieldToInt(TFPartyMonth) < 1
									|| TextFieldToInt(TFPartyMonth) > 12 && TextFieldToInt(TFPartyYear) < 999
									|| TextFieldToInt(TFPartyYear) > 2020) {
								int day = 0;
								int month = 0;
								int year = 2021;
								while (day < 1 || day > 31) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again day:");
									day = Integer.parseInt(temp);
								}
								while (month < 1 || month > 12) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again month:");
									month = Integer.parseInt(temp);
								}
								while (year < 999 || year > 2020) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again year:");
									year = Integer.parseInt(temp);

								}
								if (day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 999 && year <= 2020) {
									MyDate date = new MyDate(TextFieldToInt(TFPartyDay), TextFieldToInt(TFPartyMonth),
											TextFieldToInt(TFPartyYear));
									Party newparty = new Party(TextFieldToString(TFPartyName),
											TextFieldToString(TFPartyWing), date);
									JOptionPane.showMessageDialog(null, newparty.toString());
									l.addPartyToUI(newparty);
									StageAddParty.close();
								}

							}

							// day+month option
							else if (TextFieldToInt(TFPartyDay) < 1
									|| TextFieldToInt(TFPartyDay) > 31 && TextFieldToInt(TFPartyMonth) < 1
									|| TextFieldToInt(TFPartyMonth) > 12) {
								int day = 0;
								int month = 0;
								while (day < 1 || day > 31) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again day:");
									day = Integer.parseInt(temp);
								}
								while (month < 1 || month > 12) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again month:");
									month = Integer.parseInt(temp);
								}

								if (day >= 1 && day <= 31 && month >= 1 && month <= 12) {
									MyDate date = new MyDate(TextFieldToInt(TFPartyDay), TextFieldToInt(TFPartyMonth),
											TextFieldToInt(TFPartyYear));
									Party newparty = new Party(TextFieldToString(TFPartyName),
											TextFieldToString(TFPartyWing), date);
									JOptionPane.showMessageDialog(null, newparty.toString());
									l.addPartyToUI(newparty);
									StageAddParty.close();
								}

							}
							// day+year option
							else if (TextFieldToInt(TFPartyDay) < 1
									|| TextFieldToInt(TFPartyDay) > 31 && TextFieldToInt(TFPartyYear) < 999
									|| TextFieldToInt(TFPartyYear) > 2020) {
								int day = 0;
								int year = 2021;
								while (day < 1 || day > 31) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again day:");
									day = Integer.parseInt(temp);
								}
								while (year < 999 || year > 2020) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again year:");
									year = Integer.parseInt(temp);

								}
								if (day >= 1 && day <= 31 && year >= 999 && year <= 2020) {
									MyDate date = new MyDate(TextFieldToInt(TFPartyDay), TextFieldToInt(TFPartyMonth),
											TextFieldToInt(TFPartyYear));
									Party newparty = new Party(TextFieldToString(TFPartyName),
											TextFieldToString(TFPartyWing), date);
									JOptionPane.showMessageDialog(null, newparty.toString());
									l.addPartyToUI(newparty);

									StageAddParty.close();
								}

							}

							// month+year option
							else if (TextFieldToInt(TFPartyMonth) < 1
									|| TextFieldToInt(TFPartyMonth) > 12 && TextFieldToInt(TFPartyYear) < 999
									|| TextFieldToInt(TFPartyYear) > 2020) {
								int month = 0;
								int year = 2021;

								while (month < 1 || month > 12) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again month:");
									month = Integer.parseInt(temp);
								}
								while (year < 999 || year > 2020) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again year:");
									year = Integer.parseInt(temp);

								}
								if (month >= 1 && month <= 12 && year >= 999 && year <= 2020) {
									MyDate date = new MyDate(TextFieldToInt(TFPartyDay), TextFieldToInt(TFPartyMonth),
											TextFieldToInt(TFPartyYear));
									Party newparty = new Party(TextFieldToString(TFPartyName),
											TextFieldToString(TFPartyWing), date);
									JOptionPane.showMessageDialog(null, newparty.toString());
									l.addPartyToUI(newparty);

									StageAddParty.close();
								}

							}

							// day option
							else if (TextFieldToInt(TFPartyDay) < 1 || TextFieldToInt(TFPartyDay) > 31) {
								int day = 0;

								while (day < 1 || day > 31) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again day:");
									day = Integer.parseInt(temp);

									if (day >= 1 && day <= 31) {
										MyDate date = new MyDate(TextFieldToInt(TFPartyDay),
												TextFieldToInt(TFPartyMonth), TextFieldToInt(TFPartyYear));
										Party newparty = new Party(TextFieldToString(TFPartyName),
												TextFieldToString(TFPartyWing), date);
										JOptionPane.showMessageDialog(null, newparty.toString());
										l.addPartyToUI(newparty);

										StageAddParty.close();

									}
								}

							}

							// month option
							else if (TextFieldToInt(TFPartyMonth) < 1 || TextFieldToInt(TFPartyMonth) > 12) {

								int month = 0;
								while (month < 1 || month > 12) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again month:");
									month = Integer.parseInt(temp);
									if (month >= 1 && month <= 12) {
										MyDate date = new MyDate(TextFieldToInt(TFPartyDay),
												TextFieldToInt(TFPartyMonth), TextFieldToInt(TFPartyYear));
										Party newparty = new Party(TextFieldToString(TFPartyName),
												TextFieldToString(TFPartyWing), date);
										JOptionPane.showMessageDialog(null, newparty.toString());
										l.addPartyToUI(newparty);

										StageAddParty.close();

									}

								}

							}

							// year option
							else if (TextFieldToInt(TFPartyYear) < 999 || TextFieldToInt(TFPartyYear) > 2020) {
								int year = 2021;
								while (year < 999 || year > 2020) {
									String temp;
									temp = JOptionPane.showInputDialog("Please enter again year:");
									year = Integer.parseInt(temp);
									if (year >= 999 && year <= 2020) {
										MyDate date = new MyDate(TextFieldToInt(TFPartyDay),
												TextFieldToInt(TFPartyMonth), TextFieldToInt(TFPartyYear));
										Party newparty = new Party(TextFieldToString(TFPartyName),
												TextFieldToString(TFPartyWing), date);
										JOptionPane.showMessageDialog(null, newparty.toString());
										l.addPartyToUI(newparty);

										StageAddParty.close();

									}

								}

							}

							// perfect option
							else if (TextFieldToInt(TFPartyDay) >= 1 && TextFieldToInt(TFPartyDay) <= 31
									&& TextFieldToInt(TFPartyMonth) >= 1 && TextFieldToInt(TFPartyMonth) <= 12
									&& TextFieldToInt(TFPartyYear) >= 999 && TextFieldToInt(TFPartyYear) <= 2020) {
								MyDate date = new MyDate(TextFieldToInt(TFPartyDay), TextFieldToInt(TFPartyMonth),
										TextFieldToInt(TFPartyYear));
								Party newparty = new Party(TextFieldToString(TFPartyName),
										TextFieldToString(TFPartyWing), date);
								JOptionPane.showMessageDialog(null, newparty.toString());
								l.addPartyToUI(newparty);

								StageAddParty.close();
							}

						}
					}
				});

				// EXIT
				Button btnExit = new Button("EXIT");
				btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Stage stage = (Stage) btnExit.getScene().getWindow();
						stage.close();

					}

				});
				Label Instructions = new Label("Instructions:");
				Label notice = new Label(
						"To check if the input is correct-> press Enter\nIf input is deleted --> Invalid input!");
				notice.setTextFill(Color.RED);
				Label notice2 = new Label("When you're done, click Submit");
				notice2.setTextFill(Color.DARKBLUE);

				VBAddParty.setSpacing(15);
				VBAddParty.setPadding(new Insets(20));
				VBAddParty.setAlignment(Pos.CENTER_LEFT);
				VBAddParty.getChildren().addAll(LAddParty, TFPartyName, LPArtyWing, TFPartyWing);
				VBAddParty.getChildren().addAll(LPArtyDate, TFPartyDay, LPArtyMonth, TFPartyMonth, LPArtyYear,
						TFPartyYear, btnSubmit, Instructions, notice, notice2, btnExit);
				StageAddParty.setScene(new Scene(VBAddParty, 350, 610));
				StageAddParty.show();
			}
		});

		// ADD CONTENDER
		RadioButton RBAddContender = new RadioButton("Add Citizen as a candidate for a particular party");
		allRButtonMenu.add(RBAddContender);
		RBAddContender.setToggleGroup(TGRBMenu);
		RBAddContender.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage StageAddContender = new Stage();
				StageAddContender.setTitle("Add Contender");
				VBox VBAddContender = new VBox();

				Label LAddContender = new Label(
						"Add contender as a candidate for a particular party: \nPlease enter name of the contender:");
				TextField TFConName = new TextField();

				Label LConID = new Label("Please enter the id of the contender:");
				TextField TFConID = new TextField();
				TFConID.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						try {
							if (TextFieldToIntCount(TFConID) != 9) {
								throw new NotValidIdExspetion();
							}
						} catch (NotValidIdExspetion e) {
							TFConID.clear();
						} catch (NumberFormatException e) {
							TFConID.clear();
						} catch (InputMismatchException e) {
							TFConID.clear();
						}
					}
				});
				// JOptionPane.showMessageDialog(null, TextFieldToInt(TFConID));

				Label LConYear = new Label("Please enter the birth year of the citizen:");
				TextField TFConYear = new TextField();
				TFConYear.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (TextFieldToInt(TFConYear) < 1900 || TextFieldToInt(TFConYear) > 2002) {
							TFConYear.clear();
						}

					}
				});
				Label LConParty = new Label("Please enter party name the contender supports:");
				TextField TFConParty = new TextField();
				TFConParty.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ElectionUIListener l : allListeners) {
							if (!l.isPartyToUI(TextFieldToString(TFConParty)) == true) {
								TFConParty.clear();
							}
						}
					}
				});
				Label LConIsolated = new Label("Please enter yes/no if isolated:");
				TextField TFConIsolated = new TextField();
				TFConIsolated.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						if (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))) {
							TFConIsolated.clear();
//							JOptionPane.showMessageDialog(null, " Wrong input! -->please try again");
						}
					}

				});

				// SUMBIT
				Button btnSubmit = new Button("Submit");
				btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {

						// party+id+isolated+year+name option
						if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))
								&& TFConParty.getText().isEmpty() && TFConName.getText().isEmpty()
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {
							String tempName = GetTFEmpty(TFConName);
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntCountStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempID == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020
											&& !tempParty.isEmpty() && !tempName.isEmpty()) {

								con(tempName, tempYear, tempIsolated, tempID, tempParty, StageAddContender);

							}
						}
						// name+id+isolated+year option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))
								&& TFConName.getText().isEmpty()
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {

							String tempName = GetTFEmpty(TFConName);
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntCountStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempID == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020
											&& !tempName.isEmpty()) {

								con(tempName, tempYear, tempIsolated, tempID, TextFieldToString(TFConParty),
										StageAddContender);
							}
						}

						// name+id+party+year option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))
								&& TFConParty.getText().isEmpty() && TFConName.getText().isEmpty()
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {
							String tempName = GetTFEmpty(TFConName);
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);

							if (tempID == 9 && !tempParty.isEmpty() && tempYear >= 999 && tempYear <= 2020
									&& !tempName.isEmpty()) {

								con(tempName, tempYear, TextFieldToString(TFConIsolated), tempID, tempParty,
										StageAddContender);
							}
						}
						// name+id+isolated+party option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))
								&& TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntCountStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempID == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && !tempName.isEmpty()
											&& !tempParty.isEmpty()) {

								con(tempName, TextFieldToIntStage(TFConYear, StageAddContender), tempIsolated, tempID,
										tempParty, StageAddContender);
							}
						}

						// name+party+isolated+year option
						else if (TFConParty.getText().isEmpty()
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))
								&& TFConName.getText().isEmpty()
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {
							String tempName = GetTFEmpty(TFConName);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntCountStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (!tempParty.isEmpty() && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020
											&& !tempName.isEmpty()) {

								con(tempName, tempYear, tempIsolated, TextFieldToIntStage(TFConID, StageAddContender),
										tempParty, StageAddContender);
							}
						}

						// party+id+isolated+year option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))
								&& TFConParty.getText().isEmpty()
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntCountStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempID == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020
											&& !tempParty.isEmpty()) {

								con(TextFieldToString(TFConName), tempYear, tempIsolated, tempID, tempParty,
										StageAddContender);
							}
						}

						// id+isolated+year option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntCountStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempID == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020) {

								con(TextFieldToString(TFConName), tempYear, tempIsolated, tempID,
										TextFieldToString(TFConParty), StageAddContender);
							}
						}

						// name+id+isolated option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))
								&& TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntCountStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (intToString(tempID).length() == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && !tempName.isEmpty()) {
								con(tempName, TextFieldToIntStage(TFConYear, StageAddContender), tempIsolated, tempID,
										TextFieldToString(TFConParty), StageAddContender);

							}
						}

						// name+id+year option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020
										&& TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							int tempId = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);

							if (intToString(tempId).length() == 9 && tempYear >= 999 && tempYear <= 2020
									&& !tempName.isEmpty()) {

								con(tempName, tempYear, TextFieldToString(TFConIsolated), tempId,
										TextFieldToString(TFConParty), StageAddContender);
							}
						}

						// name+isolated+year option
						else if (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020
										&& TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempIsolated.equalsIgnoreCase("yes") || tempIsolated.equalsIgnoreCase("no")
									&& tempYear >= 999 && tempYear <= 2020 && !tempName.isEmpty()) {

								con(tempName, tempYear, tempIsolated, TextFieldToIntStage(TFConID, StageAddContender),
										TextFieldToString(TFConParty), StageAddContender);

							}
						}
						// party+isolated+id option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& TextFieldToIntStage(TFConID, StageAddContender) < 999
								|| TextFieldToIntStage(TFConID, StageAddContender) > 2020
										&& TFConParty.getText().isEmpty()) {
							int tempId = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempIsolated.equalsIgnoreCase("yes") || tempIsolated.equalsIgnoreCase("no")
									&& intToString(tempId).length() == 9 && !tempParty.isEmpty()) {

								con(TextFieldToString(TFConName), TextFieldToIntStage(TFConYear, StageAddContender),
										tempIsolated, tempId, tempParty, StageAddContender);

							}
						}
						// party+id+year option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020
										&& TFConParty.getText().isEmpty()) {
							int tempId = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);

							if (intToString(tempId).length() == 9 && tempYear >= 999 && tempYear <= 2020
									&& !tempParty.isEmpty()) {

								con(TextFieldToString(TFConName), tempYear, TextFieldToString(TFConIsolated), tempId,
										tempParty, StageAddContender);
							}
						}
						// party+year+name option
						else if (TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020
										&& TFConName.getText().isEmpty() && TFConParty.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);

							if (tempYear >= 999 && tempYear <= 2020 && !tempParty.isEmpty() && !tempName.isEmpty()) {

								con(tempName, tempYear, TextFieldToString(TFConIsolated),
										TextFieldToIntStage(TFConID, StageAddContender), tempParty, StageAddContender);

							}
						}
						// party+isolated+year option
						else if (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020
										&& TFConParty.getText().isEmpty()) {
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempIsolated.equalsIgnoreCase("yes") || tempIsolated.equalsIgnoreCase("no")
									&& tempYear >= 999 && tempYear <= 2020 && !tempParty.isEmpty()) {

								con(TextFieldToString(TFConName), tempYear, tempIsolated,
										TextFieldToIntStage(TFConID, StageAddContender), tempParty, StageAddContender);

							}
						}
						// party+isolated+name option
						else if (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))
								&& TFConName.getText().isEmpty() && TFConParty.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							String tempParty = GetTFEmptyParty(TFConParty);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempIsolated.equalsIgnoreCase("yes") || tempIsolated.equalsIgnoreCase("no")
									&& !tempParty.isEmpty() && !tempName.isEmpty()) {

								con(tempName, TextFieldToIntStage(TFConYear, StageAddContender), tempIsolated,
										TextFieldToIntStage(TFConID, StageAddContender), tempParty, StageAddContender);

							}
						}

						// party+id+name option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& TFConName.getText().isEmpty() && TFConParty.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);

							if (intToString(tempID).length() == 9 && !tempParty.isEmpty() && !tempName.isEmpty()) {

								con(tempName, TextFieldToIntStage(TFConYear, StageAddContender),
										TextFieldToString(TFConIsolated), tempID, tempParty, StageAddContender);

							}
						}
						// id+isolated option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no")))) {
							int tempID = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntCountStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (intToString(tempID).length() == 9 && tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no")) {
								con(TextFieldToString(TFConName), TextFieldToIntStage(TFConYear, StageAddContender),
										tempIsolated, tempID, TextFieldToString(TFConParty), StageAddContender);

							}
						}

						// id+year option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {
							int tempId = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);

							if (intToString(tempId).length() == 9 && tempYear >= 999 && tempYear <= 2020) {

								con(TextFieldToString(TFConName), tempYear, TextFieldToString(TFConIsolated), tempId,
										TextFieldToString(TFConParty), StageAddContender);

							}
						}

						// isolated+year option
						else if (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))
								&& TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {

							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConYear, StageAddContender), StageAddContender);

							if (tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && tempYear >= 999 && tempYear <= 2020) {

								con(TextFieldToString(TFConName), tempYear, tempIsolated,
										TextFieldToIntStage(TFConID, StageAddContender), TextFieldToString(TFConParty),
										StageAddContender);

							}
						}

						// name+year option
						else if (TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020
										&& TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);

							if (tempYear >= 999 && tempYear <= 2020 && !tempName.isEmpty()) {
								con(tempName, tempYear, TextFieldToString(TFConIsolated),
										TextFieldToIntStage(TFConID, StageAddContender), TextFieldToString(TFConParty),
										StageAddContender);

							}
						}

						// id+name option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							int tempId = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (intToString(tempId).length() == 9 && !tempName.isEmpty()) {
								con(tempName, TextFieldToIntStage(TFConYear, StageAddContender),
										TextFieldToString(TFConIsolated), tempId, TextFieldToString(TFConParty),
										StageAddContender);

							}
						}
						// name+isolated option
						else if (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))
								&& TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && !tempName.isEmpty()) {
								con(tempName, TextFieldToIntStage(TFConYear, StageAddContender), tempIsolated,
										TextFieldToIntStage(TFConID, StageAddContender), TextFieldToString(TFConParty),
										StageAddContender);
							}
						}

						// name+party option
						else if (TFConParty.getText().isEmpty() && TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);
							String tempParty = GetTFEmptyParty(TFConParty);

							if (!tempParty.isEmpty() && !tempName.isEmpty()) {
								con(tempName, TextFieldToIntStage(TFConYear, StageAddContender),
										TextFieldToString(TFConIsolated),
										TextFieldToIntStage(TFConID, StageAddContender), tempParty, StageAddContender);

							}
						}
						// party+year option
						else if (TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020
										&& TFConParty.getText().isEmpty()) {
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);

							if (tempYear >= 999 && tempYear <= 2020 && !tempParty.isEmpty()) {
								con(TextFieldToString(TFConName), tempYear, TextFieldToString(TFConIsolated),
										TextFieldToIntStage(TFConID, StageAddContender), tempParty, StageAddContender);

							}
						}

						// id+party option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9
								&& TFConParty.getText().isEmpty()) {
							int tempId = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);
							String tempParty = GetTFEmptyParty(TFConParty);

							if (intToString(tempId).length() == 9 && !tempParty.isEmpty()) {
								con(TextFieldToString(TFConName), TextFieldToIntStage(TFConYear, StageAddContender),
										TextFieldToString(TFConIsolated), tempId, tempParty, StageAddContender);

							}
						}
						// party+isolated option
						else if (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))
								&& TFConParty.getText().isEmpty()) {
							String tempParty = GetTFEmptyParty(TFConParty);
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempIsolated.equalsIgnoreCase("yes")
									|| tempIsolated.equalsIgnoreCase("no") && !tempParty.isEmpty()) {
								con(TextFieldToString(TFConName), TextFieldToIntStage(TFConYear, StageAddContender),
										tempIsolated, TextFieldToIntStage(TFConID, StageAddContender), tempParty,
										StageAddContender);

							}
						}
						// party option
						else if (TFConParty.getText().isEmpty()) {
							String tempParty = GetTFEmptyParty(TFConParty);

							if (!tempParty.isEmpty()) {
								con(TextFieldToString(TFConName), TextFieldToIntStage(TFConYear, StageAddContender),
										TextFieldToString(TFConIsolated),
										TextFieldToIntStage(TFConID, StageAddContender), tempParty, StageAddContender);

							}
						}
						// id option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) != 9) {
							int tempId = GetIdCheck(TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (intToString(tempId).length() == 9) {
								con(TextFieldToString(TFConName), TextFieldToIntStage(TFConYear, StageAddContender),
										TextFieldToString(TFConIsolated), tempId, TextFieldToString(TFConParty),
										StageAddContender);

							}
						}

						// isolated option
						else if (!(TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
								|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))) {
							String tempIsolated = GetIsolatedCheck(TextFieldToString(TFConIsolated),
									TextFieldToString(TFConName), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToIntStage(TFConID, StageAddContender), StageAddContender);

							if (tempIsolated.equalsIgnoreCase("yes") || tempIsolated.equalsIgnoreCase("no")) {
								con(TextFieldToString(TFConName), TextFieldToIntStage(TFConYear, StageAddContender),
										tempIsolated, TextFieldToIntStage(TFConID, StageAddContender),
										TextFieldToString(TFConParty), StageAddContender);

							}
						}

						// name option
						else if (TFConName.getText().isEmpty()) {
							String tempName = GetTFEmpty(TFConName);

							if (!tempName.isEmpty()) {
								con(tempName, TextFieldToIntStage(TFConYear, StageAddContender),
										TextFieldToString(TFConIsolated),
										TextFieldToIntStage(TFConID, StageAddContender), TextFieldToString(TFConParty),
										StageAddContender);

							}
						}

						// year option
						else if (TextFieldToIntStage(TFConYear, StageAddContender) < 999
								|| TextFieldToIntStage(TFConYear, StageAddContender) > 2020) {
							int tempYear = GetYear(TextFieldToIntStage(TFConYear, StageAddContender),
									StageAddContender);

							if (tempYear >= 999 && tempYear <= 2020) {
								con(TextFieldToString(TFConName), tempYear, TextFieldToString(TFConIsolated),
										TextFieldToIntStage(TFConID, StageAddContender), TextFieldToString(TFConParty),
										StageAddContender);

							}
						}

						// perfect option
						else if (TextFieldToIntCountStage(TFConID, StageAddContender) == 9
								&& (TextFieldToString(TFConIsolated).equalsIgnoreCase("yes")
										|| TextFieldToString(TFConIsolated).equalsIgnoreCase("no"))
								&& TextFieldToIntStage(TFConYear, StageAddContender) >= 999
								&& TextFieldToIntStage(TFConYear, StageAddContender) <= 2020
								&& !(TFConName.getText().isEmpty()) && !(TFConParty.getText().isEmpty())) {

							con(TextFieldToString(TFConName), TextFieldToIntStage(TFConYear, StageAddContender),
									TextFieldToString(TFConIsolated), TextFieldToIntStage(TFConID, StageAddContender),
									TextFieldToString(TFConParty), StageAddContender);

						}

					}

				});

				// EXIT
				Button btnExit = new Button("EXIT");
				btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Stage stage = (Stage) btnExit.getScene().getWindow();
						stage.close();

					}

				});
				Label Instructions = new Label("Instructions:");
				Label notice = new Label(
						"To check if the input is correct-> press Enter\nIf input is deleted --> Invalid input!");
				notice.setTextFill(Color.RED);
				Label notice2 = new Label("When you're done, click Submit");
				notice2.setTextFill(Color.DARKBLUE);

				VBAddContender.setSpacing(15);
				VBAddContender.setPadding(new Insets(20));
				VBAddContender.setAlignment(Pos.CENTER_LEFT);
				VBAddContender.getChildren().addAll(LAddContender, TFConName, LConID, TFConID);
				VBAddContender.getChildren().addAll(LConYear, TFConYear, LConParty, TFConParty, LConIsolated,
						TFConIsolated, Instructions, notice, notice2, btnSubmit, btnExit);

				StageAddContender.setScene(new Scene(VBAddContender, 350, 610));
				StageAddContender.show();
			}
		});

		// ALL KALPIES
		RadioButton RBViewAllKalpies = new RadioButton("View All kalpies");
		allRButtonMenu.add(RBViewAllKalpies);
		RBViewAllKalpies.setToggleGroup(TGRBMenu);
		RBViewAllKalpies.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListener l : allListeners) {
					l.showKalpiesToUI();
				}

			}
		});
		// ALL CITIZENS
		RadioButton RBViewAllCitizens = new RadioButton("View All citizens");
		allRButtonMenu.add(RBViewAllCitizens);
		RBViewAllCitizens.setToggleGroup(TGRBMenu);
		RBViewAllCitizens.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListener l : allListeners) {
					l.showCitizensToUI();
				}

			}
		});
		// ALL PARTIES
		RadioButton RBViewAllParties = new RadioButton("View all parties");
		allRButtonMenu.add(RBViewAllParties);
		RBViewAllParties.setToggleGroup(TGRBMenu);
		RBViewAllParties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListener l : allListeners) {
					l.showPartiesToUI();
				}
			}
		});

		// ELECTIONS
		RadioButton RBElections = new RadioButton("Elections");
		allRButtonMenu.add(RBElections);
		RBElections.setToggleGroup(TGRBMenu);
		RBElections.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				for (ElectionUIListener l : allListeners) {
					l.resetToUI();
					;
				}
				Stage StageElction = new Stage();
				StageElction.setTitle("Election");
				VBox VBElction = new VBox();

				Label LElctionDay = new Label("Elections: \nPlease enter an election date : \nPlease enter day:");
				TextField TFElctionDay = new TextField();

				TFElctionDay.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						if (TextFieldToInt(TFElctionDay) < 1 || TextFieldToInt(TFElctionDay) > 31) {
							TFElctionDay.clear();
						}

					}
				});

				Label LElctionMonth = new Label("Please enter month:");
				TextField TFElctionMonth = new TextField();
				TFElctionMonth.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (TextFieldToInt(TFElctionMonth) < 1 || TextFieldToInt(TFElctionMonth) > 12) {
							TFElctionMonth.clear();
						}

					}
				});

				Label LPElctionYear = new Label("Please enter year:");
				TextField TFElctionYear = new TextField();
				TFElctionYear.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (TextFieldToInt(TFElctionYear) < 999 || TextFieldToInt(TFElctionYear) > 2020) {
							TFElctionYear.clear();
						}

					}
				});
				Button btnSubmit = new Button("Submit");
				btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {

						// day+month+year option
						if (TextFieldToInt(TFElctionDay) < 1
								|| TextFieldToInt(TFElctionDay) > 31 && TextFieldToInt(TFElctionMonth) < 1
								|| TextFieldToInt(TFElctionMonth) > 12 && TextFieldToInt(TFElctionYear) < 999
								|| TextFieldToInt(TFElctionYear) > 2020) {
							int day = 0;
							int month = 0;
							int year = 2021;
							while (day < 1 || day > 31) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again day:");
								day = Integer.parseInt(temp);
							}
							while (month < 1 || month > 12) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again month:");
								month = Integer.parseInt(temp);
							}
							while (year < 999 || year > 2020) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again year:");
								year = Integer.parseInt(temp);

							}
							if (day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 999 && year <= 2020) {
								MyDate elctionDate = new MyDate(day, month, year);
								JOptionPane.showMessageDialog(null, "The elections are taking place on "
										+ elctionDate.toString() + " All the information about the election:");
								Elction();
								StageElction.close();
							}

						}

						// day+month option
						if (TextFieldToInt(TFElctionDay) < 1
								|| TextFieldToInt(TFElctionDay) > 31 && TextFieldToInt(TFElctionMonth) < 1
								|| TextFieldToInt(TFElctionMonth) > 12) {
							int day = 0;
							int month = 0;
							while (day < 1 || day > 31) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again day:");
								day = Integer.parseInt(temp);
							}
							while (month < 1 || month > 12) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again month:");
								month = Integer.parseInt(temp);
							}

							if (day >= 1 && day <= 31 && month >= 1 && month <= 12) {
								MyDate elctionDate = new MyDate(day, month, TextFieldToInt(TFElctionYear));
								JOptionPane.showMessageDialog(null, "The elections are taking place on "
										+ elctionDate.toString() + " All the information about the election:");
								Elction();
								StageElction.close();
							}

						}
						// day+year option
						if (TextFieldToInt(TFElctionDay) < 1
								|| TextFieldToInt(TFElctionDay) > 31 && TextFieldToInt(TFElctionYear) < 999
								|| TextFieldToInt(TFElctionYear) > 2020) {
							int day = 0;
							int year = 2021;
							while (day < 1 || day > 31) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again day:");
								day = Integer.parseInt(temp);
							}
							while (year < 999 || year > 2020) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again year:");
								year = Integer.parseInt(temp);

							}
							if (day >= 1 && day <= 31 && year >= 999 && year <= 2020) {
								MyDate elctionDate = new MyDate(day, TextFieldToInt(TFElctionMonth), year);
								JOptionPane.showMessageDialog(null, "The elections are taking place on "
										+ elctionDate.toString() + " All the information about the election:");
								Elction();
								StageElction.close();
							}

						}

						// month+year option
						if (TextFieldToInt(TFElctionMonth) < 1
								|| TextFieldToInt(TFElctionMonth) > 12 && TextFieldToInt(TFElctionYear) < 999
								|| TextFieldToInt(TFElctionYear) > 2020) {
							int month = 0;
							int year = 2021;
							while (month < 1 || month > 12) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again month:");
								month = Integer.parseInt(temp);
							}
							while (year < 999 || year > 2020) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again year:");
								year = Integer.parseInt(temp);

							}
							if (month >= 1 && month <= 12 && year >= 999 && year <= 2020) {
								MyDate elctionDate = new MyDate(TextFieldToInt(TFElctionDay), month, year);
								JOptionPane.showMessageDialog(null, "The elections are taking place on "
										+ elctionDate.toString() + " All the information about the election:");
								Elction();
								StageElction.close();
							}

						}

						// day option
						else if (TextFieldToInt(TFElctionDay) < 1 || TextFieldToInt(TFElctionDay) > 31) {
							int day = 0;

							while (day < 1 || day > 31) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again day:");
								day = Integer.parseInt(temp);

								if (day >= 1 && day <= 31) {
									MyDate elctionDate = new MyDate(day, TextFieldToInt(TFElctionMonth),
											TextFieldToInt(TFElctionYear));
									JOptionPane.showMessageDialog(null, "The elections are taking place on "
											+ elctionDate.toString() + " All the information about the election:");
									Elction();
									StageElction.close();

								}
							}

						}

						// month option
						else if (TextFieldToInt(TFElctionMonth) < 1 || TextFieldToInt(TFElctionMonth) > 12) {

							int month = 0;
							while (month < 1 || month > 12) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again month:");
								month = Integer.parseInt(temp);
								if (month >= 1 && month <= 12) {
									MyDate elctionDate = new MyDate(TextFieldToInt(TFElctionDay), month,
											TextFieldToInt(TFElctionYear));
									JOptionPane.showMessageDialog(null, "The elections are taking place on "
											+ elctionDate.toString() + " All the information about the election:");
									Elction();
									StageElction.close();

								}

							}

						}

						// year option
						else if (TextFieldToInt(TFElctionYear) < 999 || TextFieldToInt(TFElctionYear) > 2020) {
							int year = 2021;
							while (year < 999 || year > 2020) {
								String temp;
								temp = JOptionPane.showInputDialog("Please enter again year:");
								year = Integer.parseInt(temp);
								if (year >= 999 && year <= 2020) {
									MyDate elctionDate = new MyDate(TextFieldToInt(TFElctionDay),
											TextFieldToInt(TFElctionMonth), year);
									JOptionPane.showMessageDialog(null, "The elections are taking place on "
											+ elctionDate.toString() + " All the information about the election:");
									Elction();
									StageElction.close();

								}

							}

						}

						// perfect option
						if (TextFieldToInt(TFElctionDay) >= 1 && TextFieldToInt(TFElctionDay) <= 31
								&& TextFieldToInt(TFElctionMonth) >= 1 && TextFieldToInt(TFElctionMonth) <= 12
								&& TextFieldToInt(TFElctionYear) >= 999 && TextFieldToInt(TFElctionYear) <= 2020) {
							MyDate elctionDate = new MyDate(TextFieldToInt(TFElctionDay),
									TextFieldToInt(TFElctionMonth), TextFieldToInt(TFElctionYear));

							JOptionPane.showMessageDialog(null, "The elections are taking place on "
									+ elctionDate.toString() + " All the information about the election:");
							Elction();

							StageElction.close();
						}
					}

				});

				// EXIT
				Button btnExit = new Button("EXIT");
				btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Stage stage = (Stage) btnExit.getScene().getWindow();
						stage.close();

					}

				});

				Label Instructions = new Label("Instructions:");
				Label notice = new Label(
						"To check if the input is correct-> press Enter\nIf input is deleted --> Invalid input!");
				notice.setTextFill(Color.RED);
				Label notice2 = new Label("When you're done, click Submit");
				notice2.setTextFill(Color.DARKBLUE);

				VBElction.setSpacing(15);
				VBElction.setPadding(new Insets(20));
				VBElction.setAlignment(Pos.CENTER_LEFT);
				VBElction.getChildren().addAll(LElctionDay, TFElctionDay, LElctionMonth, TFElctionMonth, LPElctionYear,
						TFElctionYear, Instructions, notice, notice2);
				;
				VBElction.getChildren().addAll(btnSubmit, btnExit);

				StageElction.setScene(new Scene(VBElction, 350, 480));
				StageElction.show();
			}
		});

		// ELECTION RESULT
		RadioButton RBElectionsResults = new RadioButton("Election results ");
		allRButtonMenu.add(RBElectionsResults);
		RBElectionsResults.setToggleGroup(TGRBMenu);
		RBElectionsResults.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListener l : allListeners) {
					l.showResultsToUI();
				}

			}
		});

		// EXIT
		Button btnExit = new Button("EXIT");
		btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Stage stage = (Stage) btnExit.getScene().getWindow();
				stage.close();
				DB_Connection DB = new DB_Connection();
				DB.closeDB();
			}

		});

		VBMenu.setSpacing(15);
		VBMenu.setPadding(new Insets(20));
		VBMenu.setAlignment(Pos.CENTER_LEFT);
		VBMenu.getChildren().add(lblChoise);
		VBMenu.getChildren().addAll(allRButtonMenu);
		VBMenu.getChildren().add(btnExit);

		theStage.setScene(new Scene(VBMenu, 330, 380));
		theStage.show();
	}

	private int TextFieldToIntStage(TextField TF, Stage StageAddCitizen) {
		Integer value = 0;
		try {
			value = Integer.valueOf(TF.getText());

		} catch (NumberFormatException e) {
		} catch (InputMismatchException e) {
		}
		return value;
	}

	private int TextFieldToInt(TextField TF) {

		Integer value = Integer.valueOf(TF.getText());

		return value;
	}

	private int TextFieldToIntCountStage(TextField TF, Stage StageAddCitizen) {
		Integer value = 0;
		int lenght = 0;
		try {
			value = Integer.valueOf(TF.getText());
			String temp = Integer.toString(value);
			lenght = temp.length();
		} catch (NumberFormatException e) {
		} catch (InputMismatchException e) {
		}

		return lenght;
	}

	private int TextFieldToIntCount(TextField TF) {
		Integer value = Integer.valueOf(TF.getText());
		String temp = Integer.toString(value);
		int lenght = temp.length();
		return lenght;
	}

	private String TextFieldToString(TextField TF) {
		String value = String.valueOf(TF.getText());
		return value;
	}

	private void KalpiType(String emptyAddress) {
		Stage StageKalpiType = new Stage();
		VBox VBKalpiType = new VBox();
		ToggleGroup TGRBAddKalpi = new ToggleGroup();

		Label LKalpiType = new Label("Which kalpi you want ?");
		RadioButton RBRegular = new RadioButton("Regular");
		RBRegular.setToggleGroup(TGRBAddKalpi);
		RBRegular.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (ElectionUIListener l : allListeners) {
					JOptionPane.showMessageDialog(null, "You choose regular");
					Kalpi<Citizen> kalp = new Kalpi<Citizen>(emptyAddress);

					l.addKalpiToUI(kalp);
				}
				StageKalpiType.close();

			}
		});

		RadioButton RBMilitary = new RadioButton("Military");
		RBMilitary.setToggleGroup(TGRBAddKalpi);
		RBMilitary.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (ElectionUIListener l : allListeners) {
					JOptionPane.showMessageDialog(null, "You choose military");
					Kalpi<Soldier> military = new MilitaryKalpi<Soldier>(emptyAddress);
					l.addKalpiToUI(military);
				}
				StageKalpiType.close();

			}
		});

		RadioButton RBCorona = new RadioButton("Corona");
		RBCorona.setToggleGroup(TGRBAddKalpi);
		RBCorona.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (ElectionUIListener l : allListeners) {
					JOptionPane.showMessageDialog(null, "You choose corona");
					Kalpi<CoronaPatient> corona = new CoronaKalpi<CoronaPatient>(emptyAddress);
					l.addKalpiToUI(corona);
				}

				StageKalpiType.close();

			}
		});

		RadioButton RBMilitaryCorona = new RadioButton("Military-Corona");
		RBMilitaryCorona.setToggleGroup(TGRBAddKalpi);
		RBMilitaryCorona.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (ElectionUIListener l : allListeners) {
					JOptionPane.showMessageDialog(null, "You choose Military-corona");
					Kalpi<SickSoldier> militaryCorona = new MilitaryCoronaKalpi<SickSoldier>(emptyAddress);
					l.addKalpiToUI(militaryCorona);
				}
				StageKalpiType.close();

			}
		});

		// EXIT
		Button btnExit = new Button("EXIT");
		btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Stage stage = (Stage) btnExit.getScene().getWindow();
				stage.close();

			}

		});

		VBKalpiType.setSpacing(15);
		VBKalpiType.setPadding(new Insets(20));
		VBKalpiType.setAlignment(Pos.CENTER_LEFT);
		VBKalpiType.getChildren().add(LKalpiType);
		VBKalpiType.getChildren().addAll(RBRegular, RBMilitary, RBCorona, RBMilitaryCorona, btnExit);

		StageKalpiType.setScene(new Scene(VBKalpiType, 250, 200));
		StageKalpiType.show();

	}

	private void TFEmpty(TextField Text, String msg, Stage StageAddCitizen) {
		if (Text.getText().isEmpty()) {
			String empty = "";
			while (empty.isEmpty()) {
				empty = JOptionPane.showInputDialog(msg);
			}
		}
		StageAddCitizen.close();
	}

	private String GetTFEmptyParty(TextField TFCitiName) {
		String party = "";
		if (TFCitiName.getText().isEmpty()) {
			while (party.isEmpty()) {
				party = JOptionPane.showInputDialog("Please enter again Party : ");

				if (!party.isEmpty()) {
					return party;
				}
			}
		}
		return party;
	}

	private String GetTFEmpty(TextField TFCitiName) {
		String name = "";
		if (TFCitiName.getText().isEmpty()) {
			while (name.isEmpty()) {
				name = JOptionPane.showInputDialog("Please enter again name : ");

				if (!name.isEmpty()) {
					return name;
				}
			}
		}
		return name;
	}

	private String GetIsolatedCheck(String TFCitiISolated, String TFCitiName, int TFCitiID, int TFCitiYear,
			Stage StageAddCitizen) {

		if (!(TFCitiISolated.equalsIgnoreCase("yes") || TFCitiISolated.equalsIgnoreCase("no"))) {
			String isolatedCiti = "";
			while (!(isolatedCiti.equalsIgnoreCase("yes") || isolatedCiti.equalsIgnoreCase("no"))) {
				isolatedCiti = JOptionPane.showInputDialog("Please enter again yes/no if isolated ?");

				if (isolatedCiti.equalsIgnoreCase("yes") || isolatedCiti.equalsIgnoreCase("no")) {
					return isolatedCiti;
				}
			}
		}
		return TFCitiISolated;
	}

	private int GetIdCheck(int TFCitiID, Stage StageAddCitizen) {
		String tempId = "";
		try {
			if (intToString(TFCitiID).length() != 9) {
				throw new NotValidIdExspetion();
			}
		} catch (NotValidIdExspetion e) {
			tempId = "";
			while (tempId.length() != 9) {
				tempId = JOptionPane.showInputDialog(e.getMessage());
			}
			StageAddCitizen.close();
			return Integer.parseInt(tempId);

		} catch (NumberFormatException e) {
			tempId = "";
			while (!isDigit(tempId)) {
				tempId = JOptionPane.showInputDialog(e.getMessage() + "sssaaa");
				if (tempId.length() == 9) {

				}
			}
		} catch (InputMismatchException e) {
			tempId = JOptionPane.showInputDialog(e.getMessage());
			while (!isDigit(tempId)) {
				tempId = JOptionPane.showInputDialog(e.getMessage());
				if (tempId.length() == 9) {

				}
			}
		}
		return Integer.parseInt(tempId);
	}

	private int GetYear(int TFCitiYear, Stage StageAddCitizen) {
		int year = 2021;
		if (TFCitiYear < 999 || TFCitiYear > 2020) {
			while (year < 999 || year > 2020) {
				String temp;
				temp = JOptionPane.showInputDialog("Please enter again year:");
				year = Integer.parseInt(temp);
				if (year >= 999 && year <= 2020) {
					return year;
				}

			}
		}
		return year;
	}

	private String intToString(int num) {
		String s = String.valueOf(num);
		return s;
	}

	private boolean isDigit(String str) {
		Vector<Integer> digit = new Vector<Integer>();
		char ch = 0;

		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (Character.isDigit(ch)) {
				digit.add((int) ch);
			}
		}

		if (digit.size() == str.length()) {
			return true;

		}
		return false;
	}

	private void citi(String TFCitiName, int TFCitiYear, String TFCitiISolated, int TFCitiID, Stage StageAddCitizen) {
		try {
			for (ElectionUIListener l : allListeners) {
				Citizen citi;
				// for `mica`
				if (!(TFCitiISolated.equalsIgnoreCase("yes") || TFCitiISolated.equalsIgnoreCase("no"))) {
				}

				if (TFCitiYear >= 2001 && TFCitiYear <= 2004) {
					String weaponAnswer;
					do {
						weaponAnswer = JOptionPane.showInputDialog("Do you have a weapon?");
					} while (!(weaponAnswer.equalsIgnoreCase("yes") || weaponAnswer.equalsIgnoreCase("no")));
					if (TFCitiISolated.equalsIgnoreCase("yes")) {
						String temp = JOptionPane.showInputDialog(" how many days have you been sick?");
						int sickCiti = Integer.parseInt(temp);
						citi = new SickSoldier(TFCitiName, TFCitiID, TFCitiYear, TFCitiISolated, 0, weaponAnswer,
								sickCiti);

					} else {
						citi = new Soldier(TFCitiName, TFCitiID, TFCitiYear, TFCitiISolated, 0, weaponAnswer);

					}
				} else {
					if (TFCitiISolated.equalsIgnoreCase("yes")) {
						String temp = JOptionPane.showInputDialog(" how many days have you been sick?");
						int sickCiti = Integer.parseInt(temp);
						citi = new CoronaPatient(TFCitiName, TFCitiID, TFCitiYear, TFCitiISolated, 0, sickCiti);

					} else {
						citi = new Citizen(TFCitiName, TFCitiID, TFCitiYear, TFCitiISolated, 0);

					}

				}
				l.setCitizenKalpiNumToUI(citi);
				l.addCitizenToUI(citi);
				l.addCitizenToKalpiToUI(citi);
			}
			StageAddCitizen.close();
		} catch (NotValidIdExspetion e) {
//			add text!!!

		} catch (NumberFormatException e) {

		} catch (InputMismatchException e) {

		}

	}

	private void con(String TFConName, int TFConYear, String TFConISolated, int TFConID, String TFConParty,
			Stage StageAddContender) {

		try {
			for (ElectionUIListener l : allListeners) {
				Contender con = null;
				if (intToString(TFConID).length() != 9) {
					throw new NotValidIdExspetion();
				}
				if (TFConISolated.equalsIgnoreCase("yes")) {
					String temp = JOptionPane.showInputDialog(TFConName + " how many days have you been sick?");
					int sickCiti = Integer.parseInt(temp);
					con = new Contender(TFConName, TFConID, TFConYear, TFConISolated, 0, TFConParty, sickCiti);

				} else {
					con = new Contender(TFConName, TFConID, TFConYear, TFConISolated, 0, TFConParty);
				}
				if (l.isPartyToUI(TFConParty) == true) {

					l.setCitizenKalpiNumToUI(con);
					l.addContenderToUI(con);
					l.addCitizenToUI(con);
					l.addCitizenToKalpiToUI(con);

				} else {
					JOptionPane.showMessageDialog(null,
							"such party does not exist at the moment ,you can press in the menu on add party to try again ");
				}
			}
			StageAddContender.close();

		} catch (NotValidIdExspetion e) {

		} catch (NumberFormatException e) {

		} catch (InputMismatchException e) {

		}

	}

	private void Elction() {
		for (ElectionUIListener l : allListeners) {
			l.showCitizensToUI();
			l.showPartiesToUI();
			l.showKalpiesToUI();
			Vector<Citizen> allCitizens = l.getAllCitizensToUI();
			for (int i = 0; i < allCitizens.size(); i++) {
				String answer = JOptionPane.showInputDialog("Hi " + allCitizens.elementAt(i).getName()
						+ "\ndo you want to vote in this election? yes/no\n");
				while (!(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no"))) {

					answer = JOptionPane
							.showInputDialog("Please enter again if you want to vote in this election? yes/no");

				}
				if (answer.equalsIgnoreCase("no"))
					JOptionPane.showMessageDialog(null, allCitizens.elementAt(i).getName()
							+ "-> you better vote in the next election! every vote matters!\n");
				if (answer.equalsIgnoreCase("yes")) {
					if (allCitizens.elementAt(i).isIsolated.equalsIgnoreCase("yes")) {
						String ProtectionAnswer = JOptionPane.showInputDialog(
								allCitizens.elementAt(i).getName() + " do you have a protection suit with you? yes/no");
						while (!(ProtectionAnswer.equalsIgnoreCase("yes") || ProtectionAnswer.equalsIgnoreCase("no"))) {
							ProtectionAnswer = JOptionPane.showInputDialog(allCitizens.elementAt(i).getName()
									+ " do you have a protection suit with you? yes/no");
						}
						while (!ProtectionAnswer.equalsIgnoreCase("yes")) {
							JOptionPane.showMessageDialog(null,
									allCitizens.elementAt(i).getName() + " you can not vote without a protection suit"
											+ " , please take one from the box and come back here !\n");
							ProtectionAnswer = JOptionPane.showInputDialog(allCitizens.elementAt(i).getName()
									+ " did you grab a protection suit from the box? yes/no");
						}
					}
					String partyChoise = JOptionPane.showInputDialog("please enter the name of the party you support:");
					while (!l.isPartyToUI(partyChoise)) {
						partyChoise = JOptionPane
								.showInputDialog("the party name you typed does not exist, type again!");
					}
					try {
						if (allCitizens.elementAt(i).getYearOfBirth() >= 2004) {
							throw new TooYoungExsepction();
						}

						l.voteToUI(partyChoise, allCitizens.elementAt(i).getKaplpiNum());
						JOptionPane.showMessageDialog(null, "Thank you for voting in this election!\n");
					} catch (TooYoungExsepction e) {
						JOptionPane.showMessageDialog(null, e.getMessage() + " -> Please try again \n");
					}
				}

			}

			l.updateVotersPercentToUI();
		}
	}

	@Override
	public void registerListener(ElectionUIListener newListener) {
		allListeners.add(newListener);
	}

	@Override
	public void addPartyToUI(Party p) {

	}

	@Override
	public boolean isAlreadyPartyToUI(Party p) {

		return false;
	}

	@Override
	public void addKalpiToUI(Kalpi<?> kalp) {

	}

	@Override
	public boolean isAlreadyKalpiToUI(Kalpi<?> kalp) {
		return false;
	}

	@Override
	public boolean isAlreadyCitizenToUI(Citizen citi) {
		return false;
	}

	@Override
	public boolean isPartyToUI(String partyName) {
		return false;
	}

	@Override
	public void addCitizenToUI(Citizen citi) throws NotValidIdExspetion, InputMismatchException {

	}

	@Override
	public void addContenderToUI(Contender con) {

	}

	@Override
	public void showCitizensToUI() {

	}

	@Override
	public void showKalpiesToUI() {

	}

	@Override
	public void showPartiesToUI() {

	}

	@Override
	public void setCitizenKalpiNumToUI(Citizen citi) {

	}

	@Override
	public Vector<Citizen> getAllCitizensToUI() {
		return null;
	}

	@Override
	public Vector<Party> getAllPartiesToUI() {
		return null;
	}

	@Override
	public void addCitizenToKalpiToUI(Citizen citi) {

	}

	@Override
	public void voteToUI(String choise, int kalpiNum) throws TooYoungExsepction {

	}

	@Override
	public void showResultsToUI() {

	}

	@Override
	public void updateVotersPercentToUI() {

	}

	@Override
	public void resetToUI() {

	}

}
