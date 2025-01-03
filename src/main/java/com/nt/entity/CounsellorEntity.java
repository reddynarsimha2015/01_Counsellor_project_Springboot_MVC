package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="counsellor_tbl")
@Setter
@Getter
public class CounsellorEntity {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer counsellorId;
private String name;
private String email;
private String pwd;
private String phno;

}
