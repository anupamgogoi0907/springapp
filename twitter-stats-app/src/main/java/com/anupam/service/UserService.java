package com.anupam.service;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<Map<String, Object>> getUsersWithHighestFollowers() throws Exception;

    Map<String, Map<String, Integer>> getPostsByTagAndLang() throws Exception;

    Map<String, Map<String, Integer>> getCountByDateAndHour() throws Exception;
}
