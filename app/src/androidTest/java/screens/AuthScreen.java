package screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static tools.ToastMatcher.isToast;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import tools.GenerateData;
import tools.UIDevise;


public class AuthScreen {

    //проверка всплывающего сообщения
    public void isToastMessageDisplayed(int textId) {
        Allure.step("Получение сообщения об ошибке: " + R.string.error + ";" );
        UIDevise.waitView(withText(textId)).inRoot(isToast()).check(matches(not(isDisplayed())));
    }

    public void fillFields(GenerateData.AuthInfo info) {
        Allure.step("Заполнение полей логин и пароль с id: " +
                R.id.login_text_input_layout + ";\n " + R.id.password_text_input_layout + ";" );
        UIDevise.waitView(allOf(withText("Authorization"), withParent(withParent(withId(R.id.nav_host_fragment))))).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout))))).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout))))).perform(replaceText(info.getLogin()));
        UIDevise.waitView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout))))).check(matches(isDisplayed()));
        UIDevise.waitView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout))))).perform(replaceText(info.getPass()));
    }

    public void isAuthScreen(){
        Allure.step("Проверка перехода на страницу авторизации.");
        UIDevise.waitView(allOf(withText(R.string.authorization))).check(matches(isDisplayed()));
    }

    public void clickEnterButton() {
        Allure.step("Нажатие на кнопку войти.");
        onView((withId(R.id.enter_button))).check(matches(isDisplayed()));
        Allure.step("Клик по кнопке c id: " + R.id.enter_button);
        onView((withId(R.id.enter_button))).perform(click());
    }

}

    