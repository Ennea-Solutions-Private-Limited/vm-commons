package com.ennea.enneaservices.model.porter.fareEstimate;

import lombok.Data;

@Data
public class SizeDetails {
    private DimensionDetails length;
    private DimensionDetails breadth;
    private DimensionDetails height;
}
