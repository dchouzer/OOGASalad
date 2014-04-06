package game_authoring_environment;


/*
 * Copyright (c) 2007, Romain Guy
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   * Neither the name of the TimingFramework project nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 *
 * @author Romain Guy
 */
public class GlassPaneDragAndDrop extends javax.swing.JFrame {
    private PictureGlassPane glassPane = new PictureGlassPane();
    
    public GlassPaneDragAndDrop() {
        setContentPane(new JPanel(new BorderLayout()) {
            private BufferedImage image = null;
            
            @Override
            public boolean isOpaque() {
                return imageList.getModel().getSize() > 0;
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                Rectangle clip = g.getClipBounds();
                
                g.setColor(Color.WHITE);
                g.fillRect(clip.x, clip.y, clip.width, clip.height);
                
                if (image == null) {
                    try {
                        image = ImageIO.read(getClass().getResource("resources/actorsIcon.png"));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                
                g.drawImage(image, (getWidth() - image.getWidth()) / 2,
                        (getHeight() - image.getHeight()) / 2, null);
            }
        });
        
        initComponents();
        
        imageList.setOpaque(false);
        listScroller.setOpaque(false);
        listScroller.getViewport().setOpaque(false);
        
        setGlassPane(glassPane);
        imageList.setModel(new DefaultListModel());
        imageList.setTransferHandler(new FileDropHandler());
        imageList.addPropertyChangeListener("dropLocation", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                glassPane.moveIt(MouseInfo.getPointerInfo().getLocation());
            }
        });
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        javax.swing.JLabel jLabel1;
        javax.swing.JLabel jLabel2;
        javax.swing.JLabel jLabel3;
        javax.swing.JPanel jPanel1;
        javax.swing.JSeparator jSeparator1;

        listScroller = new javax.swing.JScrollPane();
        imageList = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glass Drag and Drop");
        imageList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        imageList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        imageList.setVisibleRowCount(-1);
        listScroller.setViewportView(imageList);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/actorsIcon.png")));

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, jLabel2.getFont().getSize()+2));
        jLabel2.setText("Glass Drag and Drop");

        jLabel3.setText("Drag and drop image files (PNG or JPEG) onto the application.");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jLabel3))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3))
                    .add(jLabel1))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(listScroller, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(listScroller, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents
    
    class FileDropHandler extends TransferHandler {
        private boolean imported = false;
        private List<File> fileList;
        
        public boolean canImport(TransferSupport support) {
            if (!support.isDrop()) {
                return false;
            }
            
            if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                return false;
            }            

            boolean copySupported = (COPY & support.getSourceDropActions()) == COPY;

            if (copySupported) {
                support.setDropAction(COPY);
                
                if (!imported) {
                    Transferable t = support.getTransferable();

                    try {
                        Object data = t.getTransferData(DataFlavor.javaFileListFlavor);
                        this.fileList = (List<File>) data;
                        BufferedImage image = createImage(fileList);
                        
                        if (image != null) {
                            Point p = MouseInfo.getPointerInfo().getLocation();

                            glassPane.showIt(image, p);
                            imported = true;
                        }
                    } catch (UnsupportedFlavorException e) {
                        return false;
                    } catch (IOException e) {
                        return false;
                    }
                }

                return true;
            }
            
            return false;
        }
                
        private BufferedImage createImage(List<File> files) {
            List<File> imageFiles = new ArrayList<File>(3);
            for (File file : files) {
                if (file.getName().endsWith(".png") ||
                    file.getName().endsWith(".jpg")) {
                    imageFiles.add(file);
                    if (imageFiles.size() == 3) {
                        break;
                    }
                }
            }
            
            if (imageFiles.size() == 0) {
                return null;
            }
            
            int width = 60 + (imageFiles.size() - 1) * 10;
            int height = 45 + (imageFiles.size() - 1) * 10;
            
            BufferedImage image =
                    GraphicsUtilities.createCompatibleTranslucentImage(width, height);
            Graphics2D g2 = image.createGraphics();
            
            for (int i = 0; i < imageFiles.size(); i++) {
                File imageFile = imageFiles.get(i);
                BufferedImage externalImage = null;
                try {
                    externalImage = GraphicsUtilities.loadCompatibleImage(imageFile.toURI().toURL());
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                externalImage = GraphicsUtilities.createThumbnail(externalImage, 60, 45);
                g2.drawImage(externalImage, i * 10, i * 10, null);
            }
            
            g2.dispose();
            return image;
        }
        
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }
            
            if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                return false;
            }
            
            for (int i = 0; i < fileList.size(); i++) {
                File imageFile = fileList.get(i);
                BufferedImage externalImage = null;
                try {
                    externalImage = GraphicsUtilities.loadCompatibleImage(imageFile.toURI().toURL());
                    externalImage = GraphicsUtilities.createThumbnail(externalImage, 120);
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ((DefaultListModel) imageList.getModel()).add(0, new ImageIcon(externalImage));
            }

            glassPane.hideIt();
            imported = false;

            return true;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GlassPaneDragAndDrop().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList imageList;
    private javax.swing.JScrollPane listScroller;
    // End of variables declaration//GEN-END:variables
    
}
/*
 * $Id: GraphicsUtilities.java,v 1.11 2007/04/29 22:33:33 gfx Exp $
 *
 * Dual-licensed under LGPL (Sun and Romain Guy) and BSD (Romain Guy).
 *
 * Copyright 2005 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * Copyright (c) 2006 Romain Guy <romain.guy@mac.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */



/**
 * <p><code>GraphicsUtilities</code> contains a set of tools to perform
 * common graphics operations easily. These operations are divided into
 * several themes, listed below.</p>
 *
 * <h2>Compatible Images</h2>
 *
 * <p>Compatible images can, and should, be used to increase drawing
 * performance. This class provides a number of methods to load compatible
 * images directly from files or to convert existing images to compatibles
 * images.</p>
 *
 * <h2>Creating Thumbnails</h2>
 *
 * <p>This class provides a number of methods to easily scale down images.
 * Some of these methods offer a trade-off between speed and result quality and
 * shouuld be used all the time. They also offer the advantage of producing
 * compatible images, thus automatically resulting into better runtime
 * performance.</p>
 *
 * <p>All these methodes are both faster than
 * {@link java.awt.Image#getScaledInstance(int, int, int)} and produce
 * better-looking results than the various <code>drawImage()</code> methods
 * in {@link java.awt.Graphics}, which can be used for image scaling.</p>
 * <h2>Image Manipulation</h2>
 *
 * <p>This class provides two methods to get and set pixels in a buffered image.
 * These methods try to avoid unmanaging the image in order to keep good
 * performance.</p>
 *
 * @author Romain Guy <romain.guy@mac.com>
 * @author rbair
 */
class GraphicsUtilities {
    private GraphicsUtilities() {
    }

    // Returns the graphics configuration for the primary screen
    private static GraphicsConfiguration getGraphicsConfiguration() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().
                    getDefaultScreenDevice().getDefaultConfiguration();
    }

    private static boolean isHeadless() {
        return GraphicsEnvironment.isHeadless();
    }

    /**
     * <p>Returns a new <code>BufferedImage</code> using the same color model
     * as the image passed as a parameter. The returned image is only compatible
     * with the image passed as a parameter. This does not mean the returned
     * image is compatible with the hardware.</p>
     *
     * @param image the reference image from which the color model of the new
     *   image is obtained
     * @return a new <code>BufferedImage</code>, compatible with the color model
     *   of <code>image</code>
     */
    public static BufferedImage createColorModelCompatibleImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        return new BufferedImage(cm,
            cm.createCompatibleWritableRaster(image.getWidth(),
                                              image.getHeight()),
            cm.isAlphaPremultiplied(), null);
    }

    /**
     * <p>Returns a new compatible image with the same width, height and
     * transparency as the image specified as a parameter. That is, the
     * returned BufferedImage will be compatible with the graphics hardware.
     * If this method is called in a headless environment, then
     * the returned BufferedImage will be compatible with the source
     * image.</p>
     *
     * @see java.awt.Transparency
     * @see #createCompatibleImage(int, int)
     * @see #createCompatibleImage(java.awt.image.BufferedImage, int, int)
     * @see #createCompatibleTranslucentImage(int, int)
     * @see #loadCompatibleImage(java.net.URL)
     * @see #toCompatibleImage(java.awt.image.BufferedImage)
     * @param image the reference image from which the dimension and the
     *   transparency of the new image are obtained
     * @return a new compatible <code>BufferedImage</code> with the same
     *   dimension and transparency as <code>image</code>
     */
    public static BufferedImage createCompatibleImage(BufferedImage image) {
        return createCompatibleImage(image, image.getWidth(), image.getHeight());
    }

    /**
     * <p>Returns a new compatible image of the specified width and height, and
     * the same transparency setting as the image specified as a parameter.
     * That is, the returned <code>BufferedImage</code> is compatible with
     * the graphics hardware. If the method is called in a headless
     * environment, then the returned BufferedImage will be compatible with
     * the source image.</p>
     *
     * @see java.awt.Transparency
     * @see #createCompatibleImage(java.awt.image.BufferedImage)
     * @see #createCompatibleImage(int, int)
     * @see #createCompatibleTranslucentImage(int, int)
     * @see #loadCompatibleImage(java.net.URL)
     * @see #toCompatibleImage(java.awt.image.BufferedImage)
     * @param width the width of the new image
     * @param height the height of the new image
     * @param image the reference image from which the transparency of the new
     *   image is obtained
     * @return a new compatible <code>BufferedImage</code> with the same
     *   transparency as <code>image</code> and the specified dimension
     */
    public static BufferedImage createCompatibleImage(BufferedImage image,
                                                      int width, int height) {
        return isHeadless() ?
                new BufferedImage(width, height, image.getType()) :
                getGraphicsConfiguration().createCompatibleImage(width, height,
                                                   image.getTransparency());
    }

    /**
     * <p>Returns a new opaque compatible image of the specified width and
     * height. That is, the returned <code>BufferedImage</code> is compatible with
     * the graphics hardware. If the method is called in a headless
     * environment, then the returned BufferedImage will be compatible with
     * the source image.</p>
     *
     * @see #createCompatibleImage(java.awt.image.BufferedImage)
     * @see #createCompatibleImage(java.awt.image.BufferedImage, int, int)
     * @see #createCompatibleTranslucentImage(int, int)
     * @see #loadCompatibleImage(java.net.URL)
     * @see #toCompatibleImage(java.awt.image.BufferedImage)
     * @param width the width of the new image
     * @param height the height of the new image
     * @return a new opaque compatible <code>BufferedImage</code> of the
     *   specified width and height
     */
    public static BufferedImage createCompatibleImage(int width, int height) {
        return isHeadless() ?
                new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB) :
                getGraphicsConfiguration().createCompatibleImage(width, height);
    }

    /**
     * <p>Returns a new translucent compatible image of the specified width and
     * height. That is, the returned <code>BufferedImage</code> is compatible with
     * the graphics hardware. If the method is called in a headless
     * environment, then the returned BufferedImage will be compatible with
     * the source image.</p>
     *
     * @see #createCompatibleImage(java.awt.image.BufferedImage)
     * @see #createCompatibleImage(java.awt.image.BufferedImage, int, int)
     * @see #createCompatibleImage(int, int)
     * @see #loadCompatibleImage(java.net.URL)
     * @see #toCompatibleImage(java.awt.image.BufferedImage)
     * @param width the width of the new image
     * @param height the height of the new image
     * @return a new translucent compatible <code>BufferedImage</code> of the
     *   specified width and height
     */
    public static BufferedImage createCompatibleTranslucentImage(int width,
                                                                 int height) {
        return isHeadless() ?
                new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB) :
                getGraphicsConfiguration().createCompatibleImage(width, height,
                                                   Transparency.TRANSLUCENT);
    }

    /**
     * <p>Returns a new compatible image from a URL. The image is loaded from the
     * specified location and then turned, if necessary into a compatible
     * image.</p>
     *
     * @see #createCompatibleImage(java.awt.image.BufferedImage)
     * @see #createCompatibleImage(java.awt.image.BufferedImage, int, int)
     * @see #createCompatibleImage(int, int)
     * @see #createCompatibleTranslucentImage(int, int)
     * @see #toCompatibleImage(java.awt.image.BufferedImage)
     * @param resource the URL of the picture to load as a compatible image
     * @return a new translucent compatible <code>BufferedImage</code> of the
     *   specified width and height
     * @throws java.io.IOException if the image cannot be read or loaded
     */
    public static BufferedImage loadCompatibleImage(URL resource)
            throws IOException {
        BufferedImage image = ImageIO.read(resource);
        return toCompatibleImage(image);
    }

    /**
     * <p>Return a new compatible image that contains a copy of the specified
     * image. This method ensures an image is compatible with the hardware,
     * and therefore optimized for fast blitting operations.</p>
     *
     * <p>If the method is called in a headless environment, then the returned
     * <code>BufferedImage</code> will be the source image.</p>
     *
     * @see #createCompatibleImage(java.awt.image.BufferedImage)
     * @see #createCompatibleImage(java.awt.image.BufferedImage, int, int)
     * @see #createCompatibleImage(int, int)
     * @see #createCompatibleTranslucentImage(int, int)
     * @see #loadCompatibleImage(java.net.URL)
     * @param image the image to copy into a new compatible image
     * @return a new compatible copy, with the
     *   same width and height and transparency and content, of <code>image</code>
     */
    public static BufferedImage toCompatibleImage(BufferedImage image) {
        if (isHeadless()) {
            return image;
        }

        if (image.getColorModel().equals(
                getGraphicsConfiguration().getColorModel())) {
            return image;
        }

        BufferedImage compatibleImage =
                getGraphicsConfiguration().createCompatibleImage(
                    image.getWidth(), image.getHeight(),
                    image.getTransparency());
        Graphics g = compatibleImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return compatibleImage;
    }

    /**
     * <p>Returns a thumbnail of a source image. <code>newSize</code> defines
     * the length of the longest dimension of the thumbnail. The other
     * dimension is then computed according to the dimensions ratio of the
     * original picture.</p>
     * <p>This method favors speed over quality. When the new size is less than
     * half the longest dimension of the source image,
     * {@link #createThumbnail(BufferedImage, int)} or
     * {@link #createThumbnail(BufferedImage, int, int)} should be used instead
     * to ensure the quality of the result without sacrificing too much
     * performance.</p>
     *
     * @see #createThumbnailFast(java.awt.image.BufferedImage, int, int)
     * @see #createThumbnail(java.awt.image.BufferedImage, int)
     * @see #createThumbnail(java.awt.image.BufferedImage, int, int)
     * @param image the source image
     * @param newSize the length of the largest dimension of the thumbnail
     * @return a new compatible <code>BufferedImage</code> containing a
     *   thumbnail of <code>image</code>
     * @throws IllegalArgumentException if <code>newSize</code> is larger than
     *   the largest dimension of <code>image</code> or &lt;= 0
     */
    public static BufferedImage createThumbnailFast(BufferedImage image,
                                                    int newSize) {
        float ratio;
        int width = image.getWidth();
        int height = image.getHeight();

        if (width > height) {
            if (newSize >= width) {
                throw new IllegalArgumentException("newSize must be lower than" +
                                                   " the image width");
            } else if (newSize <= 0) {
                 throw new IllegalArgumentException("newSize must" +
                                                    " be greater than 0");
            }

            ratio = (float) width / (float) height;
            width = newSize;
            height = (int) (newSize / ratio);
        } else {
            if (newSize >= height) {
                throw new IllegalArgumentException("newSize must be lower than" +
                                                   " the image height");
            } else if (newSize <= 0) {
                 throw new IllegalArgumentException("newSize must" +
                                                    " be greater than 0");
            }

            ratio = (float) height / (float) width;
            height = newSize;
            width = (int) (newSize / ratio);
        }

        BufferedImage temp = createCompatibleImage(image, width, height);
        Graphics2D g2 = temp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
        g2.dispose();

        return temp;
    }

    /**
     * <p>Returns a thumbnail of a source image.</p>
     * <p>This method favors speed over quality. When the new size is less than
     * half the longest dimension of the source image,
     * {@link #createThumbnail(BufferedImage, int)} or
     * {@link #createThumbnail(BufferedImage, int, int)} should be used instead
     * to ensure the quality of the result without sacrificing too much
     * performance.</p>
     *
     * @see #createThumbnailFast(java.awt.image.BufferedImage, int)
     * @see #createThumbnail(java.awt.image.BufferedImage, int)
     * @see #createThumbnail(java.awt.image.BufferedImage, int, int)
     * @param image the source image
     * @param newWidth the width of the thumbnail
     * @param newHeight the height of the thumbnail
     * @return a new compatible <code>BufferedImage</code> containing a
     *   thumbnail of <code>image</code>
     * @throws IllegalArgumentException if <code>newWidth</code> is larger than
     *   the width of <code>image</code> or if code>newHeight</code> is larger
     *   than the height of <code>image</code> or if one of the dimensions
     *   is &lt;= 0
     */
    public static BufferedImage createThumbnailFast(BufferedImage image,
                                                    int newWidth, int newHeight) {
        if (newWidth >= image.getWidth() ||
            newHeight >= image.getHeight()) {
            throw new IllegalArgumentException("newWidth and newHeight cannot" +
                                               " be greater than the image" +
                                               " dimensions");
        } else if (newWidth <= 0 || newHeight <= 0) {
            throw new IllegalArgumentException("newWidth and newHeight must" +
                                               " be greater than 0");
        }

        BufferedImage temp = createCompatibleImage(image, newWidth, newHeight);
        Graphics2D g2 = temp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
        g2.dispose();

        return temp;
    }

    /**
     * <p>Returns a thumbnail of a source image. <code>newSize</code> defines
     * the length of the longest dimension of the thumbnail. The other
     * dimension is then computed according to the dimensions ratio of the
     * original picture.</p>
     * <p>This method offers a good trade-off between speed and quality.
     * The result looks better than
     * {@link #createThumbnailFast(java.awt.image.BufferedImage, int)} when
     * the new size is less than half the longest dimension of the source
     * image, yet the rendering speed is almost similar.</p>
     *
     * @see #createThumbnailFast(java.awt.image.BufferedImage, int, int)
     * @see #createThumbnailFast(java.awt.image.BufferedImage, int)
     * @see #createThumbnail(java.awt.image.BufferedImage, int, int)
     * @param image the source image
     * @param newSize the length of the largest dimension of the thumbnail
     * @return a new compatible <code>BufferedImage</code> containing a
     *   thumbnail of <code>image</code>
     * @throws IllegalArgumentException if <code>newSize</code> is larger than
     *   the largest dimension of <code>image</code> or &lt;= 0
     */
    public static BufferedImage createThumbnail(BufferedImage image,
                                                int newSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        boolean isTranslucent = image.getTransparency() != Transparency.OPAQUE;
        boolean isWidthGreater = width > height;

        if (isWidthGreater) {
            if (newSize >= width) {
                throw new IllegalArgumentException("newSize must be lower than" +
                                                   " the image width");
            }
        } else if (newSize >= height) {
            throw new IllegalArgumentException("newSize must be lower than" +
                                               " the image height");
        }

        if (newSize <= 0) {
            throw new IllegalArgumentException("newSize must" +
                                               " be greater than 0");
        }

        float ratioWH = (float) width / (float) height;
        float ratioHW = (float) height / (float) width;

        BufferedImage thumb = image;
        BufferedImage temp = null;

        Graphics2D g2 = null;

        int previousWidth = width;
        int previousHeight = height;

        do {
            if (isWidthGreater) {
                width /= 2;
                if (width < newSize) {
                    width = newSize;
                }
                height = (int) (width / ratioWH);
            } else {
                height /= 2;
                if (height < newSize) {
                    height = newSize;
                }
                width = (int) (height / ratioHW);
            }

            if (temp == null || isTranslucent) {
                temp = createCompatibleImage(image, width, height);
                g2 = temp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }
            g2.drawImage(thumb, 0, 0, width, height,
                    0, 0, previousWidth, previousHeight, null);

            previousWidth = width;
            previousHeight = height;

            thumb = temp;
        } while (newSize != (isWidthGreater ? width : height));

        g2.dispose();

        if (width != thumb.getWidth() || height != thumb.getHeight()) {
            temp = createCompatibleImage(image, width, height);
            g2 = temp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(thumb, 0, 0, width, height, null);
            g2.dispose();
            thumb = temp;
        }

        return thumb;
    }

    /**
     * <p>Returns a thumbnail of a source image.</p>
     * <p>This method offers a good trade-off between speed and quality.
     * The result looks better than
     * {@link #createThumbnailFast(java.awt.image.BufferedImage, int)} when
     * the new size is less than half the longest dimension of the source
     * image, yet the rendering speed is almost similar.</p>
     *
     * @see #createThumbnailFast(java.awt.image.BufferedImage, int)
     * @see #createThumbnailFast(java.awt.image.BufferedImage, int, int)
     * @see #createThumbnail(java.awt.image.BufferedImage, int)
     * @param image the source image
     * @param newWidth the width of the thumbnail
     * @param newHeight the height of the thumbnail
     * @return a new compatible <code>BufferedImage</code> containing a
     *   thumbnail of <code>image</code>
     * @throws IllegalArgumentException if <code>newWidth</code> is larger than
     *   the width of <code>image</code> or if code>newHeight</code> is larger
     *   than the height of <code>image or if one the dimensions is not &gt; 0</code>
     */
    public static BufferedImage createThumbnail(BufferedImage image,
                                                int newWidth, int newHeight) {
        int width = image.getWidth();
        int height = image.getHeight();

        boolean isTranslucent = image.getTransparency() != Transparency.OPAQUE;

        if (newWidth >= width || newHeight >= height) {
            throw new IllegalArgumentException("newWidth and newHeight cannot" +
                                               " be greater than the image" +
                                               " dimensions");
        } else if (newWidth <= 0 || newHeight <= 0) {
            throw new IllegalArgumentException("newWidth and newHeight must" +
                                               " be greater than 0");
        }

        BufferedImage thumb = image;
        BufferedImage temp = null;

        Graphics2D g2 = null;

        int previousWidth = width;
        int previousHeight = height;

        do {
            if (width > newWidth) {
                width /= 2;
                if (width < newWidth) {
                    width = newWidth;
                }
            }

            if (height > newHeight) {
                height /= 2;
                if (height < newHeight) {
                    height = newHeight;
                }
            }

            if (temp == null || isTranslucent) {
                temp = createCompatibleImage(image, width, height);
                g2 = temp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }
            g2.drawImage(thumb, 0, 0, width, height,
                         0, 0, previousWidth, previousHeight, null);

            previousWidth = width;
            previousHeight = height;

            thumb = temp;
        } while (width != newWidth || height != newHeight);

        g2.dispose();

        if (width != thumb.getWidth() || height != thumb.getHeight()) {
            temp = createCompatibleImage(image, width, height);
            g2 = temp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(thumb, 0, 0, width, height, null);
            g2.dispose();
            thumb = temp;
        }

        return thumb;
    }

    /**
     * <p>Returns an array of pixels, stored as integers, from a
     * <code>BufferedImage</code>. The pixels are grabbed from a rectangular
     * area defined by a location and two dimensions. Calling this method on
     * an image of type different from <code>BufferedImage.TYPE_INT_ARGB</code>
     * and <code>BufferedImage.TYPE_INT_RGB</code> will unmanage the image.</p>
     *
     * @param img the source image
     * @param x the x location at which to start grabbing pixels
     * @param y the y location at which to start grabbing pixels
     * @param w the width of the rectangle of pixels to grab
     * @param h the height of the rectangle of pixels to grab
     * @param pixels a pre-allocated array of pixels of size w*h; can be null
     * @return <code>pixels</code> if non-null, a new array of integers
     *   otherwise
     * @throws IllegalArgumentException is <code>pixels</code> is non-null and
     *   of length &lt; w*h
     */
    public static int[] getPixels(BufferedImage img,
                                  int x, int y, int w, int h, int[] pixels) {
        if (w == 0 || h == 0) {
            return new int[0];
        }

        if (pixels == null) {
            pixels = new int[w * h];
        } else if (pixels.length < w * h) {
            throw new IllegalArgumentException("pixels array must have a length" +
                                               " >= w*h");
        }

        int imageType = img.getType();
        if (imageType == BufferedImage.TYPE_INT_ARGB ||
            imageType == BufferedImage.TYPE_INT_RGB) {
            Raster raster = img.getRaster();
            return (int[]) raster.getDataElements(x, y, w, h, pixels);
        }

        // Unmanages the image
        return img.getRGB(x, y, w, h, pixels, 0, w);
    }

    /**
     * <p>Writes a rectangular area of pixels in the destination
     * <code>BufferedImage</code>. Calling this method on
     * an image of type different from <code>BufferedImage.TYPE_INT_ARGB</code>
     * and <code>BufferedImage.TYPE_INT_RGB</code> will unmanage the image.</p>
     *
     * @param img the destination image
     * @param x the x location at which to start storing pixels
     * @param y the y location at which to start storing pixels
     * @param w the width of the rectangle of pixels to store
     * @param h the height of the rectangle of pixels to store
     * @param pixels an array of pixels, stored as integers
     * @throws IllegalArgumentException is <code>pixels</code> is non-null and
     *   of length &lt; w*h
     */
    public static void setPixels(BufferedImage img,
                                 int x, int y, int w, int h, int[] pixels) {
        if (pixels == null || w == 0 || h == 0) {
            return;
        } else if (pixels.length < w * h) {
            throw new IllegalArgumentException("pixels array must have a length" +
                                               " >= w*h");
        }

        int imageType = img.getType();
        if (imageType == BufferedImage.TYPE_INT_ARGB ||
            imageType == BufferedImage.TYPE_INT_RGB) {
            WritableRaster raster = img.getRaster();
            raster.setDataElements(x, y, w, h, pixels);
        } else {
            // Unmanages the image
            img.setRGB(x, y, w, h, pixels, 0, w);
        }
    }
}
/*
 * Copyright (c) 2007, Romain Guy
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   * Neither the name of the TimingFramework project nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */



