package services;
import data.models.UserDetails;
import data.repository.UserDetailsRepository;
import data.repository.UserDetailsRepositoryImpl;

public class UserDetailsService{
    UserDetailsRepository userDetailsRepository = new UserDetailsRepositoryImpl();

    public void register(String firstName, String lastName, int age) {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setAge(age);
        userDetailsRepository.save(userDetails);
    }
    public UserDetails findUser(int id){
        return userDetailsRepository.findById(id);
    }

    public void delete(int id) {
        userDetailsRepository.delete(findUser(id));
    }

    public void clear() {
        userDetailsRepository.clear();
    }
}
