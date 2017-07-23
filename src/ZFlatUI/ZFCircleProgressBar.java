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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ZFCircleProgressBar extends JPanel {

    private int value = 20;
    private int thickness = 10;
    private String background = "#3498db";
    private Color centerColor = Color.white;
    private Color borderColor = Color.gray;
    private boolean hasBorder = true;
    private boolean showPercent = true;
    private boolean smoothTransition = true;
    private int transtionSpeed = 20;

    public ZFCircleProgressBar() {
        super();
        setBackground(Color.decode(background));
        setFont(new Font("Segoe UI", Font.BOLD, 18));
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.decode("#CCCCCC"));
        g2.fillOval(0, 0, getWidth(), getHeight());
                
        g2.setColor(getBackground());
        g2.fill(new Arc2D.Double(0, 0, getWidth(), getHeight(), 90, -value * 3.6, Arc2D.PIE));
        
        g2.setColor(centerColor);
        g2.fill(new Ellipse2D.Double(thickness, thickness, getWidth() - thickness * 2, getHeight() - thickness * 2));
        if(hasBorder){
            g2.setColor(borderColor);
            g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
        }
        if(showPercent){
            int progressStringWidth = g.getFontMetrics().stringWidth(value + "%");
            g2.setColor(getForeground());
            g2.setFont(getFont());
            FontMetrics metrics = g2.getFontMetrics(getFont());
            int progressStringAscent = metrics.getAscent();
            java.awt.geom.Rectangle2D rect = metrics.getStringBounds(value + "%", g2);
            int progressStringHeight = (int)Math.round(rect.getHeight());
            //System.out.println(getWidth() + ":" + getHeight());
            g2.drawString(value + "%", (getWidth() - progressStringWidth) / 2, (getHeight() - progressStringHeight) / 2 + progressStringAscent);
        }
        super.paintComponent(g2);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int _progress) {
        if(_progress > 100){
            this.value = 100;
            return;
        }
        else if(_progress < 0){
            this.value = 0;
            return;
        }
        if(smoothTransition){
            Timer animate = new Timer(transtionSpeed, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(getValue() < _progress){
                        value++;
                        repaint();
                    }
                    else{
                        ((Timer)e.getSource()).stop();
                    }
                }
            });
            animate.start();
        }
        else{
            value = _progress;
            repaint();
        }
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public Color getCenterColor() {
        return centerColor;
    }

    public void setCenterColor(Color centerColor) {
        this.centerColor = centerColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public boolean isHasBorder() {
        return hasBorder;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
    }

    public boolean isShowPercent() {
        return showPercent;
    }

    public void setShowPercent(boolean showPercent) {
        this.showPercent = showPercent;
    }

    public boolean isSmoothTransition() {
        return smoothTransition;
    }

    public void setSmoothTransition(boolean smoothTransition) {
        this.smoothTransition = smoothTransition;
    }

    public int getTranstionSpeed() {
        return transtionSpeed;
    }

    public void setTranstionSpeed(int transtionSpeed) {
        this.transtionSpeed = transtionSpeed;
    }
    
}
