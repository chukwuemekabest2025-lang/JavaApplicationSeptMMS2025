import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private final ArrayList<MyShape> shapes = new ArrayList<>();
    private int shapeType = 0; // 0: Line, 1: Rectangle, 2: Oval
    private boolean filled = false;
    private Paint currentPaint = Color.BLACK;
    private Stroke currentStroke = new BasicStroke();
    private MyShape currentShape = null;

    public DrawPanel() {
        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }

    public void setShapeType(int type) { this.shapeType = type; }
    public void setFilled(boolean filled) { this.filled = filled; }
    public void setCurrentPaint(Paint paint) { this.currentPaint = paint; }
    public void setCurrentStroke(Stroke stroke) { this.currentStroke = stroke; }

    public void clearLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

    public void clearDrawing() {
        shapes.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (MyShape s : shapes) {
            s.draw(g2d);
        }
        if (currentShape != null) {
            currentShape.draw(g2d);
        }
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            switch (shapeType) {
                case 0 -> currentShape = new MyLine(x, y, x, y, currentPaint, currentStroke);
                case 1 -> currentShape = new MyRectangle(x, y, x, y, currentPaint, currentStroke, filled);
                case 2 -> currentShape = new MyOval(x, y, x, y, currentPaint, currentStroke, filled);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentShape != null) {
                int x1 = currentShape.getX1();
                int y1 = currentShape.getY1();
                int x2 = e.getX();
                int y2 = e.getY();

                switch (shapeType) {
                    case 0 -> currentShape = new MyLine(x1, y1, x2, y2, currentPaint, currentStroke);
                    case 1 -> currentShape = new MyRectangle(x1, y1, x2, y2, currentPaint, currentStroke, filled);
                    case 2 -> currentShape = new MyOval(x1, y1, x2, y2, currentPaint, currentStroke, filled);
                }
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentShape != null) {
                shapes.add(currentShape);
                currentShape = null;
                repaint();
            }
        }
    }
}