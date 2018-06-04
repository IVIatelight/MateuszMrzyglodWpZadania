
package examplecomposite;

public class Leaf implements ComponentInterface{
    
    String name;
    
    Leaf(String name){
        this.name = name;
    }
    
    @Override
    public void sayHello(){
        System.out.println(name + " say hello!!");
    }
}
