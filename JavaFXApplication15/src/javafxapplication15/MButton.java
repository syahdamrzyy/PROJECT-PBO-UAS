import javafx.scene.control.Button;

/**
 *
 * @author Kel 7
 */
public class MButton extends Button {

    int position = 0;

    public MButton(int i) {
        position = i;
    }

    public int getPlayerIndex() {
        return position;
    }
}
