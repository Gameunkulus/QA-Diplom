package screens;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.UIDevise;

public class NewsScreen {

    public Matcher<View> sortNewsMaterialBut = withId(R.id.sort_news_material_button);
    public Matcher<View> filterNewsMaterialBut = withId(R.id.filter_news_material_button);
    public Matcher<View> editNewsMaterialBut = withId(R.id.edit_news_material_button);
    public Matcher<View> expandMaterialButton = withId(R.id.expand_material_button);
    public Matcher<View> newsRecyclerList = withId(R.id.news_list_recycler_view);
    public Matcher<View> newsDescriptionText = withId(R.id.news_item_description_text_view);
    public Matcher<View> newsTitleText = withId(R.id.news_item_title_text_view);

    public void isNewsPage() {
        Allure.step("Проверка открытой страницы списка новостей.");
        UIDevise.waitView(withText("News")).check(matches(isDisplayed()));
    }

    public void isEmptyNewsList() {
        Allure.step("Проверка списка новостей на отсутствие новостей.");
        UIDevise.waitView(withId(R.id.empty_news_list_image_view)).check(matches(isDisplayed()));
        UIDevise.waitView(withText("There is nothing here yet…")).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withId(R.id.news_retry_material_button), withText("REFRESH"))).check(matches(isDisplayed()));
    }

    public void sortNewsMaterialBut () {
        Allure.step("Нажатие на кнопку сортировки списка новостей.");
        UIDevise.waitView(sortNewsMaterialBut).perform(click());
    }

    public void openFilterNews() {
        Allure.step("Открытие фильтра новостей.");
        UIDevise.waitView(filterNewsMaterialBut).perform(click());
    }

    public void openNewsOnNewsPage(int position) {
        Allure.step("Нажатие на кнопку новости в боковом меню.");
        UIDevise.waitView(UIDevise.withRecyclerView(R.id.news_list_recycler_view)
                .atPositionOnView(position, R.id.news_item_material_card_view)).perform(click());
    }

    public ViewInteraction getNewsItemDescription(int position) {
        Allure.step("Получение описания новсти из списка по порядковому номеру.");
        return UIDevise.waitView(UIDevise.withRecyclerView(R.id.news_list_recycler_view).atPositionOnView(position, R.id.news_item_description_text_view));
    }

    public ViewInteraction scrollToElementInRecyclerList(String description) {
        Allure.step("Прокручивание к элементу в списке.");
        return UIDevise.waitView(newsRecyclerList)
                .perform(RecyclerViewActions.scrollTo(
                        (withText(description))));
    }

    public ViewInteraction getNewsDescriptionText() {
        Allure.step("Получение содержимого описания новости." );
        return UIDevise.waitView(newsDescriptionText);
    }

    public ViewInteraction getNewsTitleText() {
        Allure.step("Получение содержимого тайтла новости." );
        return UIDevise.waitView(newsTitleText);
    }

    public void expandListBut() {
        Allure.step("Нажатие на кнопку 'свернуть/развернуть'." );
        UIDevise.waitView(expandMaterialButton).perform(click());
    }

    public ViewInteraction getNewsRecyclerList() {
        Allure.step("Обращение к списку новостей." );
        return UIDevise.waitView(newsRecyclerList);
    }

    public void openEditPanel() {
        Allure.step("Переход на страницу редактирования новостей через нажатие кнопки в меню." );
        UIDevise.waitView(editNewsMaterialBut).perform(click());
    }

}
