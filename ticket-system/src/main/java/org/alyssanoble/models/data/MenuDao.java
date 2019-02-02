package org.alyssanoble.models.data;


import org.alyssanoble.models.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuDao extends CrudRepository<Menu, Integer> {
}