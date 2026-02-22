package dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    T create(T t);
    boolean update(T t);
    boolean delete(int id);
    Optional<T> findById(int id);
    List<T> findAll();
}
