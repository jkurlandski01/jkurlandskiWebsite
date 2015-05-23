package interviews.findfoo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class MGordon {
	
	
public static Bar getLastBar(List<Object> stuff) throws IllegalStateException   {
    Bar last = null;
    for(int i=0;i<stuff.size();i++){
        if(stuff.get(i) instanceof Bar){
            last = (Bar) stuff.get(i);
        }
    }
    if (last == null) {
        throw new IllegalStateException();
    }
    else {
        return last;
    }
}

public static Bar getLastNthBar(List<Object> stuff, int n) throws IllegalStateException   {
    if (n < 1) {
        throw new IllegalArgumentException("n must be positive");
    }
    if (stuff == null){
        throw new IllegalArgumentException("stuff must not be null");
    }
    Bar last = null;
    int count = 0;
    for(int i=0;i<stuff.size();i++){
        if(stuff.get(i) instanceof Bar){
            count++;
            if (count==n){
                return (Bar) stuff.get(i);
            }
        }
    }
    throw new IllegalStateException();
}


}

