import data.models.UserDetails;
import data.repository.UserDetailsRepository;
import data.repository.UserDetailsRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserDetailsRepositoryTest {
    private UserDetailsRepository userDetailsRepository;
    @BeforeEach
    public void startWithThis(){
        userDetailsRepository = new UserDetailsRepositoryImpl();
    }
    @Test
    public void testThatAPhonebook_CanBeSaved(){
        UserDetails details = new UserDetails();
        userDetailsRepository.save(details);

        assertEquals(1, userDetailsRepository.count());
    }
    @Test
    public void testThatAPhonebook_CanBeSaved_And_Be_Found(){
        UserDetails details = new UserDetails();
        details.setFirstName("Esther");
        details.setLastName("Aiyeola");
        userDetailsRepository.save(details);

        assertEquals(1, userDetailsRepository.count());
        assertEquals("Esther", userDetailsRepository.findById(1).getFirstName());
        assertEquals("Aiyeola", userDetailsRepository.findById(1).getLastName());

    }
    @Test
    public void testThatAPhonebook_CanBeSaved_And_Can_Be_Updated(){
        UserDetails details = new UserDetails();
        details.setFirstName("Esther");
        details.setLastName("Aiyeola");
        userDetailsRepository.save(details);

        assertEquals(1, userDetailsRepository.count());
        assertEquals("Esther", userDetailsRepository.findById(1).getFirstName());
        assertEquals("Aiyeola", userDetailsRepository.findById(1).getLastName());

        UserDetails updatedUserDetails = userDetailsRepository.findById(1);
        updatedUserDetails.setFirstName("Deborah");

        assertEquals("Deborah", userDetailsRepository.findById(1).getFirstName());
    }
    @Test
    public void testThatPhonebooks_CanBeSaved_And_Can_All_Be_Printed_Out(){
        UserDetails details = new UserDetails();
        details.setFirstName("Esther");
        details.setLastName("Aiyeola");
        userDetailsRepository.save(details);

        assertEquals(1, userDetailsRepository.count());
        assertEquals("Esther", userDetailsRepository.findById(1).getFirstName());
        assertEquals("Aiyeola", userDetailsRepository.findById(1).getLastName());

        UserDetails details1 = new UserDetails();
        details1.setFirstName("Deborah");
        details1.setLastName("Madison");
        userDetailsRepository.save(details1);

        assertEquals(2, userDetailsRepository.count());
        assertEquals("Deborah", userDetailsRepository.findById(2).getFirstName());
        assertEquals("Madison", userDetailsRepository.findById(2).getLastName());

        UserDetails details2 = new UserDetails();
        details2.setFirstName("Pelumi");
        details2.setLastName("Mercy");
        userDetailsRepository.save(details2);

        assertEquals(3, userDetailsRepository.count());
        assertEquals("Pelumi", userDetailsRepository.findById(3).getFirstName());
        assertEquals("Mercy", userDetailsRepository.findById(3).getLastName());



        List<UserDetails> expectedList = List.of((new UserDetails[]{details, details1, details2}));
        assertEquals(expectedList, userDetailsRepository.findAll());
    }

    @Test
    public void testThat_A_Phonebook_CanBeSaved_And_Can_Be_Deleted(){
        UserDetails details = new UserDetails();
        details.setFirstName("Esther");
        details.setLastName("Aiyeola");
        userDetailsRepository.save(details);

        assertEquals(1, userDetailsRepository.count());
        assertEquals("Esther", userDetailsRepository.findById(1).getFirstName());
        assertEquals("Aiyeola", userDetailsRepository.findById(1).getLastName());

        UserDetails details1 = new UserDetails();
        details1.setFirstName("Deborah");
        details1.setLastName("Madison");
        userDetailsRepository.save(details1);

        assertEquals(2, userDetailsRepository.count());
        assertEquals("Deborah", userDetailsRepository.findById(2).getFirstName());
        assertEquals("Madison", userDetailsRepository.findById(2).getLastName());

        UserDetails details2 = new UserDetails();
        details2.setFirstName("Pelumi");
        details2.setLastName("Mercy");
        userDetailsRepository.save(details2);

        assertEquals(3, userDetailsRepository.count());
        assertEquals("Pelumi", userDetailsRepository.findById(3).getFirstName());
        assertEquals("Mercy", userDetailsRepository.findById(3).getLastName());

        userDetailsRepository.delete(details2);
        assertNull(userDetailsRepository.findById(3));
    }
    @Test
    public void testThat_All_PhonebooksCanBeDeleted(){
        UserDetails details = new UserDetails();
        details.setFirstName("Esther");
        details.setLastName("Aiyeola");
        userDetailsRepository.save(details);

        assertEquals(1, userDetailsRepository.count());
        assertEquals("Esther", userDetailsRepository.findById(1).getFirstName());
        assertEquals("Aiyeola", userDetailsRepository.findById(1).getLastName());

        UserDetails details1 = new UserDetails();
        details1.setFirstName("Deborah");
        details1.setLastName("Madison");
        userDetailsRepository.save(details1);

        assertEquals(2, userDetailsRepository.count());
        assertEquals("Deborah", userDetailsRepository.findById(2).getFirstName());
        assertEquals("Madison", userDetailsRepository.findById(2).getLastName());

        UserDetails details2 = new UserDetails();
        details2.setFirstName("Pelumi");
        details2.setLastName("Mercy");
        userDetailsRepository.save(details2);

        assertEquals(3, userDetailsRepository.count());
        assertEquals("Pelumi", userDetailsRepository.findById(3).getFirstName());
        assertEquals("Mercy", userDetailsRepository.findById(3).getLastName());

        userDetailsRepository.clear();
        assertNull(userDetailsRepository.findById(2));
    }
}
