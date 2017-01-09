package hello.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cqu on 05/01/2017.
 */
public class UserListModel {

   private List<UserModel> user_list = new ArrayList<>();

    public UserListModel(List<UserModel> user_list) {
        this.user_list = user_list;
    }

    public List<UserModel> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserModel> user_list) {
        this.user_list = user_list;
    }
}
