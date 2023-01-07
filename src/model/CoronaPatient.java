package model;

import java.util.InputMismatchException;

public class CoronaPatient extends Citizen {
	protected int sickDays = 0;

	public CoronaPatient(String name, int id, int yearOfBirth, String isIsolated, int kalpiNum, int sickDays)
			throws NotValidIdExspetion, InputMismatchException {
		super(name, id, yearOfBirth, isIsolated, kalpiNum);

		if (isIsolated.equalsIgnoreCase("Yes")) {

			this.sickDays = sickDays;
		}
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof CoronaPatient)) {
			return false;
		}
		CoronaPatient c = (CoronaPatient) other;

		return (c.id == id && c.sickDays == sickDays);
	}

	@Override
	public String toString() {
		return super.toString() +" sick for "+sickDays+" days";
	}

	public int getSickDays() {
		if (isIsolated.equalsIgnoreCase("Yes")) {
			return sickDays;
		} else
			return 0;
	}

	public void setSickDays(int sickDays) {
		if (isIsolated.equalsIgnoreCase("Yes")) {
			this.sickDays = sickDays;
		}
	}
}