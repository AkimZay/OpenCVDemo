package com.mycompany.app;

import com.mycompany.app.config.Config;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.opencvapi.OpenCvApi;
import com.mycompany.app.opencvapi.OpenCvApiImp;
import com.mycompany.app.utils.OperatingSystemUtils;
import com.mycompany.app.utils.ParseUtils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.apache.log4j.Logger;

/**
 * The type Main.
 */
public class Main {
    //private static Logger logger = Logger.getLogger(Main.class);
    private final static int[] kernelArray = {3, 5, 7, 9, 13, 15};
    private final static OpenCvApi openCvApi = new OpenCvApiImp();
    //private final static String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        //OperatingSystemUtils.loadConfig();
        System.load("/home/osboxes/OpenCV-android-sdk/sdk/OpenCVDemo/src/main/resources/libopencv_java3410.so");

//        if (args.length > 0) {
//            switch (ParseUtils.getIntegerValue(args[0])) {
//                case (1):
//                    logger.info("Welcome to OpenCV" + Core.VERSION);
//                    break;
//                case (2):
//                    logger.info("option 2");
//                    break;
//                case (3):
//                    logger.info("option 3");
//                    break;
//                case (4):
//                    logger.info("option 4");
//                    break;
//                default:
//                    logger.info("Option not found: you need to write 1, 2, 3 or 4");
//            }
//
//        } else {
//            logger.info("Please write an argument with the number of the laboratory work");
//        }


        System.out.println("Welcome to OpenCV " + Core.VERSION);
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: " + m);
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());

        Mat var = loadImage("images/Asking.jpg");

        showImage(var);
    }

    public static Mat loadImage(String path) {
        return Imgcodecs.imread(path);
    }

    public static void showImage(Mat m) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b);
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        ImageIcon icon = new ImageIcon(image);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(image.getWidth(null) + 50, image.getHeight(null) + 50);
        frame.setResizable(false);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Created frame with image");

        Imgcodecs.imwrite("resources/img/Asking.jpg",m);
    }


//    static {
//        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
//    }
//    public static void main(String[] args) {
//        //nu.pattern.OpenCV.loadShared();
////        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        //loadLibraries();
//
//        System.out.println("TEST");
//
//        System.out.println("Create a 3x3 identity matrix...");
//        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
//        System.out.println("mat = " + mat.dump());
//
////        System.out.println("Welcome to OpenCV " + Core.VERSION);
////
////        String imgFile = "images/Asking.jpg";
////        Mat src = Imgcodecs.imread(imgFile);
////
////        String xmlFile = "xml/lbpcascade_frontalface.xml";
////        CascadeClassifier cc = new CascadeClassifier(xmlFile);
////
////        MatOfRect faceDetection = new MatOfRect();
////        cc.detectMultiScale(src, faceDetection);
////        System.out.println(String.format("Detected faces: %d", faceDetection.toArray().length));
////
////        for(Rect rect: faceDetection.toArray()) {
////            Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
////        }
////
////        Imgcodecs.imwrite("images/Asking_out.png", src);
////        System.out.println("Image Detection Finished");
//    }
//
//    private static void loadLibraries() {
//
//        try {
//            InputStream in = null;
//            File fileOut = null;
//            String osName = System.getProperty("os.name");
////            String opencvpath = System.getProperty("user.dir");
//            String opencvpath = "/home/osboxes/OpenCV_Build/opencv/build/bin/opencv-3410.jar";
////            if (osName.startsWith("Windows")) {
////                int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
////                if (bitness == 32) {
////                    opencvpath = opencvpath + "\\x86\\";
////                } else if (bitness == 64) {
////                    opencvpath = opencvpath + "\\x64\\";
////                } else {
////                    opencvpath = opencvpath + "\\x86\\";
////                }
////            } else if (osName.equals("Mac OS X")) {
////                opencvpath = opencvpath + "Your path to .dylib";
////            }
//            System.out.println(opencvpath);
////        System.out.println("Core.NATIVE_LIBRARY_NAME = " + Core.NATIVE_LIBRARY_NAME);
//            System.out.println("Core.NATIVE_LIBRARY_NAME = " + "opencv_java411.dll");
////        System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
//            System.load(opencvpath);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load opencv native library", e);
//        }
//    }
}
