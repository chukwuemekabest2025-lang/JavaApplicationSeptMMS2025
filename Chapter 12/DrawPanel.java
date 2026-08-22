import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawPanel extends JPanel {

    private final MyShape[] shapes;
    private int shapeCount;
    private int shapeType; // 0: Line, 1: Rectangle, 2: Oval
    private MyShape currentShape;
    private Color currentColor;
    private boolean filledShape;
    private final JLabel statusLabel;

    public DrawPanel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
        shapes = new MyShape[100];
        shapeCount = 0;
        shapeType = 0; // Default to Line
        currentShape = null;
        currentColor = Color.BLACK;
        filledShape = false;

        setBackground(Color.WHITE);

        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all shapes stored in array
        for (int i = 0; i < shapeCount; i++) {
            shapes[i].draw(g);
        }

        // Draw shape currently being dragged
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    public void setShapeType(int shapeType) { this.shapeType = shapeType; }
    public void setCurrentColor(Color currentColor) { this.currentColor = currentColor; }
    public void setFilledShape(boolean filledShape) { this.filledShape = filledShape; }

    public void clearLastShape() {
        if (shapeCount > 0) {
            shapeCount--;
            repaint();
        }
    }

    public void clearDrawing() {
        shapeCount = 0;
        repaint();
    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            switch (shapeType) {
                case 0 -> currentShape = new MyLine(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
                case 1 -> currentShape = new MyRectangle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor, filledShape);
                case 2 -> currentShape = new MyOval(e.getX(), e.getY(), e.getX(), e.getY(), currentColor, filledShape);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentShape != null) {
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());

                if (shapeCount < shapes.length) {
                    shapes[shapeCount] = currentShape;
                    shapeCount++;
                }

                currentShape = null;
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            statusLabel.setText(String.format("(%d,%d)", e.getX(), e.getY()));
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentShape != null) {
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                statusLabel.setText(String.format("(%d,%d)", e.getX(), e.getY()));
                repaint();
            }
        }
    }
}