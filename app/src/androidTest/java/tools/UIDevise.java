package tools;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static org.hamcrest.Matchers.notNullValue;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewInteraction;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UIDevise {

    public static UiDevice device;
    private static final int LAUNCH_TIMEOUT = 5000;

    public static ModRecyclerViewActions.RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new ModRecyclerViewActions.RecyclerViewMatcher(recyclerViewId);
    }

    public static ViewInteraction waitView(Matcher<View> matcher) {
        onView(isRoot()).perform(ViewActionCommands
                .waitElement(matcher, 10000));
        return onView((matcher));
    }

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static void waitForPackage(String packageName) {
        device =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);
        // Launch the app
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(packageName);
        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)),
                LAUNCH_TIMEOUT);
        // Wait for package
        context.startActivity(intent);
        device.wait(Until.hasObject(By.pkg(packageName)), LAUNCH_TIMEOUT);
    }

    public static String getDateToString(LocalDateTime date) {
        String formatPattern = "dd.MM.yyyy";
        return date.format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String getTimeToString(LocalDateTime date) {
        String formatPattern = "HH:mm";
        return date.format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String getHourToString(LocalDateTime date) {
        String formatPattern = "HH";
        return date.format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String getMinuteToString(LocalDateTime date) {
        String formatPattern = "mm";
        return date.format(DateTimeFormatter.ofPattern(formatPattern));
    }
}
