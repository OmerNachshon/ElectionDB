package model;

import java.time.LocalDate;
import java.time.Period;

public class MyDate {
	private int year;
	private int month;
	private int day;

	public MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
		if (day > 31 || day < 1)
			this.day = 1;
		if (month > 12 || month < 1)
			this.month = 1;
		if (year > 2022 || year < 1900)
			this.year = 2022;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int daysCount(MyDate d) {
		LocalDate enter = LocalDate.of(year, month, day);
		LocalDate out = LocalDate.of(d.year, d.month, d.day);
		Period period = Period.between(enter, out);
		int diff = period.getDays();
		return diff;

	}

	@Override
	public String toString() {
		return day + "/" + month + "/" + year;
	}

}