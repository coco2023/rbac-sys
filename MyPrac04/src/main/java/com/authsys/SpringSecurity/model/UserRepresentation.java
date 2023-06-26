package com.authsys.SpringSecurity.model;

import com.authsys.SpringSecurity.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRepresentation {

    private String userName;

    private String fullName;

    @JsonIgnoreProperties({"id"})
    private List<Role> role;
}
