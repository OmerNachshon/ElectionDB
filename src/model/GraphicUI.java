package model;

import javax.swing.JOptionPane;

public class GraphicUI implements MassageAble {
	@Override
	public void showMassage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public String getString(String msg) {
		return JOptionPane.showInputDialog(msg);
	}

}