/**
 *
 * @author Romain Guy
 */
class PictureGlassPane extends JComponent {
    private BufferedImage image;
    private Point location;
    private BufferedImage shadow;
    
    public PictureGlassPane() {
    }
    
    public void moveIt(Point location) {
        Point oldLocation = this.location;
        SwingUtilities.convertPointFromScreen(location, this);
        this.location = location;
        
        Rectangle newClip = new Rectangle(location.x - image.getWidth() / 2, location.y - image.getHeight() / 2,
                image.getWidth(), image.getHeight());
        newClip.add(new Rectangle(oldLocation.x - image.getWidth() / 2, oldLocation.y - image.getHeight() / 2,
                image.getWidth(), image.getHeight()));
        newClip.add(new Rectangle(oldLocation.x - image.getWidth() / 2, oldLocation.y - image.getHeight() / 2,
                shadow.getWidth(), shadow.getHeight()));
        newClip.add(new Rectangle(location.x - image.getWidth() / 2, location.y - image.getHeight() / 2,
                shadow.getWidth(), shadow.getHeight()));

        repaint(newClip);
    }
    
    public void hideIt() {
        setVisible(false);
    }
    
    public void showIt(BufferedImage image, Point location) {
        this.image = image;
        this.shadow = new ShadowRenderer(5, 0.3f, Color.BLACK).createShadow(image);

        SwingUtilities.convertPointFromScreen(location, this);
        this.location = location;
        
        setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (image != null && location != null) {
            int x = location.x - image.getWidth() / 2;
            int y = location.y - image.getHeight() / 2;
            
            g.drawImage(shadow, x, y, null);
            g.drawImage(image, x, y, null);
        }
    }
}
/*
 * $Id: ShadowRenderer.java,v 1.1 2007/01/15 23:39:23 gfx Exp $
 *
 * Copyright 2006 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * Licensed under LGPL.
 */



