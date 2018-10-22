package DataServiceImpl;

import DataService.chargeDataService;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;


public class chargeDateServiceImpl implements chargeDataService {


    public boolean addCallTime(String phoneNumber, double minute) {
        return addData(phoneNumber,minute,"callDuration");
    }

    public boolean addData(String phoneNumber, double data,String columnLabel) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        Connection conn = DBUtil.getConnection();

        String sql1= "select "+ columnLabel+ " from userDataPerMonth where phoneNumber = ? and date between ? and ?";
        String sql2 ="update userDataPerMonth set "+columnLabel+"=? where phoneNumber =? and date between ? and ?";
        double checkData = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql1);
            psmt.setString(1,phoneNumber);
            psmt.setDate(2,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(3,java.sql.Date.valueOf(firstDayOfNextMonth));
            ResultSet rs = psmt.executeQuery(sql1);

            while(rs.next()){
                if(!columnLabel.equals("mailsNumber"))
                    checkData = rs.getDouble(columnLabel);
                else
                    checkData = rs.getInt(columnLabel);
            }
            psmt.close();

            PreparedStatement psmt1 = conn.prepareStatement(sql2);

            if(!columnLabel.equals("mailsNumber"))
                psmt1.setDouble(1,data+checkData);
            else
                psmt1.setInt(1,(int)(data+checkData));

            psmt1.setString(2,phoneNumber);
            psmt.setDate(3,java.sql.Date.valueOf(firstDayOfThisMonth));
            psmt.setDate(4,java.sql.Date.valueOf(lastDayOfThisMonth));
            int i = psmt.executeUpdate();
            psmt1.close();
            conn.close();
            System.out.println(i+"行数据被修改");
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean addCalledTime(String phoneNumber, double minute) {
        return addData(phoneNumber,minute,"calledDuration");
    }


    public boolean addMails(String phoneNumber, int num) {
        return addData(phoneNumber,(double)num,"mailsNumbers");
    }


    public boolean addLocalDateFlow(String phoneNumber, double flow) {
        return addData(phoneNumber,flow,"localDataFlow");
    }


    public boolean addinLandDateFlow(String phoneNumber, double flow) {
        return addData(phoneNumber,flow,"inlandData");
    }




}
