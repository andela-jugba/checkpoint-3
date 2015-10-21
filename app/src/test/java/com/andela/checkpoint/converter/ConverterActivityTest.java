package com.andela.checkpoint.converter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.andela.checkpoint.converter.fragments.ConvertFragment;
import com.andela.checkpoint.converter.fragments.TopTenFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.robolectric.shadows.ShadowView.clickOn;

/**
 * Created by andela on 10/15/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,  sdk = 21)
public class ConverterActivityTest {
    private ConverterActivity converterActivity;
    private ConvertFragment convertFragment;
    private TopTenFragment topTenFragment;
    private TabLayout tabLayout;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        //Given I am a User
        //When I am on the Home screen
         converterActivity = Robolectric.setupActivity(ConverterActivity.class);

        //And I tap the Top Ten tab]

        tabLayout = (TabLayout)converterActivity.findViewById(R.id.sliding_tabs);
        clickOn(tabLayout.getChildAt(0));

        //clickOn();

        topTenFragment = (TopTenFragment) converterActivity.getSupportFragmentManager().findFragmentById(R.id.viewpager);
        assertNotNull(topTenFragment);

        // Then I see a list of the top ten currencies
        FrameLayout layout = (FrameLayout)topTenFragment.getView();
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerView);
        assertEquals(recyclerView.getAdapter().getItemCount(),10);

        // Given that I am User
        // And I am on the Converter screen,

        // when I enter a Value to convert ($50) plus ($50)
        // then I Should get a result of (100)

        // when I change the operation to minus()
        // then I should get a result of $0

        // when I change the operation to multiplication
        // then I should get a result of $2500

        //when I change the operation to division
        // then I should  get a result of $1


        // Given that I a user,
        // And I am on the converter screen
        // when I enter $10 plus 10 pound to get get my result in Naira,
        // then I should get a result of 10 * dollarToNairaRate + 10 * PoundToNairaRate

        //when I change the operation, then
        // I should get the corresponding Naira value.



    }
}
