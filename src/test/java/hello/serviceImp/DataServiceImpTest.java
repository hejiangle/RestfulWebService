package hello.serviceImp;

import hello.entity.Data;
import hello.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.ws.http.HTTPException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by cqu on 29/12/2016.
 */
public class DataServiceImpTest {

    @Mock
    private Data data;

    @InjectMocks
    private DataServiceImp dataService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addUserMethodShouldReturnTrueWhenInputAnNotExistingUser(){
        int id = 89757;
        User user = new User("munaiyi","JJ");
        when(data.add(id,user)).thenReturn(true);
        assertEquals(201,dataService.addUser(id,user));
    }

    @Test(expected = HTTPException.class)
    public void addUserMethodShouldReturnFalseWhenInputAnExistingUser(){

    }

    @Test
    public void deleteUserMethodSholdReturnTrueByAnExistingId(){

    }

    @Test(expected = HTTPException.class)
    public void deleteMethodShouldReturnFalseByAnNotExistingId(){

    }

    @Test(expected = HTTPException.class)
    public void updateMethodShouldReturnFalseByAnExistingIdWhenInputAnExistingUserInfo(){

    }

    @Test
    public void updateMethodShouldReturnTrueByAnExistingIdWhenInputAnExistingUserNameAndAnNotExistingUserPassword(){

    }

    @Test
    public void updateMethodShouldReturnTrueByAnExistingIdWhenInputAnNotExistingUserNameAndAnExistingUserPassword(){

    }

    @Test
    public void updateMethodShouldReturnTrueByAnNotExistingId(){

    }

    @Test
    public void getUserListMethodShouldReturnUserList(){

    }

    @Test(expected = HTTPException.class)
    public void getUserMethodShouldReturnNullByAnNotExistingId(){

    }

    @Test
    public void getUserMethodShouldReturnTheUserDesignatedByAnExistingId(){

    }


}