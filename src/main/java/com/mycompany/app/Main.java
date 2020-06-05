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
import org.opencv.imgproc.Imgproc;

import static com.mycompany.app.constant.Constant.PATH_TO_IMAGES;
import static com.mycompany.app.constant.Constant.SRC_IMG_NAME;
import static org.opencv.imgcodecs.Imgcodecs.imread;

/**
 * The type Main.
 */
public class Main {
    //private static Logger logger = Logger.getLogger(Main.class);
    private final static int[] kernelArray = {3, 5, 7, 9, 13, 15};
    private final static OpenCvApi openCvApi = new OpenCvApiImp();
    private final static String dirPath = "images/";
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

        Mat var = getMatImage();

        //showImage(var);

        taskTwo();
    }

    public static Mat loadImage(String path) {
        return imread(path);
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

        Imgcodecs.imwrite("resources/img/image.jpg",m);
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
////        String imgFile = "images/image.jpg";
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

    private static void taskTwo() {
        Mat matImage = getMatImage();
        Mat modifiedImage;
        for (int numChn = 0; numChn < 3; numChn++) {
            modifiedImage = openCvApi.setChannelToZero(matImage, numChn);
            openCvApi.showImage(modifiedImage);
            Imgcodecs.imwrite(dirPath + "Channel_" + numChn + "_" + SRC_IMG_NAME, modifiedImage);
        }

    }

    private static void taskThree() {
        Mat matImage = getMatImage();

        for (int kernelSize : kernelArray) {
            //3.1
            openCvApi.smoothingFilters(matImage, kernelSize);
            //3.2
            openCvApi.morfologyTest(matImage, kernelSize, Imgproc.MORPH_RECT, Constant.MORPH_RECT_PREFIX);
            openCvApi.morfologyTest(matImage, kernelSize, Imgproc.MORPH_ELLIPSE, Constant.MORPH_ELLIPSE_PREFIX);
            openCvApi.morfologyTest(matImage, kernelSize, Imgproc.MORPH_GRADIENT, Constant.MORPH_GRADIENT_PREFIX);
            openCvApi.morfologyTest(matImage, kernelSize, Imgproc.MORPH_BLACKHAT, Constant.MORPH_BLACKHAT_PREFIX);
        }
    }

    private static void taskFour() {
        Mat matImage = getMatImage();
        Mat imageCopy = matImage.clone();

        //4.1
        openCvApi.testFillFlood(imageCopy, 0, 0, 100, 255, 0, 0);

        //4.2
        Mat raisedImage = openCvApi.raisePyramid(matImage, 200);
        openCvApi.showImage(raisedImage);
        Imgcodecs.imwrite(dirPath + "RaisePyramidUp_" + SRC_IMG_NAME, matImage);

        Mat reducedImage = openCvApi.reducePyramid(matImage, 200);
        openCvApi.showImage(reducedImage);
        Imgcodecs.imwrite(dirPath + "PyramidDown_" + SRC_IMG_NAME, matImage);

        Core.subtract(matImage, imageCopy, imageCopy);
        openCvApi.showImage(imageCopy);
        Imgcodecs.imwrite(dirPath + "Subtract_" + SRC_IMG_NAME, matImage);

        //4.3
        openCvApi.showImage(matImage);
        int width = 209;
        int height = 66;
    }

    private static Mat getMatImage() {
        String dirPath = "images/";
        Mat matImage = imread(dirPath + SRC_IMG_NAME);
        if (matImage.empty()) {
            System.out.println("Error: Image " + SRC_IMG_NAME + " not found in path " + dirPath);
            throw new RuntimeException("Error: Image " + SRC_IMG_NAME + " not found in path " + dirPath);
        }
        return matImage;
    }

}
