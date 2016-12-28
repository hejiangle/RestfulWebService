package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by cqu on 23/12/2016.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
