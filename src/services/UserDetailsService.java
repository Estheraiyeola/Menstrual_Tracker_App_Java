package services;
import data.models.UserDetails;
import data.repository.UserDetailsRepository;
import data.repository.UserDetailsRepositoryImpl;

import java.util.Objects;

public class UserDetailsService{
    UserDetailsRepository userDetailsRepository = new UserDetailsRepositoryImpl();

    public void register(String firstName, String lastName, int age, String password) {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setAge(age);
        userDetails.setPassword(password);
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
    public boolean authentication(String firstName, String lastname, String password){
        UserDetails userDetails = userDetailsRepository.findByUsername(firstName + " " + lastname);
        if (Objects.equals(userDetails.getPassword(), password)){
            return true;
        }
        return false;
    }
}
