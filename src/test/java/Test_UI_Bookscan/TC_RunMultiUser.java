package Test_UI_Bookscan;

import BaseClass.CommonApi;
import PageObjects.page_Login;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TC_RunMultiUser extends CommonApi {

    @Test
    public void RunMultiUser() throws Exception {
        page_Login login = new page_Login();

        for(int i =0; i<=50;i++){
            launchbrowser();
            //Login
            //Log in if alreadu not logged in
            login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        }

        sendMail("gazi.uddin@npd.com","50 User Test","Test has been done...","selenium@npd.com");

        //Keep Session Open for 2 hours
        Thread.sleep(2000 * 60 * 60);


    }
}
