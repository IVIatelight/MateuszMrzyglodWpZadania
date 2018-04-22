
package calculator;

public class Facade {
    
    Addition add;
    Subtraction sub;
    Multiplication mul;
    
    public Facade()
    {
        add = new Addition();
        sub = new Subtraction();
        mul = new Multiplication();
    }
    
    public void add()
    {
        add.add2Number();
    }
    public void sub()
    {
        sub.subtract2Number();
    }
    public void mul()
    {
        mul.multiply2Number();
    }
    
}
