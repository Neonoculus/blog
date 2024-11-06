package org.example.blog.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int sex;
    private String phone;
    private String card;
    private String avatar;
}
