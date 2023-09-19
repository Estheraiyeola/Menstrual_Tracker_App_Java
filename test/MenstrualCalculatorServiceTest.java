import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.MenstrualCalculatorService;

import static org.junit.jupiter.api.Assertions.*;

public class MenstrualCalculatorServiceTest {
    private MenstrualCalculatorService menstrualCalculatorService;
    @BeforeEach
    public void startWithThis(){
        menstrualCalculatorService = new MenstrualCalculatorService();
    }
    @Test
    public void testThatMenstrualCycleLengthCalculatorWorks(){
        menstrualCalculatorService.calculateAverageMenstrualCycle(28, 35, 25);
        assertEquals(29, menstrualCalculatorService.getAverageMenstrualCycle());
    }

    @Test
    public void testThatOvulationPeriodCalculatorWorks(){
        menstrualCalculatorService.calculateAverageMenstrualCycle(28, 35, 25);
        assertEquals(29, menstrualCalculatorService.getAverageMenstrualCycle());

        assertEquals("12 - 14", menstrualCalculatorService.calculateOvulationPeriod());
    }

    @Test
    public void testThatSafePeriodCalculatorWorks(){
        menstrualCalculatorService.calculateAverageMenstrualCycle(28, 35, 25);
        assertEquals(29, menstrualCalculatorService.getAverageMenstrualCycle());

        assertEquals("12 - 14", menstrualCalculatorService.calculateOvulationPeriod());

        assertEquals("6 - 11 and 15 - 28", menstrualCalculatorService.calculateSafePeriods(5));
    }

    @Test
    public void testThatNextPeriodDateCalculatorWorks(){
        menstrualCalculatorService.calculateAverageMenstrualCycle(28, 35, 25);
        assertEquals(29, menstrualCalculatorService.getAverageMenstrualCycle());

        assertEquals(17, menstrualCalculatorService.calculateNextPeriodDate(12));
    }


}
