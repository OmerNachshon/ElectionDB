package model;

import java.util.InputMismatchException;
import java.util.Vector;

import DataBase.DB_Connection;
import Listeners.ElectionListener;

public class ElectionMVC {
	private Vector<Party> parties;
	private Vector<Kalpi<?>> kalpies;
	private Set<Citizen> citizens;
	private Vector<ElectionListener> listeners;
	private DB_Connection db = new DB_Connection();

	public ElectionMVC() throws InputMismatchException, NotValidIdExspetion {
		// Clean DB
//		db.deleteAllParties();
//		db.deleteAllKalpies();
//		db.deleteAllCitizens();
		db.deleteTotalVotes();
		db.deleteVotersPercent();
		db.deleteVotesCounter();

		MassageAble ui = new GraphicUI();
		ui.showMassage("New election has begun!");
		parties = db.getParties();
		kalpies = db.getKalpies();
		citizens = db.getCitizens();
		listeners = new Vector<ElectionListener>();
		for (int i = 0; i < citizens.size(); i++) {
			this.addCitizenToKalpi(citizens.get(i));
			if (citizens.get(i) instanceof Contender)
				this.addContender((Contender) citizens.get(i));
		}

	}

	public void registerListener(ElectionListener listener) {
		listeners.add(listener);
	}

	public void addParty(Party p) {
		MassageAble ui = new GraphicUI();
		if (isAlreadyParty(p) == false) {
			parties.add(p);
			db.addParty(p.getName(), p.getWing(), p.getCreationDate(), p.getVotes());
		} else
			ui.showMassage("A party with the name " + p.getName() + " already exists");
	}

	public boolean isAlreadyParty(Party p) {
		for (int i = 0; i < parties.size(); i++) {
			if (parties.elementAt(i).equals(p))
				return true;

		}
		return false;
	}

	public void addKalpi(Kalpi<?> kalp) {
		MassageAble ui = new GraphicUI();
		if (isAlreadyKalpi(kalp) == false) {
			kalpies.add(kalp);
			db.addKalpi(kalp.getId(), kalp.getKalpiAdress(), 0, 0, kalp.getKalpiType());
		} else
			ui.showMassage("A Kalpi with the same id number : " + kalp.getId() + " already exists");
	}

	public boolean isAlreadyKalpi(Kalpi<?> kalp) {
		for (int i = 0; i < kalpies.size(); i++) {
			if (kalpies.elementAt(i).equals(kalp))
				return true;

		}
		return false;
	}