/**
 * <p>A shadow renderer generates a drop shadow for any given picture, respecting
 * the transparency channel if present. The resulting picture contains the
 * shadow only and to create a drop shadow effect you will need to stack the
 * original picture and the shadow generated by the renderer.</p>
 * <h2>Shadow Properties</h2>
 * <p>A shadow is defined by three properties:
 * <ul>
 *   <li><i>size</i>: The size, in pixels, of the shadow. This property also
 *   defines the fuzzyness.</li>
 *   <li><i>opacity</i>: The opacity, between 0.0 and 1.0, of the shadow.</li>
 *   <li><i>color</i>: The color of the shadow. Shadows are not meant to be
 *   black only.</li>
 * </ul>
 * You can set these properties using the provided mutaters or the appropriate
 * constructor. Here are two ways of creating a green shadow of size 10 and
 * with an opacity of 50%:
 * <pre>
 * ShadowRenderer renderer = new ShadowRenderer(10, 0.5f, Color.GREEN);
 * // ..
 * renderer = new ShadowRenderer();
 * renderer.setSize(10);
 * renderer.setOpacity(0.5f);
 * renderer.setColor(Color.GREEN);
 * </pre>
 * The default constructor provides the following default values:
 * <ul>
 *   <li><i>size</i>: 5 pixels</li>
 *   <li><i>opacity</i>: 50%</li>
 *   <li><i>color</i>: Black</li>
 * </ul></p>
 * <h2>Generating a Shadow</h2>
 * <p>A shadow is generated as a <code>BufferedImage</code> from another
 * <code>BufferedImage</code>. Once the renderer is set up, you must call
 * {@link #createShadow} to actually generate the shadow:
 * <pre>
 * ShadowRenderer renderer = new ShadowRenderer();
 * // renderer setup
 * BufferedImage shadow = renderer.createShadow(bufferedImage);
 * </pre></p>
 * <p>The generated image dimensions are computed as following:</p>
 * <pre>
 * width  = imageWidth  + 2 * shadowSize
 * height = imageHeight + 2 * shadowSize
 * </pre>
 * <h2>Properties Changes</h2>
 * <p>This renderer allows to register property change listeners with
 * {@link #addPropertyChangeListener}. Listening to properties changes is very
 * useful when you emebed the renderer in a graphical component and give the API
 * user the ability to access the renderer. By listening to properties changes,
 * you can easily repaint the component when needed.</p>
 * <h2>Threading Issues</h2>
 * <p><code>ShadowRenderer</code> is not guaranteed to be thread-safe.</p>
 * 
 * @author Romain Guy <romain.guy@mac.com>
 * @author Sebastien Petrucci
 */
