package com.uerj.image;

import java.io.IOException;
import java.io.File;
import com.uerj.util.BorderHandling;
import com.uerj.util.FileImageReader;
import com.uerj.util.FileImageWriter;
import com.uerj.util.MMTImage;
import com.uerj.util.SobelOperator;

/**
 * https://code.google.com/a/eclipselabs.org/p/mmt-engel/source/browse/MMT_En1/trunk/MMT_En1/src/image_filter/SobelOperator.java?r=12
 * commandline programm for applying the sobel operator to an image.
 * 
 * usage: CreateGradientImage <image> <BorderHandling> BorderHandling can be
 * partial, padding or limiting. partial is the defaultvalue.
 * 
 * outputfile is the gradientimage GradientImage.png
 * 
 * @author Mürzl Harald
 * 
 */
public class ExecSobelFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err
					.println("set the image for applying the sobel operator.");
		}
		File im = new File(args[0]);
		BorderHandling bh = BorderHandling.PARTIAL;

		// check if borderhandling is given
		if (args.length == 2) {
			if (args[1].equals("partial") || args[1].equals("padding")
					|| args[1].equals("limiting")) {
				bh = BorderHandling.valueOf(args[1].toUpperCase());
			}
		}

		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
		}

		// open picture and sobel it
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());

			SobelOperator so = new SobelOperator(bh);
			MMTImage gradient = so.applyFilter(img);

			// limit picture to view
			gradient.setData(gradient.getLimited_0_255());
			// save new Picture
			FileImageWriter.write(gradient, "GradientImage.png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}