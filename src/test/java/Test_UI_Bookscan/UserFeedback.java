package Test_UI_Bookscan;

import BaseClass.DBUtil;
import org.testng.annotations.Test;

public class UserFeedback {

    @Test(priority = 1)
    public void UserFeedBackGeneric() throws Exception {

        DBUtil dbUtil = new DBUtil();
        dbUtil.UserFeedBackBookScan();

    }



}
