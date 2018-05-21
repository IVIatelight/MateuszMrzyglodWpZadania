
package chainofresposibility;

public class ChainOfResposibility {

    public static void main(String[] args) {
        
        {
        WriteWhenInRangeInterface w1 = new WrWhInRange0to10();
        WriteWhenInRangeInterface w2 = new WrWhInRange11to20();
        WriteWhenInRangeInterface w3 = new WrWhInRange21to30();
        w1.setNextChain(w2);
        w2.setNextChain(w3);
        
        w1.writeComment(12);
        }
        
        WriteWhenInRangeOneClass w1 = new WriteWhenInRangeOneClass(0, 10);
        WriteWhenInRangeOneClass w2 = new WriteWhenInRangeOneClass(11, 20);
        WriteWhenInRangeOneClass w3 = new WriteWhenInRangeOneClass(21, 30);
        w1.setNextChain(w2);
        w2.setNextChain(w3);
        w1.writeComment(27);
        
        
    }
    
}
