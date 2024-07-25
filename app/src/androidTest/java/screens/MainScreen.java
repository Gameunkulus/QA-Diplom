package screens;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.assertion.ViewAssertions;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.MenuScreen;
import tools.UIDevise;

public class MainScreen {

    MenuScreen menuScreen = new MenuScreen();
    NewsScreen newsScreen = new NewsScreen();

    public Matcher<View> newsBlockHeader = withText("News");
    public Matcher<View> authImBut = withId(R.id.authorization_image_button);
    public Matcher<View> logOutBut = withText("Log out");
    public Matcher<View> allNewsBut = allOf(withId(R.id.all_news_text_view), withText("All news"));
    public Matcher<View> mainMenuImBut = withId(R.id.main_menu_image_button);
    public Matcher<View> ourMissionImBut = withId(R.id.our_mission_image_button);
    public Matcher<View> newsExpandMaterialBut = newsScreen.expandMaterialButton;
    public Matcher<View> allNewsCardsBlockConstraintLayout = withId(R.id.all_news_cards_block_constraint_layout);
    public Matcher<View> scrollView = withClassName(endsWith("ScrollView"));
    public Matcher<View> menuMainButton = withId(R.id.menu_item_main);
    public Matcher<View> menuNewsButton = withId(R.id.menu_item_news);
    public Matcher<View> menuAboutButton = withId(R.id.menu_item_about);

    public void isMainPage() {
        Allure.step("Проверка перехода на главную страницу.");
        UIDevise.waitView(newsBlockHeader).check(ViewAssertions.matches(isDisplayed()));
    }

    public void clickLogOutBut() {
        Allure.step("Нажатие на кнопку выхода из аккаунта.");
        UIDevise.waitView(authImBut).check(ViewAssertions.matches(isDisplayed())).perform(click());
        UIDevise.waitView(logOutBut).check(ViewAssertions.matches(isDisplayed())).perform(click());
    }

    public void openNewsPageThroughTheMainMenu() {
        Allure.step("Переход на страницу новостей через нажатие кнопки в меню." );
        UIDevise.waitView(mainMenuImBut).check(ViewAssertions.matches(isDisplayed())).perform(click());
        UIDevise.waitView(menuNewsButton).check(ViewAssertions.matches(isDisplayed())).perform(click());
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


    public void isNewsBlockCollapsed() {
        Allure.step("Проверка сернутости блока All news.");
        UIDevise.waitView(newsBlockHeader).check(ViewAssertions.matches(isDisplayed()));
        UIDevise.waitView(allNewsBut).check(ViewAssertions.matches(not(isDisplayed())));
        UIDevise.waitView(allNewsCardsBlockConstraintLayout).check(ViewAssertions.matches(not(isDisplayed())));
    }

    public void openOurMissionPage() {
        Allure.step("Проверка перехода на страницу наша миссия.");
        UIDevise.waitView(ourMissionImBut).perform(click());
    }

    public void clickAllNewsBut() {
        Allure.step("Открыть страницу новостей с помощью кнопки 'All news' в блоке новостей.");
        UIDevise.waitView(allNewsBut).perform(click());
    }

    public void newsExpandMaterialButtonClick() {
        Allure.step("Нажатие на клавишу 'свернуть/развернуть' блока новостей");
        UIDevise.waitView(newsExpandMaterialBut).perform(click());
    }
}
