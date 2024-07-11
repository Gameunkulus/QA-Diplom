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
import screens.AboutScreen;
import screens.AuthScreen;
import screens.ControlPanelListScreen;
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
    private ControlPanelListScreen controlPanelListScreen = new ControlPanelListScreen();


    @Before
    public void logoutCheck() throws RemoteException {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.setOrientationNatural();
        try {
            authScreen.isAuthScreen();
        } catch (PerformException e) {
            mainScreen.clickLogOutBut();
        }
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
    }
    @Test
    public void pageTransferMainNews() {
        Allure.step("Проверка что меню на экране есть.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу новостей при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickNews();
        Allure.step("Проверка что переход на страницу новостей выполнен.");
        newsScreen.isNewsPage();
    }


    @Before
    public void logoutCheck2() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
    }

    public void pageTransferMainAbout() {
        Allure.step("Проверка что меню на экране есть.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу о приложении при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickAbout();
        Allure.step("Проверка что переход на страницу новостей выполнен.");
        aboutScreen.isAboutPage();
    }

    @Before
    public void logoutCheck3() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
    }

    @Test
    public void pageTransferNewsAbout() {
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу о приложении при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickAbout();
        Allure.step("Проверка что переход на страницу о приложении выполнен.");
        aboutScreen.isAboutPage();
    }

    @Before
    public void logoutCheck4() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
    }

    @Test
    public void pageTransferNewsMain() {
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на главную страницу приложения при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickMain();
        Allure.step("Проверка что переход на главную страницу о выполнен.");
        mainScreen.isMainPage();
    }

    @Before
    public void logoutCheck5() {

    }

    @Test
    public void pageTransferNewsControlPanel() {
        Allure.step("Вход с помощью валидных данных.");
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        Allure.step("Проверка входа на главную страницу.");
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        Allure.step("Переход на страницу редактирования ленты новостей.");
        newsScreen.openEditPanel();
        Allure.step("Открыта страница редактирования.");
        controlPanelListScreen.isControlPanel();
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        Allure.step("Возврат на главную страницу приложения.");
        menuScreen.openTheMainMenu();
        menuScreen.clickMain();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
    }

    @Before
    public void logoutCheck6() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openAboutPageThroughTheMainMenu();
    }

    @Test
    public void pageFalseTransferAboutMain() {
        Allure.step("На главной странице экрана.");
        aboutScreen.isAboutPage();
        Allure.step("Поиск меню на странице экрана. Меню на странице экрана отсутствует");
        menuScreen.menuIsOnScreen();
    }

    @Before
    public void logoutCheck7() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openAboutPageThroughTheMainMenu();
    }

    @Test
    public void pageTransferAboutMain() {
        Allure.step("На главной странице экрана.");
        aboutScreen.isAboutPage();
        Allure.step("Возвращаемся на главную страницу нажатием на кнопку назад.");
        aboutScreen.aboutBackImageButClick();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
    }


    @Before
    public void logoutCheck8() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
    }

    @Test
    public void pageTransferControlPanelMain() {
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        Allure.step("Переход на главную страницу.");
        menuScreen.clickMain();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
    }


    @Before
    public void logoutCheck9() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
    }

    @Test
    public void pageTransferControlPanelNews() {
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страниц новостей.");
        menuScreen.clickNews();
        Allure.step("Открыта страница новостей.");
        mainScreen.isMainPage();
    }


    @Before
    public void logoutCheck10() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
    }

    @Test
    public void pageTransferControlPanelAbout() {
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страниц новостей.");
        menuScreen.clickNews();
        Allure.step("Открыта страница новостей.");
        mainScreen.isMainPage();
    }
}
