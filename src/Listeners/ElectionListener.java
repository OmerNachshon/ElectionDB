package Listeners;

import java.util.InputMismatchException;
import java.util.Vector;

import model.Citizen;
import model.Contender;
import model.Kalpi;
import model.NotValidIdExspetion;
import model.Party;
import model.TooYoungExsepction;

public interface ElectionListener {

	void addPartyToModel(Party p);

	boolean isAlreadyPartyToModel(Party p);

	void addKalpiToModel(Kalpi<?> kalp);

	boolean isAlreadyKalpiToModel(Kalpi<?> kalp);

	boolean isAlreadyCitizenToModel(Citizen citi);

	boolean isPartyToModel(String partyName);

	void addCitizenToModel(Citizen citi) throws NotValidIdExspetion, InputMismatchException;

	void addContenderToModel(Contender con);

	void showCitizensToModel();

	void showKalpiesToModel();

	void showPartiesToModel();

	void setCitizenKalpiNumToModel(Citizen citi);

	Vector<Citizen> getAllCitizensToModel();

	Vector<Party> getAllPartiesToModel();

	void addCitizenToKalpiToModel(Citizen citi);

	void voteToModel(String choise, int kalpiNum) throws TooYoungExsepction;

	void showResultsToModel();

	void updateVotersPercentToModel();

	void resetToModel();

	
}
