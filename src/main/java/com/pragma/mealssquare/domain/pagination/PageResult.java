package com.pragma.mealssquare.domain.pagination;

import java.util.List;

public record PageResult<T>(List<T> content, int totalPages, long totalElements) {
}
