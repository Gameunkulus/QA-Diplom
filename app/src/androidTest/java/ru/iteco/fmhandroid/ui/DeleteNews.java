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

import java.time.LocalDateTime;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screens.AuthScreen;
import screens.ControlPanelListScreen;
import screens.MainScreen;
import screens.NewsScreen;
import tools.GenerateData;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class DeleteNews {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private NewsScreen newsScreen = new NewsScreen();
    private ControlPanelListScreen controlPanelListScreen = new ControlPanelListScreen();

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
    public void deleteNews() {
        Allure.step("Заполнение верными значением поля логин и пароль с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.fillFields(GenerateData.authInfo());
        Allure.step("Нажатие на кнопку войти.");
        authScreen.clickEnterButton();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
        Allure.step("Переход на страницу новостей через нажатие кнопки в меню." );
        mainScreen.openNewsPageThroughTheMainMenu();
        Allure.step("Переход на страницу редактирования новостей через нажатие кнопки в меню." );
        newsScreen.openEditPanel();
        Allure.step("Проверка что страница редактирования новостей открылась." );
        controlPanelListScreen.isControlPanel();
        Allure.step("Проверка что имеется нужное сообщение." );
        GenerateData.CreateNews announcementNews = GenerateData.newsWithRandomNameAndDescription()
                .withCategory(GenerateData.getCategoryAnnouncement()).withDueDate(LocalDateTime.now()).build();
        controlPanelListScreen.checkNewsIsPresent(announcementNews);

        Allure.step("Нажимаем на кнопку удалить сообщение." );
        controlPanelListScreen.deleteItemNews(announcementNews.getNewsName());
        Allure.step("Отмена удаление сообщения." );
        controlPanelListScreen.cancelDeleteButtonClick();
        Allure.step("Повторно нажимаем на кнопку удалить сообщение." );
        controlPanelListScreen.deleteItemNews(announcementNews.getNewsName());
        Allure.step("Подтверждаем удаление сообщения." );
        controlPanelListScreen.okButtonClick();
        Allure.step("Проверяем что сообщение удалено." );
        controlPanelListScreen.checkNewsDoesNotPresent(announcementNews);
    }



}