class ShadowRenderer {
    /**
     * <p>Identifies a change to the size used to render the shadow.</p>
     * <p>When the property change event is fired, the old value and the new
     * value are provided as <code>Integer</code> instances.</p>
     */
    public static final String SIZE_CHANGED_PROPERTY = "shadow_size";
    
    /**
     * <p>Identifies a change to the opacity used to render the shadow.</p>
     * <p>When the property change event is fired, the old value and the new
     * value are provided as <code>Float</code> instances.</p>
     */
    public static final String OPACITY_CHANGED_PROPERTY = "shadow_opacity";
    
    /**
     * <p>Identifies a change to the color used to render the shadow.</p>
     */
    public static final String COLOR_CHANGED_PROPERTY = "shadow_color";

    // size of the shadow in pixels (defines the fuzziness)
    private int size = 5;
    
    // opacity of the shadow
    private float opacity = 0.5f;
    
    // color of the shadow
    private Color color = Color.BLACK;
    
    // notifies listeners of properties changes
    private PropertyChangeSupport changeSupport;

    /**
     * <p>Creates a default good looking shadow generator.
     * The default shadow renderer provides the following default values:
     * <ul>
     *   <li><i>size</i>: 5 pixels</li>
     *   <li><i>opacity</i>: 50%</li>
     *   <li><i>color</i>: Black</li>
     * </ul></p>
     * <p>These properties provide a regular, good looking shadow.</p>
     */
    public ShadowRenderer() {
        this(5, 0.5f, Color.BLACK);
    }
    
