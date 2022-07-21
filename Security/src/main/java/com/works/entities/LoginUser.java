package com.works.entities;

import com.works.customvalid.StatusClass;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @NotNull(message = "E-Mail NotNull")
    @NotEmpty(message = "E-Mail NotEmpty")
    @Email(message = "E-Mail Format Fail")
    private String email;

    @Column(length = 32)
    @NotNull(message = "Password NotNull")
    @NotEmpty(message = "Password NotEmpty")
    @Length(min = 3, max = 32, message = "Password min = 3, max = 32")
    private String password;

    @StatusClass
    private String userStatus;

}
