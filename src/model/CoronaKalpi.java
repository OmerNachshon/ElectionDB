package model;

public class CoronaKalpi<T extends CoronaPatient> extends Kalpi<T> {

	public CoronaKalpi(String kalpiAdress) {
		super(kalpiAdress);
		type = "Corona Kalpi";
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
		if (!(other instanceof CoronaKalpi)) {
			return false;
		}
		CoronaKalpi<?> k = (CoronaKalpi<?>) other;

		return (k.id == id);
	}

}