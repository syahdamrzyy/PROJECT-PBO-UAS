import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;

public abstract class GameElement {
    protected int i1, j1; // Starting position
    protected int i2, j2; // Ending position
    protected Paint color; // Color of the element
    protected Path path; // Path for animation

    public GameElement(int i1, int j1, int i2, int j2) {
        this.i1 = i1;
        this.j1 = j1;
        this.i2 = i2;
        this.j2 = j2;
        this.path = new Path();
        this.color = Color.WHITE; // Default color
    }

    public abstract void drawOn(Board root);

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public int getI2() {
        return i2;
    }

    public void setI2(int i2) {
        this.i2 = i2;
    }

    public int getJ1() {
        return j1;
    }

    public void setJ1(int j1) {
        this.j1 = j1;
    }

    public int getJ2() {
        return j2;
    }

    public void setJ2(int j2) {
        this.j2 = j2;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public Path getPath() {
        return path;
    }
}