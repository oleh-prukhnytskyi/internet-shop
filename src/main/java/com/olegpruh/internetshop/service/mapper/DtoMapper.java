package com.olegpruh.internetshop.service.mapper;

public interface DtoMapper<D, T> {
    T mapToModel(D dto);
}
