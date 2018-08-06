package com.anupam.service;

import com.anupam.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Map<String, Object>> getUsersWithHighestFollowers() throws Exception {
        List<Map<String, Object>> mapList = new ArrayList<>();

        List<Object[]> list = userRepository.getUsersWithHighestFollowers();
        list.forEach((arr) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("userName", arr[0].toString());
            map.put("followersCount", (Integer) arr[1]);
            mapList.add(map);
        });
        return mapList;
    }

    /**
     * tag1 - {{lang1, count},{lang2,count}}, tag2-{{lang1, count},{lang2,count}}
     *
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Map<String, Integer>> getPostsByTagAndLang() throws Exception {
        List<Object[]> list = userRepository.getCountByTagAndLanguage();
        Map<String, Map<String, Integer>> mapTagLangcount = new HashMap<>();
        list.forEach((arr) -> {
            Map<String, Integer> map = mapTagLangcount.get(arr[0].toString()) == null ? new HashMap<>() : mapTagLangcount.get(arr[0].toString());
            map.put(arr[1].toString(), (Integer) arr[2]);
            mapTagLangcount.put(arr[0].toString(), map);
        });
        return mapTagLangcount;
    }

    /**
     * date1 - {{hour1, count},{hour2,count}},date2 - {{hour1, count},{hour2,count}}
     * arr[0] = Date, arr[1]= Hour, arr[2]=Count
     *
     * @throws Exception
     */
    @Override
    public Map<String, Map<String, Integer>> getCountByDateAndHour() throws Exception {
        List<Object[]> list = userRepository.getCountByDateAndHour();
        Map<String, Map<String, Integer>> mapDateHourcount = new HashMap<>();
        list.forEach((arr) -> {
            Map<String, Integer> mapHourCount = mapDateHourcount.get(arr[0].toString()) == null ? new HashMap<>() : mapDateHourcount.get(arr[0].toString());
            mapHourCount.put(arr[1].toString().concat(":00:00"), (Integer) arr[2]);
            mapDateHourcount.put(arr[0].toString(), mapHourCount);
        });
        return mapDateHourcount;
    }
}
