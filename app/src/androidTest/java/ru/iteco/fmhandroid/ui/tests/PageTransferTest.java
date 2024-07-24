package ru.iteco.fmhandroid.ui.tests;


import static tools.UIDevise.device;

import android.os.RemoteException;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.LogcatRule;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import screens.AboutScreen;
import screens.AuthScreen;
import screens.ControlPanelListScreen;
import screens.MainScreen;
import screens.NewsScreen;
import tools.GenerateData;
import tools.MenuScreen;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class PageTransferTest {

    @Rule
    public RuleChain ruleChain = RuleChain
            .outerRule(new LogcatRule())
            .around(new ActivityScenarioRule<>(AppActivity.class))
            .around(new ScreenshotRule());
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private NewsScreen newsScreen = new NewsScreen();
    private AboutScreen aboutScreen = new AboutScreen();
    private MenuScreen menuScreen = new MenuScreen();
    private ControlPanelListScreen controlPanelListScreen = new ControlPanelListScreen();


    @BeforeEach
    public void logoutCheck() throws RemoteException {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.setOrientationNatural();
        try {
            mainScreen.isMainPage();
        } catch (PerformException e) {
            mainScreen.clickLogOutBut();
        }

    }
    @Test
    public void pageTransferMainNews() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        menuScreen.clickNews();
        newsScreen.isNewsPage();
    }

    public void pageTransferMainAbout() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        menuScreen.clickAbout();
        aboutScreen.isAboutPage();
    }

    @Test
    public void pageTransferNewsAbout() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        menuScreen.clickAbout();
        aboutScreen.isAboutPage();
    }

    @Test
    public void pageTransferNewsMain() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        menuScreen.openTheMainMenu();
        mainScreen.isMainPage();
    }

    @Test
    public void pageTransferNewsControlPanel() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        menuScreen.menuIsOnScreen();
        newsScreen.openEditPanel();
        controlPanelListScreen.isControlPanel();
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        menuScreen.openTheMainMenu();
        mainScreen.isMainPage();
    }

    @Test
    public void pageFalseTransferAboutMain() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openAboutPageThroughTheMainMenu();
        aboutScreen.isAboutPage();
        menuScreen.menuIsOnScreen();
    }

    @Test
    public void pageTransferAboutMain() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openAboutPageThroughTheMainMenu();
        aboutScreen.isAboutPage();
        aboutScreen.aboutBackImageButClick();
        mainScreen.isMainPage();
    }

    @Test
    public void pageTransferControlPanelMain() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        menuScreen.openTheMainMenu();
        mainScreen.isMainPage();
    }

    @Test
    public void pageTransferControlPanelNews() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        menuScreen.clickNews();
        mainScreen.isMainPage();
    }

    @Test
    public void pageTransferControlPanelAbout() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        menuScreen.clickNews();
        mainScreen.isMainPage();
    }
}
