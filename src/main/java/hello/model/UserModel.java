package hello.model;

import hello.entity.User;

import java.util.Map;

/**
 * Created by cqu on 05/01/2017.
 */
public class UserModel {

    private User user_info;
    private LinkModel link;

    public UserModel(User user_info, LinkModel link) {
        this.user_info = user_info;
        this.link = link;
    }

    public User getUser_info() {
        return user_info;
    }

    public void setUser_info(User user_info) {
        this.user_info = user_info;
    }

    public LinkModel getLink() {
        return link;
    }

    public void setLink(LinkModel linkModel) {
        this.link = linkModel;
    }
}
