package Controller;

import Listeners.ElectionUIListener;

import java.util.InputMismatchException;
import java.util.Vector;

import Listeners.ElectionListener;
import model.Citizen;
import model.Contender;
import model.ElectionMVC;
import model.Kalpi;
import model.NotValidIdExspetion;
import model.Party;
import model.TooYoungExsepction;
import View.AbstractElectionView;

public class ElctionController implements ElectionUIListener, ElectionListener {
	private ElectionMVC electionModel;
	private AbstractElectionView electionView;

	public ElctionController(ElectionMVC model, AbstractElectionView view) {
		electionModel = model;
		electionView = view;

		electionModel.registerListener(this);
		electionView.registerListener(this);
	}

	@Override
	public void addPartyToModel(Party p) {
		electionView.addPartyToUI(p);

	}

	@Override
	public boolean isAlreadyPartyToModel(Party p) {
		return electionView.isAlreadyPartyToUI(p);
	}

	@Override
	public void addKalpiToModel(Kalpi<?> kalp) {
		electionView.addKalpiToUI(kalp);
	}

	@Override
	public boolean isAlreadyKalpiToModel(Kalpi<?> kalp) {
		return electionView.isAlreadyKalpiToUI(kalp);
	}

	@Override
	public boolean isAlreadyCitizenToModel(Citizen citi) {
		return electionView.isAlreadyCitizenToUI(citi);
	}

	@Override
	public boolean isPartyToModel(String partyName) {
		return electionView.isPartyToUI(partyName);
	}

	@Override
	public void addCitizenToModel(Citizen citi) throws NotValidIdExspetion, InputMismatchException {
		electionView.addCitizenToUI(citi);
	}

	@Override
	public void addContenderToModel(Contender con) {
		electionView.addContenderToUI(con);
	}

	@Override
	public void showCitizensToModel() {
		electionView.showCitizensToUI();
	}

	@Override
	public void showKalpiesToModel() {
		electionView.showKalpiesToUI();
	}

	@Override
	public void showPartiesToModel() {
		electionView.showPartiesToUI();
	}

	@Override
	public void setCitizenKalpiNumToModel(Citizen citi) {
		electionView.setCitizenKalpiNumToUI(citi);
	}

	@Override
	public Vector<Citizen> getAllCitizensToModel() {
		return electionView.getAllCitizensToUI();
	}

	@Override
	public Vector<Party> getAllPartiesToModel() {
		return electionView.getAllPartiesToUI();
	}

	@Override
	public void addCitizenToKalpiToModel(Citizen citi) {
		electionView.addCitizenToKalpiToUI(citi);
	}

	@Override
	public void voteToModel(String choise, int kalpiNum) throws TooYoungExsepction {
		electionView.voteToUI(choise, kalpiNum);
	}

	@Override
	public void showResultsToModel() {
		electionView.showResultsToUI();
	}

	@Override
	public void updateVotersPercentToModel() {
		electionView.updateVotersPercentToUI();
	}

	@Override
	public void resetToModel() {
		electionView.resetToUI();
	}

	// To - UI
	@Override
	public void addPartyToUI(Party p) {
		electionModel.addParty(p);
	}

	@Override
	public boolean isAlreadyPartyToUI(Party p) {
		return electionModel.isAlreadyParty(p);
	}

	@Override
	public void addKalpiToUI(Kalpi<?> kalp) {
		electionModel.addKalpi(kalp);
	}

	@Override
	public boolean isAlreadyKalpiToUI(Kalpi<?> kalp) {
		return electionModel.isAlreadyKalpi(kalp);
	}

	@Override
	public boolean isAlreadyCitizenToUI(Citizen citi) {
		return electionModel.isAlreadyCitizen(citi);
	}

	@Override
	public boolean isPartyToUI(String partyName) {
		return electionModel.isParty(partyName);
	}

	@Override
	public void addCitizenToUI(Citizen citi) throws InputMismatchException, NotValidIdExspetion {
		electionModel.addCitizen(citi);
	}

	@Override
	public void addContenderToUI(Contender con) throws InputMismatchException, NotValidIdExspetion {
		electionModel.addContender(con);
	}

	@Override
	public void showCitizensToUI() {
		electionModel.showCitizensUI();
	}

	@Override
	public void showKalpiesToUI() {
		electionModel.showKalpiesUI();
	}

	@Override
	public void showPartiesToUI() {
		electionModel.showPartiesUI();
	}

	@Override
	public void setCitizenKalpiNumToUI(Citizen citi) {
		electionModel.setCitizenKalpiNum(citi);
	}

	@Override
	public Vector<Citizen> getAllCitizensToUI() {
		return electionModel.getAllCitizens();
	}

	@Override
	public Vector<Party> getAllPartiesToUI() {
		return electionModel.getAllParties();
	}

	@Override
	public void addCitizenToKalpiToUI(Citizen citi) {
		electionModel.addCitizenToKalpi(citi);
	}

	@Override
	public void voteToUI(String choise, int kalpiNum) throws TooYoungExsepction {
		electionModel.vote(choise, kalpiNum);
	}

	@Override
	public void showResultsToUI() {
		electionModel.showResultsUI();
	}

	@Override
	public void updateVotersPercentToUI() {
		electionModel.updateVotersPercent();
	}

	@Override
	public void resetToUI() {
		electionModel.reset();
	}

}
