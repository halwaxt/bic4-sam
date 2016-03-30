package at.technikum.bic4a16.bi.model;

import javax.ejb.Remote;

@Remote
public enum Action {
    BUY(-1), SELL(1);

    private int actionValue;

    Action(int actionValue) {
        this.actionValue = actionValue;
    }

    public int getActionValue() {
        return actionValue;
    }

    public static Action parse(int actionValue) {
        switch (actionValue) {
            case 1: return SELL;
            case -1 : return BUY;
            default: throw new RuntimeException("Action value not defined: " + actionValue);
        }
    }
}
