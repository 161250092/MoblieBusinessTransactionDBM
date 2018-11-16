package DataServiceImpl;

import DataService.comboDataService;
import Model.Combo;
import MySQL.MySQLConnector;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;


import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class comboDataServiceImpl implements comboDataService {

    //tested
    public  ArrayList<Combo> checkUserCombos(String phoneNumber, LocalDate date) {
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        //LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        ArrayList<Combo> userCombos = new ArrayList<Combo>();

        String sql ="select * from userCombos left join comboKinds on userCombos.comboId=comboKinds.comboId "+
                "where phoneNumber=? and c_date BETWEEN ? and ?";


        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(3,java.sql.Date.valueOf(lastDayOfThisMonth));
            ResultSet rs = psmt.executeQuery();


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

    //tested
    public  ArrayList<Combo> getAllCombos() {
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        ArrayList<Combo> userCombos = new ArrayList<Combo>();
        String sql = "select * from comboKinds;";

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

    // 获取所有comboId
    public ArrayList<Integer> getAllComboId(){
        ArrayList<Integer> comboIdList = new ArrayList<Integer>();
        for(Combo combo: getAllCombos()){
            comboIdList.add(combo.getComboId());
        }
        return comboIdList;
    }

    //检查套餐ID是否存在
    public boolean isComboIdExists(int comoId){
        return getAllComboId().contains(comoId);
    }


//tested
    public boolean addNewCombo(Combo newCombo) {
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");


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

    /**
     * 查询号码是否存在
     * @param phoneNumber
     * @return
     */
    public boolean isPhoneNumberExists(String phoneNumber){
        boolean isExists = false;
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        String sql = "select balance from users where phoneNumber=?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            ResultSet rs = psmt.executeQuery();

            if(rs.next())
                isExists = true;

            psmt.close();
            conn.close();
            //System.out.println(phoneNumber+"经过验证");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(phoneNumber+"没有经过验证");
        return isExists;
    }


    public boolean hadSubscribedtheComboThisMonth(String phoneNumber,int comboId,LocalDate firstDayOfThisMonth,LocalDate lastDayOfThisMonth){
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        ArrayList<Integer> userCombos = new ArrayList<Integer>();
        String sql = "select comboId from userCombos  where phoneNumber=? and c_date between ? and ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(3,java.sql.Date.valueOf(lastDayOfThisMonth));
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                userCombos.add(rs.getInt("comboId"));
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(userCombos.contains(comboId)) {
            System.out.println(phoneNumber+" has subscrubed the combo:"+comboId);
            return true;

        }

        return false;
    }




//需要 检测手机号是否存在,comboId是否存在,是否订阅过,

    /**
     * 先检查号码是否存在
     * 在查询本月是否已经订购该套餐
     * 该方法默认订购本月套餐后自动预定下月相同套餐，订购时间是下月1号
     * @param phoneNumber
     * @param comboId
     * @return
     */
    public boolean subscribeComboNow(String phoneNumber, int comboId) {
        if(!isPhoneNumberExists(phoneNumber)) {
            System.out.println("phoneNumber doesn't exist");
            return false;
        }
        if(!isComboIdExists(comboId)) {
            System.out.println("comboId doesn't exist");
            return false;
        }
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");

        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
       // LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        if(hadSubscribedtheComboThisMonth(phoneNumber,comboId,firstDayOfThisMonth,lastDayOfThisMonth)) {
            System.out.println(phoneNumber+"本月已经订购了套餐" +comboId);
            return false;

        }
        String sql ="insert into userCombos"+"(phoneNumber,c_date,comboId)"+"value(?,?,?)";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(today));
            psmt.setInt(3,comboId);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
            System.out.println(phoneNumber+"订阅新套餐(this month works):"+comboId);

            subscribeComboNextMonth(phoneNumber,comboId);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
//
    public boolean subscribeComboNextMonth(String phoneNumber, int comboId) {
        if(!isPhoneNumberExists(phoneNumber)) {
            System.out.println("phoneNumber doesn't exist");
            return false;
        }
        if(!isComboIdExists(comboId)) {
            System.out.println("comboId doesn't exist");
            return false;
        }

        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");

        LocalDate today = LocalDate.now();
        //LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        if(hadSubscribedtheComboThisMonth(phoneNumber,comboId,firstDayOfNextMonth,firstDayOfNextMonth.plusDays(1)))
            return false;

        String sql ="insert into userCombos"+"(phoneNumber,c_date,comboId)"+"value(?,?,?)";
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
        if(!isPhoneNumberExists(phoneNumber)) {
            System.out.println("phoneNumber doesn't exist");
            return false;
        }
        if(!isComboIdExists(comboId)) {
            System.out.println("comboId doesn't exist");
            return false;
        }
        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        // LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        if(!hadSubscribedtheComboThisMonth(phoneNumber,comboId,firstDayOfThisMonth,lastDayOfThisMonth)) {
            System.out.println(phoneNumber+"没有订购"+comboId+" combo");
            return false;
        }

        String  sql = "delete from userCombos where phoneNumber=? and comboId=? and c_date between ? and ?";
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
//tested
    public boolean unsubscribeComboNextMonth(String phoneNumber, int comboId) {
        if(!isPhoneNumberExists(phoneNumber)) {
            System.out.println("phoneNumber doesn't exist");
            return false;
        }
        if(!isComboIdExists(comboId)) {
            System.out.println("comboId doesn't exist");
            return false;
        }

        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        LocalDate today = LocalDate.now();
        //LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);

        if(!hadSubscribedtheComboThisMonth(phoneNumber,comboId,firstDayOfNextMonth,firstDayOfNextMonth.plusDays(1))) {
            System.out.println(phoneNumber+"没有下个月等待生效,COMBOID:"+comboId+" combo");
            return false;

        }
        String sql = "delete from userCombos where phoneNumber=? and comboId=? and c_date=?";

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
