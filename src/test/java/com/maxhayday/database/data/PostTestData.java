package com.maxhayday.database.data;

import com.maxhayday.database.model.Post;
import com.maxhayday.database.model.Region;
import com.maxhayday.database.model.Role;
import com.maxhayday.database.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PostTestData {

    public static Post testDataOfPostForCreating() {
        Post post = Post.builder().id(3l).content("Post 1 of Ira").created(Timestamp.valueOf("2021-01-03 8:43:10"))
                .updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(3l).build();
        return post;
    }

    public static Post testDataOfPostForUpdating() {
        Post post = Post.builder().id(1L).content("Updated Post 1 of Max").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(1L).build();
        return post;
    }

    public static Post expectedCreatedDataOfPost() {
        Post post = Post.builder().id(3l).content("Post 1 of Ira").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(3L).build();
        return post;
    }

    public static Post expectedDataOfPostGetById() {
        Post post = Post.builder().id(1L).content("Updated Post 1 of Max").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(1L).build();
        return post;
    }

    public static List<Post> expectedDataOfAllPosts() {
        List<Post> postList = new ArrayList<>();
        Post post1 = Post.builder().id(1l).content("Post 1 of Max").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(1l).build();
        Post post2 = Post.builder().id(2l).content("Post 1 of Ola").created(Timestamp.valueOf("2021-01-03 8:43:10")).updated(Timestamp.valueOf("2021-01-03 8:43:10")).user_id(2l).build();
        postList.add(post1);
        postList.add(post2);

        return postList;
    }
}
