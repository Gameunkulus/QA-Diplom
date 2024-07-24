package tools;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MenuScreen {
    public Matcher<View> menuMainButton = withId(R.id.menu_item_main);
    public Matcher<View> menuNewsButton = withId(R.id.menu_item_news);
    public Matcher<View> menuAboutButton = withId(R.id.menu_item_about);
    public Matcher<View> mainMenuImBut = withId(R.id.main_menu_image_button);

    public void menuIsOnScreen() {
        Allure.step("Проверка что меню на экране есть.");
        UIDevise.waitView(mainMenuImBut).check(matches(isDisplayed()));
    }
    public void openTheMainMenu() {
        Allure.step("Открытие бокового меню.");
        UIDevise.waitView(menuMainButton).check(matches(isClickable()));
        UIDevise.waitView(menuMainButton).perform(click());
    }

    public void clickNews() {
        UIDevise.waitView(menuNewsButton).check(matches(isClickable()));
        UIDevise.waitView(menuNewsButton).perform(click());
    }

    public void clickAbout() {
        Allure.step("Переход на страницу о приложении при нажатии на кнопку в открывшемся меню.");
        UIDevise.waitView(menuAboutButton).check(matches(isClickable()));
        UIDevise.waitView(menuAboutButton).perform(click());
    }
}
