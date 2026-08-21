import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public class MyOval extends MyBoundedShape {
    public MyOval(int x1, int y1, int x2, int y2, Paint paint, Stroke stroke, boolean filled) {
        super(x1, y1, x2, y2, paint, stroke, filled);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(getPaint());
        g2d.setStroke(getStroke());
        if (isFilled()) {
            g2d.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        } else {
            g2d.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    }
}