    /**
     * <p>A shadow renderer needs three properties to generate shadows.
     * These properties are:</p> 
     * <ul>
     *   <li><i>size</i>: The size, in pixels, of the shadow. This property also
     *   defines the fuzzyness.</li>
     *   <li><i>opacity</i>: The opacity, between 0.0 and 1.0, of the shadow.</li>
     *   <li><i>color</i>: The color of the shadow. Shadows are not meant to be
     *   black only.</li>
     * </ul>
     * @param size the size of the shadow in pixels. Defines the fuzziness.
     * @param opacity the opacity of the shadow.
     * @param color the color of the shadow.
     */
    public ShadowRenderer(final int size, final float opacity, final Color color) {
        //noinspection ThisEscapedInObjectConstruction
        changeSupport = new PropertyChangeSupport(this);

        setSize(size);
        setOpacity(opacity);
        setColor(color);
    }

    /**
     * <p>Add a PropertyChangeListener to the listener list. The listener is
     * registered for all properties. The same listener object may be added
     * more than once, and will be called as many times as it is added. If
     * <code>listener</code> is null, no exception is thrown and no action
     * is taken.</p> 
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * <p>Remove a PropertyChangeListener from the listener list. This removes
     * a PropertyChangeListener that was registered for all properties. If
     * <code>listener</code> was added more than once to the same event source,
     * it will be notified one less time after being removed. If
     * <code>listener</code> is null, or was never added, no exception is thrown
     * and no action is taken.</p>
     * @param listener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    /**
     * <p>Gets the color used by the renderer to generate shadows.</p>
     * @return this renderer's shadow color
     */
    public Color getColor() {
        return color;
    }

