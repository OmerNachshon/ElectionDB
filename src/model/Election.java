package model;

import java.util.Vector;

import java.util.InputMismatchException;

public class Election {
	private Vector<Party> parties;
	private Vector<Kalpi<?>> kalpies;
	private Set<Citizen> citizens;

	public Election() {
		System.out.println("New election has begun!");
		parties = new Vector<Party>();
		kalpies = new Vector<Kalpi<?>>();
		citizens = new Set<Citizen>();
	}

	public void addParty(Party p) {
		if (isAlreadyParty(p) == false)
			parties.add(p);

		else
			System.out.println("A party with the name " + p.getName() + " already exists");

	}

	public boolean isAlreadyParty(Party p) {
		for (int i = 0; i < parties.size(); i++) {
			if (parties.elementAt(i).equals(p))
				return true;
		}
		return false;
	}

	public void addKalpi(Kalpi<?> kalp) {
		if (isAlreadyKalpi(kalp) == false)
			kalpies.add(kalp);
		else
			System.out.println("A Kalpi with the same id number : " + kalp.getId() + " already exists");

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

	public boolean isParty(String partyName) {
		for (int i = 0; i < parties.size(); i++) {
			if (parties.elementAt(i).getName().equalsIgnoreCase(partyName))
				return true;
		}
		return false;
	}

	public void addCitizen(Citizen citi) throws NotValidIdExspetion, InputMismatchException {
		if (isAlreadyCitizen(citi) == false) {
			citizens.add(citi);
		} else
			System.out.println("A Citizen with the same id number : " + citi.getId() + " already exists");

	}

	public void addContender(Contender con) {
		for (int i = 0; i < parties.size(); i++) {
			if (parties.elementAt(i).getName().equalsIgnoreCase(con.getPartyName())) {
				parties.elementAt(i).addContender(con);
				citizens.add(con);
			}
		}
	}

	public void showCitizens() {
		citizens.showList();
	}

	public void showKalpies() {
		for (int i = 0; i < kalpies.size(); i++) {
			System.out.println(kalpies.elementAt(i));
		}
		System.out.println();
	}

	public void showParties() {
		for (int i = 0; i < parties.size(); i++)
			System.out.println(parties.elementAt(i));
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
				partyIndex = i;
				i = parties.size();
			}
		}
		for (int i = 0; i < kalpies.size(); i++) {
			if (kalpies.elementAt(i).getId() == kalpiNum) {
				kalpies.elementAt(i).vote(partyIndex);
				i = kalpies.size();
			}
		}
	}

	public void showResults() {
		for (int i = 0; i < kalpies.size(); i++) {
			System.out.println("Kalpi :" + kalpies.elementAt(i).getId() + "\n");
			for (int j = 0; j < parties.size(); j++) {
				System.out
						.println(parties.elementAt(j).getName() + ": " + kalpies.elementAt(i).getResult(j) + " votes");
				System.out.println("\n");
			}

		}
		System.out.println("\nThe results of the election are:\n");
		for (int j = 0; j < parties.size(); j++) {
			System.out
					.println(parties.elementAt(j).getName() + ": " + parties.elementAt(j).getVotes() + " total votes");

			System.out.println();
		}

	}

	public void updateVotersPercent() {
		for (int i = 0; i < kalpies.size(); i++)
			kalpies.elementAt(i).updateVotersPercent();
	}

	public void reset() {
		for (int i = 0; i < kalpies.size(); i++) {
			kalpies.elementAt(i).reset(parties.size());
		}
	}
}