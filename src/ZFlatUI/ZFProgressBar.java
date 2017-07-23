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
import javax.swing.JPanel;
import javax.swing.Timer;

public class ZFProgressBar extends JPanel{
    private int value = 20;
    private Color barColor = Color.decode("#2ecc71");
    private int radius = 5;
    private boolean showPercent = false;
    private Color percentColor = Color.white;
    private boolean smoothTransition = true;
    private int transtionSpeed = 20;

    public ZFProgressBar() {
        super();
        setSize(400, 30);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.setColor(barColor);
        int width = value * getWidth() / 100;
        g2.fillRoundRect(0, 0, width, getHeight() - 1, radius, radius);
        int progressStringWidth = g.getFontMetrics().stringWidth(value + "%");
        FontMetrics metrics = g2.getFontMetrics(getFont());
        int progressStringHeight = metrics.getHeight();
        if(showPercent){
            g2.setColor(percentColor);
            g2.setFont(getFont());
            g2.drawString(value + "%", width - progressStringWidth - 10, (getHeight()) / 2 + progressStringHeight / 3);
        }
        super.paintComponent(g2);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int _value) {
        //this.value = _value;
        if(_value > 100){
            this.value = 100;
            return;
        }
        else if(_value < 0){
            this.value = 0;
            return;
        }
        if(smoothTransition){
            Timer animate = new Timer(transtionSpeed, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(getValue()< _value){
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
            value = _value;
            repaint();
        }
    }

    public Color getBarColor() {
        return barColor;
    }

    public void setBarColor(Color barColor) {
        this.barColor = barColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isShowPercent() {
        return showPercent;
    }

    public void setShowPercent(boolean showPercent) {
        this.showPercent = showPercent;
    }

    public Color getPercentColor() {
        return percentColor;
    }

    public void setPercentColor(Color percentColor) {
        this.percentColor = percentColor;
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
