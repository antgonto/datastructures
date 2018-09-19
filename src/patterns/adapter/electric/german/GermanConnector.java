package patterns.adapter.electric.german;

import patterns.adapter.electric.PlugConnector;

public class GermanConnector implements PlugConnector{

	@Override
	public void giveElectricity() {
		System.out.println("giveElectricity");
		
	}

	@Override
	public void provideElectricity() {
		System.out.println("provideElectricity");
		
	}

}
