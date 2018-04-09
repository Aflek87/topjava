package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(profiles= Profiles.DATAJPA)
public class UserServiceDataJpaTest extends UserServiceTest {

    @Autowired
    private UserService service;
}
