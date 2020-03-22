package com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Polygon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;

    Timer timer;

    // for movement animation
    private double tx = 0;
    private double ty = 200;
    private int radius = 200;
    private double angle = 0;

    public Animation() {
        timer = new Timer(15, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering params.
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        // Set background color.
        g2d.setBackground(new Color(128, 128, 255));
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        // Set (0;0) to the center to draw main Frame.
        g2d.translate(maxWidth / 2, maxHeight / 2);

        BasicStroke bs2 = new BasicStroke(16, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs2);
        g2d.drawRect(-(radius + 250), -(radius + 280), (radius + 250) * 2, (radius + 250) * 2);

        // Reset center to default value for the main animation.
        g2d.translate(tx, ty);

        //draw rectangle
        GradientPaint gp = new GradientPaint(
                55, 10,
                new Color(232, 173, 66),
                35, 20,
                new Color(255, 0, 37),
                true
        );

        g2d.setPaint(gp);
        Polygon rect = new Polygon(new int[] {-150, 150, 150, -150}, new int[] {-35, -35, 35, 35},4);
        g2d.rotate(0.5 * angle, rect.getBounds2D().getCenterX(), rect.getBounds2D().getCenterY());
        g2d.fillPolygon(rect);

        //draw triangle
        g2d.setColor(new Color(255,128,64));
        Polygon p = new Polygon(new int[] {150, 210, 150}, new int[] {-35, 0, 35}, 3);
        g2d.fillPolygon(p);

        //draw top wing
        Polygon wing1 = new Polygon(new int[] {-150, -90, -50, -110}, new int[] {-85, -85, -35, -35}, 4);
        g2d.fillPolygon(wing1);

        //draw bottom wing
        Polygon wing2 = new Polygon(new int[] {-150, -110, -50, -90}, new int[] {85, 35, 35, 85}, 4);
        g2d.fillPolygon(wing2);

        //draw central wing
        BasicStroke bs1 = new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        g2d.drawLine(-150, 0, -50, 0);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 1250);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Animation());

        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right;
        maxHeight = size.height - insets.top - insets.bottom;
    }

    public void actionPerformed(ActionEvent e) {
        // scaling

        // movement
        angle -= 0.05;
        tx += 10 * Math.cos(angle);
        ty += 10 * Math.sin(angle);

        repaint();
    }
}


