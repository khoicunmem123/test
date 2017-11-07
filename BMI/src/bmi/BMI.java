/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmi;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class BMI {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("---- Welcome To BMI Program ----");
        System.out.println();

        do {            
            boolean s = isSex();

            double height = height();

            double weight = weight();

            System.out.print("Stattus: ");
            CalculatorBMI.calculateBMI(height, weight, s);
            System.out.println(CalculatorBMI.calculateBMI(height, weight, s));
            
            System.out.print("Continue: ");
            
        } while (!sc.nextLine().equals("n"));
    }

    public static boolean isSex() {
        System.out.print("Input sex(male/female): ");
        String sex = "";

        while (true) {
            sex = sc.nextLine();
            if (!sex.equals("male") && !sex.equals("female")) {
                System.out.println("Wrong input!!!");
                System.out.print("Input again: ");
            } else {
                break;
            }
        }

        if (sex.equals("male")) {
            return true;
        }

        return false;
    }

    public static double height() {
        System.out.print("Input height: ");
        double height = 0;

        while (true) {
            try {
                height = Double.parseDouble(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Wrong input!!");
                System.out.print("Input again: ");;
            }
        }

        return height;
    }

    public static double weight() {
        System.out.print("Input weight: ");
        double weight = 0;

        while (true) {
            try {
                weight = Double.parseDouble(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Wrong input!!");
                System.out.print("Input again: ");;
            }
        }
        return weight;
    }
}
