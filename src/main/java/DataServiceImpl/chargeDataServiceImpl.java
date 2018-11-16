package DataServiceImpl;

import DataService.chargeDataService;
import MySQL.MySQLConnector;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;


public class chargeDataServiceImpl implements chargeDataService {


    public boolean addCallTime(String phoneNumber, double minute) {
        return addData(phoneNumber,minute,"callDuration");
    }

    public double getPreviousDataThisMonth(String phoneNumber, String columnLabel){
        if(!new comboDataServiceImpl().isPhoneNumberExists(phoneNumber)){
            return -1;
        }


        double previousData = 0;
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");

        String sql= "select "+columnLabel+" from userDataPerMonth where phoneNumber = ? and d_date between ? and ?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(3,java.sql.Date.valueOf(lastDayOfThisMonth));
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                if(!columnLabel.equals("mailsNumber"))
                    previousData = rs.getDouble(columnLabel);
                else
                    previousData = rs.getInt(columnLabel);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return previousData;
    }



    public boolean addData(String phoneNumber, double data,String columnLabel) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        //LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");


        String sql ="update userDataPerMonth set "+columnLabel+"=? where phoneNumber =? and d_date between ? and ?";
        double previousDataThisMonth = getPreviousDataThisMonth(phoneNumber,columnLabel);
        if(previousDataThisMonth==-1)
        {
            System.out.println("phoneNumber"+phoneNumber+"doesn't exist");
            return false;
        }

        // System.out.println(previousDataThisMonth+";"+data);


        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            if(columnLabel.equals("mailsNumber"))
                psmt.setInt(1,(int)(previousDataThisMonth+data) );
            else
                psmt.setDouble(1,previousDataThisMonth+data);

            psmt.setString(2,phoneNumber);
            psmt.setDate(3,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(4,java.sql.Date.valueOf(lastDayOfThisMonth));
            psmt.executeUpdate();
            psmt.close();
            conn.close();
            System.out.println(phoneNumber+" add "+columnLabel+" "+(previousDataThisMonth+data));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;

    }

    public boolean addCalledTime(String phoneNumber, double minute) {
        return addData(phoneNumber,minute,"calledDuration");
    }


    public boolean addMails(String phoneNumber, int num) {
        return addData(phoneNumber,(double)num,"mailsNumber");
    }


    public boolean addLocalDateFlow(String phoneNumber, double flow) {
        return addData(phoneNumber,flow,"localDataFlow");
    }


    public boolean addInlandDateFlow(String phoneNumber, double flow) {
        return addData(phoneNumber,flow,"inlandData");
    }




}
