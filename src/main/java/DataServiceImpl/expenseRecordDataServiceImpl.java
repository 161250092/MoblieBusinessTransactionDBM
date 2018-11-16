package DataServiceImpl;

import DataService.expenseRecordDataService;
import MySQL.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
//
//create table if not exists `inLandFlowRecords`{
//        `recordId`  int primary key auto_increment,
//        `phoneNumber` varchar(20),
//        `flow`  double(16,2),
//        `flow_date` date,
//        `money`  double(16,2)
//        }
//
//        create table if not exists `LocalFlowRecords`{
//        `recordId`  int primary key auto_increment,
//        `phoneNumber` varchar(20),
//        `flow`  double(16,2),
//        `flow_date` date,
//        `money`  double(16,2)
//        }


public class expenseRecordDataServiceImpl implements expenseRecordDataService {
    String sql0="insert into callRecords(phoneNumber,lastTime,call_date,money)values(?,?,?,?)";
    String sql1="insert into inLandFlowRecords(phoneNumber,flow,flow_date,money)values(?,?,?,?)";
    String sql2="insert into LocalFlowRecords(phoneNumber,flow,flow_date,money)values(?,?,?,?)";


    public boolean addRecord(String phoneNumber,double quantity,int type){
        if(!new comboDataServiceImpl().isPhoneNumberExists(phoneNumber)){
            System.out.println("用户不存在");
            return false;
        }
        if(quantity<0){
            System.out.println("输入有误");
            return false;
        }

        LocalDate today = LocalDate.now();
        String sql="";

        double bill_before = new billDataServiceImpl().caculateExpense(phoneNumber);

        if(type==0) {
            sql = sql0;
            new chargeDataServiceImpl().addCallTime(phoneNumber,quantity);
        }
        else if(type==1) {
            sql = sql1;
            new chargeDataServiceImpl().addInlandDateFlow(phoneNumber,quantity);
        }
        else if(type==2){
            sql = sql2;
            new chargeDataServiceImpl().addLocalDateFlow(phoneNumber,quantity);
        }

        double bill_after = new billDataServiceImpl().caculateExpense(phoneNumber);
        //System.out.println(bill_before+";"+bill_after);


        Connection conn = new MySQLConnector().getConnection("mobilebussinessDB");
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,phoneNumber);
            psmt.setDouble(2,quantity);
            psmt.setDate(3,java.sql.Date.valueOf(today));
            psmt.setDouble(4,bill_after-bill_before);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
            System.out.println(phoneNumber+" inserted,type:"+type+" ,quantity:"+quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean addNewCallRecord(String phoneNumber, double lastTime) {
        return this.addRecord(phoneNumber,lastTime,0);
    }

    @Override
    public boolean addNewInLandFlowRecord(String phoneNumber, double flow) {
        return this.addRecord(phoneNumber,flow,1);
    }

    @Override
    public boolean addNewLocalFlowRecord(String phoneNumber, double flow) {
        return this.addRecord(phoneNumber,flow,2);
    }

}
