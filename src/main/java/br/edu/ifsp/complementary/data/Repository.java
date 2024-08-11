package br.edu.ifsp.complementary.data;

import java.util.Optional;
import java.util.Set;

public interface Repository<T> {
	boolean save(T object);

	Set<T> list();

	Optional<T> findById(Long id);

	boolean update(T object);

	boolean delete(T object);
}
