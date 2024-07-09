package ru.iteco.fmhandroid.ui;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import filter.FilterNewsScreen;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screens.AuthScreen;
import screens.MainScreen;
import screens.NewsScreen;
import tools.GenerateData;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class FilterNews {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private NewsScreen newsScreen = new NewsScreen();
    private FilterNewsScreen filterNewsScreen = new FilterNewsScreen();

    @Test
    public void filterNews() {
        Allure.step("Заполнение верными значением поля логин и пароль с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        authScreen.fillFields(GenerateData.authInfo());
        Allure.step("Нажатие на кнопку войти.");
        authScreen.clickEnterButton();
        Allure.step("Открыта главная страница приложения.");
        mainScreen.isMainPage();
        Allure.step("Переход на страницу новостей.");
        mainScreen.openNewsPageThroughTheMainMenu();
        Allure.step("Открытие фильтра новостей.");
        newsScreen.openFilterNews();
        Allure.step("Проверка работы кнопки отмена.");
        filterNewsScreen.cancelNewsButtonClick();
        filterNewsScreen.pressMessageButton(false);
        Allure.step("Заполнение строк фильтра.");
        filterNewsScreen.fillingOutTheFilterNewsForm("Праздник", LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(1),true,true);
        filterNewsScreen.filterNewsButtonClick();
        Allure.step("Проверка наличия новостей на экране.");
        newsScreen.isEmptyNewsList();
    }

}
