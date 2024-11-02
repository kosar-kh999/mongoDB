package com.example.mongodb.core.base;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, R extends RequestDTO, P extends ResponseDTO> {
    E toEntity(R d);

    void toEntity(R d, E e);

    default List<E> toEntity(List<R> d) {
        return d.stream().map(this::toEntity).toList();
    }

    P toDTO(E e);

    void toDTO(E e, P d);

    default List<P> toDTO(List<E> e) {
        return e.stream().map(this::toDTO).toList();
    }

    default Page<P> toDTO(Page<E> e) {
        return e.map(this::toDTO);
    }

    default void baseField(P d, E e) {
        d.setId(e.getId());
    }
}
