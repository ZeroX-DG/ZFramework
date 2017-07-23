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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ZFSwitch extends JPanel{
    
    public String background = "#bdc3c7";
    public boolean checked = false;
    public int speed = 5;
    JFCheckButton but;
    private boolean firstPaint = true;
    
    public ZFSwitch(){        
        initContent();
        this.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                onCheck();
            }
            public void mouseEntered(MouseEvent me){
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent me){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
    }
    
    public void onCheck(){
        if(!this.checked)
            on();
        else
            off();
    }
    
    public void on(){
        this.checked = true;
        int maxX = this.getWidth() - 6 - but.getWidth();
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int currentX = but.getX();
                int newX = currentX;
                if(currentX >= maxX){
                    ((Timer)e.getSource()).stop();
                }
               
                newX = currentX + 2;
                currentX = newX;
                but.setLocation(newX, but.getY());
                
            }
        };
        new Timer(this.speed, action).start();
        this.background = "#2c3e50";
        setBackground(Color.decode(background));
        this.but.background = "#1ABC9C";
        this.but.setBackground(Color.decode(this.but.background));
    }
    
    public void off(){
        this.checked = false;
        int minX = 6;
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int currentX = but.getX();
                int newX = currentX;
                if(currentX <= minX){
                    ((Timer)e.getSource()).stop();
                }
               
                newX = currentX - 2;
                currentX = newX;
                but.setLocation(newX, but.getY());
                
            }
        };
        new Timer(this.speed, action).start();
        this.background = "#bdc3c7";
        setBackground(Color.decode(background));
        this.but.background = "#7f8c8d";
        this.but.setBackground(Color.decode(this.but.background));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if(checked && firstPaint){
            this.but.setLocation(this.getWidth() - 6 - but.getWidth(), this.but.getY());
            this.background = "#2c3e50";
            setBackground(Color.decode(background));
            this.but.background = "#1ABC9C";
            this.but.setBackground(Color.decode(this.but.background));
            firstPaint = false;
        }
        setOpaque(false);
        setSize(55, 26);
        setPreferredSize(new Dimension(55, 26));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.decode(background));
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        super.paintComponent(g2);
    }
    
    
    public void initContent(){
        setSize(55, 26);
        setPreferredSize(new Dimension(55, 26));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setBackground(Color.decode(background));
        setLayout(null);
        but = new JFCheckButton();
        this.add(but);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
}

class JFCheckButton extends JPanel{
    
    public String background = "#7f8c8d";
    
    public JFCheckButton(){
        setSize(20, 20);
        setLocation(3, 3);
        setPreferredSize(new Dimension(20, 20));
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.decode(background));
        Ellipse2D circle = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        g2.fill(circle);
        //g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        super.paintComponent(g2);
    }
    
}