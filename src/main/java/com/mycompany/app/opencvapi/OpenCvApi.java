package com.mycompany.app.opencvapi;

import org.opencv.core.Mat;

/**
 * The interface Open cv api.
 */
public interface OpenCvApi {

    /**
     * Show image.
     *
     * @param matImage the m
     */
    void showImage(Mat matImage);

    /**
     * Sets channel to zero.
     *
     * @param matImage the mat image
     * @param chnNum   the channel number
     * @return the channel to zero
     */
    Mat setChannelToZero(Mat matImage, int chnNum);

    /**
     * Set Smoothing filters.
     *
     * @param matImage   the mat image
     * @param kernelSize the kernel size
     */
    void smoothingFilters(Mat matImage, int kernelSize);

    /**
     * Set Morfology changes.
     *
     * @param matImage   the mat image
     * @param kernelSize the kernel size
     * @param morphType  the morph type
     * @param prefix     the prefix
     */
    void morfologyTest(Mat matImage, int kernelSize, int morphType, String prefix);

    /**
     * Test fill flood.
     *
     * @param matImage the mat image
     * @param x        the x value used in seedPoint
     * @param y        the y value used in seedPoint
     * @param initVal  the init value
     * @param red      the red value used in newVal
     * @param green    the green value used in newVal
     * @param blue     the blue value used in newVal
     */
    void testFillFlood(Mat matImage, int x, int y, Integer initVal, Integer red, Integer green, Integer blue);

    /**
     * Raise the mat image.
     *
     * @param matImage   the mat image
     * @param iterations the iterations
     * @return the increased image
     */
    Mat raisePyramid(Mat matImage, int iterations);

    /**
     * Reduce the mat image mat.
     *
     * @param matImage   the mat image
     * @param iterations the iterations
     * @return the reduced mat image
     */
    Mat reducePyramid(Mat matImage, int iterations);

    /**
     * Gets the total rectangles in the image
     * of the size entered by parameters.
     *
     * @param matImage the mat image
     * @param width    the width of the rectangle
     * @param height   the height of the rectangle
     * @return the number of rectangles in image
     */
    int getTotalRectInImage(Mat matImage, int width, int height);
}
