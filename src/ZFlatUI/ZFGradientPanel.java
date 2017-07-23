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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class ZFGradientPanel extends JPanel{
    private Shape shape;
    private int radius = 5;
    private Color startColor = Color.white;
    private Color endColor = Color.white;
    private enum directions {
        TOP_DOWN, 
        BOTTOM_UP, 
        TOP_LEFT_TO_BOTTOM, 
        TOP_RIGHT_TO_BOTTOM,
        BOTTOM_LEFT_TO_TOP,
        BOTTOM_RIGHT_TO_TOP,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    };
    public directions direction = directions.TOP_DOWN;
    
    public ZFGradientPanel(){
        super();
    }
    
    public ZFGradientPanel(int borderRadius){
        super();
        radius = borderRadius;
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        int startX = getWidth() / 2;
        int startY = 0;
        int endX   = getWidth() / 2;
        int endY   = getHeight();
        switch(direction){
            case TOP_DOWN:
                startX = getWidth() / 2;
                startY = 0;
                endX   = getWidth() / 2;
                endY   = getHeight();
                break;
            case BOTTOM_UP:
                startX = getWidth() / 2;
                startY = getHeight();
                endX   = getWidth() / 2;
                endY   = 0;
                break;
            case TOP_LEFT_TO_BOTTOM:
                startX = 0;
                startY = 0;
                endX   = getWidth();
                endY   = getHeight();
                break;
            case TOP_RIGHT_TO_BOTTOM:
                startX = getWidth();
                startY = 0;
                endX   = 0;
                endY   = getHeight();
            case BOTTOM_LEFT_TO_TOP:
                startX = 0;
                startY = getHeight();
                endX   = getWidth();
                endY   = 0;
                break;
            case BOTTOM_RIGHT_TO_TOP:
                startX = getWidth();
                startY = getHeight();
                endX   = 0;
                endY   = 0;
                break;
            case LEFT_TO_RIGHT:
                startX = 0;
                startY = getHeight() / 2;
                endX   = getWidth();
                endY   = getHeight() / 2;
                break;
            case RIGHT_TO_LEFT:
                startX = getWidth();
                startY = getHeight() / 2;
                endX   = 0;
                endY   = getHeight() / 2;
                break;
        }
        GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor, true);
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g2);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
    
    public int getRadius(){
        return radius;
    }
    
    public void setRadius(int newRadius){
        radius = newRadius;
    }

    public Color getStartColor() {
        return startColor;
    }

    public void setStartColor(Color startColor) {
        this.startColor = startColor;
    }

    public Color getEndColor() {
        return endColor;
    }

    public void setEndColor(Color endColor) {
        this.endColor = endColor;
    }

    public directions getDirection() {
        return direction;
    }

    public void setDirection(directions direction) {
        this.direction = direction;
    }
}