    /**
     * <p>Sets the color used by the renderer to generate shadows.</p>
     * <p>Consecutive calls to {@link #createShadow} will all use this color
     * until it is set again.</p>
     * <p>If the color provided is null, the previous color will be retained.</p>
     * @param shadowColor the generated shadows color
     */
    public void setColor(final Color shadowColor) {
        if (shadowColor != null) {
            Color oldColor = this.color;
            this.color = shadowColor;
            changeSupport.firePropertyChange(COLOR_CHANGED_PROPERTY,
                                             oldColor,
                                             this.color);
        }
    }

    /**
     * <p>Gets the opacity used by the renderer to generate shadows.</p>
     * <p>The opacity is comprised between 0.0f and 1.0f; 0.0f being fully
     * transparent and 1.0f fully opaque.</p>
     * @return this renderer's shadow opacity
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * <p>Sets the opacity used by the renderer to generate shadows.</p>
     * <p>Consecutive calls to {@link #createShadow} will all use this opacity
     * until it is set again.</p>
     * <p>The opacity is comprised between 0.0f and 1.0f; 0.0f being fully
     * transparent and 1.0f fully opaque. If you provide a value out of these
     * boundaries, it will be restrained to the closest boundary.</p>
     * @param shadowOpacity the generated shadows opacity
     */
    public void setOpacity(final float shadowOpacity) {
        float oldOpacity = this.opacity;
        
        if (shadowOpacity < 0.0) {
            this.opacity = 0.0f;
        } else if (shadowOpacity > 1.0f) {
            this.opacity = 1.0f;
        } else {
            this.opacity = shadowOpacity;
        }
        
        changeSupport.firePropertyChange(OPACITY_CHANGED_PROPERTY,
                                         oldOpacity,
                                         this.opacity);
    }

