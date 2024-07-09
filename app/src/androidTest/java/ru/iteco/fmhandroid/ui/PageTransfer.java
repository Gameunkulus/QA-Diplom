package ru.iteco.fmhandroid.ui;


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
import screens.AboutScreen;
import screens.AuthScreen;
import screens.MainScreen;
import screens.NewsScreen;
import tools.GenerateData;
import tools.MenuScreen;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class PageTransfer {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private NewsScreen newsScreen = new NewsScreen();
    private AboutScreen aboutScreen = new AboutScreen();
    private MenuScreen menuScreen = new MenuScreen();

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
    public void pageTransfer() {
        Allure.step("Заполнение верными значением поля логин и пароль с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.fillFields(GenerateData.authInfo());
        Allure.step("Нажатие на кнопку войти.");
        authScreen.clickEnterButton();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
        Allure.step("Проверка что меню на экране есть.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу новостей при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickNews();
        Allure.step("Проверка что переход на страницу новостей выполнен.");
        newsScreen.isNewsPage();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу новостей при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickMain();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу о приложении при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickAbout();
        Allure.step("Проверка что переход на страницу о приложении выполнен.");
        aboutScreen.isAboutPage();
        Allure.step("Возвращаемся на главную страницу нажатием на кнопку назад.");
        aboutScreen.aboutBackImageButClick();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
    }
}
