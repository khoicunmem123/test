/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTest;

import bmi.CalculatorBMI;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ADMIN
 */
public class TestBMI {
    
    @Test
//    public TestBMI() {
//    }
    
    public void testBMI() {
        assertEquals("Underweight", CalculatorBMI.calculateBMI(1.75, 55, true));
        assertEquals("Normal", CalculatorBMI.calculateBMI(1.75, 65, true));
        assertEquals("Overweight", CalculatorBMI.calculateBMI(1.65, 78, true));
        assertEquals("Obesity", CalculatorBMI.calculateBMI(1.65, 90, true));
        
        assertEquals("Underweight", CalculatorBMI.calculateBMI(1.75, 50, false));
        assertEquals("Normal", CalculatorBMI.calculateBMI(1.70, 55, false));
        assertEquals("Overweight", CalculatorBMI.calculateBMI(1.6, 60, false));
        assertEquals("Obesity", CalculatorBMI.calculateBMI(1.55, 75, false));
        
        
    }
    
}
