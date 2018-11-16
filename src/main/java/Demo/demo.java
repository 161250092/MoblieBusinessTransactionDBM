package Demo;

import DataService.chargeDataService;
import DataService.comboDataService;
import DataService.billDataService;
import DataService.expenseRecordDataService;
import DataServiceImpl.billDataServiceImpl;
import DataServiceImpl.chargeDataServiceImpl;
import DataServiceImpl.comboDataServiceImpl;
import DataServiceImpl.expenseRecordDataServiceImpl;
import Model.Combo;

import java.time.LocalDate;


public class demo {

    LocalDate today = LocalDate.now();

    //可以通过一下实例调用对应方法，具体注释在DataService中
    chargeDataService charge = new chargeDataServiceImpl();
    comboDataService  combo = new comboDataServiceImpl();
    billDataService bill = new billDataServiceImpl();
    expenseRecordDataService record = new expenseRecordDataServiceImpl();
    long startTime=0;
    long endTime = 0;

    public static void main(String args[]){
//          new expenseRecordDataServiceImpl().addNewLocalFlowRecord("10",15);
//          System.out.println(new billDataServiceImpl().caculateExpense("10"));

        /**
         * 可以按顺序执行以下三个方法，它们验证了大部分的方法
         *
         * */
 //       new demo().testChargeDataService();
 //       new demo().testComboDataService();
  //      new demo().testBillDataService();
       new demo().testExpenseRecordService();
    }

    public void testExpenseRecordService(){


        startTime=System.currentTimeMillis();

        for(int i=0;i<=9;i++){
            record.addNewCallRecord("1880519903" + i,8);
            System.out.println("1880519903" + i+" latest expense: "+bill.checkNewExpenseForCalling("1880519903" + i));

            record.addNewInLandFlowRecord("1880519903" + i,10+i*10);
            System.out.println("1880519903" + i+" latest expense: "+bill.checkNewExpenseForInlandFlow("1880519903" + i));

            record.addNewLocalFlowRecord("1880519903" + i,10+i*10);
            System.out.println("1880519903" + i+" latest expense: "+bill.checkNewExpenseForLocalFlow("1880519903" + i));
        }
        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }


    public void testChargeDataService(){
        startTime=System.currentTimeMillis();

        for(int i=0;i<=9;i++) {
            charge.addLocalDateFlow("1880519903" + i, 100);
            charge.addCallTime("1880519903" + i,100);
            charge.addCalledTime("1880519903" + i,100);
            charge.addInlandDateFlow("1880519903" + i,100);
            charge.addMails("1880519903" + i,100);
        }

        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

    }



    public void testComboDataService(){
        //列出所有的套餐
        startTime=System.currentTimeMillis();
        int count = combo.getAllCombos().size();
        for(Combo c:combo.getAllCombos()){
            System.out.println(c.getComboId()+" "+c.getComboName());
        }
        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");


        //添加新套餐
        startTime=System.currentTimeMillis();

        for(int i=0;i<3;i++)
            combo.addNewCombo(new Combo("comboName"+count+i,100,0.5,
                    0,0.1,
                    0+i*100,2,
                    0+i*100,5,
                    20.00+i*10));

        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        //订购本月新套餐
        startTime=System.currentTimeMillis();
        for(int i=0;i<=9;i++){
            for(int j=1;j<=count;j++)
            combo.subscribeComboNow("1880519903" + i,j);
        }
        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        //订购下个月起效套餐
        startTime=System.currentTimeMillis();
        for(int i=0;i<=9;i++){
            for(int j=1;j<=count;j++)
                combo.subscribeComboNextMonth("1880519903" + i,j);
        }
        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        //查看用户本月订购套餐
        startTime=System.currentTimeMillis();
        for(int i=0;i<=9;i++)
            System.out.println("1880519903" + i+"订购了"+ combo.checkUserCombos("1880519903" + i,today).size()+"个套餐");
        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        //退订本月新套餐
        startTime=System.currentTimeMillis();
        for(int i=0;i<=5;i++){
            for(int j=1;j<=count;j++)
                combo.unsubscribeComboNow("1880519903" + i,j);
        }
        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");


        //退订套餐下月生效
        startTime=System.currentTimeMillis();
        for(int i=0;i<=5;i++){
            for(int j=1;j<=count;j++)
                combo.unsubscribeComboNextMonth("1880519903" + i,j);
        }
        endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");



    }

    public void testBillDataService(){
        startTime=System.currentTimeMillis();
        for(int i=0;i<=9;i++){
            for(int j=1;j<=4;j++)
                combo.subscribeComboNow("1880519903" + i,j);
        }
        //获取本月账单
        startTime=System.currentTimeMillis();
        for(int i=0;i<=9;i++){
            bill.getUserBill("1880519903" + i);
        }
        endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        //计算本月资费
        startTime=System.currentTimeMillis();
        for(int i=0;i<=9;i++){
           System.out.println("1880519903" + i+" 本月截止到现在资费为:" +bill.caculateExpense("1880519903" + i));
        }
        endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        startTime=System.currentTimeMillis();
        for(int i=0;i<=9;i++){
            System.out.println("1880519903" + i+" 本月截止到现在余额为:" +bill.getUserBalance("1880519903" + i));
        }
        endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        startTime=System.currentTimeMillis();
        for(int i=0;i<=9;i++){
            System.out.println("1880519903" + i+" :" +bill.deductUserExpense("1880519903" + i));
        }
        endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");




    }




}
