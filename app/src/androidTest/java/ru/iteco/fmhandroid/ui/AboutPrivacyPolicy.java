package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static tools.UIDevise.device;

import android.content.Intent;
import android.os.RemoteException;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screens.AboutScreen;
import screens.AuthScreen;
import screens.MainScreen;
import tools.GenerateData;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutPrivacyPolicy {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private AboutScreen aboutScreen = new AboutScreen();

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
    public void aboutPrivacyPolicy() {

        Allure.step("Заполнение верными значением поля логин и пароль с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.fillFields(GenerateData.authInfo());
        Allure.step("Нажатие на кнопку войти.");
        authScreen.clickEnterButton();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
        Allure.step("Переход на страницу о приложении через нажатие кнопки в меню." );
        mainScreen.openAboutPageThroughTheMainMenu();
        aboutScreen.isAboutPage();
        aboutScreen.openPrivacyPolicy();
        intended(allOf(hasData( "https://vhospice.org/#/privacy-policy/"), hasAction(Intent.ACTION_VIEW)));
        Intents.release();
        aboutScreen.getHeaderPrivacyPolicyPage().check(matches(withText("Privacy policy")));

    }
}
