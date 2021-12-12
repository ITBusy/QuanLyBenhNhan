package com.QLBN.Poly.Service;

import java.util.List;

abstract public class SystemService<EntityType, KeyType> {

    abstract public int insert(EntityType et);

    abstract public int update(EntityType et);

    abstract public int delete(List<KeyType> list);

    abstract public EntityType selectByID(KeyType id);

    abstract public List<EntityType> getAll();

    abstract public int insertRetore(EntityType et);

    abstract public KeyType getRowLast();
}
