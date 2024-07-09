package screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.time.LocalDateTime;

import screens.filter.blocks.ContainerScreen;
import ru.iteco.fmhandroid.R;
import tools.GenerateData;
import tools.UIDevise;

public class CreateNewsScreen {

    private static ControlPanelListScreen controlPanelSteps = new ControlPanelListScreen();
    private static ContainerScreen containerScreen = new ContainerScreen();

    public Matcher<View> messageAboutDelete = withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future.");

    public Matcher<View> newsItemCategoryField = withId(R.id.news_item_category_text_auto_complete_text_view);
    public Matcher<View> newsItemTitleField = withId(R.id.news_item_title_text_input_edit_text);
    public Matcher<View> newsItemPublishDateField = withId(R.id.news_item_publish_date_text_input_edit_text);
    public Matcher<View> newsItemPublishTimeField = withId(R.id.news_item_publish_time_text_input_edit_text);
    public Matcher<View> newsItemDescriptionField = withId(R.id.news_item_description_text_input_edit_text);
    public Matcher<View> switcher = withId(R.id.switcher);
    public Matcher<View> saveBut = withId(R.id.save_button);
    public Matcher<View> cancelBut = withId(R.id.cancel_button);
    public Matcher<View> okBut = withId(android.R.id.button1);
    public Matcher<View> cancelDeleteBut = withId(android.R.id.button2);
    public Matcher<View> datePicker = isAssignableFrom(DatePicker.class);
    public Matcher<View> timePicker = isAssignableFrom(TimePicker.class);
    public Matcher<View> timePickerToggleMode = withContentDescription("Switch to text input mode for the time input.");
    public Matcher<View> inputHour = Matchers.allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
            UIDevise.childAtPosition(
                    Matchers.allOf(withClassName(is("android.widget.RelativeLayout")),
                            UIDevise.childAtPosition(
                                    withClassName(is("android.widget.TextInputTimePickerView")),
                                    1)),
                    0));
    public Matcher<View> inputMinute = Matchers.allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
            UIDevise.childAtPosition(
                    Matchers.allOf(withClassName(is("android.widget.RelativeLayout")),
                            UIDevise.childAtPosition(
                                    withClassName(is("android.widget.TextInputTimePickerView")),
                                    1)),
                    3));
    public Matcher<View> messageChangesWonTBeSaved = withText("The changes won't be saved, do you really want to log out?");

    //Выбор категории новости из списка
    public void selectANewsCategoryFromTheList(String nameCategory) {
        UIDevise.waitView(newsItemCategoryField).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withText(nameCategory)).inRoot((RootMatchers.isPlatformPopup())).check(matches(isDisplayed())).perform(click());
    }

    //задать дату в поле дата
    public void setDateToDatePicker(LocalDateTime date) {
        UIDevise.waitView(newsItemPublishDateField).perform(click());
        UIDevise.waitView(datePicker).check(matches(isDisplayed()));
        UIDevise.waitView(datePicker).perform(setDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth()));
    }

    //задать время в поле время
    public void setTimeToTimePicker(int hour, int minute) {
        UIDevise.waitView(timePicker).check(matches(isDisplayed()));
        UIDevise.waitView(timePicker).perform(setTime(hour, minute));
    }

    //задать время в поле время через экранную клавиатуру
    public void setTimeToTimePickerFromTheKeyboard(String hour, String minutes) {
        UIDevise.waitView(timePicker).check(matches(isDisplayed()));
        UIDevise.waitView(timePickerToggleMode).perform(click());
        UIDevise.waitView(inputHour).check(matches(isDisplayed())).perform(replaceText(hour));
        UIDevise.waitView(inputMinute).check(matches(isDisplayed())).perform(replaceText(minutes));
        okButtonClick();
    }

    //открыть поле время
    public void openNewsTimePicker() {
        UIDevise.waitView(newsItemPublishTimeField).perform(click());
    }

    //задать время в поле времени
    public void setTimeToTimeField(LocalDateTime date) {
        UIDevise.waitView(newsItemPublishTimeField).perform(click());
        setTimeToTimePicker(date.getHour(), date.getMinute());
        UIDevise.waitView(okBut).perform(click());
    }


    //проверка перехода на страницу создания новости
    public void isCreatingNewsForm() {
        UIDevise.waitView(newsItemCategoryField).check(matches(isDisplayed()));
        UIDevise.waitView(newsItemTitleField).check(matches(isDisplayed()));
        UIDevise.waitView(newsItemPublishDateField).check(matches(isDisplayed()));
        UIDevise.waitView(newsItemPublishTimeField).check(matches(isDisplayed()));
        UIDevise.waitView(newsItemDescriptionField).check(matches(isDisplayed()));
        UIDevise.waitView(saveBut).check(matches(isDisplayed()));
        UIDevise.waitView(cancelBut).check(matches(isDisplayed()));
        UIDevise.waitView(switcher).check(matches(isDisplayed()));
    }

    //сообщение об ошибке при сохранении сообщения
    public void isDialogWindowMessageSavingFailed() {
        UIDevise.waitView(withText("Saving failed. Try again later.")).check(matches(isDisplayed()));
        UIDevise.waitView(okBut).check(matches(isDisplayed()));
    }

    //проверка поля титл
    public ViewInteraction getNewsItemTitle() {
        return UIDevise.waitView(newsItemTitleField);
    }

    //нажать на кнопку сохранить
    public void saveButtonClick() {
        UIDevise.waitView(saveBut).perform(click());
    }

    //нажать на кнопку отменить
    public void cancelButtonClick() {
        UIDevise.waitView(cancelBut).perform(click());
    }

    //получить сообщение внесенные изменения не будут сохранены
    public ViewInteraction getMessageChangesWonTBeSaved() {
        return UIDevise.waitView(messageChangesWonTBeSaved);
    }

    //нажать на кнопку ОК в всплывающем окне
    public void okButtonClick() {
        UIDevise.waitView(okBut).perform(click());
    }

    //нажать на кнопку отмену в всплывающем окне
    public void cancelDeleteButtonClick() {
        UIDevise.waitView(cancelDeleteBut).perform(click());
    }

    //смена категрии новости
    public void replaceNewsCategoryText(String category) {
        UIDevise.waitView(newsItemCategoryField).perform(replaceText(category));
    }

    //смена статуса новости
    public void switchNewsStatus() {
        UIDevise.waitView(switcher).perform(click());
    }

    //взять дату публикации
    public ViewInteraction getNewsItemPublishDate() {
        return UIDevise.waitView(newsItemPublishDateField);
    }

    //взять время публикации
    public ViewInteraction getNewsItemPublishTime() {
        return UIDevise.waitView(newsItemPublishTimeField);
    }

    //взять категорию публикации новости
    public ViewInteraction getNewsItemCategory() {
        return UIDevise.waitView(newsItemCategoryField);
    }

    //взять описание новости
    public ViewInteraction getNewsItemDescription() {
        return UIDevise.waitView(newsItemDescriptionField);
    }

    public void creatingNews(GenerateData.CreateNews news) {
        selectANewsCategoryFromTheList(news.getNewsCategory());
        fillingOutTheFormCreatingNewsWithDate(news);
        UIDevise.waitView(saveBut).perform(click());
    }

    public void createNews(GenerateData.CreateNews... array) {
        for (GenerateData.CreateNews news : array) {
            creatingNews(news);
        }
    }

    public void fillingOutTheFormCreatingNewsWithDate(GenerateData.CreateNews news) {
        UIDevise.waitView(newsItemTitleField).perform(replaceText(news.getNewsName()));
        setDateToDatePicker(news.getDueDate());
        UIDevise.waitView(okBut).perform(click());
        setTimeToTimeField(news.getDueDate());
        UIDevise.waitView(newsItemDescriptionField).perform(replaceText(news.getNewsDescription()));
    }

    public void replaceNewsTitleText(String title) {
        UIDevise.waitView(newsItemTitleField).perform(replaceText(title));
    }

    public void replaceNewsDescriptionText(String description) {
        UIDevise.waitView(newsItemDescriptionField).perform(replaceText(description));
    }

    public ViewInteraction getItemNewsEditElement(String title) {
        return onView(allOf(withText(title)));
    }

    public ViewInteraction getItemNewsButViewElement(String title) {
        return onView(withParent(withText(title)));
    }

    public ViewInteraction getItemNewsDescriptionElement(String title) {
        return onView(withText(title));
    }

}
