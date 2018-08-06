package com.anupam.endpoint;


import com.anupam.pojo.AppResponse;
import com.anupam.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    private static Logger logger = LogManager.getLogger(StatisticsController.class);
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public AppResponse getUsers() {
        try {
            logger.debug("Getting user statistics.");
            List<Map<String, Object>> list = userService.getUsersWithHighestFollowers();
            return AppResponse.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AppResponse.failure(e.getMessage());
        }


    }

    @GetMapping("/hour")
    public AppResponse getPostsByHour() {
        try {
            logger.debug("Getting count by hour.");
            Map<String, Map<String, Integer>> map = userService.getCountByDateAndHour();
            return AppResponse.success(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AppResponse.failure(e.getMessage());
        }
    }

    @GetMapping("/tag")
    public AppResponse getPostsByTagAndLang() {
        try {
            logger.debug("Getting count by tag.");
            Map<String, Map<String, Integer>> map = userService.getPostsByTagAndLang();
            return AppResponse.success(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AppResponse.failure(e.getMessage());
        }

    }
}
