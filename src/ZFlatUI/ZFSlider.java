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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class ZFSlider extends JPanel {

    private Color trackColor = Color.decode("#bdc3c7");
    private Color thumbColor = Color.decode("#16a085");
    private Color valueColor = Color.decode("#1abc9c");
    private int value = 0;
    private int maxValue = 100;
    private int minValue = 0;
    private Dimension size = new Dimension(400, 50);
    private Dimension trackSize = new Dimension(size.width - 40, 10);
    private Point trackPos = new Point(10, size.height - trackSize.height - 10);
    private Dimension thumbSize = new Dimension(20, 20);
    private Dimension toolTipSize = new Dimension(30, 20);
    private Point thumbPos = new Point(trackPos.x, trackPos.y - (thumbSize.height - trackSize.height) / 2);
    private Point toolTipPos = new Point(thumbPos.x - (toolTipSize.width - thumbSize.width) / 2, thumbPos.y - thumbSize.height - 5);
    private int radius = 10;
    private boolean showToolTip = false;

    public ZFSlider() {
        super();
        setLayout(null);
        setSize(size);
        setPreferredSize(size);
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int value = thumbPos.x * (maxValue - minValue) / (10 + (trackSize.width - thumbSize.width));
                if(value < maxValue && value > minValue){
                    moveThumb(e.getX());
                }
                else if(value == maxValue){
                    if(e.getX() - thumbSize.width / 2 < thumbPos.x){
                        moveThumb(e.getX());
                    }
                }
                else if(value == minValue){
                    if(e.getX() - thumbSize.width / 2 > thumbPos.x){
                        moveThumb(e.getX());
                    }
                }

                setValue(value);
                repaint();
            }
        });
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (value >= maxValue) {
                    thumbPos.x = maxValue * (10 + (trackSize.width - thumbSize.width)) / (maxValue - minValue);
                    setValue(maxValue);
                } else if (value <= minValue) {
                    thumbPos.x = minValue * (trackSize.width - thumbSize.width) / (maxValue - minValue);
                    setValue(minValue);
                }
                showToolTip = false;
                repaint();
            }
        });
    }
    
    private void moveThumb(int mouseX){
        thumbPos.x = mouseX - thumbSize.width / 2;
        toolTipPos.x = thumbPos.x - (toolTipSize.width - thumbSize.width) / 2;
        toolTipPos.y = thumbPos.y - thumbSize.height - 5;
        showToolTip = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //draw track
        g2.setColor(trackColor);
        g2.fillRoundRect(
                trackPos.x,
                trackPos.y,
                trackSize.width,
                trackSize.height,
                radius,
                radius
        );

        //draw value
        g2.setColor(valueColor);
        //int fillValue = map(value, minValue, maxValue, 0, trackSize.width);
        g2.fillRoundRect(
                trackPos.x,
                trackPos.y,
                thumbPos.x + thumbSize.width / 2,
                trackSize.height,
                radius,
                radius
        );
        //draw thumb
        g2.setColor(thumbColor);
        g2.fillOval(
                thumbPos.x,
                thumbPos.y,
                thumbSize.width,
                thumbSize.height
        );
        if (showToolTip) {
            //draw tooltip
            g2.setColor(thumbColor);
            g2.fillRoundRect(
                    toolTipPos.x,
                    toolTipPos.y,
                    toolTipSize.width,
                    toolTipSize.height,
                    5,
                    5);
            g2.setColor(Color.white);
            int valueWidth = g2.getFontMetrics().stringWidth(value + "");
            FontMetrics metrics = g2.getFontMetrics(getFont());
            int valueAscent = metrics.getAscent();
            java.awt.geom.Rectangle2D rect = metrics.getStringBounds(value + "", g2);
            int valueHeight = (int) Math.round(rect.getHeight());
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            g2.drawString(
                    value + "",
                    toolTipPos.x + (toolTipSize.width - valueWidth) / 2,
                    (toolTipSize.height - valueHeight) / 2 + valueAscent
            );
        }
        g.dispose();
    }

    public Color getTrackColor() {
        return trackColor;
    }

    public void setTrackColor(Color trackColor) {
        this.trackColor = trackColor;
    }

    public Color getThumbColor() {
        return thumbColor;
    }

    public void setThumbColor(Color thumbColor) {
        this.thumbColor = thumbColor;
    }

    public Color getValueColor() {
        return valueColor;
    }

    public void setValueColor(Color valueColor) {
        this.valueColor = valueColor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        int oldVal = this.value;
        this.value = value;
        firePropertyChange("value", oldVal, this.value);
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
        this.trackSize.width = this.size.width;
        repaint();
    }

}
