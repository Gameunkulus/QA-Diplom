package screens.filter.blocks;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import tools.UIDevise;

public class BlockEditNewsScreen {


    public Matcher<View> blockTitle = withId(R.id.news_item_title_text_view);
    public Matcher<View> blockPubDate = withId(R.id.news_item_publication_date_text_view);
    public Matcher<View> blockCreateDate = withId(R.id.news_item_create_date_text_view);
    public Matcher<View> blockAuthor = withText(R.string.news_item_author);
    public Matcher<View> blockDescription = withText(R.string.news_item_description);
    public Matcher<View> blockExpandBtn = withContentDescription(R.string.expand_news_button);
    public Matcher<View> blockDeleteBtn = withId(R.id.delete_news_item_image_view);
    public Matcher<View> blockEditBtn = withId(R.id.edit_news_item_image_view);

    public ViewInteraction getBlockTitle() {
        return UIDevise.waitView(blockTitle);
    }

    public ViewInteraction getPubDate() {
        return UIDevise.waitView(blockPubDate);
    }

    public ViewInteraction getCreateDate() {
        return UIDevise.waitView(blockCreateDate);
    }

    public ViewInteraction getAuthor() {
        return UIDevise.waitView(blockAuthor);
    }

    public ViewInteraction getDescription() {
        return UIDevise.waitView(blockDescription);
    }

    public void pressExpandBtn() {
        UIDevise.waitView(blockExpandBtn).perform(click());
    }

    public void pressDeleteBtn() {
        UIDevise.waitView(blockDeleteBtn).perform(click());
    }

    public void pressEditBtn() {
        UIDevise.waitView(blockEditBtn).perform(click());
    }


}
