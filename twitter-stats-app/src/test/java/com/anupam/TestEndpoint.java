package com.anupam;

import com.anupam.endpoint.StatisticsController;
import com.anupam.service.UserService;
import com.anupam.service.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class TestEndpoint {

    static MockMvc mockMvc;

    private static UserService userService;
    private static StatisticsController statisticsController;

    @BeforeClass
    public static void init() throws Exception {
        userService = mock(UserServiceImpl.class);
        statisticsController = new StatisticsController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();

        // getUsersWithHighestFollowers.
        Map<String, Object> user1 = new HashMap<String, Object>() {
            {
                put("userName", "anupam");
                put("followersCount", 10);
            }
        };
        Map<String, Object> user2 = new HashMap<String, Object>() {
            {
                put("userName", "gogoi");
                put("followersCount", 10);
            }
        };
        List<Map<String, Object>> list = Arrays.asList(user1, user2);
        when(userService.getUsersWithHighestFollowers()).thenReturn(list);

        // getCountByDateAndHour
        Map<String, Integer> mapHourCount = new HashMap<String, Integer>() {
            {
                put("10:00:00", 10);
                put("11:00:00", 20);
            }
        };
        Map<String, Map<String, Integer>> mapDateHourcount = new HashMap<String, Map<String, Integer>>() {
            {
                put("2018-08-04", mapHourCount);
                put("2018-08-05", mapHourCount);
            }
        };
        when(userService.getCountByDateAndHour()).thenReturn(mapDateHourcount);

        // getCountByTagAndLanguage
        Map<String, Integer> mapLangCount = new HashMap<String, Integer>() {
            {
                put("en", 10);
                put("pt", 20);
            }
        };
        Map<String, Map<String, Integer>> mapTagLangcount = new HashMap<String, Map<String, Integer>>() {
            {
                put("openbanking", mapLangCount);
                put("swagger", mapLangCount);
            }
        };
        when(userService.getPostsByTagAndLang()).thenReturn(mapTagLangcount);
    }

    @Test
    public void testGetUsersWithHighestFollowers() throws Exception {

        mockMvc.perform(get("/stats/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].userName", is("anupam")));
    }

    @Test
    public void testGetCountByDateAndHour() throws Exception {
        mockMvc.perform(get("/stats/hour"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.2018-08-04.10:00:00", is(10)));
    }

    @Test
    public void testGetCountByTagAndLanguage() throws Exception {
        mockMvc.perform(get("/stats/tag"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.openbanking.en", is(10)));
    }


}
