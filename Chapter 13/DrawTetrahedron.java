import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class DrawTetrahedron extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Define 3D wireframe points projected onto a 2D plane
        double x1 = 200, y1 = 80;   // Top vertex
        double x2 = 100, y2 = 300;  // Bottom-left vertex
        double x3 = 300, y3 = 300;  // Bottom-right vertex
        double x4 = 210, y4 = 220;  // Interior center vertex

        Path2D.Double tetrahedron = new Path2D.Double();

        // Outer triangle face
        tetrahedron.moveTo(x1, y1);
        tetrahedron.lineTo(x2, y2);
        tetrahedron.lineTo(x3, y3);
        tetrahedron.closePath();

        // Inner lines connecting to center vertex
        tetrahedron.moveTo(x1, y1);
        tetrahedron.lineTo(x4, y4);

        tetrahedron.moveTo(x2, y2);
        tetrahedron.lineTo(x4, y4);

        tetrahedron.moveTo(x3, y3);
        tetrahedron.lineTo(x4, y4);

        g2d.draw(tetrahedron);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("13.15 Tetrahedron");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DrawTetrahedron());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}