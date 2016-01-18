package com.aziis98.edesk.display;

import com.aziis98.edesk.event.*;
import com.aziis98.edesk.math.IColor.*;
import com.aziis98.edesk.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

public class DisplayJFrame extends AbstractDisplay {

    JFrame frame;
    Canvas canvas;

    BufferedImage image = new BufferedImage( 640, 480, BufferedImage.TYPE_INT_RGB );
    GraphicsDrawer drawer;

    public DisplayJFrame() {

        frame = new JFrame();

        frame.setVisible( true );
        frame.setResizable( true );
        frame.setLocationRelativeTo( null );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );


        frame.getContentPane().add( canvas = new Canvas(){

            @Override
            public void update(Graphics g) {
                this.paint( g );
            }

            @Override
            public void paint(Graphics g) {
                long start = Time.milliTime();

                EventManager.invoke( "display.render", drawer );

                g.drawImage( image, 0, 0, null );

                long delta = Time.milliTime() - start;
                long remaining = frameTime - delta;

                System.out.println("Amount of sleep: " + remaining);

                if ( remaining > 0 )
                {
                    Time.runLater( remaining - 1, this::repaint );
                }
                else
                {
                    repaint();
                }
            }

        });

        frame.addComponentListener( new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = (Component) e.getSource();

                image = new BufferedImage( component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB );
                drawer = new GraphicsDrawer( DisplayJFrame.this, image );
            }
        } );


        canvas.setSize( 640, 480 );
        frame.pack();

    }

    @Override
    public void setTitle(String title) {
        frame.setTitle( title );
    }

    @Override
    public int getWidth() {
        return frame.getWidth();
    }

    @Override
    public void setWidth(int width) {
        frame.setSize( width, getHeight() );
    }

    @Override
    public int getHeight() {
        return frame.getHeight();
    }

    @Override
    public void setHeight(int height) {
        frame.setSize( getWidth(), height );
    }

    @Override
    public void setSize(int width, int height) {
        frame.setSize( width, height );
    }

    @Override
    public Drawer getDrawer() {
        return drawer;
    }

    public static class GraphicsDrawer extends Drawer {

        private BufferedImage image;
        private Graphics2D graphics2D;
        private Stroke stroke = new BasicStroke( 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );
        private LinkedList<AffineTransform> transformStack = new LinkedList<>();

        public GraphicsDrawer(DisplayJFrame display, BufferedImage image) {
            super( display );
            this.image = image;
            this.graphics2D = (Graphics2D) image.getGraphics();
        }

        @Override
        public void line(float x1, float y1, float x2, float y2) {
            if ( mode == DrawingMode.Line )
            {
                graphics2D.drawLine( (int) x1, (int) y1, (int) x2, (int) y2 );
            }
        }

        @Override
        public void rectangle(float x, float y, float width, float height) {
            if ( mode == DrawingMode.Line )
            {
                graphics2D.drawRect( (int) x, (int) y, (int) width, (int) height );
            }
            else if ( mode == DrawingMode.Filling )
            {
                graphics2D.fillRect( (int) x, (int) y, (int) width, (int) height );
            }
        }

        @Override
        public void ellpise(float x, float y, float width, float height) {
            if ( mode == DrawingMode.Line )
            {
                graphics2D.drawOval( (int) x, (int) y, (int) width, (int) height );
            }
            else if ( mode == DrawingMode.Filling )
            {
                graphics2D.fillOval( (int) x, (int) y, (int) width, (int) height );
            }
        }

        @Override
        public void arc(float x, float y, float width, float height, float angStart, float angEnd) {
            if ( mode == DrawingMode.Line )
            {
                graphics2D.drawArc( (int) x, (int) y, (int) width, (int) height, (int) angStart, (int) angEnd );
            }
            else if ( mode == DrawingMode.Filling )
            {
                graphics2D.fillArc( (int) x, (int) y, (int) width, (int) height, (int) angStart, (int) angEnd );
            }
        }

        @Override
        public void clear() {
            graphics2D.setBackground( graphics2D.getColor() );
            graphics2D.clearRect( 0, 0, image.getWidth(), image.getHeight() );
        }

        @Override
        public void translate(float dx, float dy) {
            graphics2D.translate( dx, dy );
        }

        @Override
        public void rotate(float angle) {
            graphics2D.rotate( angle );
        }

        @Override
        public void scale(float dx, float dy) {
            graphics2D.scale( dx, dy );
        }

        @Override
        public void pushTransform() {
            transformStack.push( new AffineTransform( graphics2D.getTransform() ) );
        }

        @Override
        public void popTransform() {
            graphics2D.setTransform( transformStack.pop() );
        }

        @Override
        public void applyFilter(float x, float y, float width, float height, FilterFunction filterFunction) {
            BufferedImage computed = new BufferedImage( (int) width, (int) height, image.getType() );

            for (int u = 0; u < width; u++)
            {
                for (int v = 0; v < height; v++)
                {
                    computed.setRGB( u, v, filterFunction.transform( u, v, new ColorRGB( image.getRGB( u + (int) x, v + (int) y ) ) ).toRGB() );
                }
            }

            graphics2D.drawImage( computed, (int) x, (int) y, null );
        }

        @Override
        public void lineWidth(int width) {
            stroke = new BasicStroke( width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );
        }

        @Override
        public void color(ColorRGB color) {
            graphics2D.setColor( new Color( color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() ) );
        }
    }

}
