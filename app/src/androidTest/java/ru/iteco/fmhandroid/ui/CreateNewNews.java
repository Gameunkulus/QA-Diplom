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

import screens.filter.blocks.ReadyNews;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screens.AuthScreen;
import screens.ControlPanelListScreen;
import screens.CreateNewsScreen;
import screens.MainScreen;
import screens.NewsScreen;
import tools.GenerateData;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CreateNewNews {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private NewsScreen newsScreen = new NewsScreen();
    private ControlPanelListScreen controlPanelListScreen = new ControlPanelListScreen();
    private CreateNewsScreen createNewsScreen = new CreateNewsScreen();
    private ReadyNews readyNews = new ReadyNews();

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
    public void createNewNews() {
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
        Allure.step("Нажатие на кнопку создать новость." );
        controlPanelListScreen.openCreatingNewsForm();
        Allure.step("Нажатие на кнопку отменить создание новости." );
        createNewsScreen.cancelButtonClick();
        Allure.step("Подтвердить отмену создания новости." );
        createNewsScreen.okButtonClick();
        Allure.step("Проверка что страница редактирования новостей открылась." );
        controlPanelListScreen.isControlPanel();
        Allure.step("Нажатие на кнопку создать новость." );
        controlPanelListScreen.openCreatingNewsForm();
        Allure.step("Создание новости на завтра." );
        createNewsScreen.selectANewsCategoryFromTheList("Объявление");
        createNewsScreen.replaceNewsTitleText(readyNews.getTitle());
        createNewsScreen.setDateToDatePicker(LocalDateTime.now());
        createNewsScreen.setTimeToTimeField(LocalDateTime.now());
        createNewsScreen.replaceNewsDescriptionText(readyNews.getDescription());
        createNewsScreen.switchNewsStatus();
        Allure.step("Проверяем работу кнопки отмены создания новости.");
        createNewsScreen.cancelButtonClick();
        createNewsScreen.cancelDeleteButtonClick();
        createNewsScreen.saveButtonClick();
        createNewsScreen.okButtonClick();
        Allure.step("Проверяем наличие новости.");
        controlPanelListScreen.checkNewsIsPresent(readyNews.getTitle());

    }


}
