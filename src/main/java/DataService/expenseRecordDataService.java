package DataService;

public interface expenseRecordDataService {
    /**
     * 通话记录表添加新的通话记录
     * 月数据相关信息更新
     * @param phoneNumber
     * @param lastTime
     * @return
     */
    public boolean addNewCallRecord(String phoneNumber,double lastTime);

    /**
     * 本地流量记录表添加新的流量使用记录
     * 月数据相关信息更新
     * @param phoneNumber
     * @param flow
     * @return
     */
    public boolean addNewLocalFlowRecord(String phoneNumber,double flow);

    /**
     * 全国流量记录表添加新的流量使用信息
     * 月数据相关信息更新
     * @param phoneNumber
     * @param flow
     * @return
     */
    public boolean addNewInLandFlowRecord(String phoneNumber,double flow);





}
