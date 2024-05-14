package com.signalquest.api;

import static org.junit.Assert.assertEquals;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StatusTest {
    @Test public void testModeLabel() {
        System.loadLibrary("sqsp_wrapper");
        String[] modes = new String[]{
            "Offline", "Acquiring", "2D", "3D", "RTK-float", "RTK-fixed", "Auto surveying", "Fixed base"
        };

        for (int i = 0; i < 8; i++) {
            Status s = new Status(0, 0, i, 0, 0, false, new boolean[8]);
            assertEquals(modes[i], s.getModeLabel());
        }
    }
}