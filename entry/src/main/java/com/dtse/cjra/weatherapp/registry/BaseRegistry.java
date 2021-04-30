package com.dtse.cjra.weatherapp.registry;

public interface BaseRegistry<V, P> {
    public P provide(V view);
}
