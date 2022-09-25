package edu.uwplatt.team05;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Sean Maidana Dollanarte
 * This class tests the default music volume and whether it is 25% like we
 * expect it to be since that is our default value
 */

public class testMusicEquals25Percent {

    // The default music volume is 25% (represented as 0.25 in our code
    // since it is a double). We are testing and verifying this by
    // doing assertions at various bounds.

    TetrisController tc = new TetrisController();
    @Test
    void testIsMusicAt25PercentTrue() // Our sound is by default set to 25.
    {
        Assertions.assertEquals(tc.getMusicVol(), 0.25f);
    }

    @Test
    void testIsMusicOver25PercentFalse() // 25% -> 0.25f
    {
        Assertions.assertNotEquals(tc.getMusicVol(), 0.50f);
        Assertions.assertNotEquals(tc.getMusicVol(), 1.0f);
        Assertions.assertNotEquals(tc.getMusicVol(), 100f);

    }

    @Test
    void testIsMusicUnder25PercentFalse() // 25% -> 0.25f
    {
        Assertions.assertNotEquals(tc.getMusicVol(),0.10f);
        Assertions.assertNotEquals(tc.getMusicVol(),0f);
        Assertions.assertNotEquals(tc.getMusicVol(),-100.0f);
    }



}
