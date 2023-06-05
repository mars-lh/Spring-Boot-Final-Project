package com.project.java.project.springboot.model.pagination;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponse<T> {

    private List<T> data;
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;


}