package com.anupam.repository;

import java.util.List;


public interface UserRepository {

    List<Object[]> getUsersWithHighestFollowers();

    List<Object[]> getCountByDateAndHour();

    List<Object[]> getCountByTagAndLanguage();


}
