    package com.pragma.mealssquare.domain.model;

    import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
    import lombok.*;

    @Getter
    @Setter
    public class Pagination {
        private final int page;
        private final int size;

        public Pagination(int page, int size) {
            if (page < 0) {
                throw new IllegalArgumentException(ConstantsErrorMessage.INDEX_PAGE_INVALID);
            }
            if (size <= 0) {
                throw new IllegalArgumentException(ConstantsErrorMessage.SIZE_PAGE_INVALID);
            }
            this.page = page;
            this.size = size;
        }
    }
