import data.models.UserDetails;
import data.repository.MenstrualCalculatorRepository;
import data.repository.UserDetailsRepository;
import data.repository.UserDetailsRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.UserDetailsService;
import data.repository.UserDetailsRepository;

import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsServiceTest {
    private UserDetailsService userDetailsService;
    @BeforeEach
    public void startWithThis(){
        userDetailsService = new UserDetailsService();
    }
    @Test
    public void testThat_A_User_CanRegister(){
        userDetailsService.register("Esther", "Aiyeola", 25, "password");
        assertEquals("Esther", userDetailsService.findUser(1).getFirstName());
    }

    @Test
    public void testThat_More_Users_CanRegister_AndBeFound(){
        userDetailsService.register("Esther", "Aiyeola", 25, "password");
        userDetailsService.register("Sola", "Java", 25, "password");
        userDetailsService.register("Bola", "Syntax", 25, "password");
        userDetailsService.register("Deborah", "Maven", 25, "password");

        assertEquals("Esther", userDetailsService.findUser(1).getFirstName());
        assertEquals("Sola", userDetailsService.findUser(2).getFirstName());
        assertEquals("Bola", userDetailsService.findUser(3).getFirstName());
        assertEquals("Deborah", userDetailsService.findUser(4).getFirstName());

    }
    @Test
    public void testThat_A_User_CanUpdateTheirDetails(){
        userDetailsService.register("Esther", "Aiyeola", 25,"password");
        userDetailsService.register("Sola", "Java", 25, "password");
        userDetailsService.register("Bola", "Syntax", 25, "password");
        userDetailsService.register("Deborah", "Maven", 25, "password");

        assertEquals("Esther", userDetailsService.findUser(1).getFirstName());
        assertEquals("Sola", userDetailsService.findUser(2).getFirstName());
        assertEquals("Bola", userDetailsService.findUser(3).getFirstName());
        assertEquals("Deborah", userDetailsService.findUser(4).getFirstName());

        UserDetails updatedDetails = userDetailsService.findUser(3);
        updatedDetails.setFirstName("Tola");

        assertEquals("Tola", userDetailsService.findUser(3).getFirstName());

    }
    @Test
    public void testThat_A_User_CanDeleteTheirDetails(){
        userDetailsService.register("Esther", "Aiyeola", 25, "password");
        userDetailsService.register("Sola", "Java", 25, "password");
        userDetailsService.register("Bola", "Syntax", 25, "password");
        userDetailsService.register("Deborah", "Maven", 25, "password");

        assertEquals("Esther", userDetailsService.findUser(1).getFirstName());
        assertEquals("Sola", userDetailsService.findUser(2).getFirstName());
        assertEquals("Bola", userDetailsService.findUser(3).getFirstName());
        assertEquals("Deborah", userDetailsService.findUser(4).getFirstName());

        userDetailsService.delete(1);
        assertNull(userDetailsService.findUser(1));

    }
    @Test
    public void testThatAll_UsersCanBeCleared(){
        userDetailsService.register("Esther", "Aiyeola", 25, "password");
        userDetailsService.register("Sola", "Java", 25, "password");
        userDetailsService.register("Bola", "Syntax", 25, "password");
        userDetailsService.register("Deborah", "Maven", 25, "password");

        assertEquals("Esther", userDetailsService.findUser(1).getFirstName());
        assertEquals("Sola", userDetailsService.findUser(2).getFirstName());
        assertEquals("Bola", userDetailsService.findUser(3).getFirstName());
        assertEquals("Deborah", userDetailsService.findUser(4).getFirstName());

        userDetailsService.clear();
        assertNull(userDetailsService.findUser(1));
        assertNull(userDetailsService.findUser(2));
        assertNull(userDetailsService.findUser(3));
        assertNull(userDetailsService.findUser(4));

    }
    @Test
    public void testThatIf_Password_isIncorrect_User_Cannnot_Login(){
        userDetailsService.register("Esther", "Aiyeola", 25, "password");
        userDetailsService.register("Sola", "Java", 25, "password");
        userDetailsService.register("Bola", "Syntax", 25, "password");
        userDetailsService.register("Deborah", "Maven", 25, "password");

        assertEquals("Esther", userDetailsService.findUser(1).getFirstName());
        assertEquals("Sola", userDetailsService.findUser(2).getFirstName());
        assertEquals("Bola", userDetailsService.findUser(3).getFirstName());
        assertEquals("Deborah", userDetailsService.findUser(4).getFirstName());

        assertFalse(userDetailsService.authentication("Esther", "Aiyeola", "pass"));

    }
}
