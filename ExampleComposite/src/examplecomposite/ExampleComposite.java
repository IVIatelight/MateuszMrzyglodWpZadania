
package examplecomposite;

public class ExampleComposite {

    public static void main(String[] args) {
        
        Leaf leaf1 = new Leaf("Erich Gamma");
	Leaf leaf2 = new Leaf("Richard Helm");
        Leaf leaf3 = new Leaf("Ralph Johnson");
        Leaf leaf4 = new Leaf("John Vlissides");
        
        Composite gangOfFour = new Composite();
        
        gangOfFour.addComponent(leaf1);
        gangOfFour.addComponent(leaf2);
        gangOfFour.addComponent(leaf3);
        gangOfFour.addComponent(leaf4);
        
        gangOfFour.sayHello();
    }
    
}
