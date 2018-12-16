package pl.patryk.ztpj.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    boolean save(T t);

    boolean delete(T t);
}