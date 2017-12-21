package com.renardbebe.ex9.model;

/**
 * Created by renardbebe on 2017/12/12.
 */

public class Github {
    private String login;
    private String id;
    private String blog;

    public Github(String name, String id, String blog) {
        this.login = name;
        this.id = id;
        this.blog = blog;
    }

    public String getName() { return login; }
    public String getId() { return id; }
    public String getBlog() { return blog; }
}
