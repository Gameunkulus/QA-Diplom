package screens;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.assertion.ViewAssertions;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.MenuScreen;
import tools.UIDevise;

public class OurMissionScreen {

    MenuScreen menuScreen = new MenuScreen();

    public Matcher<View> newsBlockHeader = withText("Love is all");
    public Matcher<View> scrollView = withId(R.id.our_mission_item_list_recycler_view);
    public Matcher<View> mainMenuImBut = withId(R.id.main_menu_image_button);
    public void isOurMissionPage() {
        Allure.step("Проверка открытой страницы наша миссия.");
        UIDevise.waitView(newsBlockHeader).check(ViewAssertions.matches(isDisplayed()));
    }

    public void openNewsPageThroughTheMainMenu() {
        Allure.step("Проверка открытой страницы наша миссия.");
        UIDevise.waitView(mainMenuImBut).perform(click());
        menuScreen.menuIsOnScreen();
        menuScreen.clickNews();
    }

    public void openAboutPageThroughTheMainMenu() {
        Allure.step("Переход на страницу о приложении." );
        UIDevise.waitView(mainMenuImBut).perform(click());
        menuScreen.menuIsOnScreen();
        menuScreen.clickAbout();
    }

    public void openMainPageThroughTheMainMenu() {
        Allure.step("Переход на главную страницу." );
        UIDevise.waitView(mainMenuImBut).perform(click());
        menuScreen.menuIsOnScreen();
        menuScreen.openTheMainMenu();
    }
}
