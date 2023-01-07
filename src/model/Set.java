package model;

import java.util.Vector;


public class Set<T extends Comparable<Citizen>> {

   
    protected Vector<Citizen> list;

   
    public Set() {
        list = new Vector<Citizen>();
    }

    // you can add an object
    public Boolean add(Citizen obj) {
        for (Citizen t : list) {
            if (t.compareTo(obj) == 0)
                return false;
        }
        list.add(obj);
        return true;
    }

    public Citizen get(int index) {
        return list.get(index);
    }
    public void showList() {
    	for (int i = 0; i < list.size(); i++) 
    		System.out.println(list.elementAt(i));	
    }

	public int size() {
		return list.size();
	}
	public void showListUI() {
		MassageAble ui = new GraphicUI();
		StringBuffer SB = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			SB.append(list.elementAt(i).toString());
			SB.append("\n");
		}
		String showCitizens = SB.substring(0, SB.length() - 1);
		ui.showMassage("Presenting all citizens: \n" + showCitizens);

	}

}