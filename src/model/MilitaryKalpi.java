package model;

public class MilitaryKalpi<T extends Soldier> extends Kalpi<T> {

	public MilitaryKalpi(String kalpiAdress) {
		super(kalpiAdress);
		type = "Military Kalpi";
	}

	@Override
	public String getKalpiAdress() {
		return super.getKalpiAdress();
	}

	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof MilitaryKalpi)) {
			return false;
		}
		MilitaryKalpi<?> k = (MilitaryKalpi<?>) other;

		return (k.id == id);
	}

}