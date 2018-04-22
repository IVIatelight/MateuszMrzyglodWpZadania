
package calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Multiplication {
    public void multiply2Number()
    {
        Interface sc = new Interface();
        System.out.println("Mnożę 2 liczby");
        System.out.print("Podaj pierwszą liczbę: ");
        double number1 = sc.loadDouble();
        System.out.print("Podaj drugą liczbę: ");
        double number2 = sc.loadDouble();
        System.out.println(number1 + " * " + number2 + " = " + (number1*number2));
    }
}
