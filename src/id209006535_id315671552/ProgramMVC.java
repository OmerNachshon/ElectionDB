package id209006535_id315671552;

import java.util.InputMismatchException;

import Controller.ElctionController;
import View.AbstractElectionView;
import View.ElectionFXView;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Citizen;
import model.Contender;
import model.CoronaKalpi;
import model.CoronaPatient;
import model.ElectionMVC;
import model.Kalpi;
import model.MilitaryCoronaKalpi;
import model.MilitaryKalpi;
import model.MyDate;
import model.NotValidIdExspetion;
import model.Party;
import model.SickSoldier;
import model.Soldier;

public class ProgramMVC extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ElectionMVC theModel = new ElectionMVC();
		// HardCode(theModel);
		AbstractElectionView View = new ElectionFXView(primaryStage);
		ElctionController controller = new ElctionController(theModel, View);
	}

	public static void main(String[] args) throws InputMismatchException, NotValidIdExspetion {
		launch(args);
	}

	public void HardCode(ElectionMVC kneset) throws InputMismatchException, NotValidIdExspetion {
		MyDate date1 = new MyDate(16, 4, 1997);
		MyDate date2 = new MyDate(3, 2, 2001);
		MyDate date3 = new MyDate(8, 2, 1997);
		Party p1 = new Party("olgaShlita", "center", date1);
		Party p2 = new Party("RozenTSVAIG", "center", date2);
		Party p3 = new Party("yegerrr", "center", date3);
		Kalpi<Citizen> k1 = new Kalpi<Citizen>("Hod hasharon");
		Kalpi<CoronaPatient> k2 = new CoronaKalpi<CoronaPatient>("Bne brak");
		Kalpi<Soldier> k3 = new MilitaryKalpi<Soldier>("Beer shva");
		Kalpi<SickSoldier> k4 = new MilitaryCoronaKalpi<SickSoldier>("afula");
		Contender con1 = new Contender("aiai", 209006511, 1997, "no", 1, "olgaShlita");
		Contender con2 = new Contender("bibi", 209006512, 1997, "no", 1, "olgaShlita");
		Contender con3 = new Contender("cici", 209006513, 1999, "no", 3, "RozenTSVAIG");
		Contender con4 = new Contender("didi", 209006514, 2000, "no", 3, "RozenTSVAIG");
		Contender con5 = new Contender("fifi", 209006515, 1997, "yes", 2, "yegerrr");
		Contender con6 = new Contender("hihi", 209006516, 1997, "yes", 2, "yegerrr");
		Citizen citi1 = new Citizen("aoao", 209006517, 1997, "no", 1);
		Citizen citi2 = new Citizen("bobo", 209006518, 1997, "no", 1);
		Citizen citi3 = new CoronaPatient("coco", 209006519, 1997, "yes", 2, 0);
		Citizen citi4 = new CoronaPatient("dodo", 209006520, 1997, "yes", 2, 0);
		Soldier citi5 = new Soldier("fofo", 209006521, 2001, "no", 4, "yes");
		SickSoldier citi6 = new SickSoldier("gogo", 209096522, 2001, "yes", 4, "yes", 15);
		kneset.addCitizen(citi1);
		kneset.addCitizen(citi2);
		kneset.addCitizen(citi3);
		kneset.addCitizen(citi4);
		kneset.addCitizen(citi5);
		kneset.addCitizen(citi6);
		kneset.addCitizen(con1);
		kneset.addCitizen(con2);
		kneset.addCitizen(con3);
		kneset.addCitizen(con4);
		kneset.addCitizen(con5);
		kneset.addCitizen(con6);
		kneset.addParty(p1);
		kneset.addParty(p2);
		kneset.addParty(p3);
		kneset.addKalpi(k1);
		kneset.addKalpi(k2);
		kneset.addKalpi(k3);
		kneset.addKalpi(k4);
		p1.addContender(con1);
		p1.addContender(con2);
		p2.addContender(con3);
		p2.addContender(con4);
		p3.addContender(con5);
		p3.addContender(con6);
		kneset.addCitizenToKalpi(citi1);
		kneset.addCitizenToKalpi(citi2);
		kneset.addCitizenToKalpi(citi3);
		kneset.addCitizenToKalpi(citi4);
		kneset.addCitizenToKalpi(citi5);
		kneset.addCitizenToKalpi(con1);
		kneset.addCitizenToKalpi(con2);
		kneset.addCitizenToKalpi(con3);
		kneset.addCitizenToKalpi(con4);
		kneset.addCitizenToKalpi(con5);
		kneset.addCitizenToKalpi(con6);

	}
}
