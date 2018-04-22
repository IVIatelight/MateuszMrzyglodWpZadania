
package calculator;

public class Facade {
    
    public void add()
    {
        System.out.println("Dodaje 2 liczby");
        Addition add = new Addition();
        add.add2Number();
    }
    public void sub()
    {
        System.out.println("Odejmuje 2 liczby");
        Subtraction sub = new Subtraction();
        sub.subtract2Number();
    }
    public void mul()
    {
        System.out.println("Mnożę 2 liczby");
        Multiplication mul = new Multiplication();
        mul.multiply2Number();
    }
    
}
