    package com.pragma.mealssquare.domain.pagination;

    import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;

        public record Pagination(int page, int size) {
        public Pagination {
            if (page < 0) {
                throw new IllegalArgumentException(ConstantsErrorMessage.INDEX_PAGE_INVALID);
            }
            if (size <= 0) {
                throw new IllegalArgumentException(ConstantsErrorMessage.SIZE_PAGE_INVALID);
            }
        }
        }
