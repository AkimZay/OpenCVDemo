package com.mycompany.app.opencvapi;

import org.apache.log4j.Logger;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.*;

import static org.opencv.imgproc.Imgproc.MORPH_RECT;

/**
 * Implements Open cv api imp.
 */
public class OpenCvApiImp implements OpenCvApi {

    private final static Logger logger = Logger.getLogger(OpenCvApiImp.class);
    private final static String dirPath = "";

    public void showImage(Mat matImage) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matImage.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matImage.channels() * matImage.cols() * matImage.rows();
        byte[] b = new byte[bufferSize];
        matImage.get(0, 0, b);
        BufferedImage image = new BufferedImage(matImage.cols(), matImage.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        ImageIcon icon = new ImageIcon(image);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(image.getWidth() + 50, image.getHeight() + 50);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Mat setChannelToZero(Mat matImage, int chnNum) {
        Mat clone = matImage.clone();
        for (int i = 0; i < matImage.cols(); i++) {
            for (int j = 0; j < matImage.rows(); j++) {
                double[] data = matImage.get(j, i);
                data[chnNum] = 0;
                clone.put(j, i, data);
            }
        }
        return clone;

    }

    public void smoothingFilters(Mat matImage, int kernelSize) {
        logger.debug("SmoothingFilters: kernelSize " + kernelSize);

        Mat dst = matImage.clone();
        Size kSize = new Size(kernelSize, kernelSize);

        Imgproc.blur(matImage, dst, kSize);
        Imgcodecs.imwrite(dirPath + "Blur_[" + kernelSize + "]_" + Constant.SRC_IMG_NAME, dst);
        showImage(dst);

        dst = matImage.clone();
        Imgproc.GaussianBlur(matImage, dst, kSize, 0);
        Imgcodecs.imwrite(dirPath + "GaussianBlur_[" + kernelSize + "]_" + Constant.SRC_IMG_NAME, dst);
        showImage(dst);

        dst = matImage.clone();
        Imgproc.medianBlur(matImage, dst, kernelSize);
        Imgcodecs.imwrite(dirPath + "EdianBlur_[" + kernelSize + "]_" + Constant.SRC_IMG_NAME, dst);
        showImage(dst);

        dst = matImage.clone();
        Imgproc.bilateralFilter(matImage, dst, 15, 80, 80);
        Imgcodecs.imwrite(dirPath + "BilateralFilter_[" + kernelSize + "]_" + Constant.SRC_IMG_NAME, dst);
        showImage(dst);

    }

    public void morfologyTest(Mat matImage, int kernelSize, int morphType, String prefix) {

        logger.debug("MorfologyTest: kernelSize " + kernelSize + " morphType " + morphType);

        Mat dst = matImage.clone();

        Mat kernel = Imgproc.getStructuringElement(MORPH_RECT, new Size(kernelSize, kernelSize));

        Imgproc.erode(matImage, dst, kernel);
        Imgcodecs.imwrite(dirPath + "Erode_" + "[" + kernelSize + "]_" + Constant.SRC_IMG_NAME, dst);
        showImage(dst);

        dst = matImage.clone();
        Imgproc.dilate(matImage, dst, kernel);
        Imgcodecs.imwrite(dirPath + "Dilate_" + "[" + kernelSize + "]_" + Constant.SRC_IMG_NAME, dst);
        showImage(dst);

        dst = matImage.clone();
        Imgproc.morphologyEx(matImage, dst, morphType, kernel);
        Imgcodecs.imwrite(dirPath + prefix + "_[" + kernelSize + "]_" + Constant.SRC_IMG_NAME, dst);
        showImage(dst);


    }

    public void testFillFlood(Mat matImage, int x, int y, Integer initVal, Integer red, Integer green, Integer blue) {
//
//        logger.debug("TestFillFlood: x " + x + " y " + y + " initVal " + initVal + " red " + red + " green " + green + " blue " + blue);
//
//        Map<String, Integer> parameters = getValidatedParameters(matImage, x, y, initVal, red, green, blue);
//        Point seedPoint = new Point(parameters.get(X), parameters.get(Y));
//        Scalar newVal = new Scalar(parameters.get(RED), parameters.get(GREEN), parameters.get(BLUE));
//        Scalar loDiff = new Scalar(parameters.get(INIT_VAL), parameters.get(INIT_VAL), parameters.get(INIT_VAL));
//        Scalar upDiff = new Scalar(parameters.get(INIT_VAL), parameters.get(INIT_VAL), parameters.get(INIT_VAL));
//        Mat mask = new Mat();
//        Imgproc.floodFill(matImage, mask, seedPoint, newVal, new Rect(), loDiff, upDiff, Imgproc.FLOODFILL_FIXED_RANGE);
//        showImage(matImage);
//        Imgcodecs.imwrite(dirPath + "FillFlood_" + SRC_IMG_NAME, matImage);
    }

    public Mat raisePyramid(Mat matImage, int iterations) {
        Mat clone = new Mat();

        for (int i = 0; i < iterations; i++) {
            Imgproc.pyrUp(matImage, clone);
        }

        return clone;
    }

    public Mat reducePyramid(Mat matImage, int iterations) {
        Mat clone = new Mat();

        for (int i = 0; i < iterations; i++) {
            Imgproc.pyrDown(matImage, clone);
        }

        return clone;

    }

    public int getTotalRectInImage(Mat matImage, int width, int height) {
        logger.debug("GetTotalRectInImage: width " + width + " height " + height);
        Mat srcImage = matImage.clone();
        int totalRect = 0;

        // 1
        Mat grayImage = new Mat();
        Imgproc.cvtColor(srcImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        // 2
        Mat denoisingImage = new Mat();
        Photo.fastNlMeansDenoising(grayImage, denoisingImage);

        // 3
        Mat histogramEqualizationImage = new Mat();
        Imgproc.equalizeHist(denoisingImage, histogramEqualizationImage);
        // 4
        Mat morphologicalOpeningImage = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        Imgproc.morphologyEx(histogramEqualizationImage, morphologicalOpeningImage,
                Imgproc.MORPH_RECT, kernel);
        // 5
        Mat subtractImage = new Mat();
        Core.subtract(histogramEqualizationImage, morphologicalOpeningImage, subtractImage);
        // 6
        Mat thresholdImage = new Mat();
        double threshold = Imgproc.threshold(subtractImage, thresholdImage, 50, 255,
                Imgproc.THRESH_OTSU);
        thresholdImage.convertTo(thresholdImage, CvType.CV_16SC1);
        // 7
        Mat edgeImage = new Mat();
        thresholdImage.convertTo(thresholdImage, CvType.CV_8U);

        Imgproc.Canny(thresholdImage, edgeImage, threshold, threshold * 3, 3, true);
        // 8
        Mat dilatedImage = new Mat();
        Imgproc.dilate(thresholdImage, dilatedImage, kernel);
        // 9
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(dilatedImage, contours, new Mat(), Imgproc.RETR_TREE,
                Imgproc.CHAIN_APPROX_SIMPLE);
        contours.sort(Collections.reverseOrder(Comparator.comparing(Imgproc::contourArea)));

        for (MatOfPoint contour : contours.subList(0, 10)) {
            System.out.println(Imgproc.contourArea(contour));
            MatOfPoint2f point2f = new MatOfPoint2f();
            MatOfPoint2f approxContour2f = new MatOfPoint2f();
            MatOfPoint approxContour = new MatOfPoint();
            contour.convertTo(point2f, CvType.CV_32FC2);
            double arcLength = Imgproc.arcLength(point2f, true);
            Imgproc.approxPolyDP(point2f, approxContour2f, 0.03 * arcLength, true);
            approxContour2f.convertTo(approxContour, CvType.CV_32S);
            Rect rect = Imgproc.boundingRect(approxContour);
            if (approxContour.total() == 4 && rect.width == width && rect.height == height)
                totalRect++;
        }
        return totalRect;
    }


    private Map<String, Integer> getValidatedParameters(Mat matImage, int x, int y, Integer initVal, Integer red, Integer green, Integer blue) {

        initVal = generateValueIfNull(initVal);
        blue = generateValueIfNull(blue);
        red = generateValueIfNull(red);
        green = generateValueIfNull(green);


        if (x > matImage.width() || y > matImage.height()) {
            logger.error("TestFillFlood: Argument Error: coordinate X,Y out of range");
            throw new IllegalArgumentException("TestFillFlood: Argument Error: coordinate X,Y out of range");
        }

        if (validateColor(initVal, red, green, blue)){
            logger.error("TestFillFlood: Argument Error: not valid color parameters");
            throw new IllegalArgumentException("TestFillFlood: Argument Error: not valid color parameters");
        }

        Map<String, Integer> map = new HashMap<>();
//        map.put(X, x);
//        map.put(Y, y);
//        map.put(INIT_VAL, initVal);
//        map.put(BLUE, blue);
//        map.put(RED, red);
//        map.put(GREEN, green);

        return map;
    }

    private boolean validateColor(Integer initVal, Integer red, Integer green, Integer blue) {
        return red > 255 || red < 0 ||
            green > 255 || green < 0 ||
            blue > 255 || blue < 0 ||
            initVal > 255 || initVal < 0;
    }

    private Integer generateValueIfNull(Integer integerValue) {
        return integerValue != null ? integerValue : new Random().nextInt(256);
    }
}

