package screens;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.assertion.ViewAssertions;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import tools.MenuScreen;
import tools.UIDevise;

public class MainScreen {

    MenuScreen menuScreen = new MenuScreen();
    NewsScreen newsScreen = new NewsScreen();

    public Matcher<View> newsBlockHeader = withText("News");
    public Matcher<View> authImBut = withId(R.id.authorization_image_button);
    public Matcher<View> logOutBut = withText("Log out");
    public Matcher<View> allNewsBut = withId(R.id.all_news_text_view);
    public Matcher<View> mainMenuImBut = withId(R.id.main_menu_image_button);
    public Matcher<View> ourMissionImBut = withId(R.id.our_mission_image_button);
    public Matcher<View> newsExpandMaterialBut = newsScreen.expandMaterialButton;
    public Matcher<View> allNewsCardsBlockConstraintLayout = withId(R.id.all_news_cards_block_constraint_layout);
    public Matcher<View> scrollView = withClassName(endsWith("ScrollView"));

    public void isMainPage() {
        UIDevise.waitView(newsBlockHeader).check(ViewAssertions.matches(isDisplayed()));
        UIDevise.waitView(withId(R.id.container_list_news_include_on_fragment_main)).check(ViewAssertions.matches(isDisplayed()));
    }

    public void clickLogOutBut() {
        UIDevise.waitView(authImBut).check(ViewAssertions.matches(isDisplayed())).perform(click());
        UIDevise.waitView(logOutBut).check(ViewAssertions.matches(isDisplayed())).perform(click());
    }

    public void openNewsPageThroughTheMainMenu() {
        UIDevise.waitView(mainMenuImBut).perform(click());
        menuScreen.menuIsOnScreen();
        menuScreen.clickNews();
    }

    public void openAboutPageThroughTheMainMenu() {
        UIDevise.waitView(mainMenuImBut).perform(click());
        menuScreen.menuIsOnScreen();
        menuScreen.clickAbout();
    }

    public void openMainPageThroughTheMainMenu() {
        UIDevise.waitView(mainMenuImBut).perform(click());
        menuScreen.menuIsOnScreen();
        menuScreen.clickMain();
    }


    public void isNewsBlockCollapsed() {
        UIDevise.waitView(newsBlockHeader).check(ViewAssertions.matches(isDisplayed()));
        UIDevise.waitView(allNewsBut).check(ViewAssertions.matches(not(isDisplayed())));
        UIDevise.waitView(allNewsCardsBlockConstraintLayout).check(ViewAssertions.matches(not(isDisplayed())));
    }

    public void openOurMissionPage() {
        UIDevise.waitView(ourMissionImBut).perform(click());
    }

    public void clickAllNewsBut() {
        UIDevise.waitView(allNewsBut).perform(click());
    }

    public void newsExpandMaterialButtonClick() {
        UIDevise.waitView(newsExpandMaterialBut).perform(click());
    }

}
