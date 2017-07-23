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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;


public class ZFAnimator {
    public static final int ANIMATE_TIME = 1000;

    // <editor-fold defaultstate="collapsed" desc="Slide effects"> 
    public static final int LEFT_SIDE = 1;
    public static final int RIGHT_SIDE = 2;
    public static final int TOP_SIDE = 3;
    public static final int BOTTOM_SIDE = 4;
    
    public static void slide(int side, JComponent control){
        switch(side){
            case LEFT_SIDE:
                slideFromLeft(control);
                break;
            case RIGHT_SIDE:
                slideFromRight(control);
                break;
            case TOP_SIDE:
                slideFromTop(control);
            case BOTTOM_SIDE:
                slideFromBottom(control);
        }
    }
    
    private static void slideFromLeft(JComponent control){
        int delay = ANIMATE_TIME / control.getWidth();
        boolean isGoingOut = control.getX() < 0;
        Timer animate = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isGoingOut){
                    if(control.getX() < 0){
                        control.setLocation(control.getX() + 5, control.getY());
                    }
                    else{
                        ((Timer) e.getSource()).stop();
                    }
                }
                else{
                    if(control.getX() > -control.getWidth()){
                        control.setLocation(control.getX() - 5, control.getY());
                    }
                    else{
                        ((Timer) e.getSource()).stop();
                    }
                }
            }
        });
        animate.start();
    }
    
    private static void slideFromRight(JComponent control){
        int delay = ANIMATE_TIME / control.getWidth();
        int frameWidth = control.getParent().getWidth();
        boolean isGoingOut = control.getX() >= frameWidth;
        Timer animate = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isGoingOut){
                    if(control.getX() > frameWidth - control.getWidth()){
                        control.setLocation(control.getX() - 5, control.getY());
                    }
                    else{
                        ((Timer) e.getSource()).stop();
                    }
                }
                else{
                    if(control.getX() < frameWidth){
                        control.setLocation(control.getX() + 5, control.getY());
                    }
                    else{
                        ((Timer) e.getSource()).stop();
                    }
                }
            }
        });
        animate.start();
    }
    
    private static void slideFromTop(JComponent control){
        int delay = ANIMATE_TIME / control.getHeight();
        //int frameWidth = control.getParent().getWidth();
        boolean isGoingOut = control.getY() < 0;
        Timer animate = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isGoingOut){
                    if(control.getY() < control.getHeight()){
                        control.setLocation(control.getX(), control.getY() + 5);
                    }
                    else{
                        ((Timer) e.getSource()).stop();
                    }
                }
                else{
                    if(control.getX() > -control.getHeight()){
                        control.setLocation(control.getX(), control.getY() - 5);
                    }
                    else{
                        ((Timer) e.getSource()).stop();
                    }
                }
            }
        });
        animate.start();
    }
    
    private static void slideFromBottom(JComponent control){
        int delay = ANIMATE_TIME / control.getWidth();
        int frameHeight = control.getParent().getHeight();
        boolean isGoingOut = control.getY() >= frameHeight;
        Timer animate = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isGoingOut){
                    if(control.getY() > frameHeight - control.getHeight()){
                        control.setLocation(control.getX(), control.getY() - 5);
                    }
                    else{
                        ((Timer) e.getSource()).stop();
                    }
                }
                else{
                    if(control.getX() < frameHeight){
                        control.setLocation(control.getY(), control.getY() + 5);
                    }
                    else{
                        ((Timer) e.getSource()).stop();
                    }
                }
            }
        });
        animate.start();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Resize effects">
    public static void toSize(JComponent control, int width, int height){
        Dimension originalSize = control.getSize();
        int widthDifference = originalSize.width > width ? originalSize.width - width : width - originalSize.width;
        int heightDifference = originalSize.height > height ? originalSize.height - height : height - originalSize.height;
        int delayW = ANIMATE_TIME / widthDifference;
        int delayH = ANIMATE_TIME / heightDifference;
        Timer animateW = new Timer(delayW, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(control.getSize().width < width){
                    control.setSize(control.getWidth() + 5, control.getHeight());
                }
                else{
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        Timer animateH = new Timer(delayH, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(control.getSize().height < height){
                    control.setSize(control.getWidth(), control.getHeight() + 5);
                }
                else{
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        animateW.start();
        animateH.start();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Color transition effects">
    public static void toBackgroundColor(JComponent componentToApply, Color destinationColor, int duration){        
        int destinateRed = destinationColor.getRed();
        int destinateGreen = destinationColor.getGreen();
        int destinateBlue = destinationColor.getBlue();
        Timer animate = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color componentBackground = componentToApply.getBackground();
                int currentRed = componentBackground.getRed();
                int currentGreen = componentBackground.getGreen();
                int currentBlue = componentBackground.getBlue();
                
                int tempRed = currentRed;
                int tempGreen = currentGreen;
                int tempBlue = currentBlue;
                
                if(currentRed > destinateRed){
                    currentRed = currentRed - duration < 0 ? 0 : currentRed - duration;
                }
                else if(currentRed < destinateRed){
                    currentRed = currentRed + duration > 255 ? 255 : currentRed + duration;
                }            
                if(currentBlue > destinateBlue){
                    currentBlue = currentBlue - duration < 0 ? 0 : currentBlue - duration;
                }
                else if(currentBlue < destinateBlue){
                    currentBlue = currentBlue + duration > 255 ? 255 : currentBlue + duration;
                }
                if(currentGreen > destinateGreen){
                    currentGreen = currentGreen - duration < 0 ? 0 : currentGreen - duration;
                }
                else if(currentGreen < destinateGreen){
                    currentGreen = currentGreen + duration > 255 ? 255 : currentGreen + duration;
                }
                if(
                        ((tempRed > destinateRed && currentRed <= destinateRed) || (tempRed < destinateRed && currentRed >= destinateRed)) && 
                        ((tempBlue > destinateBlue && currentBlue <= destinateBlue) || (tempBlue < destinateBlue && currentBlue >= destinateBlue)) &&
                        ((tempGreen > destinateGreen && currentGreen <= destinateGreen) || (tempGreen < destinateGreen && currentGreen >= destinateGreen))){
                    ((Timer)e.getSource()).stop();
                }
                componentToApply.setBackground(new Color(currentRed, currentGreen, currentBlue));
            }
        });
        
        animate.start();
    }
    public static void toBackgroundColor(JComponent componentToApply, Color destinationColor){
        toBackgroundColor(componentToApply, destinationColor, 10);
    }
    
    public static void toForegroundColor(JComponent componentToApply, Color destinationColor, int duration){        
        int destinateRed = destinationColor.getRed();
        int destinateGreen = destinationColor.getGreen();
        int destinateBlue = destinationColor.getBlue();
        Timer animate = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color componentForeground = componentToApply.getForeground();
                int currentRed = componentForeground.getRed();
                int currentGreen = componentForeground.getGreen();
                int currentBlue = componentForeground.getBlue();
                
                int tempRed = currentRed;
                int tempGreen = currentGreen;
                int tempBlue = currentBlue;
                
                if(currentRed > destinateRed){
                    currentRed = currentRed - duration < 0 ? 0 : currentRed - duration;
                }
                else if(currentRed < destinateRed){
                    currentRed = currentRed + duration > 255 ? 255 : currentRed + duration;
                }            
                if(currentBlue > destinateBlue){
                    currentBlue = currentBlue - duration < 0 ? 0 : currentBlue - duration;
                }
                else if(currentBlue < destinateBlue){
                    currentBlue = currentBlue + duration > 255 ? 255 : currentBlue + duration;
                }
                if(currentGreen > destinateGreen){
                    currentGreen = currentGreen - duration < 0 ? 0 : currentGreen - duration;
                }
                else if(currentGreen < destinateGreen){
                    currentGreen = currentGreen + duration > 255 ? 255 : currentGreen + duration;
                }
                if(
                        ((tempRed > destinateRed && currentRed <= destinateRed) || (tempRed < destinateRed && currentRed >= destinateRed)) && 
                        ((tempBlue > destinateBlue && currentBlue <= destinateBlue) || (tempBlue < destinateBlue && currentBlue >= destinateBlue)) &&
                        ((tempGreen > destinateGreen && currentGreen <= destinateGreen) || (tempGreen < destinateGreen && currentGreen >= destinateGreen))){
                    ((Timer)e.getSource()).stop();
                }
                componentToApply.setForeground(new Color(currentRed, currentGreen, currentBlue));
            }
        });
        
        animate.start();
    }
    public static void toForegroundColor(JComponent componentToApply, Color destinationColor){
        toForegroundColor(componentToApply, destinationColor, 10);
    }
    // </editor-fold>
}

