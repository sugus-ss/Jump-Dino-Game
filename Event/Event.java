package Event;
import Character.*;
public class Event {
    public static boolean checkHit(Dog dog, Wave wave){
        if(dog.x+ dog.dogSize>wave.x && dog.x < wave.x) // Hit
            if(dog.y+dog.dogSize>=wave.y-wave.height)
            {
                return true;
            }
        return false;
    }
}
