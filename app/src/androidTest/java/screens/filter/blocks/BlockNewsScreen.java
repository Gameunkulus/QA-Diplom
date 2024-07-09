package screens.filter.blocks;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import tools.UIDevise;

public class BlockNewsScreen {

    public Matcher<View> blockExpandBtn = withContentDescription(R.string.expand_news_button);
    public Matcher<View> blockTitle = withText(R.string.news_item_title);
    public Matcher<View> blockDate = withText(R.string.news_item_date);
    public Matcher<View> blockDescription = withText(R.string.news_item_description);

    public ViewInteraction pressExpandBtn(){
        return UIDevise.waitView(blockExpandBtn).perform(click());
    }

    public ViewInteraction getBlockTitle(){
        return UIDevise.waitView(blockTitle);
    }

    public ViewInteraction getDate() { return UIDevise.waitView(blockDate); }

    public ViewInteraction getDescription() { return  UIDevise.waitView(blockDescription); }
}
