/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ZValidate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dell
 */
public class ZValidatorTest {
    
    public ZValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isNumber method, of class ZValidator.
     */
    

    /**
     * Test of isAgeGreaterThan method, of class ZValidator.
     */
    @Test
    public void testIsPhone() {
        System.out.println("IsPhone");
        String text = "0922102231";
        String range = "9-11";
        boolean expResult = true;
        boolean result = ZValidator.isNumberInRage(text, range);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testIsPhoneWrong() {
        System.out.println("IsPhone");
        String text = "0922102231";
        String range = "1-2";
        boolean expResult = false;
        boolean result = ZValidator.isNumberInRage(text, range);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
