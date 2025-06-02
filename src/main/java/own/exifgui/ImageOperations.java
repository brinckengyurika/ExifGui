/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package own.exifgui;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.BufferedImageFactory;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImagingParameters;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImagingParameters;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;
/**
 *
 * @author satch
 */
public class ImageOperations {
    public static class ManagedImageBufferedImageFactory implements BufferedImageFactory {

        @Override
        public BufferedImage getColorBufferedImage(final int width, final int height, final boolean hasAlpha) {
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            final GraphicsDevice gd = ge.getDefaultScreenDevice();
            final GraphicsConfiguration gc = gd.getDefaultConfiguration();
            return gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        }

        @Override
        public BufferedImage getGrayscaleBufferedImage(final int width, final int height, final boolean hasAlpha) {
            return getColorBufferedImage(width, height, hasAlpha);
        }
    }

    public static BufferedImage imageRead(final File file) throws ImagingException, IOException {
        final TiffImagingParameters params = new TiffImagingParameters();

        // set optional parameters if you like
        params.setBufferedImageFactory(new ManagedImageBufferedImageFactory());

        // params.setStrict(Boolean.TRUE);

        // read and return the TIFF image
        return new TiffImageParser().getBufferedImage(file, params);
    }
    
    public static byte[] imageWrite(final File file) throws ImagingException, ImagingException, IOException {
        // read image
        final BufferedImage image = Imaging.getBufferedImage(file);

        // set optional parameters if you like
        final TiffImagingParameters params = new TiffImagingParameters();
        params.setCompression(TiffConstants.COMPRESSION_UNCOMPRESSED);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            new TiffImageParser().writeImage(image, baos, params);
            return baos.toByteArray();
        }
    }    
}
