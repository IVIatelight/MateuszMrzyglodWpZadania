
package calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {
        
    public double loadDouble()
    {
        double number=0.0;
        Scanner sc = new Scanner(System.in);
          
        while(true)
            try{
                number = sc.nextDouble();
                break;
            }catch(InputMismatchException blad){
                System.out.println( "  Podano błędne dane!" );
                sc.nextLine();
                System.out.print("  Podaj jeszcze raz: ");
            }       

        return number;
    }
}
