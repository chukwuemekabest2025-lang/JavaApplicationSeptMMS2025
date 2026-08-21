import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public abstract class MyShape {
    private int x1, y1, x2, y2;
    private Paint myPaint;
    private Stroke myStroke;

    public MyShape(int x1, int y1, int x2, int y2, Paint paint, Stroke stroke) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.myPaint = paint;
        this.myStroke = stroke;
    }

    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }
    public Paint getPaint() { return myPaint; }
    public Stroke getStroke() { return myStroke; }

    public int getUpperLeftX() { return Math.min(x1, x2); }
    public int getUpperLeftY() { return Math.min(y1, y2); }
    public int getWidth() { return Math.abs(x1 - x2); }
    public int getHeight() { return Math.abs(y1 - y2); }

    public abstract void draw(Graphics2D g2d);
}