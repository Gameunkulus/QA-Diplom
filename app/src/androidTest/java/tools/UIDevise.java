package tools;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UIDevise {

    public static UiDevice device;

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
