package com.charleskelly.magic.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class MathUtilityTest
{
    @Test
    public void test010SimpleExponentiation()
    {
        try
        {
            int base = 2;
            int exponent = 5;
            int expectedResult = 32;
            int result = MathUtility.integerExponentiation(base, exponent);

            assertEquals(expectedResult, result);
        }
        catch (Exception e)
        {
            fail (e.toString());
        }
    }

    @Test
    public void test020SimpleExponentiation()
    {
        try
        {
            int base = 5;
            int exponent = 2;
            int expectedResult = 25;
            int result = MathUtility.integerExponentiation(base, exponent);

            assertEquals(expectedResult, result);
        }
        catch (Exception e)
        {
            fail (e.toString());
        }
    }


    @Test
    public void test030ZeroExponent()
    {
        try
        {
            int base = 5;
            int exponent = 0;
            int expectedResult = 1;
            int result = MathUtility.integerExponentiation(base, exponent);

            assertEquals(expectedResult, result);
        }
        catch (Exception e)
        {
            fail (e.toString());
        }
    }
}
