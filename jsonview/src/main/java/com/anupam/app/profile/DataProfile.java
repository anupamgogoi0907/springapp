package com.anupam.app.profile;

import org.omg.CORBA.COMM_FAILURE;

/**
 * Created by brisatc186.gogoi on 10/11/2016.
 */
public class DataProfile
{

    /**
     * [id,firstName,lastName] of {@link com.anupam.app.controller.User}
     */
    public static class CommonInfo
    {
    }

    ;

    /**
     * [id,firstName,lastName,email,phone] of {@link com.anupam.app.controller.User}
     */
    public static class BriefInfo extends CommonInfo
    {
    }

    ;

    /**
     * [id,firstName,lastName,email,phone,age,homeAddress,officeAddress] of {@link com.anupam.app.controller.User}
     */
    public static class FullInfo extends CommonInfo
    {
    }

    ;
}
