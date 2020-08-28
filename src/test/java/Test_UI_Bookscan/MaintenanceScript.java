package Test_UI_Bookscan;

import BaseClass.CommonApi;
import org.testng.annotations.Test;

public class MaintenanceScript extends CommonApi {

    @Test
    public void MaintenanceOutputFile() throws Exception {
        String FolderLocation_archive = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\";

        int wk_ar_del = DeleteFileByDates(FolderLocation_archive + "WORK\\Results\\HTML Reports\\Archive\\");
        int wk_ar_scr = DeleteFileByDates(FolderLocation_archive + "WORK\\Results\\Screenshots\\");

        int qa_ar_del = DeleteFileByDates(FolderLocation_archive + "QA\\Results\\HTML Reports\\Archive\\");
        int qa_ar_scr =DeleteFileByDates(FolderLocation_archive + "QA\\Results\\Screenshots\\");

        int preprod_ar_del = DeleteFileByDates(FolderLocation_archive + "PREPROD\\Results\\HTML Reports\\Archive\\");
        int preprod_ar_scr = DeleteFileByDates(FolderLocation_archive + "PREPROD\\Results\\Screenshots\\");

        int prod_ar_del = DeleteFileByDates(FolderLocation_archive + "PROD\\Results\\HTML Reports\\Archive\\");
        int prod_ar_scr = DeleteFileByDates(FolderLocation_archive + "PROD\\Results\\Screenshots\\");

        int dev_ar_del = DeleteFileByDates(FolderLocation_archive + "DEV\\Results\\HTML Reports\\Archive\\");
        int dev_ar_scr = DeleteFileByDates(FolderLocation_archive + "DEV\\Results\\Screenshots\\");


        String str_work = "Archive File Deleted at Work : " + wk_ar_del + "<Br>Screenshot File Deleted at Work : " + wk_ar_scr + "<BR>";
        String str_qa = "Archive File Deleted at QA : " + qa_ar_del + "<Br>Screenshot File Deleted at QA : " + qa_ar_scr + "<BR>";
        String str_preprod = "Archive File Deleted at PREPROD : " + preprod_ar_del + "<Br>Screenshot File Deleted at PREPROD : " + preprod_ar_scr + "<BR>";
        String str_prod = "Archive File Deleted at PROD : " + prod_ar_del + "<Br>Screenshot File Deleted at PROD : " + prod_ar_scr + "<BR>";
        String str_dev = "Archive File Deleted at DEV : " + dev_ar_del + "<Br>Screenshot File Deleted at DEV : " + dev_ar_scr + "<BR>";

        String HTMLBody = "<HTML><Body>" + str_work + str_qa + str_preprod + str_prod + str_dev + "</Body></HTML>";
//        String HTMLBody = "<HTML><Body>" + str_work + str_qa + str_preprod  + "</Body></HTML>";

        sendMail("gazi.uddin@npd.com","BookScan Test Env Maintenance",HTMLBody,"Bookscan_Test_Automation");


    }


}