	public boolean isAlreadyCitizen(Citizen citi) {
		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.list.elementAt(i).equals(citi))
				return true;
		}
		return false;
	}

	public Boolean isParty(String partyName) {
		for (int i = 0; i < parties.size(); i++) {
			if (parties.elementAt(i).getName().equalsIgnoreCase(partyName))
				return true;
		}
		return false;
	}

	public void addCitizen(Citizen citi) throws NotValidIdExspetion, InputMismatchException {
		MassageAble ui = new GraphicUI();
		if (isAlreadyCitizen(citi) == false) {
			citizens.add(citi);
			db.addCitizen(citi);
		} else
			ui.showMassage("A Citizen with the same id number : " + citi.getId() + " already exists");
	}

	public void addContender(Contender con) throws InputMismatchException, NotValidIdExspetion {
		for (int i = 0; i < parties.size(); i++) {
			if (parties.elementAt(i).getName().equalsIgnoreCase(con.getPartyName())) {
				parties.elementAt(i).addContender(con);
				// db.addCitizen(con);
			}
		}
	}

	public void showCitizensUI() {
		citizens.showListUI();
	}

	public void showKalpiesUI() {
		MassageAble ui = new GraphicUI();
		StringBuffer SB = new StringBuffer();
		for (int i = 0; i < kalpies.size(); i++) {
			SB.append(kalpies.elementAt(i).toString());
			SB.append("\n");
		}
		String showKalpies = SB.substring(0, SB.length() - 1);
		ui.showMassage("View all kalpies: \n" + showKalpies);
	}

	public void showPartiesUI() {
		MassageAble ui = new GraphicUI();
		StringBuffer SB = new StringBuffer();
		for (int i = 0; i < parties.size(); i++) {
			SB.append(parties.elementAt(i).toString());
			SB.append("\n");
		}
		String showParties = SB.substring(0, SB.length() - 1);
		ui.showMassage("View all parties: \n" + showParties);
	}

	public void setCitizenKalpiNum(Citizen citi) {
		int temp = (int) (Math.random() * kalpies.size());
		if (citi instanceof CoronaPatient || (citi instanceof Contender && citi.isIsolated.equalsIgnoreCase("Yes"))) {
			while ((!(kalpies.elementAt(temp).getKalpiType().equalsIgnoreCase("Corona Kalpi"))))
				temp = (int) (Math.random() * kalpies.size());
		} else {
			if (citi instanceof Soldier) {
				while ((!(kalpies.elementAt(temp).getKalpiType().equalsIgnoreCase("Military Kalpi"))))
					temp = (int) (Math.random() * kalpies.size());
			}

			else {
				if (citi instanceof SickSoldier) {
					while ((!(kalpies.elementAt(temp).getKalpiType().equalsIgnoreCase("Military-Corona Kalpi"))))
						temp = (int) (Math.random() * kalpies.size());
				}

				else {
					while (!(kalpies.elementAt(temp).getKalpiType().equalsIgnoreCase("Regular kalpi")))
						temp = (int) (Math.random() * kalpies.size());
				}
			}
		}
		citi.setKaplpiNum(kalpies.elementAt(temp).getId());

	}

	public Vector<Citizen> getAllCitizens() {
		return citizens.list;
	}

	public Vector<Party> getAllParties() {
		return parties;
	}

	public void addCitizenToKalpi(Citizen citi) {
		for (int i = 0; i < kalpies.size(); i++) {
			if (kalpies.elementAt(i).getId() == citi.getKaplpiNum()) {
				kalpies.elementAt(i).addCitizenToKalpi(citi);
				i = kalpies.size();
			}
		}

	}

	public void vote(String choise, int kalpiNum) throws TooYoungExsepction {
		int partyIndex = 0;
		for (int i = 0; i < parties.size(); i++) {
			if (parties.elementAt(i).getName().equalsIgnoreCase(choise)) {
				parties.elementAt(i).upVotesCounter();
				db.voteParty(parties.elementAt(i).getName()); // up votes in db
				partyIndex = i;
				i = parties.size();
			}
		}
		for (int i = 0; i < kalpies.size(); i++) {
			if (kalpies.elementAt(i).getId() == kalpiNum) {
				kalpies.elementAt(i).vote(partyIndex);
				db.voteKalpi(kalpiNum); // up votes in db
				i = kalpies.size();
			}
		}
	}

	public void showResultsUI() {
		MassageAble ui = new GraphicUI();
		StringBuffer SB = new StringBuffer();
		StringBuffer SB2 = new StringBuffer();
		for (int i = 0; i < parties.size(); i++) {
			SB.append("\nKalpi :" + kalpies.elementAt(i).getId() + "\n");
			SB.append("\n");
			for (int j = 0; j < parties.size(); j++) {
				SB.append(parties.elementAt(j).getName() + ": " + kalpies.elementAt(i).getResult(j) + " votes ");
				SB.append("\n");
			}
		}
		String showResults = SB.substring(0, SB.length() - 1);
		ui.showMassage("Election results: \n" + showResults);

		for (int j = 0; j < parties.size(); j++) {
			// SB2.append(parties.elementAt(j).getName() + ": " +
			// parties.elementAt(j).getVotes() + " total votes"); //using list
			SB2.append(parties.elementAt(j).getName() + ": " + db.getVotesFromParty(parties.elementAt(j).getName())
					+ " total votes"); // using db
			SB2.append("\n");
		}
		String showTotalResults = SB2.substring(0, SB2.length() - 1);
		ui.showMassage("\nThe results of the election are:\n\n" + showTotalResults);
	}

	public void updateVotersPercent() {
		for (int i = 0; i < kalpies.size(); i++) {
			kalpies.elementAt(i).updateVotersPercent();
			db.updateVotersPercentKalpi(kalpies.elementAt(i).getId(), kalpies.elementAt(i).getVotersPercent());
		}
	}

	public void reset() {
		for (int i = 0; i < kalpies.size(); i++) {
			kalpies.elementAt(i).reset(parties.size());
		}
	}

}
