/*
 * The MIT License
 *
 * Copyright 2017 Nguyen Viet Hung.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ZFlatUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;


public class ZFCircleButton extends JButton {
    private Shape shape;
    private final String hover = "#2980b9";
    private final String click = "#2980b9";
    private Color hoverColor = Color.decode(hover);
    private Color clickColor = Color.decode(click);

    public ZFCircleButton() {
        super();
        setSize(50, 50);
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.decode("#3498db"));
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        setFocusPainted(false);
        setBackground(getBackground());
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isPressed()) {
            g2.setColor(getClickColor().darker());
        } else if (getModel().isArmed()) {
            g2.setColor(getClickColor());
        } else if (getModel().isRollover()) {
            g2.setColor(getHoverColor());
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            g2.setColor(getBackground());
        }
        g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g2);
    }

    public boolean contains(int x, int y) {
        if (shape == null
                || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0,
                    getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Color getClickColor() {
        return clickColor;
    }

    public void setClickColor(Color clickColor) {
        this.clickColor = clickColor;
    }

}
