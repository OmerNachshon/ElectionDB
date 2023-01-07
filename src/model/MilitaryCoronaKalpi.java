package model;

public class MilitaryCoronaKalpi<T extends SickSoldier> extends Kalpi<T> {
	public MilitaryCoronaKalpi(String kalpiAdress) {
		super(kalpiAdress);
		type="Military-Corona Kalpi";

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
		if (!(other instanceof MilitaryCoronaKalpi)) {
			return false;
		}
		MilitaryCoronaKalpi<?> k = (MilitaryCoronaKalpi<?>) other;

		return (k.id == id);
	}

}