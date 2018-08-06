package com.anupam;

import com.anupam.repository.UserRepository;
import com.anupam.repository.UserRepositoryImpl;
import com.anupam.service.UserService;
import com.anupam.service.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TestService {

    private static UserService userService;
    private static UserRepository userRepository;

    @BeforeClass
    public static void init() throws Exception {
        userRepository = mock(UserRepositoryImpl.class);
        userService = new UserServiceImpl(userRepository);

        // getUsersWithHighestFollowers()
        List<Object[]> listUser = Arrays.asList(new Object[]{"anupamgogoi", 10}, new Object[]{"xyz", 12});
        when(userRepository.getUsersWithHighestFollowers()).thenReturn(listUser);

        // getCountByDateAndHour()
        List<Object[]> listHour = (List<Object[]>) Arrays.asList(new Object[]{"2018-08-04", "10", 10},
                new Object[]{"2018-08-04", "11", 12});
        when(userRepository.getCountByDateAndHour()).thenReturn(listHour);

        // getCountByTagAndLanguage
        List<Object[]> listTag = (List<Object[]>) Arrays.asList(new Object[]{"openbanking", "en", 10}, new Object[]{"api", "en", 10});
        when(userRepository.getCountByTagAndLanguage()).thenReturn(listTag);
    }

    @Test
    public void testGetUsersWithHighestFollowers() throws Exception {

        assertEquals(userService.getUsersWithHighestFollowers().size(), 2);
    }

    @Test
    public void testGetCountByDateAndHour() throws Exception {
        assertEquals(userService.getCountByDateAndHour().size(), 1);
    }

    @Test
    public void testGetCountByTagAndLanguage() throws Exception {
        assertEquals(userService.getPostsByTagAndLang().size(), 2);
    }
}
