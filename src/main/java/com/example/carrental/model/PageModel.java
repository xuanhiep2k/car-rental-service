package com.example.carrental.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageModel {
    private int pageNumber;
    private int pageSize;
    private Sort.Direction sortDirection;
    private String sortBy;
}
