package edu.upc.dsa.minim2_github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Usuario {
    private String login;
    private int id;
    private String avatar_url;
    private String name;
    private int public_repos;
    private int following;
    private List<Usuario> userFollowers;

    public List<Usuario> getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(List<Usuario> userFollowers) {
        this.userFollowers = userFollowers;
    }

    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }


}
