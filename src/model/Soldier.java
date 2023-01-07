package model;

import java.util.InputMismatchException;

public class Soldier extends Citizen {
	private String carryWeapon;

	public Soldier(String name, int id, int yearOfBirth, String isIsolated, int kalpiNum, String carryWeapon)
			throws NotValidIdExspetion, InputMismatchException {
		super(name, id, yearOfBirth, isIsolated, kalpiNum);
		this.carryWeapon = carryWeapon;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Soldier)) {
			return false;
		}
		Soldier c = (Soldier) other;

		return (c.id == id);
	}
	
	public String getCarryWeapon() {
		return carryWeapon;
	}

	@Override
	public String toString() {
		if (carryWeapon.equalsIgnoreCase("yes")) {
			return super.toString() + " is carrying a weapon";
		} else {
			return super.toString() + " is not carrying a weapon";
		}
	}

}