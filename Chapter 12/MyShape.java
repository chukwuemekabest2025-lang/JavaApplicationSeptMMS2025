import java.awt.Color;
import java.awt.Graphics;

// Abstract base class representing a generic shape
abstract class MyShape {
    private int x1, y1, x2, y2;
    private Color color;

    public MyShape() {
        this(0, 0, 0, 0, Color.BLACK);
    }

    public MyShape(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void setX1(int x1) { this.x1 = x1; }
    public int getX1() { return x1; }

    public void setY1(int y1) { this.y1 = y1; }
    public int getY1() { return y1; }

    public void setX2(int x2) { this.x2 = x2; }
    public int getX2() { return x2; }

    public void setY2(int y2) { this.y2 = y2; }
    public int getY2() { return y2; }

    public void setColor(Color color) { this.color = color; }
    public Color getColor() { return color; }

    public abstract void draw(Graphics g);
}

// Line shape implementation
class MyLine extends MyShape {
    public MyLine(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawLine(getX1(), getY1(), getX2(), getY2());
    }
}

// Abstract base class for bounded shapes (Rectangles & Ovals)
abstract class MyBoundedShape extends MyShape {
    private boolean filled;

    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color);
        this.filled = filled;
    }

    public boolean isFilled() { return filled; }
    public void setFilled(boolean filled) { this.filled = filled; }

    public int getUpperLeftX() { return Math.min(getX1(), getX2()); }
    public int getUpperLeftY() { return Math.min(getY1(), getY2()); }
    public int getWidth() { return Math.abs(getX1() - getX2()); }
    public int getHeight() { return Math.abs(getY1() - getY2()); }
}

// Oval shape implementation
class MyOval extends MyBoundedShape {
    public MyOval(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        if (isFilled()) {
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        } else {
            g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    }
}

// Rectangle shape implementation
class MyRectangle extends MyBoundedShape {
    public MyRectangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        if (isFilled()) {
            g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        } else {
            g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    }
}