
package examplecomposite;

import java.util.ArrayList;

public class Composite implements ComponentInterface{
    
    private ArrayList< ComponentInterface > leafList = new ArrayList< ComponentInterface >();
    
    public void addComponent(ComponentInterface leaf){

	leafList.add(leaf);
    }
    
    @Override
    public void sayHello(){
        
        if (!leafList.isEmpty()){
            for (ComponentInterface leaf : leafList){
		if (leaf instanceof ComponentInterface){
                    leaf.sayHello();
                }
            } 
	}
    }
    
}
