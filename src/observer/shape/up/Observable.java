package observer.shape.up;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The class Observable is the same as the {@link java.util.Observable}, but we implemented a new one because it's a depreciated class.
 * Indeed, it causes several problems during the process.
 * Thus, see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Observable.html">Class Observable Javadoc</a>.
 *
 * @author Daniel
 */
public class Observable {

    protected ArrayList<Observer> listObserver = new ArrayList<>();
    protected boolean changed = false;

    /**
     * Adds an observer to the set of observers for this object, provided that it is not the same as some observer already in the set.
     * @param o Observers
     */
    public void addObserver(Observer o) {
        this.listObserver.add(o);
    }

    /**
     * Clear the observer list
     */
    public void clearObservers(){
        this.listObserver.clear();
    }

    /**
     * Marks this Observable object as having been changed; the <code>hasChanged</code> method will now return true.
     */
    public void setChanged(){
        this.changed = true;
    }

    /**
     * Tests if this object has changed.
     *
     * @return boolean value
     */
    public boolean hasChanged(){
        return this.changed;
    }

    //Notification des observateurs
    /**
     * If this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed.
     */
    public void notifyObservers(){
        if(this.changed){
            Iterator<Observer> i = this.listObserver.iterator();
            while (i.hasNext()){
                Observer o = (Observer)i.next();
                o.update(this,null);
            }
        }
    }
    /**
     * If this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed.
     * Each observer has its update method called with two arguments: this observable object and the arg argument.
     * @param b ant Object
     */
    public void notifyObservers(Object b){
        if(this.changed){
            Iterator<Observer> i = this.listObserver.iterator();
            while (i.hasNext()){
                Observer o = (Observer)i.next();
                o.update(this,b);
            }
        }
    }
}
