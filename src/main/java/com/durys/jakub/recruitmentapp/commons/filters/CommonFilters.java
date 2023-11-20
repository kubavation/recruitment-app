package com.durys.jakub.recruitmentapp.commons.filters;

public class CommonFilters {

    private final Integer pageSize;
    private final Integer pageNumber;

    public CommonFilters(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public Integer pageSize() {
        return pageSize;
    }

    public Integer pageNumber() {
        return pageNumber;
    }
}
