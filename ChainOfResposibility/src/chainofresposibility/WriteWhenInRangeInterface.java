
package chainofresposibility;

public interface WriteWhenInRangeInterface {
    
    void setNextChain(WriteWhenInRangeInterface nextChain);
    
    void writeComment(int number);
    
    
}
