package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/21 23:48
 */
@Entity
@Table(name = "te_users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long user_id;


    private  String name;

    private String createTime;

}
