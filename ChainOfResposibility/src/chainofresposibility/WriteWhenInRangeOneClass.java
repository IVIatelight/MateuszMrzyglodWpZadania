
package chainofresposibility;

public class WriteWhenInRangeOneClass {
    
    private WriteWhenInRangeOneClass nextInChain;
    private final int rangeMin;
    private final int rangeMax;    
    
    WriteWhenInRangeOneClass(int min, int max){
        
       rangeMin = min;
       rangeMax = max;
    }

    public void setNextChain(WriteWhenInRangeOneClass nextChain){
        this.nextInChain = nextChain;
    }

     public void writeComment(int number){
         
        if(number < rangeMin){
             System.out.println("Number is lower than: " + rangeMin);
        }
        else{
            if(number > rangeMax){
                if(nextInChain != null){
                    nextInChain.writeComment(number);
                }else{
                    System.out.println("Number is higher than: " + rangeMax);
                }
            }else{
                System.out.println("Number is in range from " + rangeMin + " to " + rangeMax);
            }
        }  
     }
    
    
    
    
}
