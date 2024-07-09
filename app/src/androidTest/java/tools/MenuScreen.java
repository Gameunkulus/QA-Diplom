package tools;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class MenuScreen {


    public Matcher<View> menuMainButton = withId(R.id.menu_item_main);
    public Matcher<View> menuNewsButton = withId(R.id.menu_item_news);
    public Matcher<View> menuAboutButton = withId(R.id.menu_item_about);
    public Matcher<View> mainMenuImBut = withId(R.id.main_menu_image_button);

    public void menuIsOnScreen() {
        UIDevise.waitView(menuMainButton).check(matches(isDisplayed()));
        UIDevise.waitView(menuNewsButton).check(matches(isDisplayed()));
        UIDevise.waitView(menuAboutButton).check(matches(isDisplayed()));
    }

    public void menuIsActive(){
        UIDevise.waitView(menuMainButton).check(matches(isClickable()));
        UIDevise.waitView(menuNewsButton).check(matches(isClickable()));
        UIDevise.waitView(menuAboutButton).check(matches(isClickable()));
    }

    public void openTheMainMenu() {
        UIDevise.waitView(mainMenuImBut).perform(click());
    }

    public void clickMain(){
        UIDevise.waitView(menuMainButton).perform(click());
    }
    public void clickNews(){
        UIDevise.waitView(menuNewsButton).perform(click());
    }
    public void clickAbout() {
        UIDevise.waitView(menuAboutButton).perform(click());
    }
}
