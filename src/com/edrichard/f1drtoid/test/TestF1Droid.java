package com.edrichard.f1drtoid.test;

import org.junit.Assert;
import org.junit.Test;

public class TestF1Droid {

    @Test
    public void test() {
       final Integer a = 5;
       final Integer b = 5;
       final Integer result = 10;
       final double delta = 0.01;
       
       final Integer test = a + b;
       
       Assert.assertEquals(result, test, delta);
    }

}
