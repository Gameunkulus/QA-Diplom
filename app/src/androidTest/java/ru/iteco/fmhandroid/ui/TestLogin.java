package ru.iteco.fmhandroid.ui;


import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
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
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screens.AuthScreen;
import screens.MainScreen;
import tools.GenerateData;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class TestLogin {



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
        Allure.step("Заполнение верным значением поля логин и неверным значением пароля с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.fillFields(GenerateData.invalidAuthInfo());
        Allure.step("Нажатие на кнопку войти: " +
                R.id.enter_button + ";" );
        String expectedWarning = getApplicationContext().getString(R.string.error);
        authScreen.clickEnterButton();
        Allure.step("Получение сообщения об ошибке: " + R.string.error + ";" );
        authScreen.isToastMessageDisplayed(R.string.error);

    }

    @Test
    public void testValidLogin() {

        Allure.step("Заполнение верными значением поля логин и пароль с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.fillFields(GenerateData.authInfo());
        Allure.step("Нажатие на кнопку войти: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.clickEnterButton();
        mainScreen.isMainPage();

    }

}
