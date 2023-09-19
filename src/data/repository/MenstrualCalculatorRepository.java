package data.repository;

import data.models.MenstrualCalculator;

public interface MenstrualCalculatorRepository {
    MenstrualCalculator findById(int id);
    Iterable<MenstrualCalculator> findAll();
    MenstrualCalculator save(MenstrualCalculator details);
    void delete(MenstrualCalculator details);
    long count();
    void clear();
}
