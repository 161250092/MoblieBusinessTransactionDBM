package DataService;

import Model.po.Bill;

public interface billDataService {
    /**
     * 计算截本月的资费
     * @param phoneNumber
     * @return
     */
    public double caculateExpense(String phoneNumber);


    /**
     * 返回该用户  本月的  账单
     * 账单包含该月移动数据量、订购套餐
     * @param phoneNumber
     * @return
     */
    public Bill getUserBill(String phoneNumber);

}
