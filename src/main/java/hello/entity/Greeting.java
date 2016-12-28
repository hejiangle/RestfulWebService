package hello.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cqu on 23/12/2016.
 */
public class Greeting {

    private final long id;
    private final String context;

    public Greeting(long id, String context) {
        this.id = id;
        this.context = context;
    }

    public long getId() {
        return id;
    }

    public String getContext() {
        return context;
    }

}
