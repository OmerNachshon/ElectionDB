package model;


import java.util.Vector;

public class Party {
	private enum eWing {
		left, center, right
	};

	private eWing wing;
	private String name;
	private MyDate creationDate;
	private Vector<Contender> contenders;
	private int votesCounter;

	public Party(String name, String wing, MyDate creationDate) {
		this.name = name;
		this.wing = eWing.valueOf(wing);
		this.creationDate = creationDate;
		contenders = new Vector<Contender>();
		votesCounter = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWing() {
		return wing.name();
	}

	public MyDate getCreationDate() {
		return creationDate;
	}

	public Vector<Contender> getContenders() {
		return contenders;
	}

	public void setContenders(Vector<Contender> contenders) {
		this.contenders = contenders;
	}

	public void addContender(Contender con) {
		if (isAlreadyCon(con) == false && con.getPartyName().equalsIgnoreCase(name)) {
			contenders.add(con);
		}
	}

	public boolean isAlreadyCon(Contender con) {
		for (int i = 0; i < contenders.size(); i++) {
			if (contenders.elementAt(i).equals(con))
				return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Party)) {
			return false;
		}
		Party p = (Party) other;

		return (p.name.equalsIgnoreCase(name));
	}

	public void upVotesCounter() {
		votesCounter += 1;
	}

	@Override
	public String toString() {
		return "Name: " + name + " , Wing:" + wing + " , Creation date:" + creationDate + " , Quantity of voters:"
				+ votesCounter + "\nContenders:\n" + toStringContenders() + "\n";
	}

	public String toStringContenders() {
		StringBuffer SB = new StringBuffer();
		for (int i = 0; i < contenders.size(); i++) {			
				SB.append(contenders.elementAt(i).toString());
				SB.append("\n");
			
		}
		return SB.toString();
	}

	public int getVotes() {
		return votesCounter;
	}
}