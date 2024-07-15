package ru.iteco.fmhandroid.ui;


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
        Allure.step("Проверка что меню на экране есть.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу новостей при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickNews();
        Allure.step("Проверка что переход на страницу новостей выполнен.");
        newsScreen.isNewsPage();
    }

    public void pageTransferMainAbout() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        Allure.step("Проверка что меню на экране есть.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу о приложении при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickAbout();
        Allure.step("Проверка что переход на страницу новостей выполнен.");
        aboutScreen.isAboutPage();
    }

    @Test
    public void pageTransferNewsAbout() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страницу о приложении при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickAbout();
        Allure.step("Проверка что переход на страницу о приложении выполнен.");
        aboutScreen.isAboutPage();
    }

    @Test
    public void pageTransferNewsMain() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        Allure.step("Открытие бокового меню.");
        menuScreen.openTheMainMenu();
        Allure.step("Переход на главную страницу приложения при нажатии на кнопку в открывшемся меню.");
        menuScreen.clickMain();
        Allure.step("Проверка что переход на главную страницу о выполнен.");
        mainScreen.isMainPage();
    }

    @Test
    public void pageTransferNewsControlPanel() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
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

    @Test
    public void pageFalseTransferAboutMain() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openAboutPageThroughTheMainMenu();
        Allure.step("На главной странице экрана.");
        aboutScreen.isAboutPage();
        Allure.step("Поиск меню на странице экрана. Меню на странице экрана отсутствует");
        menuScreen.menuIsOnScreen();
    }

    @Test
    public void pageTransferAboutMain() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openAboutPageThroughTheMainMenu();
        Allure.step("На главной странице экрана.");
        aboutScreen.isAboutPage();
        Allure.step("Возвращаемся на главную страницу нажатием на кнопку назад.");
        aboutScreen.aboutBackImageButClick();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
    }

    @Test
    public void pageTransferControlPanelMain() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        Allure.step("Переход на главную страницу.");
        menuScreen.clickMain();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
    }

    @Test
    public void pageTransferControlPanelNews() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страниц новостей.");
        menuScreen.clickNews();
        Allure.step("Открыта страница новостей.");
        mainScreen.isMainPage();
    }

    @Test
    public void pageTransferControlPanelAbout() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
        Allure.step("Проверка наличия меню.");
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
        Allure.step("Переход на страниц новостей.");
        menuScreen.clickNews();
        Allure.step("Открыта страница новостей.");
        mainScreen.isMainPage();
    }
}
