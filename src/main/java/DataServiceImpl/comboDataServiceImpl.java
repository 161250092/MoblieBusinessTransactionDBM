package DataServiceImpl;

import DataService.comboDataService;
import Model.po.Combo;


import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class comboDataServiceImpl implements comboDataService {

    public  ArrayList<Combo> checkUserCombos(String phoneNumber, LocalDate date) {
        Connection conn = DBUtil.getConnection();
        //LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        ArrayList<Combo> userCombos = new ArrayList<Combo>();

        String sql = "select * from userCombos left join comboKinds on userCombos.comboId=comboKinds.comboId "+
                "where phoneNumer =? and date betweend ? and ?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(3,java.sql.Date.valueOf(lastDayOfThisMonth));
            ResultSet rs = psmt.executeQuery(sql);

            while(rs.next()){
                int comboId = rs.getInt("comboId");
                String comboName = rs.getString("comboName");
                int free_phoneTime = rs.getInt("free_phoneTime");
                double phone_excessCost = rs.getDouble("phone_excessCost");
                int free_mails = rs.getInt("free_mails");
                double mail_excessCost = rs.getDouble("mail_excessCost");
                int free_localDataFlow = rs.getInt("free_localDataFlow");
                double  localDataFlow_excessCost =rs.getDouble("localDataFlow_excessCost");
                int free_inlandDataFlow = rs.getInt("free_inlandDataFlow");
                double inlandDataFlow_excessCost = rs.getDouble("inlandDataFlow_excessCost");
                double cost = rs.getDouble("cost");
                userCombos.add(new Combo( comboId,comboName,free_phoneTime,phone_excessCost,free_mails,mail_excessCost,free_localDataFlow,
                        localDataFlow_excessCost,free_inlandDataFlow,inlandDataFlow_excessCost,cost));

            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return userCombos;
    }

    public  ArrayList<Combo> getAllCombos() {
        Connection conn = DBUtil.getConnection();
        ArrayList<Combo> userCombos = new ArrayList<Combo>();
        String sql = "select * from comboKinds";

        Statement  stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet  rs = stmt.executeQuery(sql);
            while(rs.next()){
                int comboId = rs.getInt("comboId");
                String comboName = rs.getString("comboName");
                int free_phoneTime = rs.getInt("free_phoneTime");
                double phone_excessCost = rs.getDouble("phone_excessCost");
                int free_mails = rs.getInt("free_mails");
                double mail_excessCost = rs.getDouble("mail_excessCost");
                int free_localDataFlow = rs.getInt("free_localDataFlow");
                double  localDataFlow_excessCost =rs.getDouble("localDataFlow_excessCost");
                int free_inlandDataFlow = rs.getInt("free_inlandDataFlow");
                double inlandDataFlow_excessCost = rs.getDouble("inlandDataFlow_excessCost");
                double cost = rs.getDouble("cost");
                userCombos.add(new Combo(comboId,comboName,free_phoneTime,phone_excessCost,free_mails,mail_excessCost,free_localDataFlow,
                        localDataFlow_excessCost,free_inlandDataFlow,inlandDataFlow_excessCost,cost));

            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userCombos;
    }



    public boolean addNewCombo(Combo newCombo) {
        Connection conn = DBUtil.getConnection();



        String sql =""+"insert into ComboKinds"+"(comboName,free_phoneTime,phone_excessCost,free_mails," +
                "mail_excessCost,free_localDataFlow,localDataFlow_excessCost,free_inlandDataFlow,inlandDataFlow_excessCost,cost)"+
                "values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,newCombo.getComboName());
            psmt.setInt(2,newCombo.getFree_phoneTime());
            psmt.setDouble(3,newCombo.getPhone_excessCost());
            psmt.setInt(4,newCombo.getFree_mails());
            psmt.setDouble(5,newCombo.getMail_excessCost());
            psmt.setInt(6,newCombo.getFree_localDataFlow());
            psmt.setDouble(7,newCombo.getLocalDataFlow_excessCost());
            psmt.setInt(8,newCombo.getFree_inlandDataFlow());
            psmt.setDouble(9,newCombo.getInlandDataFlow_excessCost());
            psmt.setDouble(10,newCombo.getCost());

            psmt.executeUpdate();

            psmt.close();
            conn.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean subscribeComboNow(String phoneNumber, int comboId) {
        Connection conn = DBUtil.getConnection();

        LocalDate today = LocalDate.now();
        //LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
       // LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        String sql ="insert into userCombos"+"(phoneNumber,date,comboId)"+"value(?,?,?)";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(today));
            psmt.setInt(3,comboId);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
            System.out.println(phoneNumber+"订阅新套餐(this month works):"+comboId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean subscribeComboNextMonth(String phoneNumber, int comboId) {
        Connection conn = DBUtil.getConnection();

        LocalDate today = LocalDate.now();
        //LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        String sql ="insert into userCombos"+"(phoneNumber,date,comboId)"+"value(?,?,?)";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(firstDayOfNextMonth));
            psmt.setInt(3,comboId);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
            System.out.println(phoneNumber+"订阅新套餐(next month works):"+comboId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unsubscribeComboNow(String phoneNumber, int  comboId) {
        Connection conn = DBUtil.getConnection();
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        // LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        String  sql = "delete from userCombos where phoneNumber=? and comboId=? and date between ? and ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setInt(2,comboId);
            psmt.setDate(3,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(4,java.sql.Date.valueOf(lastDayOfThisMonth));
            psmt.executeUpdate();
            psmt.close();
            conn.close();
            System.out.println("删除"+phoneNumber+" " +comboId + " 套餐");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unsubscribeComboNextMonth(String phoneNumber, int comboId) {
        Connection conn = DBUtil.getConnection();
        LocalDate today = LocalDate.now();
        //LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        String sql = "delete from userCombos where phoneNumber=? and comboId=? and date=?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setInt(2,comboId);
            psmt.setDate(3,java.sql.Date.valueOf(firstDayOfNextMonth));
            psmt.executeUpdate();
            psmt.close();
            conn.close();
            System.out.println("删除"+phoneNumber+" " +comboId + " 套餐");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
