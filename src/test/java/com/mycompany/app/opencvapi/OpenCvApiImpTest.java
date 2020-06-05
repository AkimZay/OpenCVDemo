package com.mycompany.app.opencvapi;

import com.mycompany.app.config.Config;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.utils.OperatingSystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgcodecs.Imgcodecs.imread;

public class OpenCvApiImpTest {

    @Before
    public void setUp() {
        System.setProperty("config.properties", "src/test/resources/config.properties");
    }

    @After
    public void tearDown() {
        System.clearProperty("config.properties");
    }



    @Test
    public void showImage() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.showImage(matImage);
    }

    @Test
    public void setChannelToZero() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.setChannelToZero(matImage, 1);
    }

    @Test
    public void smoothingFilters() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.smoothingFilters(matImage, 5);
    }

    @Test
    public void morfologyTest() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.morfologyTest(matImage, 5, Imgproc.MORPH_RECT, Constant.MORPH_RECT_PREFIX);
    }

    @Test
    public void testFillFlood() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.testFillFlood(matImage, 0, 0, null, null, null, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFillFloodInvalidCoordinat() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.testFillFlood(matImage, 500, 500, null, null, null, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFillFloodInvalidColor() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.testFillFlood(matImage, 2, 5, 468, 643, 342, 342);

    }

    @Test
    public void raisePyramid() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.raisePyramid(matImage, 200);
    }

    @Test
    public void reducePyramid() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.reducePyramid(matImage, 200);
    }

    @Test
    public void getTotalRectInImage() throws Exception {
        OperatingSystemUtils.loadConfig();
        OpenCvApi openCvApi = new OpenCvApiImp();
        String dirPath = Config.getProp(Constant.PATH_TO_IMAGES);
        Mat matImage = imread(dirPath + Constant.SRC_IMG_NAME);

        openCvApi.getTotalRectInImage(matImage, 209, 66);
    }
}
