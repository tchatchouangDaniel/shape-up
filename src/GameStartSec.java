import model.shape.up.GameManager;
import view.shape.up.SetupException;

public class GameStartSec {
    public static void main(String[] args) throws SetupException {
        GameManager gm = GameManager.getUniqueGm();
        gm.mainMenu();
    }
}
