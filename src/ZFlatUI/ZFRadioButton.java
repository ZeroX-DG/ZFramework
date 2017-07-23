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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class ZFRadioButton extends JPanel{
    
    public String color = "#7f8c8d";
    public Dimension size = new Dimension(20, 20);
    private ZFCheckBoxBut but;
    private String value = "";
    public boolean checked = false;
 
    public ZFRadioButton(){
        super();
        setSize(size);
        setPreferredSize(size);
        setLayout(null);
        but = new ZFCheckBoxBut();
        this.add(but);
        but.setVisible(false);
        but.color = "#7f8c8d";
        but.setBackground(Color.decode(but.color));
        //setBackground(Color.decode(color));
        this.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                onCheck();
            } 
            public void mouseEntered(MouseEvent me){
                but.setVisible(true);
            }
            public void mouseExited(MouseEvent me){
                if(!checked)
                    but.setVisible(false);
            }
        });
        
        
    }
    private void onCheck(){
        if(this.checked){
            unCheck();
        }
        else{
            check();
        }
    }
    
    private void unCheck(){
        this.checked = false;
        this.but.setVisible(false);
        this.color = "#7f8c8d";
        but.color = "#7f8c8d";
        but.setBackground(Color.decode(but.color));
        setBackground(Color.decode(color));
    }
    
    private void check(){
        this.checked = true;
        this.but.setVisible(true);
        this.color = "#1abc9c";
        but.color = "#1abc9c";
        but.setBackground(Color.decode(but.color));
        setBackground(Color.decode(color));
    }
    @Override
    protected void paintBorder(Graphics g) {
        setOpaque(false);
        setSize(size);
        setPreferredSize(size);
        setLayout(null);
        but.setLocation((getWidth() - 2 - but.size.width) / 2, (getHeight() - 2 - but.size.height) / 2);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.decode(color));
        g2.drawRoundRect(1, 1, getWidth() - 4, getHeight() - 4, size.width, size.height);
        super.paintComponent(g2);
    }
}

class ZFCheckBoxBut extends JPanel{
    public String color = "#1abc9c";
    public Dimension size = new Dimension(10, 10);
    
    public ZFCheckBoxBut(){
        super();
        setSize(size);
        setPreferredSize(size);
        setLayout(null);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        setSize(size);
        setPreferredSize(size);
        setLayout(null);
        //setBackground(Color.decode(color));
        //setBorder(new LineBorder(Color.decode(color), 3));
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.decode(color));
        g2.fillRoundRect(1, 1, getWidth() - 1, getHeight() - 1, size.width, size.height);
        super.paintComponent(g2);
    }
}
