package com.durys.jakub.recruitmentapp.commons.filters;

public class CommonFilters {
    private final Integer pageSize;
    private final Integer pageNumber;

    CommonFilters(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    Integer pageSize() {
        return pageSize;
    }

    Integer pageNumber() {
        return pageNumber;
    }
}
