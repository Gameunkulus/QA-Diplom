package ru.iteco.fmhandroid.ui.tests;


import static tools.UIDevise.device;

import android.os.RemoteException;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import screens.AuthScreen;
import screens.ControlPanelListScreen;
import screens.CreateNewsScreen;
import screens.MainScreen;
import screens.NewsScreen;
import screens.filter.blocks.ReadyNews;
import tools.GenerateData;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CreateNewNewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthScreen authScreen = new AuthScreen();
    private MainScreen mainScreen = new MainScreen();
    private NewsScreen newsScreen = new NewsScreen();
    private ControlPanelListScreen controlPanelListScreen = new ControlPanelListScreen();
    private CreateNewsScreen createNewsScreen = new CreateNewsScreen();
    private ReadyNews readyNews = new ReadyNews();

    @Before
    public void logoutCheck() throws RemoteException {
        device =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.setOrientationNatural();
        try {
            authScreen.isAuthScreen();
        } catch (PerformException e) {
            mainScreen.clickLogOutBut();
        }
    }

    @Test
    public void createNewNews() {
        authScreen.fillFields(GenerateData.authInfo());
        authScreen.clickEnterButton();
        mainScreen.isMainPage();
        mainScreen.openNewsPageThroughTheMainMenu();
        newsScreen.openEditPanel();
        controlPanelListScreen.isControlPanel();
        controlPanelListScreen.openCreatingNewsForm();
        createNewsScreen.cancelButtonClick();
        createNewsScreen.okButtonClick();
        controlPanelListScreen.isControlPanel();
        controlPanelListScreen.openCreatingNewsForm();
        createNewsScreen.selectANewsCategoryFromTheList("Объявление");
        createNewsScreen.replaceNewsTitleText(readyNews.getTitle());
        createNewsScreen.setDateToDatePicker(LocalDateTime.now());
        createNewsScreen.setTimeToTimeField(LocalDateTime.now());
        createNewsScreen.replaceNewsDescriptionText(readyNews.getDescription());
        createNewsScreen.switchNewsStatus();
        createNewsScreen.cancelButtonClick();
        createNewsScreen.cancelDeleteButtonClick();
        createNewsScreen.saveButtonClick();
        createNewsScreen.okButtonClick();
        controlPanelListScreen.checkNewsIsPresent(readyNews.getTitle());

    }


}
