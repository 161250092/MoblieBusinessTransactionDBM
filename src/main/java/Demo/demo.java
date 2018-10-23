package Demo;

import DataService.billDataService;
import DataService.chargeDataService;
import DataService.comboDataService;
import DataServiceImpl.billDataServiceImpl;
import DataServiceImpl.chargeDateServiceImpl;
import DataServiceImpl.comboDataServiceImpl;
import Model.po.Combo;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class demo {
    public static void main(String args[]){
        String phone = "18805199037";
        comboDataService  combo = new comboDataServiceImpl();
        combo.subscribeComboNow(phone,2);
        
//        chargeDataService c = new chargeDateServiceImpl();
//
//        c.addCallTime(phone,100);
//        c.addMails(phone,200);
//        c.addLocalDateFlow(phone,2048);
//        c.addinLandDateFlow(phone,2048);
//
//
//
//        billDataService bill = new billDataServiceImpl();
//
//        System.out.println(bill.caculateExpense(phone));



    }

}
