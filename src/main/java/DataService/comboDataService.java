package DataService;

import Model.po.Combo;

import java.time.LocalDate;
import java.util.ArrayList;


public interface comboDataService {


        /**
         * 根据电话号码、日期查询用订购的套餐
         * @param phoneNumber
         * @param date
         * @return
         */
        public ArrayList<Combo> checkUserCombos(String phoneNumber, LocalDate date);


        /**
         *获取所有的套餐信息
         * @return
         */
        public  ArrayList<Combo>  getAllCombos();


        /**
         *添加一个新的套餐
         * @param newCombo
         * @return
         */
        public  boolean  addNewCombo(Combo newCombo);


        /**
         *订购一个套餐，立刻生效
         * @param phoneNumber
         * @param comboId
         * @return
         * 订阅成功，返回值为TRUE
         * 如果已经订阅过该套餐或者套餐名称不存在
         * 则订阅失败，返回FALSE
         */
        public boolean subscribeComboNow(String phoneNumber,int comboId);

        /**
         *订购一个套餐，次月生效
         * @param phoneNumber
         * @param comboId
         * @return
         */
        public boolean subscribeComboNextMonth(String phoneNumber,int comboId);

        /**
         *退订一个套餐，立刻生效
         * @param phoneNumber
         * @param comboId
         * @return
         * 如果未订阅过该套餐或者套餐名不存在
         * 则退订失败，返回FALSE
         */
        public boolean  unsubscribeComboNow(String phoneNumber,int comboId);

        /**
         *退订一个套，次月生效
         * @param phoneNumber
         * @param comboId
         * @return
         * 如果未订阅过该套餐或者套餐名不存在
         * 则退订失败，返回FALSE
         */
        public boolean unsubscribeComboNextMonth(String phoneNumber,int comboId);

}
