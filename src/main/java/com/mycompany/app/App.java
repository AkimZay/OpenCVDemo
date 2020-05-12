package com.mycompany.app;

import org.opencv.core.*;

import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.InputStream;


public class App {


    public static void main(String[] args) {
        System.load("/home/osboxes/OpenCV_Build/opencv/build/lib/libopencv_java3410.so");


        System.out.println("Welcome to OpenCV " + Core.VERSION);
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: " + m);
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());
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
