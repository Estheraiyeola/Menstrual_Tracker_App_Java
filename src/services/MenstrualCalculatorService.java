package services;

public interface MenstrualCalculatorService {
    public void calculateAverageMenstrualCycle(int firstMonth, int secondMonth, int thirdMonth);
    public int getAverageMenstrualCycle();
    public String calculateOvulationPeriod();
    public String calculateSafePeriods(int periodLength);
    public int calculateNextPeriodDate(int firstDayOfLastPeriod);
    public int getFirstDayOf_FirstRangeOfPeriod();
    public int getLastDayOf_FirstRangeOfPeriod();
    public int getFirstDayOf_LastRangeOfPeriod();
    public int getLastDayOf_LastRangeOfPeriod();
    public int getFirstDayOf_OvulationPeriod();
    public int getLastDayOf_OvulationPeriod();
    public int getPeriodLength();
}
