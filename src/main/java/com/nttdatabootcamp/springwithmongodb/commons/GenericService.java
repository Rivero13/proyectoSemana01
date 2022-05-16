package com.nttdatabootcamp.springwithmongodb.commons;

import java.util.List;

public interface GenericService<T, ID>{

    List<T> getAll();

    T findById(ID id);

    T create(T t);

    void update(ID id, T t);

    void delete(ID id);

}
