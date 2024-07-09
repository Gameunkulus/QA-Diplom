package screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.GenerateData;
import tools.UIDevise;


public class AuthScreen {
    private UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    public ViewInteraction emptyToast(int id) {
        return onView(withText(id)).inRoot(new GenerateData.ToastMatcher());
    }

    public void fillFields(GenerateData.AuthInfo info) {
        UIDevise.waitView(allOf(withText("Authorization"), withParent(withParent(withId(R.id.nav_host_fragment))))).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout))))).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout))))).perform(replaceText(info.getLogin()));
        UIDevise.waitView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout))))).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout))))).perform(replaceText(info.getPass()));
    }

    public void pushSignIn(){
        UIDevise.waitView(allOf(withText("Sign in"), withParent(withParent(withId(R.id.enter_button))))).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withText("Sign in"), withParent(withParent(withId(R.id.enter_button))))).check(matches(isClickable()));
        UIDevise.waitView(allOf(withText("Sign in"), withParent(withParent(withId(R.id.enter_button))))).perform(click());
    }

    public void isAuthorisationPage(){
        UIDevise.waitView(allOf(withText(R.string.authorization))).check(matches(isDisplayed()));
    }

    public void checkToastMessage(int id, boolean visible) {
            emptyToast(id).check(matches(isDisplayed()));
    }

    public void clickEnterButton() {
        onView((withId(R.id.enter_button))).check(matches(isDisplayed()));
        Allure.step("Клик по кнопке c id: " + R.id.enter_button);
        onView((withId(R.id.enter_button))).perform(click());
    }

}

    