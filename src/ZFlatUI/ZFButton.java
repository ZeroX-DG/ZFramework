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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class ZFButton extends JButton {

    protected Shape shape;
    public int radius = 10;
    protected String background = "#95a5a6";
    protected String hover = "#CACFD2";
    protected String click = "#7f8c8d";
    protected Color hoverColor = Color.decode(hover);
    protected Color clickColor = Color.decode(click);
    protected Color borderColor = Color.black;
    public enum Role{
        DEFAULT,
        SUCCESS,
        DANGER,
        INFO,
        INVERSE,
        BORDER
    };
    protected Role role = Role.DEFAULT;

    public ZFButton() {
        super();
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        switch(role){
            case DEFAULT:
                background = "#95a5a6";
                hover      = "#CACFD2";
                click      = "#7f8c8d";
                break;
            case SUCCESS:
                background = "#2ecc71";
                hover      = "#58D68D";
                click      = "#27ae60";
                break;
            case DANGER:
                background = "#e74c3c";
                hover      = "#EC7063";
                click      = "#c0392b";
                break;
            case INFO:
                background = "#3498db";
                hover      = "#5DADE2";
                click      = "#2980b9";
                break;
            case INVERSE:
                background = "#34495e";
                hover      = "#415B76";
                click      = "#2c3e50";
                break;
        }
        hoverColor = Color.decode(hover);
        clickColor = Color.decode(click);
        setOpaque(false);
        setFocusPainted(false);
        if(role != Role.BORDER){
            setBackground(Color.decode(background));
        }
        else{
            setBackground(new Color(0 ,0 ,0 ,0));
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isPressed() && role != Role.BORDER) {
            g2.setColor(getClickColor());
        } else if (getModel().isRollover() && role != Role.BORDER) {
            g2.setColor(getHoverColor());
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            g2.setColor(getBackground());
        }
        //g.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g2);
    }

    @Override
    protected void paintBorder(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if(role != Role.BORDER){
            g2.setColor(getBackground());
        }
        else{
            g2.setStroke(new BasicStroke(3));
            g2.setColor(borderColor);
            
        }
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
    
}
