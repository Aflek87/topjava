package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(profiles= Profiles.JDBC)
public class UserServiceJdbcTest extends UserServiceTest {

    @Autowired
    private UserService service;
}
