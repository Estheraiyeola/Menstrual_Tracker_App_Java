package services;
import data.models.MenstrualCalculator;

public class MenstrualCalculatorService {
    private int averageCycleLength;
    private int periodLength;
    MenstrualCalculator menstrualCalculator = new MenstrualCalculator();


    public void calculateAverageMenstrualCycle(int firstMonth, int secondMonth, int thirdMonth) {
        this.averageCycleLength = (firstMonth + secondMonth + thirdMonth) / 3;
        menstrualCalculator.setCycleLength(this.averageCycleLength);
    }

    public int getAverageMenstrualCycle() {
        return menstrualCalculator.getCycleLength();
    }

    public String calculateOvulationPeriod() {
        int ovulationPeriodEnd = this.averageCycleLength / 2;
        int ovulationPeriodBegins = ovulationPeriodEnd - 2;
        return ovulationPeriodBegins + " - " + ovulationPeriodEnd;
    }

    public String calculateSafePeriods(int periodLength) {
        this.periodLength = periodLength;
        int ovulationPeriodEnd = this.averageCycleLength / 2;
        int ovulationPeriodBegins = ovulationPeriodEnd - 2;

        int firstRangeBegins = ovulationPeriodBegins - periodLength - 1;
        int firstRangeEnds = ovulationPeriodBegins - 1;

        int secondRangeBegins = ovulationPeriodEnd + 1;
        int secondRangeEnds = this.averageCycleLength - 1;
        return firstRangeBegins + " - " +firstRangeEnds + " and " + secondRangeBegins + " - " + secondRangeEnds;
    }

    public int calculateNextPeriodDate(int firstDayOfLastPeriod) {

        return this.averageCycleLength - firstDayOfLastPeriod;
    }
}
