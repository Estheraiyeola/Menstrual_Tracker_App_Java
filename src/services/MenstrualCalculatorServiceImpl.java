package services;
import data.models.MenstrualCalculator;

public class MenstrualCalculatorServiceImpl implements  MenstrualCalculatorService{
    private int averageCycleLength;


    private int periodLength;
    private int firstRangeBegins;
    private int firstRangeEnds;
    private int secondRangeBegins;
    private int secondRangeEnds;
    private int ovulationPeriodBegins;

    private int ovulationPeriodEnd;
    private int nextPeriodDate;

    MenstrualCalculator menstrualCalculator = new MenstrualCalculator();


    public void calculateAverageMenstrualCycle(int firstMonth, int secondMonth, int thirdMonth) {
        this.averageCycleLength = (firstMonth + secondMonth + thirdMonth) / 3;
        menstrualCalculator.setCycleLength(this.averageCycleLength);
    }

    public int getAverageMenstrualCycle() {
        return menstrualCalculator.getCycleLength();
    }

    public String calculateOvulationPeriod() {
        this.ovulationPeriodEnd = this.averageCycleLength / 2;
        this.ovulationPeriodBegins = ovulationPeriodEnd - 2;
        return ovulationPeriodBegins + " - " + ovulationPeriodEnd;
    }

    public String calculateSafePeriods(int periodLength) {
        this.periodLength = periodLength;
        int ovulationPeriodEnd = this.averageCycleLength / 2;
        int ovulationPeriodBegins = ovulationPeriodEnd - 2;

        this.firstRangeBegins = ovulationPeriodBegins - periodLength - 1;
        this.firstRangeEnds = ovulationPeriodBegins - 1;

        this.secondRangeBegins = ovulationPeriodEnd + 1;
        this.secondRangeEnds = this.averageCycleLength - 1;
        return firstRangeBegins + " - " +firstRangeEnds + " and " + secondRangeBegins + " - " + secondRangeEnds;
    }

    public int calculateNextPeriodDate(int firstDayOfLastPeriod) {
        this.nextPeriodDate = this.averageCycleLength - firstDayOfLastPeriod;
        return this.nextPeriodDate;
    }

    public int getFirstDayOf_FirstRangeOfPeriod() {
        return this.firstRangeBegins;
    }

    public int getLastDayOf_FirstRangeOfPeriod() {
        return this.firstRangeEnds;
    }

    public int getFirstDayOf_LastRangeOfPeriod() {
        return this.secondRangeBegins;
    }

    public int getLastDayOf_LastRangeOfPeriod() {
        return this.secondRangeEnds;
    }

    public int getFirstDayOf_OvulationPeriod() {
        return this.ovulationPeriodBegins;
    }

    public int getLastDayOf_OvulationPeriod() {
        return this.ovulationPeriodEnd;
    }
    public int getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(int periodLength) {
        this.periodLength = periodLength;
    }

}
