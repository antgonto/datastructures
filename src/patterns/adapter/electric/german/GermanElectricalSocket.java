package patterns.adapter.electric.german;

import patterns.adapter.electric.PlugConnector;

public class GermanElectricalSocket {

    public void plugIn(PlugConnector plug) {
        plug.giveElectricity();
    }
}