    /**
     * <p>Gets the size in pixel used by the renderer to generate shadows.</p>
     * @return this renderer's shadow size
     */
    public int getSize() {
        return size;
    }

    /**
     * <p>Sets the size, in pixels, used by the renderer to generate shadows.</p>
     * <p>The size defines the blur radius applied to the shadow to create the
     * fuzziness.</p>
     * <p>There is virtually no limit to the size. The size cannot be negative.
     * If you provide a negative value, the size will be 0 instead.</p>
     * @param shadowSize the generated shadows size in pixels (fuzziness)
     */
    public void setSize(final int shadowSize) {
        int oldSize = this.size;
        
        if (shadowSize < 0) {
            this.size = 0;
        } else {
            this.size = shadowSize;
        }
        
        changeSupport.firePropertyChange(SIZE_CHANGED_PROPERTY,
                                         new Integer(oldSize),
                                         new Integer(this.size));
    }

    /**
     * <p>Generates the shadow for a given picture and the current properties
     * of the renderer.</p>
     * <p>The generated image dimensions are computed as following:</p>
     * <pre>
     * width  = imageWidth  + 2 * shadowSize
     * height = imageHeight + 2 * shadowSize
     * </pre>
     * @param image the picture from which the shadow must be cast
     * @return the picture containing the shadow of <code>image</code> 
     */
    public BufferedImage createShadow(final BufferedImage image) {
        // Written by Sesbastien Petrucci
        int shadowSize = size * 2;

        int srcWidth = image.getWidth();
        int srcHeight = image.getHeight();

        int dstWidth = srcWidth + shadowSize;
        int dstHeight = srcHeight + shadowSize;

        int left = size;
        int right = shadowSize - left;

        int yStop = dstHeight - right;

        int shadowRgb = color.getRGB() & 0x00FFFFFF;
        int[] aHistory = new int[shadowSize];
        int historyIdx;

        int aSum;

        BufferedImage dst = new BufferedImage(dstWidth, dstHeight,
                                              BufferedImage.TYPE_INT_ARGB);

        int[] dstBuffer = new int[dstWidth * dstHeight];
        int[] srcBuffer = new int[srcWidth * srcHeight];

        GraphicsUtilities.getPixels(image, 0, 0, srcWidth, srcHeight, srcBuffer);

        int lastPixelOffset = right * dstWidth;
        float hSumDivider = 1.0f / shadowSize;
        float vSumDivider = opacity / shadowSize;

        int[] hSumLookup = new int[256 * shadowSize];
        for (int i = 0; i < hSumLookup.length; i++) {
            hSumLookup[i] = (int) (i * hSumDivider);
        }

        int[] vSumLookup = new int[256 * shadowSize];
        for (int i = 0; i < vSumLookup.length; i++) {
            vSumLookup[i] = (int) (i * vSumDivider);
        }

        int srcOffset;

        // horizontal pass : extract the alpha mask from the source picture and
        // blur it into the destination picture
        for (int srcY = 0, dstOffset = left * dstWidth; srcY < srcHeight; srcY++) {

            // first pixels are empty
            for (historyIdx = 0; historyIdx < shadowSize; ) {
                aHistory[historyIdx++] = 0;
            }

            aSum = 0;
            historyIdx = 0;
            srcOffset = srcY * srcWidth;

            // compute the blur average with pixels from the source image
            for (int srcX = 0; srcX < srcWidth; srcX++) {

                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;   // store the alpha value only
                                                    // the shadow color will be added in the next pass

                aSum -= aHistory[historyIdx]; // substract the oldest pixel from the sum

                // extract the new pixel ...
                a = srcBuffer[srcOffset + srcX] >>> 24;
                aHistory[historyIdx] = a;   // ... and store its value into history
                aSum += a;                  // ... and add its value to the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }

            // blur the end of the row - no new pixels to grab
            for (int i = 0; i < shadowSize; i++) {

                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;

                // substract the oldest pixel from the sum ... and nothing new to add !
                aSum -= aHistory[historyIdx];

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
        }

        // vertical pass
        for (int x = 0, bufferOffset = 0; x < dstWidth; x++, bufferOffset = x) {

            aSum = 0;

            // first pixels are empty
            for (historyIdx = 0; historyIdx < left;) {
                aHistory[historyIdx++] = 0;
            }

            // and then they come from the dstBuffer
            for (int y = 0; y < right; y++, bufferOffset += dstWidth) {
                int a = dstBuffer[bufferOffset] >>> 24;         // extract alpha
                aHistory[historyIdx++] = a;                     // store into history
                aSum += a;                                      // and add to sum
            }

            bufferOffset = x;
            historyIdx = 0;

            // compute the blur avera`ge with pixels from the previous pass
            for (int y = 0; y < yStop; y++, bufferOffset += dstWidth) {

                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;  // store alpha value + shadow color

                aSum -= aHistory[historyIdx];   // substract the oldest pixel from the sum

                a = dstBuffer[bufferOffset + lastPixelOffset] >>> 24;   // extract the new pixel ...
                aHistory[historyIdx] = a;                               // ... and store its value into history
                aSum += a;                                              // ... and add its value to the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }

            // blur the end of the column - no pixels to grab anymore
            for (int y = yStop; y < dstHeight; y++, bufferOffset += dstWidth) {

                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;

                aSum -= aHistory[historyIdx];   // substract the oldest pixel from the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
        }

        GraphicsUtilities.setPixels(dst, 0, 0, dstWidth, dstHeight, dstBuffer);
        return dst;
    }
}

