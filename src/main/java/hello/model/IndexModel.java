package hello.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cqu on 05/01/2017.
 */

public class IndexModel {

    private Map<String,URL> index = new HashMap<>();

    public IndexModel() throws MalformedURLException {
        String title = "list_of_all_user";
        URL url = new URL("http://localhost:8080/users");
        index.put(title,url);
    }

    public Map<String, URL> getIndex() {
        return index;
    }

    public void setIndex(Map<String, URL> index) {
        this.index = index;
    }
}
