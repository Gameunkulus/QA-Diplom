package screens.filter;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matcher;

import java.time.LocalDateTime;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.UIDevise;

public class FilterNewsScreen {

    public Matcher<View> categoryButton = withId(R.id.news_item_category_text_auto_complete_text_view);
    public Matcher<View> titleButton = withId(R.id.news_item_title_text_input_edit_text);
    public Matcher<View> startDateLine = withId(R.id.news_item_publish_date_start_text_input_edit_text);
    public Matcher<View> endDateLine = withId(R.id.news_item_publish_date_end_text_input_edit_text);
    public Matcher<View> filterNewsActive = withId(R.id.filter_news_active_material_check_box);
    public Matcher<View> filterNewsNotActive = withId(R.id.filter_news_inactive_material_check_box);
    public Matcher<View> filterFilterBtn = withId(R.id.filter_button);
    public Matcher<View> filterCancelBtn = withId(R.id.cancel_button);
    public Matcher<View> messageOkBut = withId(android.R.id.button1);
    public Matcher<View> messageCancelBut = withId(android.R.id.button2);
    public Matcher<View> datePicker = isAssignableFrom(DatePicker.class);
    public Matcher<View> timePicker = isAssignableFrom(TimePicker.class);
    public Matcher<View> messageAboutDelete = withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future.");


    public void isFilterNewsForm() {
        Allure.step("Открыта страница фильтра новостей." );
        UIDevise.waitView(titleButton).check(matches(isDisplayed()));
        UIDevise.waitView(categoryButton).check(matches(isDisplayed()));
        UIDevise.waitView(startDateLine).check(matches(isDisplayed()));
        UIDevise.waitView(endDateLine).check(matches(isDisplayed()));
        UIDevise.waitView(filterFilterBtn).check(matches(isDisplayed()));
        UIDevise.waitView(filterCancelBtn).check(matches(isDisplayed()));
    }

    public void isFilterNewsFormControlPanel() {
        Allure.step("Открыта панеь управления страницы фильтра новостей." );
        isFilterNewsForm();
        UIDevise.waitView(filterNewsActive).check(matches(isDisplayed()));
        UIDevise.waitView(filterNewsNotActive).check(matches(isDisplayed()));
    }

    public void selectANewsCategoryFromTheList(String nameCategory) {
        Allure.step("Выбор категории фильтра из списка." );
        UIDevise.waitView(categoryButton).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withText(nameCategory)).inRoot((RootMatchers.isPlatformPopup())).check(matches(isDisplayed())).perform(click());
    }

    public void fillingOutTheFilterNewsForm(String nameCategory, LocalDateTime startDate, LocalDateTime endDate, boolean Active, boolean Notactive) {
        Allure.step("Заполнение строк фильтра.");
        selectANewsCategoryFromTheList(nameCategory);
        setDateToDatePicker(startDateLine, startDate);
        UIDevise.waitView(messageOkBut).perform(click());
        setDateToDatePicker(endDateLine, endDate);
        UIDevise.waitView(messageOkBut).perform(click());
        if(Active){
            UIDevise.waitView(filterNewsActive).perform(click());
        }
        if(Notactive) {
            UIDevise.waitView(filterNewsNotActive).perform(click());
        }

    }

    public void setDateToDatePicker(Matcher<View> nameDatePicker, LocalDateTime date) {
        Allure.step("Ввод даты в DatePicker." );
        UIDevise.waitView(nameDatePicker).perform(click());
        UIDevise.waitView(datePicker).check(matches(isDisplayed()));
        UIDevise.waitView(datePicker).perform(setDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth()));
    }

    public void pressMessageButton(Boolean OK) {
        Allure.step("Заполнение строк фильтра.");
        UIDevise.waitView(messageOkBut).check(matches(isDisplayed()));
        UIDevise.waitView(messageCancelBut).check(matches(isDisplayed()));
        if (OK) {
            UIDevise.waitView(messageOkBut).perform(click());
        } else {
            UIDevise.waitView(messageCancelBut).perform(click());
        }
    }

    public void filterNewsButtonClick() {
        Allure.step("Подтверждение установленных параметров фильтра.");
        UIDevise.waitView(filterFilterBtn).perform(click());
    }

    public void cancelNewsButtonClick() {
        Allure.step("Нажатие на кнопку отмены.");
        UIDevise.waitView(filterCancelBtn).perform(click());
    }

    public ViewInteraction getNewsFilterCategoryField() {
        Allure.step("Обращение к полю категория новости." );
        return UIDevise.waitView(categoryButton);
    }

    public ViewInteraction getNewsFilterPublishDateStartField() {
        Allure.step("Обращение к полю ранней даты." );
        return UIDevise.waitView(startDateLine);
    }

    public ViewInteraction getNewsFilterPublishDateEndField() {
        Allure.step("Обращение к полю поздней даты." );
        return UIDevise.waitView(endDateLine);
    }

}
