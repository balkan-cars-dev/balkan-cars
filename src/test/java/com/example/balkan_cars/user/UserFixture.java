package com.example.balkan_cars.user;

import java.time.Instant;
import java.util.UUID;

public class UserFixture {

    private static final UUID FIXED_UUID = UUID.fromString("f87f4a8c-8559-4e4b-8dde-132333fb35eb");
    
    public static UserDto createUserDto() {
        return new UserDto(FIXED_UUID, "Petko" , "Stoyanov",
                "Petko_1.9", "miniKosmos", "08888888",
                "petko@abv.bg", "Plovdiv Trakiya", "Plovdiv", "REGULAR_USER");
    }
    public static User createUser() {
        User user = new User();
        setUserProperties(user);
        return user;
    }

    private static void setUserProperties(User user) {
        user.setId(FIXED_UUID);
        user.setBusinessId(UUID.fromString("f87f4a8c-8559-4e4b-8dde-132333fb35eb"));
        user.setFirstName("Petko");
        user.setLastName("Stoyanov");
        user.setEmail("petko@abv.bg");
        user.setNickname("Petko_1.9");
        user.setPassword("miniKosmos");
        user.setPhone("08888888");
        user.setAddress("Plovdiv Trakiya");
        user.setCity("Plovdiv");
        user.setUserType(UserType.REGULAR_USER);
        user.setLastLogin(Instant.now());
    }
}
