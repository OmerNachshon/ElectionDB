package model;

import java.util.Vector;

public class Kalpi<T extends Citizen> {
	public static int kalpiId = 1;
	protected String kalpiAddress;
	protected Vector<Citizen> voters;
	protected int id;
	protected double votersPercent;
	protected Vector<Integer> partyVotes;
	protected int totalVotes;
	protected String type;

	public Kalpi(String kalpiAdress) {
		this.kalpiAddress = kalpiAdress;
		voters = new Vector<Citizen>();
		this.id = kalpiId++;
		votersPercent = 0;
		partyVotes = new Vector<Integer>();
		totalVotes = 0;
		type = "Regular kalpi";
	}

	public String getKalpiAdress() {
		return kalpiAddress;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Kalpi)) {
			return false;
		}
		Kalpi<?> k = (Kalpi<?>) other;

		return (k.id == id);
	}

	public void addCitizenToKalpi(Citizen citi) {
		voters.add(citi);
	}

	public void vote(int partyIndex) throws TooYoungExsepction {
		partyVotes.set(partyIndex, partyVotes.elementAt(partyIndex) + 1);
		totalVotes++;
	}

	@Override
	public String toString() {
		return type+ " --> Kalpi address:" + kalpiAddress + " , Voters percent:" + votersPercent + " , Id:" + id
				+ "\nVoters:\n" + toStringVoters();
	}

	

	private String toStringVoters() {
		StringBuffer SB = new StringBuffer();
		for (int i = 0; i < voters.size(); i++) {
			SB.append(voters.elementAt(i).toString());
			SB.append("\n");
		}
		return SB.toString() + "\n";
	}

	public void updateVotersPercent() {
		int counter = voters.size();
		votersPercent = 0;
		if (counter != 0)
			votersPercent = totalVotes * 100 / counter;
	}
	public double getVotersPercent() {
		return votersPercent;
	}

	public String getKalpiType() {
		return type;
	}

	public int getResult(int index) {
		if (index > partyVotes.size() - 1)
			return 0;
		return partyVotes.elementAt(index);
	}

	public void reset(int numOfParties) {
		for (int i = 0; i < numOfParties; i++)
			partyVotes.add(0);

	}
}