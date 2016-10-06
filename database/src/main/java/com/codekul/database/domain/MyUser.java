package com.codekul.database.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by aniruddha on 6/10/16.
 */

@Entity
public class MyUser {

    @Id(autoincrement = true)
    private Long idUser;

    @Property(nameInDb = "user_name")
    private String userName;

    @Property(nameInDb = "user_pasword")
    private String password;

    @Generated(hash = 2039163671)
    public MyUser(Long idUser, String userName, String password) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
    }

    @Generated(hash = 623865568)
    public MyUser() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
