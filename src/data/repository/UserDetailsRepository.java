package data.repository;

import data.models.UserDetails;

public interface UserDetailsRepository {
    UserDetails findById(int id);
    Iterable<UserDetails> findAll();
    UserDetails save(UserDetails details);
    void delete(UserDetails details);
    long count();
    void clear();
    UserDetails findByUsername(String username);
}
