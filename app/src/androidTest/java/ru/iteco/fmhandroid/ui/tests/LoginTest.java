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
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import screens.AuthScreen;
import screens.MainScreen;
import tools.GenerateData;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class LoginTest {



    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();


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
    public void testInvalidPassword() {
        authScreen.fillFields(GenerateData.invalidAuthInfo());
        authScreen.clickEnterButton();
        authScreen.isToastMessageDisplayed(R.string.error);
    }

    @Test
    public void testValidLogin() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
    }

}
