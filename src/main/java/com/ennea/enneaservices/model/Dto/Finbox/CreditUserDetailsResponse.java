package com.ennea.enneaservices.model.Dto.Finbox;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreditUserDetailsResponse {
    private long total;
    private List<CreditUserDetails> userList = new ArrayList<>();
}
