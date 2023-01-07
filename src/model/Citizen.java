package model;

import java.util.InputMismatchException;

public class Citizen implements Comparable<Citizen> {
	protected String name;
	protected int id;
	protected int yearOfBirth;
	public String isIsolated;
	protected int kaplpiNum;

	public Citizen(String name, int id, int yearOfBirth, String isIsolated, int kalpiNum)
			throws NotValidIdExspetion, InputMismatchException {
		this.name = name;
		this.id = id;
		this.yearOfBirth = yearOfBirth;
		this.isIsolated = isIsolated;
		this.kaplpiNum = kalpiNum;
		if (id < 1000000 || id > 999999999) {
			throw new NotValidIdExspetion();
		}
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Citizen)) {
			return false;
		}
		Citizen c = (Citizen) other;

		return (c.id == id);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "--> Name:" + name + " , Id:" + id + " , Year of birth:" + yearOfBirth
				+ " , Is isolated:" + isIsolated + " , Kaplpi number:" + kaplpiNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() throws NotValidIdExspetion, InputMismatchException {
		return id;
	}

	public void setId(int id) throws NotValidIdExspetion, InputMismatchException {
		this.id = id;
		if (id < 1000000 || id > 999999999) {
			throw new NotValidIdExspetion();
		}
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getIsIsolated() {
		return isIsolated;
	}

	public void setIsolated(String isIsolated) {
		this.isIsolated = isIsolated;
	}

	public int getKaplpiNum() {
		return kaplpiNum;
	}

	public void setKaplpiNum(int kaplpiNum) {
		this.kaplpiNum = kaplpiNum;
	}

	@Override
	public int compareTo(Citizen o) {
		if (this.equals(o))
			return 0;
		return 1;
	}

}