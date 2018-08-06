package com.anupam.repository;

import java.util.List;
import java.util.Map;


public interface UserRepository {

    List<Object[]> getUsersWithHighestFollowers();

    List<Object[]> getCountByDateAndHour();

    List<Object[]> getCountByTagAndLanguage();


}
