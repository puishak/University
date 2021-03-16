package a2;

import java.awt.Color;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class A2 {
	/**
	 * The original image
	 */
	private static Picture orig;
	
	/**
	 * The image viewer class
	 */
	private static A2Viewer viewer;
	
	
	
	/**
	 * Returns a 300x200 image containing the Queen's flag (without the crown).
	 * 
	 * @return an image containing the Queen's flag
	 */
	public static Picture flag() {
		Picture img = new Picture(300, 200);
		int w = img.width();
		int h = img.height();

		// set the pixels in the blue stripe
		Color blue = new Color(0, 48, 95);
		for (int col = 0; col < w / 3; col++) {
		    for (int row = 0; row < h - 1; row++) {
		        img.set(col, row, blue);
		    }
		}

		// set the pixels in the yellow stripe
		Color yellow = new Color(255, 189, 17);
		for (int col = w / 3; col < 2 * w / 3; col++) {
		    for (int row = 0; row < h - 1; row++) {
		        img.set(col, row, yellow);
		    }
		}

		// set the pixels in the red stripe
		Color red = new Color(185, 17, 55);
		for (int col = 2 * w / 3; col < w; col++) {
		    for (int row = 0; row < h - 1; row++) {
		        img.set(col, row, red);
		    }
		}
		return img;
	}

	public static Picture copy(Picture p) {
		Picture result = new Picture(p.width(), p.height());

		for(int i = 0; i < p.width(); i++) {
			for(int j = 0; j < p.height(); j++) {
				Color c = p.get(i, j);
				result.set(i, j, c);
			}
		}
		
		return result;
		
	}
	
	// ADD YOUR METHODS HERE
	
	
	public static Picture border(Picture p, int thickness) {
		Picture result = A2.copy(p);
		
		Color blue = Color.BLUE;
		
		for (int i = 0; i < thickness; i++) {
//			make top border
			for(int j = 0; j < p.width(); j++) {
				result.set(j, i, blue);
			}
//			make bottom border
			for(int j = 0; j < p.width(); j++) {
				result.set(j, p.height() - 1 - i, blue);
			}
//			make left border
			for(int j = 0; j < p.height(); j++) {
				result.set(i, j, blue);
			}
//			make right border
			for(int j = 0; j < p.height(); j++) {
				result.set(p.width() -1 - i, j, blue);
			}
		}
		
		return result;
	}
	
	public static Picture grayScale(Picture p) {
		Picture result = new Picture(p.width(), p.height());

		for(int i = 0; i < p.width(); i++) {
			for(int j = 0; j < p.height(); j++) {
				int grayLevel = A2.computeGreyLevel(p.get(i, j));
				Color c = new Color(grayLevel, grayLevel, grayLevel);
				result.set(i, j, c);
			}
		}
		
		return result;
	}
	
	public static Picture binary(Picture p, Color c1, Color c2) {
		Picture result = new Picture(p.width(), p.height());
		
		for(int i = 0; i < p.width(); i++) {
			for(int j = 0; j < p.height(); j++) {
				
				int grayLevel = A2.computeGreyLevel(p.get(i, j));
				Color c;
				
				if(grayLevel < 128) c = c1;
				else c = c2;
				
				result.set(i, j, c);
			}
		}
		
		return result;
	}
	
	public static Picture flipVertical(Picture p) {
		Picture result = new Picture(p.width(), p.height());
		
		for(int i = 0; i < p.width(); i++) {
			for(int j = 0; j < p.height(); j++) {
				Color c = p.get(i, j);
				result.set(i, p.height() - 1 - j, c);
			}
		}
		
		return result;
	}
	
	public static Picture rotateClockwise(Picture p) {
		Picture result = new Picture( p.height(), p.width());
		
		for(int i = 0; i < p.width(); i++) {
			for(int j = 0; j < p.height(); j++) {
				Color c = p.get(i, j);
				result.set(p.height() - 1 - j, i, c);
			}
		}
		
		return result;
	}
	
	/**This function identifies the pixels of the red-eye and replaces them with the color
	 * black. For each pixel in the original picture it retrieves the rgb values and calculates
	 * the red to green ratio and red to blue ratio. If those ratios are above a certain 
	 * threshold it tags that pixel as a red-eye pixel. To avoid tagging all the dark pixels it
	 * also uses a threshold for minimum red level in that pixel. The 3 different thresholds
	 * are calculated by trial and error.
	 */
	public static Picture removeRedEye(Picture p) {
		
		//Thresholds
		int minRedLevel = 85;
		float minRGRatio = 1.6f;
		float minRBRatio = 1.6f;
		
		Color replacementColor = Color.BLACK;
		Picture result = new Picture(p.width(), p.height());
		
		for(int i = 0; i < p.width(); i++) {
			for(int j = 0; j < p.height(); j++) {
				
				Color c = p.get(i, j);
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				
				//red to green ratio of the pixel
				float rgRatio = (float) r/g;
				//red to blue ratio of the pixel
				float rbRatio = (float) r/b;
				
				//This condition checks if the pixel meets red-eye pixel requirement
				if (r > minRedLevel && rgRatio > minRGRatio && rbRatio > minRBRatio) {
					result.set(i, j, replacementColor);
				}
				else {
					result.set(i, j, c);
				}
			}
		}
		
		return result;
	}
	
	
	public static Picture blur(Picture p, int radius) {
		Picture result = new Picture(p.width(), p.height());
		
		for(int i = 0; i < p.width(); i++) {
			for(int j = 0; j < p.height(); j++) {
				
				//sCol and sRow marks the top left pixel of the blur box
				int sCol = Math.max(i - radius, 0);
				int sRow = Math.max(j - radius, 0);
				
				//eCol and eRow marks the bottom right pixel of the blur box
				int eCol = Math.min(i + radius, p.width() - 1);
				int eRow = Math.min(j + radius, p.height() - 1);
				
				//Total number of pixels in the box
				int numPixel = (eCol - sCol) * (eRow - sRow);
				
				//Make and fill the array box with all the pixels from the blur box
				Color[] box = new Color[numPixel];
				int count = 0;
				
				for(int a = sCol; a <= eCol; a++) {
					for(int b = sRow; b <=eRow; b++) {
						if(count < numPixel) {
							box[count] = p.get(a, b);
							count++;
						}
					}
				}
				
				Color c = A2.averageColor(box);
				result.set(i, j, c);
			}
		}
		
		return result;
	}
	
	//Return the average of the colors in c
	private static Color averageColor(Color[] c) {
		
		int r = 0;
		int g = 0;
		int b = 0;
		
		for(Color i: c) {
			r += i.getRed();
			g += i.getGreen();
			b += i.getBlue();
		}
		
		r = Math.round(r/c.length);
		g = Math.round(g/c.length);
		b = Math.round(b/c.length);
		
		Color result = new Color(r, g, b);
		
		return result;
	}
	
	//Return the gray level of the color c
	private static int computeGreyLevel(Color C) {
		
		int r = C.getRed();
		int g = C.getGreen();
		int b = C.getBlue();
		int y = (int) Math.round(0.2989 * r + 0.5870 * g + 0.1140 * b);
		
		return y;
	}

	/**
	 * A2Viewer class calls this method when a menu item is selected.
	 * This method computes a new image and then asks the viewer to
	 * display the computed image.
	 * 
	 * @param op the operation selected in the viewer
	 */
	public static void processImage(String op) {
		
		switch (op) {
		case A2Viewer.FLAG:
			// create a new image by copying the original image
			Picture p = A2.flag();
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.COPY:
			// create a new image by copying the original image
			p = A2.copy(A2.orig);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BORDER_1:
			// create a new image by adding a border of width 1 to the original image
			p = A2.border(A2.orig, 1);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.BORDER_5:
			// create a new image by adding a border of width 5 the original image
			p = A2.border(A2.orig, 5);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.BORDER_10:
			// create a new image by adding a border of width 10  the original image
			p = A2.border(A2.orig, 10);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.TO_GRAY:
			// create a new image by converting the original image to grayscale
			p = A2.grayScale(A2.orig);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.TO_BINARY:
			// create a new image by converting the original image to black and white
			p = A2.binary(A2.orig, Color.BLACK, Color.WHITE);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.FLIP_VERTICAL:
			// create a new image by flipping the original image vertically
			p = A2.flipVertical(A2.orig);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.ROTATE_RIGHT:
			// create a new image by rotating the original image to the right by 90 degrees
			p = A2.rotateClockwise(A2.orig);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.RED_EYE:
			// create a new image by removing the redeye effect in the original image
			p = A2.removeRedEye(A2.orig);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.BLUR_1:
			// create a new image by blurring the original image with a box blur of radius 1
			p = A2.blur(A2.orig, 1);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.BLUR_3:
			// create a new image by blurring the original image with a box blur of radius 3
			p = A2.blur(A2.orig, 3);
			A2.viewer.setComputed(p);
			
			break;
		case A2Viewer.BLUR_5:
			// create a new image by blurring the original image with a box blur of radius 5
			p = A2.blur(A2.orig, 5);
			A2.viewer.setComputed(p);
			
			break;
		default:
			// do nothing
		}
	}
	
	/**
	 * Starting point of the program. Students can comment/uncomment which image
	 * to use when testing their program.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		A2.viewer = new A2Viewer();
		A2.viewer.setVisible(true);
		
		
		URL img;
		// uncomment one of the next two lines to choose which test image to use (person or cat)
//		img = A2.class.getResource("redeye-400x300.jpg");  		
		img = A2.class.getResource("cat.jpg");
		
		try {
			URI uri = new URI(img.toString());
			A2.orig = new Picture(new File(uri.getPath()));
			A2.viewer.setOriginal(A2.orig);
		}
		catch (Exception x) {
			
		}
	}

}
