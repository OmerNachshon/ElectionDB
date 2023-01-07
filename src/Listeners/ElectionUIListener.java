package Listeners;

import java.util.InputMismatchException;
import java.util.Vector;

import model.Citizen;
import model.Contender;
import model.Kalpi;
import model.NotValidIdExspetion;
import model.Party;
import model.TooYoungExsepction;

public interface ElectionUIListener {
	void addPartyToUI(Party p);

	boolean isAlreadyPartyToUI(Party p);

	void addKalpiToUI(Kalpi<?> kalp);

	boolean isAlreadyKalpiToUI(Kalpi<?> kalp);

	boolean isAlreadyCitizenToUI(Citizen citi);

	boolean isPartyToUI(String partyName);

	void addCitizenToUI(Citizen citi) throws InputMismatchException, NotValidIdExspetion ;

	void addContenderToUI(Contender con) throws InputMismatchException, NotValidIdExspetion;

	void showCitizensToUI();

	void showKalpiesToUI();

	void showPartiesToUI();

	void setCitizenKalpiNumToUI(Citizen citi);

	Vector<Citizen> getAllCitizensToUI();

	Vector<Party> getAllPartiesToUI();

	void addCitizenToKalpiToUI(Citizen citi);

	void voteToUI(String choise, int kalpiNum) throws TooYoungExsepction;

	void showResultsToUI();

	void updateVotersPercentToUI();

	void resetToUI();

}
