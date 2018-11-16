package DataServiceImpl;

import DataService.billDataService;
import DataService.comboDataService;
import Model.Bill;
import Model.Combo;
import Model.MobileDataPerMonth;
import MySQL.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class billDataServiceImpl  implements billDataService {
    comboDataService comboDate = new comboDataServiceImpl();

// expense in this month
    public double caculateExpense(String phoneNumber) {
        Bill bill = getUserBill(phoneNumber);
        MobileDataPerMonth  data = bill.getMoblieData();
        double callDuration = data.getCallDuration();
        int mailsNumber = data.getMailsNumber();
        double localDataFlow = data.getLocalDataFlow();
        double inlandData = data.getInlandData();
//套餐费用
        Combo comboGather =  generateCombos(bill.getCombos());
        double expense = comboGather.getCost();
//接下来算超额
        if(callDuration > comboGather.getFree_phoneTime() )
            expense +=( callDuration-comboGather.getFree_phoneTime())*comboGather.getPhone_excessCost();

        if(mailsNumber > comboGather.getFree_mails())
            expense +=(mailsNumber -  comboGather.getFree_mails())*comboGather.getMail_excessCost();

        if(localDataFlow >comboGather.getFree_localDataFlow())
            expense +=(localDataFlow - comboGather.getFree_localDataFlow())*comboGather.getLocalDataFlow_excessCost();

        if(inlandData > comboGather.getFree_inlandDataFlow())
            expense += (inlandData -comboGather.getFree_inlandDataFlow() )*comboGather.getInlandDataFlow_excessCost();


        return expense;
    }
//bill in this month
    public Bill getUserBill(String phoneNumber) {
        if(!new comboDataServiceImpl().isPhoneNumberExists(phoneNumber)){
            System.out.println("用户不存在");
            return null;
        }
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
       // LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");

        MobileDataPerMonth  dataPerMonth = null;
        String sql_data = "select * from userDataPerMonth where phoneNumber = ? and d_date between ? and ?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql_data);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(3,java.sql.Date.valueOf(lastDayOfThisMonth));
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){

                double callDuration = rs.getDouble("callDuration");
                double calledDuration = rs.getDouble("calledDuration");

                int mailsNumber = rs.getInt("mailsNumber") ;
                double localDataFlow = rs.getInt("localDataFlow");
                double inlandData = rs.getDouble("inlandData");
                dataPerMonth = new MobileDataPerMonth(phoneNumber,today,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData);

            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Bill(dataPerMonth,comboDate.checkUserCombos(phoneNumber,today));
    }

    @Override
    public double getUserBalance(String phoneNumber) {
        if(!new comboDataServiceImpl().isPhoneNumberExists(phoneNumber)){
            System.out.println("用户不存在");
            return 0;
        }
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        // LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        String sql = "select * from  users where phoneNumber = ?";
        double balance = 0;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                balance = rs.getDouble("balance");
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double expense = this.caculateExpense(phoneNumber);

        return balance-expense;
    }

    @Override
    public boolean deductUserExpense(String phoneNumber) {
        if(!new comboDataServiceImpl().isPhoneNumberExists(phoneNumber)){
            System.out.println("用户不存在");
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        if(today.compareTo(lastDayOfThisMonth)!=0) {
            System.out.println("不是月底，无法扣除费用");
            return false;
        }
        String sql = "update users set balance=? where phoneNumber=?";
        double left = this.getUserBalance(phoneNumber);

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setDouble(1,left);
            psmt.setString(2,phoneNumber);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
            System.out.println("成功扣除费用");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public double checkNewExpense(String phoneNumber,String type){
        double newExpense =0;
        if(!new comboDataServiceImpl().isPhoneNumberExists(phoneNumber)){
            System.out.println("用户不存在");
            return -1;
        }

        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        String sql = "select money from "+type+" where phoneNumber=? order by recordId desc limit 0,1";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                newExpense = rs.getDouble("money");
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  newExpense;


    }



    @Override
    public double checkNewExpenseForCalling(String phoneNumber) {
        return this.checkNewExpense(phoneNumber,"callRecords");
    }

    @Override
    public double checkNewExpenseForLocalFlow(String phoneNumber) {
        return this.checkNewExpense(phoneNumber,"inLandFlowRecords");
    }

    @Override
    public double checkNewExpenseForInlandFlow(String phoneNumber) {
        return this.checkNewExpense(phoneNumber,"LocalFlowRecords");
    }


    public Combo generateCombos(ArrayList<Combo> combos){
        int free_phoneTime = combos.get(0).getFree_phoneTime();
        double phone_excessCost = combos.get(0).getPhone_excessCost();
        int free_mails = combos.get(0).getFree_mails();
        double mail_excessCost = combos.get(0).getMail_excessCost();
        int free_localDataFlow = combos.get(0).getFree_localDataFlow();
        double localDataFlow_excessCost = combos.get(0).getLocalDataFlow_excessCost();
        int free_inlandDataFlow = combos.get(0).getFree_inlandDataFlow();
        double inlandDataFlow_excessCost = combos.get(0).getInlandDataFlow_excessCost();
        double cost =combos.get(0).getCost() ;

        for(int i=1;i<combos.size();i++){
            //免费量相加，套餐总额相加
            free_phoneTime +=combos.get(i).getFree_phoneTime();
            free_mails +=combos.get(i).getFree_mails();
            free_inlandDataFlow +=combos.get(i).getFree_inlandDataFlow();
            free_localDataFlow += combos.get(i).getFree_localDataFlow();
            cost += combos.get(i).getCost();

            //寻找最低电话超额费用
            if(combos.get(i).getPhone_excessCost() < phone_excessCost)
                phone_excessCost  = combos.get(i).getPhone_excessCost();

            //寻找最低短信超额费用
            if(combos.get(i).getMail_excessCost()<mail_excessCost)
                mail_excessCost = combos.get(i).getMail_excessCost();

            //寻找最低本地流量费用
            if(combos.get(i).getLocalDataFlow_excessCost()<localDataFlow_excessCost)
                localDataFlow_excessCost  =combos.get(i).getLocalDataFlow_excessCost();

            //寻找最低全国流量费用
            if(combos.get(i).getInlandDataFlow_excessCost()<inlandDataFlow_excessCost)
                inlandDataFlow_excessCost = combos.get(i).getInlandDataFlow_excessCost();

        }

        return new Combo(free_phoneTime,phone_excessCost,free_mails,mail_excessCost,free_localDataFlow,localDataFlow_excessCost,
        free_inlandDataFlow,inlandDataFlow_excessCost,cost);

    }

}
