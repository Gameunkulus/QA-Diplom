package ru.iteco.fmhandroid.ui;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

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
public class TestAuthorisationButton {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();

    @Test
    public void testAuthorisationButton() {
        Allure.step("Заполнение верными значением поля логин и пароль с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";");
        authScreen.fillFields(GenerateData.authInfo());
        Allure.step("Нажатие на кнопку войти.");
        authScreen.clickEnterButton();
        Allure.step("Проверка перехода на главную страницу.");
        mainScreen.isMainPage();
        Allure.step("Нажатие на кнопку выхода из аккаунта.");
        mainScreen.clickLogOutBut();
        Allure.step("Проверка перехода на страницу авторизации.");
        authScreen.isAuthorisationPage();
    }
}
