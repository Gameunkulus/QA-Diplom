package ru.iteco.fmhandroid.ui.tests;


import static tools.UIDevise.device;

import android.os.RemoteException;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import io.qameta.allure.android.rules.LogcatRule;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import screens.AuthScreen;
import screens.MainScreen;
import screens.NewsScreen;
import screens.filter.FilterNewsScreen;
import tools.GenerateData;
import tools.MenuScreen;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class FilterNewsUseTest {

    @Rule
    public RuleChain ruleChain = RuleChain
            .outerRule(new LogcatRule())
            .around(new ActivityScenarioRule<>(AppActivity.class))
            .around(new ScreenshotRule());
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private NewsScreen newsScreen = new NewsScreen();
    private MenuScreen menuScreen = new MenuScreen();
    private FilterNewsScreen filterNewsScreen = new FilterNewsScreen();

    @Before
    public void logoutCheck() throws RemoteException {
        device =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.setOrientationNatural();
        try {
            authScreen.isAuthScreen();
        } catch (PerformException e) {
            mainScreen.clickLogOutBut();
        }
    }
    @Test
    public void filterNews() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        menuScreen.openTheMainMenu();
        menuScreen.clickNews();
        newsScreen.openFilterNews();
        filterNewsScreen.cancelNewsButtonClick();
        filterNewsScreen.pressMessageButton(false);
        filterNewsScreen.fillingOutTheFilterNewsForm("Праздник", LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(1),true,true);
        filterNewsScreen.filterNewsButtonClick();
        newsScreen.isEmptyNewsList();
    }

}
