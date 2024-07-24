package screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static tools.ToastMatcher.isToast;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.GenerateData;
import tools.ModRecyclerViewActions;
import tools.UIDevise;

public class ControlPanelListScreen {

    public Matcher<View> addNewsImBut = withId(R.id.add_news_image_view);
    public Matcher<View> okBut = withId(android.R.id.button1);
    public Matcher<View> cancelDeleteBut = withId(android.R.id.button2);
    public Matcher<View> messageAboutDelete = withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future.");
    public Matcher<View> newsRecyclerList = withId(R.id.news_list_recycler_view);
    public Matcher<View> openFilterButton = withId(R.id.filter_news_material_button);
    public int newsItemTitleTextView = (R.id.news_item_title_text_view);

    public void clickOpenFilterButton(){
        Allure.step("Нажатие на кнопку 'открыть фильтр'." );
        UIDevise.waitView(openFilterButton).perform(click());
    }

    public ViewInteraction wrongСategoryToast(int idText) {
        Allure.step("Проверка что появилось сообщение что задана не та категория." );
        return onView(withText(idText)).inRoot(isToast()).check(matches(isDisplayed()));
    }

    //проверка перехода на страницу контрольной панели
    public void isControlPanel() {
        Allure.step("Проверка что открыта страница редактирования новостей." );
        UIDevise.waitView(withText("Control panel")).check(matches(isDisplayed()));
        UIDevise.waitView(addNewsImBut).check(matches(isDisplayed()));
    }
    public void isEmptyNewsList() {
        Allure.step("Проверка что список новостей пуст." );
        UIDevise.waitView(withId(R.id.control_panel_empty_news_list_image_view)).check(matches(isDisplayed()));
        UIDevise.waitView(withText("There is nothing here yet…")).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withId(R.id.control_panel_news_retry_material_button), withText("REFRESH"))).check(matches(isDisplayed()));
    }

    // перенести к нужному элементу строки
    public ViewInteraction scrollToElementInRecyclerList(String string) {
        Allure.step("Переход к элементу страницы новостей по содержимому блока." );
        return UIDevise.waitView(newsRecyclerList)
                // scrollTo will fail the test if no item matches.
                .perform(RecyclerViewActions.scrollTo(withText(string)));
    }

    //проверка всплывающего сообщения
    public void checkToast(int idText, boolean visible) {
        if (visible) {
            wrongСategoryToast(idText).check(matches(isDisplayed()));
        } else {
            wrongСategoryToast(idText).check(matches(not(isDisplayed())));
        }
    }

    //нажатие на кнопку создания новости
    public void openCreatingNewsForm() {
        Allure.step("Нажатие на кнопку 'создать новость'." );
        UIDevise.waitView(addNewsImBut).perform(click());
    }

    //нажать на кнопку ОК в всплывающем окне
    public void okButtonClick() {
        Allure.step("Нажатие на подтверждения действия." );
        UIDevise.waitView(okBut).perform(click());
    }

    //нажать на кнопку отмену в всплывающем окне
    public void cancelDeleteButtonClick() {
        Allure.step("Нажатие на отмену действия." );
        UIDevise.waitView(cancelDeleteBut).perform(click());
    }

    //список новостей
    public ViewInteraction getNewsRecyclerList() {
        Allure.step("Получено сообщение об удалении сообщения." );
        return UIDevise.waitView(newsRecyclerList);
    }

    //взять сообшение вы дествительно хотите удалить данное сообщение
    public ViewInteraction getMessageAboutDelete() {
        Allure.step("Получено сообщение об удалении сообщения." );
        return UIDevise.waitView(messageAboutDelete);
    }

    //удаление через титл новости
    public void deleteItemNews(String title) {
        Allure.step("Нажимаем на кнопку удалить сообщение." );
        scrollToElementInRecyclerList(title);
        getItemNewsDeleteElement(title).check(matches(isDisplayed())).perform(click());
        UIDevise.waitView(messageAboutDelete).check(matches(isDisplayed()));
        UIDevise.waitView(okBut).perform(click());
    }

    public ViewInteraction getItemNewsDeleteElement(String title) {
        Allure.step("Обращение к элементу с тайтлом для удаления." );
        return UIDevise.waitView(withText(title));
    }

    public void checkNewsIsPresent(GenerateData.CreateNews news) {
        Allure.step("Проверка что сообщение присутсутствует." );
        scrollToElementInRecyclerList(news.getNewsName()).check(matches(isDisplayed()));
    }

    public void checkNewsIsPresent(String string) {
        Allure.step("Проверка что сообщение присутсутствует." );
        scrollToElementInRecyclerList(string).check(matches(isDisplayed()));
    }

    public void checkNewsDoesNotPresent(GenerateData.CreateNews news) {
        Allure.step("Проверка что сообщение отсутствует." );
        getNewsRecyclerList()
                .check(matches(ModRecyclerViewActions.RecyclerViewMatcher
                        .matchChildViewIsNotExist(newsItemTitleTextView, withText(news.getNewsName()))));
    }

}
