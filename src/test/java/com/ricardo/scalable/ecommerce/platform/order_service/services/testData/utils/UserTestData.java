package com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Role;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.User;

public class UserTestData {

    public static List<User> createListOfUsers() {
        User user1 = createUser001().orElseThrow();
        User user2 = createUser002().orElseThrow();

        return List.of(user1, user2);
    }

    public static Optional<User> createUser001() {
        User user1 = new User();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();

        role.setId(1L);
        role.setName("ROLE_USER");

        roles.add(role);

        user1.setId(1L);
        user1.setAvatar("avatar1.jpg");
        user1.setFirstName("Ricardo");
        user1.setLastName("Retamal");
        user1.setUsername("ricardo10");
        user1.setEmail("ricardo@gmail.com");
        user1.setPassword("ricardo12345");
        user1.setBirthDate(LocalDate.of(1996, 4, 10));
        user1.setPhoneNumber("+56912345678");
        user1.setEnabled(true);
        user1.setAdmin(false);
        user1.setRoles(roles);
        user1.setCreatedAt(Timestamp.from(Instant.now()));

        return Optional.of(user1);
    }

    public static Optional<User> createUser002() {
        User user = new User();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();

        role.setId(2L);
        role.setName("ROLE_USER");

        roles.add(role);

        user.setId(2L);
        user.setAvatar("avatar2.jpg");
        user.setFirstName("Mateo");
        user.setLastName("Retamal");
        user.setUsername("mateo10");
        user.setEmail("mateo@gmail.com");
        user.setPassword("mateo12345");
        user.setBirthDate(LocalDate.of(2024, 9, 1));
        user.setPhoneNumber("+56987654321");
        user.setEnabled(true);
        user.setAdmin(false);
        user.setRoles(roles);
        user.setCreatedAt(Timestamp.from(Instant.now()));

        return Optional.of(user);
    }

    public static Optional<User> createUser003() {
        User user = new User();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();

        role.setId(2L);
        role.setName("ROLE_USER");
        
        user.setId(3L);
        user.setAvatar("avatar3.jpg");
        user.setFirstName("Carla");
        user.setLastName("Tur");
        user.setUsername("carla17");
        user.setEmail("carla@gmail.com");
        user.setPassword("carla12345");
        user.setBirthDate(LocalDate.of(1994, 1, 17));
        user.setPhoneNumber("+56954879623");
        user.setEnabled(true);
        user.setAdmin(false);
        user.setRoles(roles);
        user.setCreatedAt(Timestamp.from(Instant.now()));

        return Optional.of(user);
    }

    public static Optional<User> createUser004() {
        User user = new User();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();

        role.setId(2L);
        role.setName("ROLE_USER");
        
        user.setId(4L);
        user.setAvatar("avatar4.jpg");
        user.setFirstName("Leo");
        user.setLastName("Messi");
        user.setUsername("messi10");
        user.setEmail("messi@gmail.com");
        user.setPassword("messi12345");
        user.setBirthDate(LocalDate.of(1989, 10, 27));
        user.setPhoneNumber("+56954875412");
        user.setEnabled(true);
        user.setAdmin(false);
        user.setRoles(roles);
        user.setCreatedAt(Timestamp.from(Instant.now()));

        return Optional.of(user);
    }

}
