package DataService;

import Model.Bill;

public interface billDataService {
    /**
     * 计算截止到今日的本月的资费
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

    /**
     * 返回用户 截止到现在本月的 余额
     * TIPS:用户数据库里的余额实际尚没有变动，
     * boolean deductUserExpense(String phoneNumber)方法才会改变数据库中用户的余额。
     * @param phoneNumber
     * @return
     */


    public double getUserBalance(String phoneNumber);


    /**
     * 扣除本月费用，只有日期为月底时调用该方法才会起效
     * @param phoneNumber
     * @return
     */
    public boolean deductUserExpense(String phoneNumber);


    /**
     * 查询最新的电话费用
     * @param phoneNumber
     * @return
     */
    public double checkNewExpenseForCalling(String phoneNumber);

    /**
     * 查询最新的本地流量费用
     * @param phoneNumber
     * @return
     */
    public double checkNewExpenseForLocalFlow(String phoneNumber);

    /**
     * 查询最新的全国流量费用
     * @param phoneNumber
     * @return
     */
    public double checkNewExpenseForInlandFlow(String phoneNumber);

}
