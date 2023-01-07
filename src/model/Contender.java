package model;
import java.util.InputMismatchException;
public class Contender extends Citizen {
	protected String partyName;
	private int sickDays=-1; // if equals -1 >>> not sick
	
	public Contender(String name, int id, int yearOfBirth, String isIsolated, int kalpiNum, String partyName,int sickDays)
			throws NotValidIdExspetion, InputMismatchException {
		super(name, id, yearOfBirth, isIsolated, kalpiNum);
		this.partyName = partyName;
		this.sickDays=sickDays; 
	}
	public Contender(String name, int id, int yearOfBirth, String isIsolated, int kalpiNum, String partyName)
			throws NotValidIdExspetion, InputMismatchException {
		super(name, id, yearOfBirth, isIsolated, kalpiNum);
		this.partyName = partyName;	
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Contender)) {
			return false;
		}
		Contender c = (Contender) other;

		return (c.id == id && c.partyName.equalsIgnoreCase(partyName));
	}

	@Override
	public String toString() {
		return super.toString() + " , Belongs to the party:" + partyName +(sickDays>-1 ? " and been sick for "+ sickDays +" days" : " ");
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public int getSickDays() {
		return sickDays;
	}
	public void setSickDays(int sickDays) {
		this.sickDays = sickDays;
	}
	

}