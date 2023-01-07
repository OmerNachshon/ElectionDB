package id209006535_id315671552;

import java.util.InputMismatchException;
import java.util.Vector;

import model.Citizen;
import model.Contender;
import model.CoronaKalpi;
import model.CoronaPatient;
import model.ElectionUI;
import model.GraphicUI;
import model.Kalpi;
import model.MassageAble;
import model.MilitaryCoronaKalpi;
import model.MilitaryKalpi;
import model.MyDate;
import model.NotValidIdExspetion;
import model.Party;
import model.SickSoldier;
import model.Soldier;
import model.TooYoungExsepction;

public class ProgramUI {

	public static void main(String[] args) throws NotValidIdExspetion, InputMismatchException, TooYoungExsepction {
		MassageAble ui = new GraphicUI();
		ElectionUI kneset = new ElectionUI();
		MyDate date1 = new MyDate(16, 4, 1997);
		MyDate date2 = new MyDate(3, 2, 2001);
		MyDate date3 = new MyDate(8, 2, 1997);
		Party p1 = new Party("olgaShlita", "center", date1);
		Party p2 = new Party("RozenTSVAIG", "center", date2);
		Party p3 = new Party("yegerrr", "center", date3);
		Kalpi<Citizen> k1 = new Kalpi<Citizen>("Hod hasharon");
		Kalpi<CoronaPatient> k2 = new CoronaKalpi<CoronaPatient>("Bne brak");
		Kalpi<Soldier> k3 = new MilitaryKalpi<Soldier>("Beer shva");
		Kalpi<SickSoldier> k4 = new MilitaryCoronaKalpi<SickSoldier>("afula");
		Contender con1 = new Contender("aiai", 209006511, 1997, "no", 1, "olgaShlita");
		Contender con2 = new Contender("bibi", 209006512, 1997, "no", 1, "olgaShlita");
		Contender con3 = new Contender("cici", 209006513, 1999, "no", 3, "RozenTSVAIG");
		Contender con4 = new Contender("didi", 209006514, 2000, "no", 3, "RozenTSVAIG");
		Contender con5 = new Contender("fifi", 209006515, 1997, "yes", 2, "yegerrr");
		Contender con6 = new Contender("hihi", 209006516, 1997, "yes", 2, "yegerrr");
		Citizen citi1 = new Citizen("aoao", 209006517, 1997, "no", 1);
		Citizen citi2 = new Citizen("bobo", 209006518, 1997, "no", 1);
		Citizen citi3 = new CoronaPatient("coco", 209006519, 1997, "yes", 2, 0);
		Citizen citi4 = new CoronaPatient("dodo", 209006520, 1997, "yes", 2, 0);
		Soldier citi5 = new Soldier("fofo", 209006521, 2001, "yes", 4, "yes");
		kneset.addCitizen(citi1);
		kneset.addCitizen(citi2);
		kneset.addCitizen(citi3);
		kneset.addCitizen(citi4);
		kneset.addCitizen(citi5);
		kneset.addCitizen(con1);
		kneset.addCitizen(con2);
		kneset.addCitizen(con3);
		kneset.addCitizen(con4);
		kneset.addCitizen(con5);
		kneset.addCitizen(con6);
		kneset.addParty(p1);
		kneset.addParty(p2);
		kneset.addParty(p3);
		kneset.addKalpi(k1);
		kneset.addKalpi(k2);
		kneset.addKalpi(k3);
		kneset.addKalpi(k4);
		p1.addContender(con1);
		p1.addContender(con2);
		p2.addContender(con3);
		p2.addContender(con4);
		p3.addContender(con5);
		p3.addContender(con6);
		kneset.addCitizenToKalpi(citi1);
		kneset.addCitizenToKalpi(citi2);
		kneset.addCitizenToKalpi(citi3);
		kneset.addCitizenToKalpi(citi4);
		kneset.addCitizenToKalpi(citi5);
		kneset.addCitizenToKalpi(con1);
		kneset.addCitizenToKalpi(con2);
		kneset.addCitizenToKalpi(con3);
		kneset.addCitizenToKalpi(con4);
		kneset.addCitizenToKalpi(con5);
		kneset.addCitizenToKalpi(con6);

		int choice = 0;
		String menu = "Menu:\n 1) For add kalpi press (1)\n 2) For add citizen press (2)\n"
				+ " 3) For add party press (3)\n 4) For add Citizen as a candidate for a particular party press (4) \n"
				+ " 5) For view all kalpies press (5)\n 6) For presenting all citizens press (6)\n "
				+ "7) For view all parties press (7) \n" + " 8) For Elections press (8)\n"
				+ " 9) For Election results press (9)\n" + "10) For Exit press (10)" + "\n Please enter your choice : ";
		do {
			try {
				choice = Integer.parseInt(ui.getString(menu));
				switch (choice) {
				case 1:
					String address = ui.getString("Add kalpi: \nPlease enter kalpi address:");
					int choice1 = 0;
					do {
						choice1 = Integer.parseInt(ui.getString(
								"\nWhich kalpi you want add for:\nRegular prees (1)\nMilitary press (2)\nCorona prees (3)\nMilitaryCorona prees (4)"));
						switch (choice1) {
						case 1:
							ui.showMassage("You choose regular");
							Kalpi<Citizen> kalp = new Kalpi<Citizen>(address);
							kneset.addKalpi(kalp);
							break;

						case 2:
							ui.showMassage("You choose military");
							Kalpi<Soldier> military = new MilitaryKalpi<Soldier>(address);
							kneset.addKalpi(military);
							break;
						case 3:
							ui.showMassage("You choose corona");
							Kalpi<CoronaPatient> corona = new CoronaKalpi<CoronaPatient>(address);
							kneset.addKalpi(corona);
							break;
						case 4:
							ui.showMassage("You choose Military corona");
							Kalpi<SickSoldier> militaryCorona = new MilitaryCoronaKalpi<SickSoldier>(address);
							kneset.addKalpi(militaryCorona);
							break;
						}
					} while (choice1 < 1 || choice1 > 4);

					break;
				case 2:
					Citizen citi;
					String citiName = ui.getString("Add citizen: \nPlease enter the name of the citizen:");
					try {
						String tempId = ui.getString("Please enter the id of the citizen:");
						if (tempId.length() != 9) {
							throw new NotValidIdExspetion();
						}
						int citiId = Integer.parseInt(tempId);
						int citiBirthYear = Integer
								.parseInt(ui.getString("Please enter the birth year of the citizen:"));
						while (citiBirthYear < 1900 || citiBirthYear > 2020) {
							citiBirthYear = Integer
									.parseInt(ui.getString("Please enter again the birth year of the citizen:"));
						}
						String citiBidud = ui.getString("Please enter yes/no if isolated :");
						while (!(citiBidud.equalsIgnoreCase("yes") || citiBidud.equalsIgnoreCase("no"))) {
							citiBidud = ui.getString("Please enter again yes/no if isolated ? \\n");
						}
						if (citiBirthYear >= 1999 && citiBirthYear <= 2002) {
							String carryWeapon = ui.getString("do you have a weapon?");
							if (citiBidud.equalsIgnoreCase("yes")) {
								int sickDays = Integer
										.parseInt(ui.getString(citiName + " how many days have you been sick?"));
								citi = new SickSoldier(citiName, citiId, citiBirthYear, citiBidud, 0, carryWeapon,
										sickDays);
							} else
								citi = new Soldier(citiName, citiId, citiBirthYear, citiBidud, 0, carryWeapon);
						} else {
							if (citiBidud.equalsIgnoreCase("yes")) {
								int sickDays = Integer
										.parseInt(ui.getString(citiName + " how many days have you been sick?"));
								citi = new CoronaPatient(citiName, citiId, citiBirthYear, citiBidud, 0, sickDays);
							} else
								citi = new Citizen(citiName, citiId, citiBirthYear, citiBidud, 0);
						}
						kneset.setCitizenKalpiNum(citi);
						kneset.addCitizen(citi);
						kneset.addCitizenToKalpi(citi);
					} catch (NotValidIdExspetion e) {
						ui.showMassage(e.getMessage() + "-> Please try again\n");
					} catch (NumberFormatException e) {
						ui.showMassage("Not valid id - must be a digit -> Please try again\n");
					} catch (InputMismatchException e) {
						ui.showMassage("Not valid id - must be a digit -> Please try again\n ");
					}
					break;
				case 3:
					String partyName = ui.getString("Add party: \nPlease enter the name of the party:");
					String wing = ui.getString("Please enter the wing of the party(left,center,right):");
					while (!(wing.equalsIgnoreCase("left") || wing.equalsIgnoreCase("center")
							|| wing.equalsIgnoreCase("right"))) {
						wing = ui.getString("Plesae enter again the wing of the party:");
					}
					int day = Integer.parseInt(ui
							.getString("Please enter the date of the establishment of the party:\nPlease enter day:"));
					while (day < 1 || day > 31) {
						day = Integer.parseInt(ui.getString("Please enter again day:"));
					}
					int month = Integer.parseInt(ui.getString("Please enter month:"));
					while (month < 1 || month > 12) {
						month = Integer.parseInt(ui.getString("Please enter again month:"));
					}
					int year = Integer.parseInt(ui.getString("Please enter year:"));
					while (year < 999 || year > 2020) {
						year = Integer.parseInt(ui.getString("Please enter again year:"));
					}
					MyDate date = new MyDate(day, month, year);
					Party newparty = new Party(partyName, wing, date);
					kneset.addParty(newparty);
					break;
				case 4:
					Contender contender;
					String contenderName = ui.getString(
							"Add contender as a candidate for a particular party: \nPlease enter name of the contender:");
					try {
						String tempId = ui.getString("Please enter the id of the contender:");
						if (tempId.length() != 9) {
							throw new NotValidIdExspetion();
						}
						int contenderId = Integer.parseInt(tempId);
						int yearOfBirth = Integer.parseInt(ui.getString("Please enter year of birth:"));
						while (yearOfBirth < 1900 || yearOfBirth > 2002) {
							yearOfBirth = Integer.parseInt(ui.getString("Please enter again year of birth:"));
						}
						String contenderPartyName = ui.getString("Please enter party name the contender supports:");
						String isIsolated = ui.getString("Please enter yes/no if isolated:");
						while (!(isIsolated.equalsIgnoreCase("yes") || isIsolated.equalsIgnoreCase("no"))) {
							isIsolated = ui.getString("Please enter again yes/no if isolated? \n");
						}
						if (isIsolated.equalsIgnoreCase("yes")) {
							int sickDays = Integer
									.parseInt(ui.getString(contenderName + " how many days have you been sick?"));
							contender = new Contender(contenderName, contenderId, yearOfBirth, isIsolated, 0,
									contenderPartyName, sickDays);
						} else
							contender = new Contender(contenderName, contenderId, yearOfBirth, isIsolated, 0,
									contenderPartyName);

						if (kneset.isParty(contenderPartyName) == true) {
							kneset.setCitizenKalpiNum(contender);
							kneset.addContender(contender);
							kneset.addCitizen(contender);
							kneset.addCitizenToKalpi(contender);
						} else {
							ui.showMassage(
									"such party does not exist at the moment ,you can press 3 in the menu to add party ");
						}
					} catch (NotValidIdExspetion e) {
						ui.showMassage(e.getMessage() + "-> Please try again\n");
					} catch (NumberFormatException e) {
						ui.showMassage("Not valid id - must be a digit -> Please try again\n");
					} catch (InputMismatchException e) {
						ui.showMassage("Not valid id - must be a digit -> Please try again\n ");
					} catch (Exception e) {
						ui.showMassage(e.getMessage());
					}
					break;
				case 5:
					kneset.showKalpiesUI();
					break;
				case 6:
					kneset.showCitizensUI();
					break;
				case 7:
					kneset.showPartiesUI();
					break;
				case 8:
					kneset.reset();
					int electionDay = Integer
							.parseInt(ui.getString("Elections: \nPlease enter an election date\nPlease enter day:"));
					while (electionDay < 1 || electionDay > 31) {
						electionDay = Integer.parseInt(ui.getString("Please enter again day:"));
					}
					int electionMonth = Integer.parseInt(ui.getString("Please enter month:"));
					while (electionMonth < 1 || electionMonth > 12) {
						electionMonth = Integer.parseInt(ui.getString("Please enter again month:"));
					}
					int electionYear = Integer.parseInt(ui.getString("Please enter year:"));
					while (electionYear < 2020 || electionYear > 9999) {
						electionYear = Integer.parseInt(ui.getString("Please enter again year:"));
					}
					MyDate electionDate = new MyDate(electionDay, electionMonth, electionYear);
					ui.showMassage("The elections are taking place on " + electionDate
							+ "\nAll the information about the election: \n");
					kneset.showCitizensUI();
					kneset.showPartiesUI();
					kneset.showKalpiesUI();
					Vector<Citizen> allCitizens = kneset.getAllCitizens();
					for (int i = 0; i < allCitizens.size(); i++) {
						String answer = ui.getString("Hi " + allCitizens.elementAt(i).getName()
								+ "\ndo you want to vote in this election? yes/no\n");
						while (!(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no"))) {
							answer = ui
									.getString("Please enter again if you want to vote in this election? yes/no\\\\n");
						}
						if (answer.equalsIgnoreCase("no"))
							ui.showMassage(allCitizens.elementAt(i).getName()
									+ "-> you better vote in the next election! every vote matters!\n");
						if (answer.equalsIgnoreCase("yes")) {
							if (allCitizens.elementAt(i).isIsolated.equalsIgnoreCase("yes")) {
								String ProtectionAnswer = ui.getString(allCitizens.elementAt(i).getName()
										+ " do you have a protection suit with you? yes/no");
								while (!(ProtectionAnswer.equalsIgnoreCase("yes")
										|| ProtectionAnswer.equalsIgnoreCase("no"))) {
									ProtectionAnswer = ui.getString(allCitizens.elementAt(i).getName()
											+ " do you have a protection suit with you? yes/no");
								}
								while (!ProtectionAnswer.equalsIgnoreCase("yes")) {
									ui.showMassage(allCitizens.elementAt(i).getName()
											+ " you can not vote without a protection suit"
											+ " , please take one from the box and come back here !\n");
									ProtectionAnswer = ui.getString(allCitizens.elementAt(i).getName()
											+ " did you grab a protection suit from the box? yes/no");
								}
							}
							String partyChoise = ui.getString("please enter the name of the party you support:");
							while (!kneset.isParty(partyChoise)) {
								partyChoise = ui.getString("the party name you typed does not exist, type again!");
							}
							try {
								if (allCitizens.elementAt(i).getYearOfBirth() >= 2002) {
									throw new TooYoungExsepction();
								}

								kneset.vote(partyChoise, allCitizens.elementAt(i).getKaplpiNum());
								ui.showMassage("Thank you for voting in this election!\n");
							} catch (TooYoungExsepction e) {
								ui.showMassage(e.getMessage() + " -> Please try again \n");
							}
						}

					}
					kneset.updateVotersPercent();
					break;
				case 9:
					kneset.showResultsUI();
					break;
				case 10:
					ui.showMassage("Exit \n Thanks for attending :)");
					break;
				}
			} catch (NumberFormatException e) {
				ui.showMassage("Wrong input please try again");
			}
		} while (choice != 10);
	}
}