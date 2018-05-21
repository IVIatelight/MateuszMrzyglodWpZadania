
package chainofresposibility;

public class WrWhInRange0to10 implements WriteWhenInRangeInterface {
    private WriteWhenInRangeInterface nextInChain;
    
    private int rangeMin = 0;
    private int rangeMax = 10;    
    
    @Override
    public void setNextChain(WriteWhenInRangeInterface nextChain){
        this.nextInChain = nextChain;
    }

    @Override
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
