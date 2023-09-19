import data.models.MenstrualCalculator;
import data.repository.MenstrualCalculatorRepository;
import data.repository.MenstrualCalculatorRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MenstrualCalculatorRepositoryTest {
    private MenstrualCalculatorRepository menstrualCalculatorRepository;
    @BeforeEach
    public void startWithThis(){
        menstrualCalculatorRepository = new MenstrualCalculatorRepositoryImpl();
    }
    @Test
    public void testThatAPhonebook_CanBeSaved(){
        MenstrualCalculator menstrualCalculator = new MenstrualCalculator();
        menstrualCalculatorRepository.save(menstrualCalculator);

        assertEquals(1, menstrualCalculatorRepository.count());
    }
    @Test
    public void testThatAPhonebook_CanBeSaved_And_Be_Found(){
        MenstrualCalculator menstrualCalculator = new MenstrualCalculator();
        menstrualCalculator.setCycleLength(28);
        menstrualCalculatorRepository.save(menstrualCalculator);

        assertEquals(1, menstrualCalculatorRepository.count());
        assertEquals(28, menstrualCalculatorRepository.findById(1).getCycleLength());
    }
    @Test
    public void testThatAPhonebook_CanBeSaved_And_Can_Be_Updated(){
        MenstrualCalculator menstrualCalculator = new MenstrualCalculator();
        menstrualCalculator.setCycleLength(28);
        menstrualCalculatorRepository.save(menstrualCalculator);

        assertEquals(1, menstrualCalculatorRepository.count());
        assertEquals(28, menstrualCalculatorRepository.findById(1).getCycleLength());

        MenstrualCalculator updatedPhonebook = menstrualCalculatorRepository.findById(1);
        updatedPhonebook.setCycleLength(29);

        assertEquals(29, menstrualCalculatorRepository.findById(1).getCycleLength());
    }
    @Test
    public void testThatPhonebooks_CanBeSaved_And_Can_All_Be_Printed_Out(){
        MenstrualCalculator menstrualCalculator = new MenstrualCalculator();
        menstrualCalculator.setCycleLength(28);
        menstrualCalculatorRepository.save(menstrualCalculator);

        MenstrualCalculator menstrualCalculator1 = new MenstrualCalculator();
        menstrualCalculator1.setCycleLength(29);
        menstrualCalculatorRepository.save(menstrualCalculator1);

        MenstrualCalculator menstrualCalculator2 = new MenstrualCalculator();
        menstrualCalculator2.setCycleLength(25);
        menstrualCalculatorRepository.save(menstrualCalculator2);

        assertEquals(3, menstrualCalculatorRepository.count());
        assertEquals(28, menstrualCalculatorRepository.findById(1).getCycleLength());
        assertEquals(29, menstrualCalculatorRepository.findById(2).getCycleLength());
        assertEquals(25, menstrualCalculatorRepository.findById(3).getCycleLength());


        List<MenstrualCalculator> expectedList = List.of((new MenstrualCalculator[]{menstrualCalculator, menstrualCalculator1, menstrualCalculator2}));
        assertEquals(expectedList, menstrualCalculatorRepository.findAll());
    }

    @Test
    public void testThat_A_Phonebook_CanBeSaved_And_Can_Be_Deleted(){
        MenstrualCalculator menstrualCalculator = new MenstrualCalculator();
        menstrualCalculator.setCycleLength(28);
        menstrualCalculatorRepository.save(menstrualCalculator);

        MenstrualCalculator menstrualCalculator1 = new MenstrualCalculator();
        menstrualCalculator1.setCycleLength(29);
        menstrualCalculatorRepository.save(menstrualCalculator1);

        MenstrualCalculator menstrualCalculator2 = new MenstrualCalculator();
        menstrualCalculator2.setCycleLength(25);
        menstrualCalculatorRepository.save(menstrualCalculator2);

        assertEquals(3, menstrualCalculatorRepository.count());
        assertEquals(28, menstrualCalculatorRepository.findById(1).getCycleLength());
        assertEquals(29, menstrualCalculatorRepository.findById(2).getCycleLength());
        assertEquals(25, menstrualCalculatorRepository.findById(3).getCycleLength());

        menstrualCalculatorRepository.delete(menstrualCalculator2);
        assertNull(menstrualCalculatorRepository.findById(3));
    }
    @Test
    public void testThat_All_PhonebooksCanBeDeleted(){
        MenstrualCalculator menstrualCalculator = new MenstrualCalculator();
        menstrualCalculator.setCycleLength(28);
        menstrualCalculatorRepository.save(menstrualCalculator);

        MenstrualCalculator menstrualCalculator1 = new MenstrualCalculator();
        menstrualCalculator1.setCycleLength(29);
        menstrualCalculatorRepository.save(menstrualCalculator1);

        MenstrualCalculator menstrualCalculator2 = new MenstrualCalculator();
        menstrualCalculator2.setCycleLength(25);
        menstrualCalculatorRepository.save(menstrualCalculator2);

        assertEquals(3, menstrualCalculatorRepository.count());
        assertEquals(28, menstrualCalculatorRepository.findById(1).getCycleLength());
        assertEquals(29, menstrualCalculatorRepository.findById(2).getCycleLength());
        assertEquals(25, menstrualCalculatorRepository.findById(3).getCycleLength());

        menstrualCalculatorRepository.clear();
        assertNull(menstrualCalculatorRepository.findById(2));
    }
}
