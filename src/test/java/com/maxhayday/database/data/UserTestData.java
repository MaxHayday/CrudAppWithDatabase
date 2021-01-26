package com.maxhayday.database.data;

import com.maxhayday.database.model.Post;
import com.maxhayday.database.model.Region;
import com.maxhayday.database.model.Role;
import com.maxhayday.database.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserTestData {

    public static User testDataOfUserForCreating() {
        Region region = Region.builder().id(3L).name("POL").build();
        User user = User.builder().id(3l).name("Dima").lastName("Matsech").posts(null).region(region).role(Role.USER).build();
        return user;
    }

    public static User testDataOfUserForUpdating() {
        List<Post> postList = new ArrayList<>();
        Post post1 = Post.builder().id(2L).content("Post 1 of Ola").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(2L).build();
        postList.add(post1);
        Region region = Region.builder().id(2L).name("IRL").build();
        User user = User.builder().id(2l).name("Olaa").lastName("Hayday").posts(postList).region(region).role(Role.USER).build();
        return user;
    }

    public static User expectedDataOfUserForUpdating() {
        List<Post> postList = new ArrayList<>();
        Post post1 = Post.builder().id(5L).content("updated post 1 of Ola").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(3L).build();
        postList.add(post1);
        Region region = Region.builder().id(3L).name("UKR").build();
        User user = User.builder().id(3L).name("Ola").lastName("Hayday").posts(postList).region(region).role(Role.USER).build();
        return user;
    }

    public static User expectedCreatedDataOfUser() {
        List<Post> postList = new ArrayList<>();
        Post post1 = Post.builder().id(3l).content("Post 1 of Dima").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(3L).build();
        postList.add(post1);
        Region region = Region.builder().id(3L).name("POL").build();
        User user = User.builder().id(3l).name("Dima").lastName("Matsech").posts(null).region(null).role(Role.USER).build();
        return user;
    }
    public static User expectedDataOfUserGetById(){
        List<Post> postList = new ArrayList<>();
        Post post1 = Post.builder().id(1L).content("Post 1 of Max").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(1L).build();
        postList.add(post1);
        Region region = Region.builder().id(1L).name("UKR").build();
        User user = User.builder().id(1l).name("Max").lastName("Hayday").posts(postList).region(region).role(Role.USER).build();
        return user;
    }

    public static List<User> expectedDataOfAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        List<Post> postList = new ArrayList<>();
        Post post1 = Post.builder().id(1l).content("Post 1 of Max").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(1l).build();
        postList.add(post1);
        Region region = Region.builder().id(1l).name("UKR").build();
        User user = User.builder().id(1l).name("Max").lastName("Hayday").posts(postList).region(region).role(Role.USER).build();
        users.add(user);

        postList = new ArrayList<>();
        post1 = Post.builder().id(2l).content("Post 1 of Ola").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(2l).build();
        postList.add(post1);
        region = Region.builder().id(2l).name("IRL").build();
        user = User.builder().id(2l).name("Ola").lastName("Hayday").posts(postList).region(region).role(Role.USER).build();

        users.add(user);
        return users;
    }

}
