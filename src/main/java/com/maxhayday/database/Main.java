package com.maxhayday.database;

import com.maxhayday.database.view.observer.MenuObserved;
import com.maxhayday.database.view.observer.PostViewObserver;
import com.maxhayday.database.view.observer.RegionViewObserver;
import com.maxhayday.database.view.observer.UserViewObserver;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException {
        MenuObserved menu = new MenuObserved();
        menu.addObserver(new PostViewObserver());
        menu.addObserver(new UserViewObserver());
        menu.addObserver(new RegionViewObserver());
        menu.showMenu();
    }
}
