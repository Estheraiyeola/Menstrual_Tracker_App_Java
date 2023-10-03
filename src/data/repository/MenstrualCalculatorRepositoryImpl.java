package data.repository;

import data.models.MenstrualCalculator;

import java.util.ArrayList;
import java.util.List;

public class MenstrualCalculatorRepositoryImpl implements MenstrualCalculatorRepository{
    private long count;
    private final List<MenstrualCalculator> menstrualDetails = new ArrayList<>();

    @Override
    public MenstrualCalculator findById(int id) {
        for (MenstrualCalculator menstrualDetail: menstrualDetails) {
            if (menstrualDetail.getId() == id) return menstrualDetail;
        }
        return null;
    }

    @Override
    public Iterable<MenstrualCalculator> findAll() {
        return menstrualDetails;
    }

    @Override
    public MenstrualCalculator save(MenstrualCalculator details) {
        boolean detailsDoesNotExist = details.getId() == 0;
        if (detailsDoesNotExist) saveNew(details);
        else update(details);
        return details;
    }

    private void update(MenstrualCalculator details) {
        MenstrualCalculator newDetails = findById(details.getId());
        newDetails.setDetails(details);
    }

    private void saveNew(MenstrualCalculator details) {
        details.setId(generateId());
        menstrualDetails.add(details);
        count++;
    }

    private int generateId() {
        return (int) (count + 1);
    }

    @Override
    public void delete(MenstrualCalculator details) {
        menstrualDetails.remove(details);
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void clear() {
        count -= menstrualDetails.size();
        menstrualDetails.removeAll(menstrualDetails);
    }
}
