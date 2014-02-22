package com.edrichard.f1drtoid.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class TestF1Droid.
 * @author edrichard.
 */
public class TestF1Droid {

    /**
     * fonction addition test().
     */
    @Test
    public final void test() {
       final Integer a = 5;
       final Integer b = 5;
       final Integer result = 10;
       final double delta = 0.01;

       final Integer test = a + b;

       Assert.assertEquals(result, test, delta);
    }

}
