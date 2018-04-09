package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(profiles= Profiles.JPA)
public class UserServiceJpaTest extends UserServiceTest {

    @Autowired
    private UserService service;
}
