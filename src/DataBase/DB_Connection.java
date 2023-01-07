package DataBase;

import java.sql.*;
import java.util.Vector;

import model.Citizen;
import model.Contender;
import model.CoronaKalpi;
import model.CoronaPatient;
import model.Kalpi;
import model.MilitaryCoronaKalpi;
import model.MilitaryKalpi;
import model.MyDate;
import model.Party;
import model.Set;
import model.SickSoldier;
import model.Soldier;

public class DB_Connection {
	private Connection conn;

	// Constructor - connect DB
	public DB_Connection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connString = "jdbc:sqlserver://localhost;" + "databaseName=ElectionDB;user=maor;password=maor;";
			conn = DriverManager.getConnection(connString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// close DB
	public void closeDB() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// Delete data from tables

	// Parties
	public void deleteAllParties() {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE From Parties_tbl");
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteVotesCounter() {
		try {
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE Parties_tbl SET votesCounter = 0 WHERE votesCounter !=0");
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Kalpies
	public void deleteAllKalpies() {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE From Kalpies_tbl");
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteVotersPercent() {
		try {
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE Kalpies_tbl SET votersPercent = 0 WHERE votersPercent !=0");
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteTotalVotes() {
		try {
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE Kalpies_tbl SET totalVotes = 0 WHERE totalVotes !=0");
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Citizens
	public void deleteAllCitizens() {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE From Citizens_tbl");
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Parties Queries

	public Vector<Party> getParties() {
		try {

			Vector<Party> parties = new Vector<Party>();
			Statement stmt = conn.createStatement();
			String query = "SELECT * From Parties_tbl";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String getDBDate = rs.getString(3);

				String spl[] = getDBDate.split("/");
				int day = Integer.parseInt(spl[0]);
				int month = Integer.parseInt(spl[1]);
				int year = Integer.parseInt(spl[2]);

				MyDate date = new MyDate(day, month, year);
				Party p = new Party(rs.getString(1), rs.getString(2), date);
				parties.add(p);
			}
			stmt.close();
			rs.close();
			return parties;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// NULL?
		return null;
	}

	// add Party
	public void addParty(String name, String wing, MyDate creationDate, int votesCounter) {
		try {

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Parties_tbl VALUES (?,?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, wing);
			stmt.setString(3, creationDate.toString());
			stmt.setInt(4, votesCounter);

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// vote Party
	public void voteParty(String choise) {
		try {

			PreparedStatement stmt = conn.prepareStatement(" UPDATE Parties_tbl SET votesCounter +=1 WHERE name = ?");
			stmt.setString(1, choise);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getVotesFromParty(String name) {
		try {

			PreparedStatement stmt = conn.prepareStatement(" select votesCounter from Parties_tbl  WHERE name = ?");
			stmt.setString(1, name);
			int votes = stmt.executeUpdate();
			stmt.close();

			return votes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Kalpies Queries

	public Vector<Kalpi<?>> getKalpies() {
		try {
			Vector<Kalpi<?>> kalpies = new Vector<Kalpi<?>>();

			Statement stmt = conn.createStatement();
			String query = "SELECT * From Kalpies_tbl";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String type = rs.getString(5); // indicates type of kalpi
				String address = rs.getString(2);
				Kalpi<?> k;
				if (type.equalsIgnoreCase("Regular kalpi"))
					k = new Kalpi<Citizen>(address);
				else if (type.equalsIgnoreCase("Corona Kalpi"))
					k = new CoronaKalpi<CoronaPatient>(address);
				else if (type.equalsIgnoreCase("Military Kalpi"))
					k = new MilitaryKalpi<Soldier>(address);
				else
					k = new MilitaryCoronaKalpi<SickSoldier>(address);

				kalpies.add(k);
			}
			stmt.close();
			rs.close();
			return kalpies;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// NULL?
		return null;
	}

	// add Kalpi
	public void addKalpi(int id, String kalpiAdress, double votersPercent, int totalVotes, String type) {
		try {

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Kalpies_tbl VALUES (?,?,?,?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, kalpiAdress);
			stmt.setFloat(3, (float) votersPercent);
			stmt.setInt(4, totalVotes);
			stmt.setString(5, type);

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// vote kalpi
	public void voteKalpi(int id) {
		try {

			PreparedStatement stmt = conn.prepareStatement("UPDATE Kalpies_tbl SET totalVotes +=1 WHERE id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateVotersPercentKalpi(int id, double votersPercent) {
		try {

			PreparedStatement stmt = conn.prepareStatement("UPDATE Kalpies_tbl SET votersPercent = ? WHERE id = ?");
			stmt.setFloat(1, (float) votersPercent);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Citizens Queries
	public Set<Citizen> getCitizens() {
		try {
			Set<Citizen> citizens = new Set<Citizen>();

			Statement stmt = conn.createStatement();
			String query = "SELECT * From Citizens_tbl";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Citizen citi;
				String type = rs.getString(9);
				if (type.equalsIgnoreCase("Contender")) {
					if (rs.getString(4).equalsIgnoreCase("no"))
						citi = new Contender(rs.getString(2), rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getInt(5),
								rs.getString(6));
					else
						citi = new Contender(rs.getString(2), rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getInt(5),
								rs.getString(6), rs.getInt(8));
				} else if (type.equalsIgnoreCase("CoronaPatient"))
					citi = new CoronaPatient(rs.getString(2), rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getInt(5),
							rs.getInt(8));
				else if (type.equalsIgnoreCase("Soldier"))
					citi = new Soldier(rs.getString(2), rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getInt(5),
							rs.getString(7));
				else if (type.equalsIgnoreCase("SickSoldier"))
					citi = new SickSoldier(rs.getString(2), rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getInt(5),
							rs.getString(7), rs.getInt(8));
				else
					citi = new Citizen(rs.getString(2), rs.getInt(1), rs.getInt(3), rs.getString(4), rs.getInt(5));

				citizens.add(citi);
			}
			stmt.close();
			rs.close();
			return citizens;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// add Citizen
	public void addCitizen(Citizen citi) {
		try {
			String partyName = null;
			String carryWeapon = null;
			int sickDays = -1;
			if (citi instanceof Soldier || citi instanceof SickSoldier)
				if (citi instanceof Soldier)
					carryWeapon = ((Soldier) citi).getCarryWeapon();
				else
					carryWeapon = ((SickSoldier) citi).getCarryWeapon();

			if (citi instanceof CoronaPatient || citi instanceof SickSoldier) {
				if (citi instanceof CoronaPatient)
					sickDays = ((CoronaPatient) citi).getSickDays();
				else
					sickDays = ((SickSoldier) citi).getSickDays();
			}
			if (citi instanceof Contender) {

				sickDays = ((Contender) citi).getSickDays();
				partyName = ((Contender) citi).getPartyName();
			}
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Citizens_tbl VALUES (?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, citi.getId());
			stmt.setString(2, citi.getName());
			stmt.setInt(3, citi.getYearOfBirth());
			stmt.setString(4, citi.getIsIsolated());
			stmt.setInt(5, citi.getKaplpiNum());
			stmt.setString(6, partyName);
			stmt.setString(7, carryWeapon);
			stmt.setInt(8, sickDays);
			stmt.setString(9, citi.getClass().getSimpleName());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
