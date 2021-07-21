package Listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import regresiontests.BaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {
    private static final String SOURCE_FOLDER_SCREENSHOT = "target/screenshots/";

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test success  : " + iTestResult.getInstanceName() + "." + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test failed  : " + iTestResult.getInstanceName() + "." + iTestResult.getName());
        takeScreenShot(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skipped  : " + iTestResult.getInstanceName() + "." + iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    public void takeScreenShot(ITestResult iTestResult) {
        System.out.println("Taking a screenshot");
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) currentClass).getWebDriver();

        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String screenName = SOURCE_FOLDER_SCREENSHOT + iTestResult.getName() + ".png";
        File outputFile = new File(screenName);
        try {
            BufferedImage bufferedImage = ImageIO.read(scrFile);
            ImageIO.write(bufferedImage, "png", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("screenshot was taken at: " + outputFile.getAbsolutePath());
    }
}
