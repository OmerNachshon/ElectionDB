package id209006535_id315671552;

import model.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Program {

	public static void main(String[] args) throws NotValidIdExspetion, InputMismatchException, TooYoungExsepction {
		Scanner scan = new Scanner(System.in);
		Election kneset = new Election();
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
		Contender con3 = new Contender("cici", 209006513, 1999, "no", 1, "RozenTSVAIG");
		Contender con4 = new Contender("didi", 209006514, 2000, "no", 1, "RozenTSVAIG");
		Contender con5 = new Contender("fifi", 209006515, 1997, "yes", 2, "yegerrr", 3);
		Contender con6 = new Contender("hihi", 209006516, 1997, "yes", 2, "yegerrr", 5);
		Citizen citi1 = new Citizen("aoao", 209006517, 1997, "no", 1);
		Citizen citi2 = new Citizen("bobo", 209006518, 1997, "no", 1);
		Citizen citi3 = new CoronaPatient("coco", 209006519, 1997, "yes", 2, 0);
		Citizen citi4 = new CoronaPatient("dodo", 209006520, 1997, "yes", 2, 0);
		SickSoldier citi5 = new SickSoldier("fofo", 209006521, 2001, "yes", 4, "yes", 0);
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
		System.out.println(menu);
		do {
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Add kalpi: \n");
				System.out.println("Please enter kalpi address:");
				scan.nextLine();
				String address = scan.nextLine();
				System.out.println(
						"\nWhich kalpi you want add for:\nRegular prees (1)\nMilitary press (2)\nCorona prees (3)\nMilitaryCorona prees (4)");
				int choice1 = 0;
				do {
					choice1 = scan.nextInt();
					switch (choice1) {
					case 1:
						System.out.println("You choose regular");
						Kalpi<Citizen> kalp = new Kalpi<Citizen>(address);
						kneset.addKalpi(kalp);
						System.out.println(menu);
						break;

					case 2:
						System.out.println("You choose military");
						Kalpi<Soldier> military = new MilitaryKalpi<Soldier>(address);
						kneset.addKalpi(military);
						System.out.println(menu);
						break;
					case 3:
						System.out.println("You choose corona");
						Kalpi<CoronaPatient> corona = new CoronaKalpi<CoronaPatient>(address);
						kneset.addKalpi(corona);
						System.out.println(menu);
						break;
					case 4:
						System.out.println("You choose Military corona");
						Kalpi<SickSoldier> militaryCorona = new MilitaryCoronaKalpi<SickSoldier>(address);
						kneset.addKalpi(militaryCorona);
						System.out.println(menu);
						break;
					}
				} while (choice1 < 1 || choice1 > 4);
				break;
			case 2:
				Citizen citi;
				System.out.println("Add citizen: \n");
				System.out.println("Please enter the name of the citizen:");
				scan.nextLine();
				String citiName = scan.nextLine();
				System.out.println("Please enter the id of the citizen:");
				try {
					String tempId = scan.next();
					if (tempId.length() != 9)
						throw new NotValidIdExspetion();
					int citiId = Integer.parseInt(tempId);
					System.out.println("Please enter the birth year of the citizen:");
					int citiBirthYear = scan.nextInt();
					while (citiBirthYear < 1900 || citiBirthYear > 2020) {
						System.out.println("Please enter again the birth year of the citizen:");
						citiBirthYear = scan.nextInt();
					}
					System.out.println("Please enter yes/no if isolated :");
					String citiBidud = scan.next();
					while (!(citiBidud.equalsIgnoreCase("yes") || citiBidud.equalsIgnoreCase("no"))) {
						System.out.println("Please enter again yes/no if isolated ? \n");
						citiBidud = scan.next();
					}
					if (citiBirthYear >= 1999 && citiBirthYear <= 2002) {
						System.out.println("do you have a weapon?");
						String carryWeapon = scan.next();
						if (citiBidud.equalsIgnoreCase("yes")) {
							System.out.println(citiName + " how many days have you been sick?");
							int sickDays = scan.nextInt();
							citi = new SickSoldier(citiName, citiId, citiBirthYear, citiBidud, 0, carryWeapon,
									sickDays);
						} else
							citi = new Soldier(citiName, citiId, citiBirthYear, citiBidud, 0, carryWeapon);
					} else {
						if (citiBidud.equalsIgnoreCase("yes")) {
							System.out.println(citiName + " how many days have you been sick?");
							int sickDays = scan.nextInt();
							citi = new CoronaPatient(citiName, citiId, citiBirthYear, citiBidud, 0, sickDays);
						} else
							citi = new Citizen(citiName, citiId, citiBirthYear, citiBidud, 0);
					}
					kneset.setCitizenKalpiNum(citi);
					kneset.addCitizen(citi);
					kneset.addCitizenToKalpi(citi);
				} catch (NotValidIdExspetion e) {
					System.out.println(e.getMessage() + "-> Please try again\n");
				} catch (InputMismatchException e) {
					System.out.println("Not valid id - must be a digit -> Please try again\n ");
					scan.nextLine(); // Clean buffer
				}
				System.out.println(menu);
				break;
			case 3:
				System.out.println("Add party: \n");
				System.out.println("Please enter the name of the party:");
				scan.nextLine();
				String partyName = scan.nextLine();
				System.out.println("Please enter the wing of the party(left,center,right):");
				String wing = scan.nextLine();
				while (!(wing.equalsIgnoreCase("left") || wing.equalsIgnoreCase("center")
						|| wing.equalsIgnoreCase("right"))) {
					System.out.println("Plesae enter again the wing of the party:");
					wing = scan.nextLine();
				}
				System.out.println("Please enter the date of the establishment of the party:");
				System.out.println("Please enter day:");
				int day = scan.nextInt();
				while (day < 1 || day > 31) {
					System.out.println("Please enter again day:");
					day = scan.nextInt();
				}
				System.out.println("Please enter month:");
				int month = scan.nextInt();
				while (month < 1 || month > 12) {
					System.out.println("Please enter again month:");
					month = scan.nextInt();
				}
				System.out.println("Please enter year:");
				int year = scan.nextInt();
				while (year < 999 || year > 2020) {
					System.out.println("Please enter again year:");
					year = scan.nextInt();
				}
				MyDate date = new MyDate(day, month, year);
				Party newparty = new Party(partyName, wing, date);
				kneset.addParty(newparty);
				System.out.println(menu);
				break;
			case 4:
				Contender contender;
				System.out.println("Add contender as a candidate for a particular party: \n");
				System.out.println("Please enter name of the contender:");
				scan.nextLine();
				String contenderName = scan.nextLine();
				System.out.println("Please enter the id of the contender:");
				try {
					String tempId = scan.next();
					if (tempId.length() != 9)
						throw new NotValidIdExspetion();
					int contenderId = Integer.parseInt(tempId);
					System.out.println("Please enter year of birth:");
					int yearOfBirth = scan.nextInt();
					while (yearOfBirth < 1900 || yearOfBirth > 2002) {
						System.out.println("Please enter again year of birth:");
						yearOfBirth = scan.nextInt();
					}
					System.out.println("Please enter party name the contender supports:");
					scan.nextLine();
					String contenderPartyName = scan.nextLine();
					System.out.println("Please enter yes/no if isolated:");
					String isIsolated = scan.next();
					while (!(isIsolated.equalsIgnoreCase("yes") || isIsolated.equalsIgnoreCase("no"))) {
						System.out.println("Please enter again yes/no if isolated? \n");
						isIsolated = scan.next();
					}
					if (isIsolated.equalsIgnoreCase("yes")) {
						System.out.println(contenderName + " how many days have you been sick?");
						int sickDays = scan.nextInt();
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
						System.out.println(
								"such party does not exist at the moment ,you can press 3 in the menu to add party ");
					}
				} catch (NotValidIdExspetion e) {
					System.out.println(e.getMessage() + "-> Please try again\n");
				} catch (InputMismatchException e) {
					System.out.println("Not valid id - must be a digit -> Please try again\n ");
					scan.nextLine(); // Clean buffer
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println(menu);
				break;
			case 5:
				System.out.println("View all kalpies: \n");
				kneset.showKalpies();
				System.out.println(menu);
				break;
			case 6:
				System.out.println("Presenting all citizens: \n");
				kneset.showCitizens();
				System.out.println(menu);
				break;
			case 7:
				System.out.println("View all parties: \n");
				kneset.showParties();
				System.out.println(menu);
				break;
			case 8:
				kneset.reset();
				System.out.println("Elections: \n");
				System.out.println("Please enter an election date");
				System.out.println("Please enter day:");
				int electionDay = scan.nextInt();
				while (electionDay < 1 || electionDay > 31) {
					System.out.println("Please enter again day:");
					electionDay = scan.nextInt();
				}
				System.out.println("Please enter month:");
				int electionMonth = scan.nextInt();
				while (electionMonth < 1 || electionMonth > 12) {
					System.out.println("Please enter again month:");
					electionMonth = scan.nextInt();
				}
				System.out.println("Please enter year:");
				int electionYear = scan.nextInt();
				while (electionYear < 2020 || electionYear > 9999) {
					System.out.println("Please enter again year:");
					electionYear = scan.nextInt();
				}
				MyDate electionDate = new MyDate(electionDay, electionMonth, electionYear);
				System.out.println("The elections are taking place on " + electionDate + "\n");
				System.out.println("All the information about the election: \n");
				System.out.println("Citizens list:");
				kneset.showCitizens();
				System.out.println();

				System.out.println("Parties list:");
				kneset.showParties();
				System.out.println();

				System.out.println("Kalpies list:");
				kneset.showKalpies();
				Vector<Citizen> allCitizens = kneset.getAllCitizens();
				for (int i = 0; i < allCitizens.size(); i++) {
					System.out.println("Hi " + allCitizens.elementAt(i).getName()
							+ "\ndo you want to vote in this election? yes/no\n");
					String answer = scan.next();
					while (!(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no"))) {
						System.out.println("Please enter again if you want to vote in this election? yes/no\\n");
						answer = scan.next();
					}
					if (answer.equalsIgnoreCase("no"))
						System.out.println(allCitizens.elementAt(i).getName()
								+ "-> you better vote in the next election! every vote matters!\n");
					if (answer.equalsIgnoreCase("yes")) {
						if (allCitizens.elementAt(i).isIsolated.equalsIgnoreCase("yes")) {
							System.out.println(allCitizens.elementAt(i).getName()
									+ " do you have a protection suit with you? yes/no");
							String ProtectionAnswer = scan.next();
							while (!(ProtectionAnswer.equalsIgnoreCase("yes")
									|| ProtectionAnswer.equalsIgnoreCase("no"))) {
								System.out.println(allCitizens.elementAt(i).getName()
										+ " do you have a protection suit with you? yes/no");
								ProtectionAnswer = scan.next();
							}
							while (!ProtectionAnswer.equalsIgnoreCase("yes")) {
								System.out.println(allCitizens.elementAt(i).getName()
										+ " you can not vote without a protection suit"
										+ " , please take one from the box and come back here !\n");
								System.out.println(allCitizens.elementAt(i).getName()
										+ " did you grab a protection suit from the box? yes/no");
								ProtectionAnswer = scan.next();
							}

						}
						System.out.println("please enter the name of the party you support:");
						scan.nextLine();
						String partyChoise = scan.nextLine();
						while (!kneset.isParty(partyChoise)) {
							System.out.println("the party name you typed does not exist, type again!");
							scan.nextLine();
							partyChoise = scan.nextLine();
						}
						try {
							if (allCitizens.elementAt(i).getYearOfBirth() >= 2002) {
								throw new TooYoungExsepction();
							}

							kneset.vote(partyChoise, allCitizens.elementAt(i).getKaplpiNum());
							System.out.println("Thank you for voting in this election!\n");
						} catch (TooYoungExsepction e) {
							System.out.println(e.getMessage() + " -> Please try again \n");
						}
					}

				}
				kneset.updateVotersPercent();

				System.out.println(menu);
				break;
			case 9:
				System.out.println("Election results: \n");
				kneset.showResults();
				System.out.println(menu);
				break;
			case 10:
				System.out.println("Exit \n Thanks for attending :)");
				break;
			}
		} while (choice != 10);

		scan.close();

	}

}