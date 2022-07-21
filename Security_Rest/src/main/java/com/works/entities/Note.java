package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "title NotNull")
    @NotEmpty(message = "title NotEmpty")
    @Length(min = 3, max = 50, message = "title min = 3, max = 50")
    private String title;

    @NotNull(message = "detail NotNull")
    @NotEmpty(message = "detail NotEmpty")
    @Length(min = 3, max = 250, message = "detail min = 3, max = 250")
    private String detail;
    private Date date;

    @NotNull(message = "extrakey NotNull")
    @NotEmpty(message = "extrakey NotEmpty")
    @Length(min = 3, max = 50, message = "extrakey min = 3, max = 50")
    private String extrakey;


}
