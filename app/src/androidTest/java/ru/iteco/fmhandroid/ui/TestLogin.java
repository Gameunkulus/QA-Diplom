package ru.iteco.fmhandroid.ui;


import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

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
    private View decorView;
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void testInvalidPassword() {
        Allure.step("Заполнение верным значением поля логин и неверным значением пароля с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.fillFields(GenerateData.invalidAuthInfo());
        Allure.step("Нажатие на кнопку войти: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.clickEnterButton();
        Allure.step("Получение сообщения об ошибке: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.checkToastMessage(R.string.wrong_login_or_password, true);

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
