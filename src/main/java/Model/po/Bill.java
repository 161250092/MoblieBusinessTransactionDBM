package Model.po;

import java.util.ArrayList;

public class Bill {

    MobileDataPerMonth moblieData;
    ArrayList<Combo> combos;

    public Bill(MobileDataPerMonth moblieData,ArrayList<Combo>  combos) {
        this.moblieData = moblieData;
        this.combos = combos;
    }

    public MobileDataPerMonth getMoblieData() {
        return moblieData;
    }

    public ArrayList<Combo> getCombos() {
        return combos;
    }
}
