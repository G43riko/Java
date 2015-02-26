package webCam;

import static com.googlecode.javacv.cpp.opencv_core.cvFlip;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class MainWebCam implements Runnable{
    IplImage image;
    CanvasFrame canvas = new CanvasFrame("Web Cam");
    
    public static void main(String[] args){
    	MainWebCam webCam = new MainWebCam();
    	webCam.run();
    }
    
    public void GrabberShow(){
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public void run() {
        FrameGrabber grabber = new VideoInputFrameGrabber(0); 
        int i=0;
        try {
            grabber.start();
            IplImage img;
            while (true) {
                img = grabber.grab();
                if (img != null) {
                    cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
                    cvSaveImage((i++)+"-capture.jpg", img);
                    // show image on window
                    canvas.showImage(img);
                }
            }
        } catch (Exception e) {}
    }
}
