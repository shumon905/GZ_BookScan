package Reporting;

import BaseClass.CommonApi;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.ITestContext;

import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import javax.swing.text.html.HTML;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExtentManager extends CommonApi {

    private static ExtentReports extent;
    private static ITestContext context;

    public synchronized static ExtentReports getInstance() {

        System.out.println(System.getProperty("user.dir") + "/report-config.xml");
//        ExcelUtils data = new ExcelUtils();
//        String[][] env = data.getLocalEnv();
//        String browser = env[1][1].toUpperCase();
//        String ENV = env[1][0].toUpperCase();
        String browser = "CHROME";
//        String ENV = "DQI";

        if (extent == null) {
            File outputDirectory = new File(context.getOutputDirectory());
            File resultDirectory = new File(outputDirectory.getParentFile(), "html");
            //extent = new ExtentReports("C:\\Program Files\\APP_DecisionKey_Reports\\Mobile\\ExtentReports\\ExtentReport.html", true);
            //extent = new ExtentReports("C:\\Users\\Gazi Uddin\\Desktop Items_Gazi\\Bookscan 2.0\\Results\\HTML\\ExtentReport.html", true);
            extent = new ExtentReports("C:\\Program Files\\APP_Bookscan_Reports\\ExtentReports\\ExtentReport.html", true);

            //extent = new ExtentReports(System.getProperty("user.dir") + "Extent-Report/ExtentReport.html", true);
            Reporter.log("Extent Report Directory" + resultDirectory, true);
            try {
                extent.addSystemInfo("Host Name", GetCurrentTestHostName()).addSystemInfo("Environment", "PROD").addSystemInfo("Browser", browser)
                        .addSystemInfo("User Name", GetCurrentTestUserName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            extent.loadConfig(new File(System.getProperty("user.dir") + "/report-config.xml"));
        }
        return extent;
    }

    // Get Current Host Name
    private static String GetCurrentTestHostName(){
        InetAddress localMachine = null;
        try {
            localMachine = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostName = localMachine.getHostName();
        System.out.println(hostName);
        return hostName;
    }
    // Get Current User Name
    private static String GetCurrentTestUserName() {
        String UserName = System.getProperty("user.name");
        System.out.println(UserName);
        return UserName;
    }
    public static void setOutputDirectory(ITestContext context){
        ExtentManager.context = context;

    }

}
