/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmi;

/**
 *
 * @author ADMIN
 */
public class CalculatorBMI {
    
    public static String calculateBMI(double height, double weight, boolean sex) {
        double result = weight / (height * height);
        
        if(sex == true) {
            if(result < 20) {
                return "Underweight";
            } else if(result < 25) {
                return "Normal";
            } else if(result < 30) {
               return "Overweight";
            } else {
                return "Obesity";
            }
        } else {
            if(result < 18) {
                return "Underweight";
            } else if(result < 23) {
                return "Normal";
            } else if(result < 30) {
                return "Overweight";
            } else {
                return "Obesity";
            }
        }
    }
    
}
