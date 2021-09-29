package observer.shape.up;
/**
 * The class Observable is the same as the {@link java.util.Observer}, but we implemented a new one because it's a depreciated class.
 * Indeed, it causes several problems during the process.
 * Thus, see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Observer.html">Class Observable Javadoc</a>.
 *
 * A class can implement the Observer interface when it wants to be informed of changes in observable objects.
 *
 * @author Daniel
 */
public interface Observer {

    /**
     * This method is called whenever the observed object is changed.
     *
     * @param s any Observable
     * @param o any Object
     */
    public void update(Observable s, Object o);
}
