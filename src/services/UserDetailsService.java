package services;

import data.models.UserDetails;

public interface UserDetailsService {
    public void register(String firstName, String lastName, int age, String password);
    public UserDetails findUser(int id);
    public void delete(int id);
    public void clear();
    public boolean authentication(String firstName, String lastname, String password);


}
