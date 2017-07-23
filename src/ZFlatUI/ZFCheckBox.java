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
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

public class ZFCheckBox extends JPanel{
    private Dimension size = new Dimension(20, 20);
    private Color UncheckBackground = Color.decode("#95a5a6");
    private Color CheckedBackground = Color.decode("#1abc9c");
    private checkButton checkMark = new checkButton();
    private boolean checked = false;
    public ZFCheckBox() {
        super();
        setSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        this.add(checkMark);
        if(this.checked){
            checkMark.setVisible(true);
        }
        else{
            checkMark.setVisible(false);
        }
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) { 
                onCheck();
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
        this.setBackground(UncheckBackground);
        this.checkMark.setVisible(false);
    }
    
    private void check(){
        this.checked = true;
        this.setBackground(CheckedBackground);
        this.checkMark.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        setOpaque(false);
        setSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        if(this.checked){
            g2.setColor(getCheckedBackground());
            this.checkMark.setVisible(true);
        }
        else{
            g2.setColor(getUncheckBackground());
            this.checkMark.setVisible(false);
        }
        
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
        
    }

    public Color getUncheckBackground() {
        return UncheckBackground;
    }

    public void setUncheckBackground(Color UncheckBackground) {
        this.UncheckBackground = UncheckBackground;
    }

    public Color getCheckedBackground() {
        return CheckedBackground;
    }

    public void setCheckedBackground(Color CheckedBackground) {
        this.CheckedBackground = CheckedBackground;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
}

class checkButton extends JPanel{

    private Dimension size = new Dimension(13, 13);
    public checkButton() {
        super();
        setSize(size);
        setLocation(0, -6);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    @Override
    protected void paintComponent(Graphics g){
        setOpaque(false);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.white);
        GeneralPath path = new GeneralPath();
        path.moveTo(1, getHeight() - getHeight() / 2);
        path.lineTo(getWidth() / 2 - 1, getHeight() - 4);
        path.lineTo(getWidth() - 3, 2);
        g2.draw(path);
    }
}