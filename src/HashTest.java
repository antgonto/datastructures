import java.util.HashMap;
import java.util.Map;

public class HashTest {
        public static void main(String args[]){
            HashMap<Integer,String> hm = new HashMap<Integer,String>();
            hm.put(100,"Pedro");
            hm.put(101,"Mario");
            hm.put(102,"Raúl");
            for(Map.Entry m: hm.entrySet()){
                System.out.println(m.getKey()+" "+m.getValue());
            }
        }
}
