package com.dtse.cjra.domain.mapper;

public interface TransformMapper<D, E> {
    public E mapTo(D value);

    public D mapFrom(E value);
}
