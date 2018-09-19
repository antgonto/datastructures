package patterns.adapter.electric;

import patterns.adapter.electric.german.GermanConnector;
import patterns.adapter.electric.uk.UKElectricalSocket;

public class ElectricAdapterDemo {
	public static void main(String[] args){
	
		UKElectricalSocket electricalSocket = new UKElectricalSocket();
		GermanConnector ukAdapter = new GermanConnector();
		electricalSocket.plugIn(ukAdapter);
	}
}