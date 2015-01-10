package com.uerj.util;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * provides methods for reading an image file
 * @author muetze
 *
 */
public class FileImageReader {

        /**
         * opens the file fname and stores the imagedata in Image
         * @param fname String
         * @return MMTImage
         * @throws IOException 
         */
        public static MMTImage read(String fname) throws IOException {
                File f = new File(fname);
                BufferedImage bi = ImageIO.read(f);
                Raster raster = bi.getData();
                String iname = fname.substring(0, fname.lastIndexOf('.'));
                int width = raster.getWidth();
                int height = raster.getHeight();
                
                MMTImage img = new MMTImage(width, height);
                img.setName(iname);
                img.setData(raster.getPixels(0, 0, width, height, img.getData()));
                
                
                return img;
        }
}