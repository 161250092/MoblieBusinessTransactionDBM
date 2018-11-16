package DataService;

public interface chargeDataService {


    /**
     * 增加该号码用户 本月的 呼叫时间
     * @param phoneNumber
     * @param minute
     * @return
     * 增加成功则返回TRUE
     * 失败返回TRUE
     */
    public boolean addCallTime(String phoneNumber,double minute);

    /**
     * 增加该号码用户 本月的 被呼叫时间
     * @param phoneNumber
     * @param minute
     * @return
     * 增加成功则返回TRUE
     * 失败返回TRUE
     */
    public boolean addCalledTime(String phoneNumber,double minute);

    /**
     * 增加该号码用户 本月的 短信数
     * @param phoneNumber
     * @param num
     * @return
     */
    public boolean addMails(String phoneNumber,int num);

    /**
     * 增加该号码用户 本月的 本地流量使用量
     * @param phoneNumber
     * @param flow
     * @return
     * 增加成功则返回TRUE
     * 失败返回TRUE
     */
    public boolean  addLocalDateFlow(String phoneNumber,double flow);

    /**
     * 增加该号码用户 本月的 全国流量使用量
     * @param phoneNumber
     * @param flow
     * @return
     * 增加成功则返回TRUE
     * 失败返回TRUE
     *
     */
    public boolean  addInlandDateFlow(String phoneNumber,double flow);




}
