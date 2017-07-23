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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class ZFTextField extends JTextField {

    private Shape shape;
    private int radius = 5;
    private String themeColor = "#2c3e50";
    private Dimension defaultSize = new Dimension(300, 35);
    private boolean isMoney = false;
    private String value = "";

    //private String thumbColor = "#4a6887";
    private enum Theme {
        LIGHT,
        DARK
    }
    private Theme theme = Theme.DARK;

    public ZFTextField() {
        super();
        setForeground(Color.white);
        setSize(defaultSize);
        setPreferredSize(defaultSize);
        setFont(new Font("Segoe UI", Font.PLAIN, 18));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                if(isMoney){
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                if(isMoney){
                    setText(insertCommas(getText().replace(",", "")));
                    value = getText().replace(",", "");
                }
            }
        });
    }
    
    private String insertCommas(String str)
    {
        if(str.length() < 4){
            return str;
        }
        return insertCommas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        //setFocusPainted(false);
        if (theme == Theme.DARK) {
            themeColor = "#2c3e50";
        } else {
            themeColor = "#ecf0f1";
        }
        setBackground(Color.decode(themeColor));
        setForeground(getForeground());
        setCaretColor(getForeground());
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.decode(themeColor));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //g.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g2);
        
    }

    //@Override
    //protected void paintBorder(Graphics g) {
    //setOpaque(false);
    //g.setColor(getBackground());
    //g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    //super.paintBorder(g);
    //}
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public boolean isIsMoney() {
        return isMoney;
    }

    public void setIsMoney(boolean isMoney) {
        this.isMoney = isMoney;
    }

    public String getValue() {
        return value;
    }
    
    
}
