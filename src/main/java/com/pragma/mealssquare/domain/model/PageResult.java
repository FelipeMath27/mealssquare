package com.pragma.mealssquare.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PageResult <T>{
    private final List<T> content;
    private final int totalPages;
    private final long totalElements;
}
