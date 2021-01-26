package com.maxhayday.database;


public enum SqlQueries {
    GET_USER_BY_ID("SELECT users.id, " +
            "       users.name, " +
            "       users.surname, " +
            "       users.role, " +
            "       users.region_id, " +
            "       posts.id, " +
            "       posts.content, " +
            "       posts.created, " +
            "       posts.updated, " +
            "       posts.user_id, " +
            "       regions.id, " +
            "       regions.name " +
            "FROM users " +
            "         INNER JOIN posts ON posts.user_id = users.id AND posts.user_id = ?" +
            "         LEFT JOIN regions ON regions.id = users.region_id"),
    GET_LAST_ADDED_USER("SELECT users.id, " +
            "       users.name, " +
            "       users.surname, " +
            "       users.role, " +
            "       users.region_id " +
            "       FROM users"),
    SAVE_USER("INSERT INTO crud_app.users (name,surname,role,region_id) VALUES (?,?,?,?)"),
    UPDATE_USER("UPDATE crud_app.users SET name=?,surname=?,role=? WHERE id=?"),
    GET_ALL_USERS("SELECT users.id, " +
            "       users.name, " +
            "       users.surname, " +
            "       users.role, " +
            "       users.region_id, " +
            "       posts.id, " +
            "       posts.content, " +
            "       posts.created, " +
            "       posts.updated, " +
            "       posts.user_id, " +
            "       regions.id, " +
            "       regions.name " +
            "FROM users " +
            "         LEFT JOIN posts ON posts.user_id = users.id " +
            "         LEFT JOIN regions ON regions.id = users.region_id " +
            "ORDER BY users.id ASC "),
    DELETE_USER_BY_ID("DELETE FROM crud_app.users WHERE id="),

    GET_REGION_BY_ID("SELECT * FROM crud_app.regions WHERE id="),
    SAVE_REGION("INSERT INTO crud_app.regions (name) VALUES (?)"),
    UPDATE_REGION("UPDATE crud_app.regions SET name=? where id="),
    GET_ALL_REGIONS("SELECT * FROM crud_app.regions"),
    DELETE_REGION_BY_ID("DELETE FROM crud_app.regions WHERE id="),

    GET_POST_BY_ID("SELECT * FROM crud_app.posts WHERE id="),
    GET_POST_BY_USER_ID("SELECT * FROM crud_app.posts WHERE user_id="),
    SAVE_POST("INSERT INTO crud_app.posts (content,created,updated,user_id) VALUES (?,?,?,?)"),
    UPDATE_POST("UPDATE crud_app.posts SET content=?,updated=? WHERE id="),
    GET_ALL_POSTS("SELECT * FROM crud_app.posts"),
    DELETE_POST_BY_ID("DELETE FROM crud_app.posts WHERE id="),
    DELETE_POST_BY_USER_ID("DELETE FROM crud_app.posts WHERE user_id=");

    private final String SQL;

    SqlQueries(String s) {
        this.SQL = s;
    }

    public String getSQL() {
        return SQL;
    }
}
