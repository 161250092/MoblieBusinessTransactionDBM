package Model.po;

//`comboId`   int primary key auto_increment,,
//`comboName` varchar(20),
//
//`free_phoneTime`  int,
//`phone_excessCost` double(16,2),
//
//`free_mails`   int,
//`mail_excessCost`  double(16,2),
//
//`free_localDataFlow`  int,
//`localDataFlow_excessCost`  double(16,2),
//
//`free_inlandDataFlow`  int,
//`inlandDataFlow_excessCost`  double(16,2),
//
//`cost`   double(16,2)

public class Combo {
    private int comboId = -1;
    private String comboName = "gather";

    private int free_phoneTime;
    private double  phone_excessCost;

    private int free_mails;
    private double  mail_excessCost;

    private int  free_localDataFlow;
    private double localDataFlow_excessCost;

    private int free_inlandDataFlow;
    private double inlandDataFlow_excessCost;

    private double cost;

    public Combo(String comboName,
                 int free_phoneTime, double phone_excessCost,
                 int free_mails, double mail_excessCost,
                 int free_localDataFlow, double localDataFlow_excessCost,
                 int free_inlandDataFlow, double inlandDataFlow_excessCost,
                 double cost) {
        this.comboName = comboName;
        this.free_phoneTime = free_phoneTime;
        this.phone_excessCost = phone_excessCost;
        this.free_mails = free_mails;
        this.mail_excessCost = mail_excessCost;
        this.free_localDataFlow = free_localDataFlow;
        this.localDataFlow_excessCost = localDataFlow_excessCost;
        this.free_inlandDataFlow = free_inlandDataFlow;
        this.inlandDataFlow_excessCost = inlandDataFlow_excessCost;
        this.cost = cost;
    }

    public Combo(String comboName, int free_phoneTime, double phone_excessCost,double cost) {
        this.comboName = comboName;
        this.free_phoneTime = free_phoneTime;
        this.phone_excessCost = phone_excessCost;
        this.free_mails = 0;
        this.mail_excessCost = 0.1;
        this.free_localDataFlow = 0;
        this.localDataFlow_excessCost = 3;
        this.free_inlandDataFlow = 0;
        this.inlandDataFlow_excessCost = 5;
        this.cost = cost;
    }

    public Combo(int free_phoneTime, double phone_excessCost,
                 int free_mails, double mail_excessCost,
                 int free_localDataFlow, double localDataFlow_excessCost,
                 int free_inlandDataFlow, double inlandDataFlow_excessCost,
                 double cost) {
        this.free_phoneTime = free_phoneTime;
        this.phone_excessCost = phone_excessCost;
        this.free_mails = free_mails;
        this.mail_excessCost = mail_excessCost;
        this.free_localDataFlow = free_localDataFlow;
        this.localDataFlow_excessCost = localDataFlow_excessCost;
        this.free_inlandDataFlow = free_inlandDataFlow;
        this.inlandDataFlow_excessCost = inlandDataFlow_excessCost;
        this.cost = cost;
    }

    public Combo(int comboId, String comboName,
                 int free_phoneTime, double phone_excessCost,
                 int free_mails, double mail_excessCost,
                 int free_localDataFlow, double localDataFlow_excessCost,
                 int free_inlandDataFlow, double inlandDataFlow_excessCost,
                 double cost) {
        this.comboId = comboId;
        this.comboName = comboName;
        this.free_phoneTime = free_phoneTime;
        this.phone_excessCost = phone_excessCost;
        this.free_mails = free_mails;
        this.mail_excessCost = mail_excessCost;
        this.free_localDataFlow = free_localDataFlow;
        this.localDataFlow_excessCost = localDataFlow_excessCost;
        this.free_inlandDataFlow = free_inlandDataFlow;
        this.inlandDataFlow_excessCost = inlandDataFlow_excessCost;
        this.cost = cost;
    }

    public int getComboId() {
        return comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public int getFree_phoneTime() {
        return free_phoneTime;
    }

    public double getPhone_excessCost() {
        return phone_excessCost;
    }

    public int getFree_mails() {
        return free_mails;
    }

    public double getMail_excessCost() {
        return mail_excessCost;
    }

    public int getFree_localDataFlow() {
        return free_localDataFlow;
    }

    public double getLocalDataFlow_excessCost() {
        return localDataFlow_excessCost;
    }

    public int getFree_inlandDataFlow() {
        return free_inlandDataFlow;
    }

    public double getInlandDataFlow_excessCost() {
        return inlandDataFlow_excessCost;
    }

    public double getCost() {
        return cost;
    }
}
