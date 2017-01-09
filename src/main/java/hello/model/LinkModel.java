package hello.model;

import java.net.URL;

/**
 * Created by cqu on 05/01/2017.
 */
public class LinkModel {

    private String rel;
    private URL href;
    private String title;
    private String type;

    public LinkModel(){

    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public URL getHref() {
        return href;
    }

    public void setHref(URL href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
