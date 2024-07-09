package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;
import android.os.SystemClock;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

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
public class AboutTermsOfUse {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private AboutScreen aboutScreen = new AboutScreen();

    @Test
    public void aboutTermsOfUse() {
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

        aboutScreen.openTermsOfUse();
        SystemClock.sleep(3000);
        intended(allOf(hasData("https://vhospice.org/#/terms-of-use"), hasAction(Intent.ACTION_VIEW)));
        Intents.release();
        aboutScreen.getHeaderTermsOfUsePage().check(matches(withText("Terms of use")));
    }
}
