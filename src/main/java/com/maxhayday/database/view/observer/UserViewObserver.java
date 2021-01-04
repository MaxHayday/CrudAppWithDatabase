package com.maxhayday.database.view.observer;

import com.maxhayday.database.controller.UserController;
import com.maxhayday.database.model.Post;
import com.maxhayday.database.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserViewObserver implements ViewObserver {
    private UserController userController;
    private RegionViewObserver regionViewObserver;
    private PostViewObserver postViewObserver;
    private List<User> userList;
    private List<Post> postList;
    private List<String> posts;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String firstNameDataStr = "", lastNameDataStr = "", dataStr = "", postStr = "", roleStr = "", regionStr = "";
    private User user;

    public UserViewObserver() {
        userController = new UserController();
        regionViewObserver = new RegionViewObserver();
        postViewObserver = new PostViewObserver();
    }

    public void create() throws IOException, SQLException, ClassNotFoundException {
        posts = new ArrayList<>();
        System.out.println("Write first name of user: ");
        firstNameDataStr = reader.readLine();
        System.out.println("Write last name of user: ");
        lastNameDataStr = reader.readLine();
        System.out.println("Choose role of user: ");
        System.out.println("1: User");
        System.out.println("2: Admin");
        System.out.println("3: Moderator");
        roleStr = reader.readLine();
        posts = createPosts();
        System.out.println("Write region of user");
        regionStr = reader.readLine();
        userController.save(null, firstNameDataStr, lastNameDataStr, posts, regionStr, roleStr);
    }

    public List<String> createPosts() throws IOException {
        posts = new ArrayList<>();
        do {
            System.out.println("Write post of user");
            System.out.println("If you finish creates posts write exit");
            postStr = reader.readLine();
            if (postStr.equals("exit")) break;
            posts.add(postStr);
        } while (!dataStr.equals("exit"));
        return posts;
    }

    public void update(Long id) throws IOException {
        user = userController.getById(id);
        if (user == null) {
            System.out.println("You haven`t user with " + id + " id.");
            return;
        }
        System.out.println("Write first name of user: ");
        firstNameDataStr = reader.readLine();
        System.out.println("Write last name of user: ");
        lastNameDataStr = reader.readLine();
        System.out.println("Choose role of user: ");
        System.out.println("1: User");
        System.out.println("2: Admin");
        System.out.println("3: Moderator");
        roleStr = reader.readLine();
        regionViewObserver.update(user.getRegion().getId());
        postViewObserver.update(user.getId());
        userController.update(id, firstNameDataStr, lastNameDataStr, null, null, roleStr);
    }

    public void getById(Long id) {
        user = userController.getById(id);
        if (user == null) {
            System.out.println("You have`t user with " + id + " id.");
            return;
        }
        postList = user.getPosts();
        System.out.println("========================================================================================================");
        System.out.printf("%-5s%-55s%-25s%-25s%n", "ID", "POST", "CREATED", "UPDATED");
        System.out.println("========================================================================================================");
        for (Post p : postList) {
            System.out.printf("%-5s%-55s%-25s%-25s%n", p.getId(), p.getContent(), p.getCreated(), p.getUpdated());
        }
    }

    public void getAll(){
        userList = userController.getAll();
        if (!userList.isEmpty()) {
            System.out.println("========================================================================================================");
            System.out.printf("%-5s%-20s%-25s%-20s%-20s%-20s%n", "ID", "FIRST_NAME", "LAST_NAME", "ROLE", "REGION", "POSTS");
            System.out.println("========================================================================================================");
            for (User u : userList) {
                if (u.getRegion() == null) {
                    System.out.printf("%-5s%-20s%-25s%-20s%-20s", u.getId(), u.getName(), u.getLastName(), u.getRole(), " ");
                } else
                    System.out.printf("%-5s%-20s%-25s%-20s%-20s", u.getId(), u.getName(), u.getLastName(), u.getRole(), u.getRegion().getName());
                System.out.print(u.getPosts().stream().map(i -> {
                    if (i.getId() == null) {
                        return null;
                    } else return i.getId();
                }).collect(Collectors.toList()));
                System.out.println();
            }
        } else System.out.println("You haven`t users.");
    }

    public void delete(Long id) {
        userList = userController.getAll();
        if (userList.isEmpty()) {
            System.out.println("You have not users.");
            return;
        }
        userController.deleteById(id);
    }
}
