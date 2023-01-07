package model;

import java.util.InputMismatchException;

public class SickSoldier extends Citizen {
	private String carryWeapon;
	private int sickDays;
	
	public SickSoldier(String name, int id, int yearOfBirth, String isIsolated, int kalpiNum, String carryWeapon,int sickDays)
			throws NotValidIdExspetion, InputMismatchException {
		super(name, id, yearOfBirth, isIsolated, kalpiNum);
		this.carryWeapon = carryWeapon;
		this.sickDays=sickDays;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Soldier)) {
			return false;
		}
		Soldier c = (Soldier) other;

		return (c.id == id);
	}

	@Override
	public String toString() {
		if (carryWeapon.equalsIgnoreCase("yes")) {
			return super.toString() + " is carrying a weapon" +" sick for "+sickDays+" days";
		} else {
			return super.toString() + " is not carrying a weapon"+" sick for "+sickDays+" days";
		}
	}

	public String getCarryWeapon() {
		return carryWeapon;
	}
	public int getSickDays() {
		return sickDays;
	}

}