package screens;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.UIDevise;

public class AboutScreen {

    public Matcher<View> aboutBackImageBut = withId(R.id.about_back_image_button);
    public Matcher<View> headerTermsOfUsePage = withText("Terms of use");
    public Matcher<View> headerPrivacyPolicyPage = withText("Privacy policy");

    public void isAboutPage() {
        Allure.step("Проверка страницы о приложении.");
        UIDevise.waitView(withText("Version:")).check(matches(isDisplayed()));
        UIDevise.waitView(withText("Privacy Policy:")).check(matches(isDisplayed()));
        UIDevise.waitView(withText("Terms of use:")).check(matches(isDisplayed()));
        UIDevise.waitView(withId(R.id.about_company_info_label_text_view)).check(matches(isDisplayed()));
    }

    public void openPrivacyPolicy() {
        Allure.step("Переход по ссылке строки гипертекста 'Privacy policy'");
        UIDevise.waitView(withId(R.id.about_privacy_policy_value_text_view)).check(matches(isDisplayed()));
        UIDevise.waitView(withId(R.id.about_privacy_policy_value_text_view)).perform(click());
    }

    public void openTermsOfUse() {
        Allure.step("Переход по ссылке строки гипертекста 'Terms of use'");
        UIDevise.waitView(withId(R.id.about_terms_of_use_value_text_view)).check(matches(isDisplayed()));
        UIDevise.waitView(withId(R.id.about_terms_of_use_value_text_view)).perform(click());
    }

    public ViewInteraction getHeaderPrivacyPolicyPage() {
        Allure.step("Проверка наличия текста 'Privacy policy'");
        return UIDevise.waitView(headerPrivacyPolicyPage);
    }

    public ViewInteraction getHeaderTermsOfUsePage() {
        Allure.step("Проверка наличия текста 'Terms of use'");
        return UIDevise.waitView(headerTermsOfUsePage);
    }

    public void aboutBackImageButClick() {
        Allure.step("Нажатие на кнопку назад.");
    UIDevise.waitView(aboutBackImageBut).perform(click());
    }
}
