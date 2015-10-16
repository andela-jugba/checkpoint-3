package com.andela.checkpoint.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.robolectric.shadows.ShadowView.clickOn;

/**
 * Created by andela on 10/15/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,  sdk = 21)
public class ConverterActivityTest {


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        //Given I am a User
        //When I am on the Home screen
        ConverterActivity converterActivity = Robolectric.setupActivity(ConverterActivity.class);
        //And I tap the Top Ten tab
        //clickOn();

    }
}
