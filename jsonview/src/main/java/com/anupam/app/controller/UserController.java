package com.anupam.app.controller;

import com.anupam.app.profile.DataProfile;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brisatc186.gogoi on 10/11/2016.
 */

@RestController
@RequestMapping("/user")
public class UserController
{

    @GetMapping(path = "/full_info", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(DataProfile.FullInfo.class)
    public User getFullUserInfo()
    {
        User user = makeDummyData();
        return user;
    }

    @GetMapping(path = "/brief_info", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(DataProfile.BriefInfo.class)
    public User getBriefUserInfo()
    {
        User user = makeDummyData();
        return user;
    }

    public User makeDummyData()
    {
        User user = new User();
        user.setUserId(1);
        user.setFirstName("Json");
        user.setLastName("Bond");
        user.setEmail("json.bond@gmail.com");
        user.setPhone("11-34560987");
        user.setAge(37);
        user.setHomeAdress("LA");
        user.setOfficeAddress("CA");
        return user;
    }
}

class User
{
    @JsonView(DataProfile.CommonInfo.class)
    Integer userId;

    @JsonView(DataProfile.CommonInfo.class)
    String firstName;

    @JsonView(DataProfile.CommonInfo.class)
    String lastName;

    @JsonView({DataProfile.BriefInfo.class, DataProfile.FullInfo.class})
    String email;

    @JsonView({DataProfile.BriefInfo.class, DataProfile.FullInfo.class})
    String phone;

    @JsonView(DataProfile.FullInfo.class)
    Integer age;

    @JsonView(DataProfile.FullInfo.class)
    String homeAdress;

    @JsonView(DataProfile.FullInfo.class)
    String officeAddress;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getHomeAdress()
    {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress)
    {
        this.homeAdress = homeAdress;
    }

    public String getOfficeAddress()
    {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress)
    {
        this.officeAddress = officeAddress;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}

