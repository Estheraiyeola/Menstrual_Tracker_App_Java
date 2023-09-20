package data.repository;

import data.models.MenstrualCalculator;
import data.models.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsRepositoryImpl implements UserDetailsRepository{
    private long count;
    private List<UserDetails> userDetailsList = new ArrayList<>();
    @Override
    public UserDetails findById(int id) {
        for (UserDetails userDetails: userDetailsList) {
            if (userDetails.getId() == id) return userDetails;
        }
        return null;
    }

    @Override
    public Iterable<UserDetails> findAll() {
        return userDetailsList;
    }

    @Override
    public UserDetails save(UserDetails details) {
        boolean userDetailsDoesNotExist = details.getId() == 0;
        if (userDetailsDoesNotExist) saveNew(details);
        else update(details);
        return details;
    }

    private void update(UserDetails details) {
        UserDetails newDetails = findById(details.getId());
        newDetails.setDetails(details);
    }

    private void saveNew(UserDetails details) {
        details.setId(generateId());
        userDetailsList.add(details);
        count++;
    }

    private int generateId() {
        return (int) (count + 1);
    }

    @Override
    public void delete(UserDetails details) {
        userDetailsList.remove(details);
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void clear() {
        count -= userDetailsList.size();
        userDetailsList.removeAll(userDetailsList);
    }

    @Override
    public UserDetails findByUsername(String username) {
        for (UserDetails userDetails:userDetailsList) {
            if (userDetails.getUsername().equals(username)) return userDetails;
        }
        return null;
    }
}
