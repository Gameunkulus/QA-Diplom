package screens.filter.blocks;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.UIDevise;

public class BlockNewsScreen {

    public Matcher<View> blockExpandBtn = withContentDescription(R.string.expand_news_button);
    public Matcher<View> blockTitle = withText(R.string.news_item_title);
    public Matcher<View> blockDate = withText(R.string.news_item_date);
    public Matcher<View> blockDescription = withText(R.string.news_item_description);

    public ViewInteraction pressExpandBtn(){
        Allure.step("Нажатие на кнопку свернуть/развернуть блока новости." );
        return UIDevise.waitView(blockExpandBtn).perform(click());
    }

    public ViewInteraction getBlockTitle() {
        Allure.step("Обращение к элементу тайтл." );
        return UIDevise.waitView(blockTitle);
    }

    public ViewInteraction getDate() {
        Allure.step("Обращение к элементу дата." );
        return UIDevise.waitView(blockDate); }

    public ViewInteraction getDescription() {
        Allure.step("Обращение к элементу описание." );
        return  UIDevise.waitView(blockDescription); }
}
