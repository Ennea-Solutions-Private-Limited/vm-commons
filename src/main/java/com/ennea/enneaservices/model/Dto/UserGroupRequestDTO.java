package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserGroupRequestDTO implements Serializable {

    private long id;

    private long groupAdminId;

    private String groupName;

    private List<Long> customerIds;

}
