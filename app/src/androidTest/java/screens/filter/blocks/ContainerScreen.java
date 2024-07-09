package screens.filter.blocks;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import tools.GenerateData;
import tools.ModRecyclerViewActions;
import tools.UIDevise;

public class ContainerScreen {

    BlockNewsScreen blockNewsScreen = new BlockNewsScreen();


    public Matcher<View> containerText = withText("News");
    public Matcher<View> containerNewsSortButton = withId(R.id.sort_news_material_button);
    public Matcher<View> containerFilterButton = withId(R.id.filter_news_material_button);
    public Matcher<View> containerEditButton = withId(R.id.edit_news_material_button);
    public Matcher<View> containerExpandButton = withId(R.id.expand_material_button);
    public Matcher<View> containerAllNewsButton = withId(R.id.all_news_text_view);
    public Matcher<View> blockNewsList = withId(R.id.news_list_recycler_view);
    public int newsItemTitleTextView = (R.id.news_item_title_text_view);

    void containerNewsIsOnScreen() {
        UIDevise.waitView(containerText).check(matches(isDisplayed()));
        UIDevise.waitView(containerAllNewsButton).check(matches(isDisplayed()));
        UIDevise.waitView(containerNewsSortButton).check(matches(isDisplayed()));
        UIDevise.waitView(containerFilterButton).check(matches(isDisplayed()));
        UIDevise.waitView(containerEditButton).check(matches(isDisplayed()));
        UIDevise.waitView(containerExpandButton).check(matches(isDisplayed()));
    }

    void clickContainerSort() {
        UIDevise.waitView(containerNewsSortButton).perform(click());
    }

    void clickContainerFilter() {
        UIDevise.waitView(containerFilterButton).perform(click());
    }

    void clickContainerEdit() {
        UIDevise.waitView(containerEditButton).perform(click());
    }

    void clickContainerExpand() {
        UIDevise.waitView(containerExpandButton).perform(click());
    }

    public void checkNewsDoesNotPresent(GenerateData.CreateNews news) {
        getblockNewsList()
                .check(matches(ModRecyclerViewActions.RecyclerViewMatcher
                        .matchChildViewIsNotExist(newsItemTitleTextView, withText(news.getNewsName()))));
    }

    public ViewInteraction scrollToElementInRecyclerList(String description) {
        return UIDevise.waitView(blockNewsList).check(matches(isDisplayed()))
                // scrollTo will fail the test if no item matches.
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(description))));
    }

    public ViewInteraction getblockNewsList() {
        return UIDevise.waitView(blockNewsList);
    }

}
