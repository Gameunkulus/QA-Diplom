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
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import screens.filter.FilterNewsScreen;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screens.AuthScreen;
import screens.ControlPanelListScreen;
import screens.MainScreen;
import screens.NewsScreen;
import tools.GenerateData;
import tools.TestListener;

@ExtendWith(TestListener.class)
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class RedactorFilterNews {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private NewsScreen newsScreen = new NewsScreen();
    private ControlPanelListScreen controlPanelListScreen = new ControlPanelListScreen();
    private FilterNewsScreen filterNewsScreen = new FilterNewsScreen();

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
    public void redactorFilterNews() {
        Allure.step("Заполнение верными значением поля логин и пароль с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.fillFields(GenerateData.authInfo());
        Allure.step("Нажатие на кнопку войти.");
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        Allure.step("Переход на страницу новостей.");
        mainScreen.openNewsPageThroughTheMainMenu();
        Allure.step("Переход на страницу новостей.");
        mainScreen.openNewsPageThroughTheMainMenu();
        Allure.step("Страница новостей открыта.");
        newsScreen.isNewsPage();
        Allure.step("Переход на страницу редактирования новостей.");
        newsScreen.openEditPanel();
        Allure.step("Страница редактирования новостей открыта.");
        controlPanelListScreen.isControlPanel();
        controlPanelListScreen.clickOpenFilterButton();
        Allure.step("Проверка работы кнопки отмена.");
        filterNewsScreen.cancelNewsButtonClick();
        filterNewsScreen.pressMessageButton(false);
        Allure.step("Заполнение строк фильтра.");
        filterNewsScreen.fillingOutTheFilterNewsForm("Праздник", LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(1),true,true);
        filterNewsScreen.filterNewsButtonClick();
        Allure.step("Проверка перехода на страницу редактора новостей.");
        controlPanelListScreen.isEmptyNewsList();
    }

}
