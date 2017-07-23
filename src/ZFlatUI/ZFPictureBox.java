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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ZFPictureBox extends JPanel{
    private Image image;
    private String ImagePath;
    private boolean circle = false;
    
    public ZFPictureBox() {
        super();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
        RenderingHints.VALUE_STROKE_PURE);
        if(image != null){
            if(circle)
                g.setClip(new Ellipse2D.Float(0, 0, getWidth(), getHeight()));
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
        super.paintComponent(g2);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image Image) {
        this.image = Image;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
        if(ImagePath.indexOf("http://") != -1 
                || ImagePath.indexOf("https://") != -1){
            try{
                URL link = new URL(ImagePath);
                setImage(ImageIO.read(link));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            try{
                File link = new File(ImagePath);
                setImage(ImageIO.read(link));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean isCircle() {
        return circle;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
    }
    
    
}
