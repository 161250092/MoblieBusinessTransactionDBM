package Model.po;

import java.text.DateFormat;
import java.time.LocalDate;

//`userId`   int,
//`month`  int,
//
//`callDuration`  double(16,2),
//`calledDuration` double(16,2),
//
//`mailsNumber`  int,
//`localDataFlow`   double(16,2),
//`inlandData`   double(16,2),
public class MobileDataPerMonth {
    String phoneNumber;
    LocalDate date;

    double callDuration;
    double calledDuration;

    int mailsNumber;
    double localDataFlow;
    double inlandData;

    public MobileDataPerMonth(String phoneNumber, LocalDate date, double callDuration, double calledDuration, int mailsNumber, double localDataFlow, double inlandData) {
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.callDuration = callDuration;
        this.calledDuration = calledDuration;
        this.mailsNumber = mailsNumber;
        this.localDataFlow = localDataFlow;
        this.inlandData = inlandData;
    }

    public double getCallDuration() {
        return callDuration;
    }

    public double getCalledDuration() {
        return calledDuration;
    }

    public int getMailsNumber() {
        return mailsNumber;
    }

    public double getLocalDataFlow() {
        return localDataFlow;
    }

    public double getInlandData() {
        return inlandData;
    }
}
