package com.digicert.usermanagement.domain.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
public class UserMdl {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public UserMdl(){}
    public UserMdl(String firstName, String lastName, String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }
}
