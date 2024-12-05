package com.ennea.enneaservices.model.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GridSFP { // Grid Data with Query, Sorting, Filtering, Pagination

    private String query = "";
    private Integer page;
    private Integer size;
    private List<Sorter> sorters = new ArrayList<>();
    private List<Filter> filters = new ArrayList<>();

}
