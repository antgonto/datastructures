package patterns.adapter.electric.uk;

import patterns.adapter.electric.PlugConnector;

public class UKElectricalSocket {

    public void plugIn(PlugConnector plug) {
        plug.provideElectricity();
    }
}