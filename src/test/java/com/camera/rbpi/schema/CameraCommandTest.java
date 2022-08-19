package com.camera.rbpi.schema;

import com.camera.rbpi.type.GoodTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class CameraCommandTest {

    private static CameraCommand cameraCommand;

    @BeforeEach
    void init() {
        cameraCommand = new CameraCommand();
    }

    @GoodTestCase
    @DisplayName("Default initialization")
    public void defaultInitialization() {
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-still -e jpg --width 0 --height 0 -o "+cameraCommand.getImageFileOutputName()+" -q 100 -t 100", command);
    }

    @GoodTestCase
    @DisplayName("Change default libcamera from still to jpeg")
    public void changeDefaultLibCamera() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e jpg --width 0 --height 0 -o "+cameraCommand.getImageFileOutputName()+" -q 100 -t 100", command);
    }

    @GoodTestCase
    @DisplayName("Change default quality")
    public void changeDefaultQuality() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(90);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e jpg --width 0 --height 0 -o "+cameraCommand.getImageFileOutputName()+" -q 90 -t 100", command);
    }

    @GoodTestCase
    @DisplayName("Change default timeout")
    public void changeDefaultTimeout() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(50);
        cameraCommand.setTimeout(5000);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e jpg --width 0 --height 0 -o "+cameraCommand.getImageFileOutputName()+" -q 50 -t 5000", command);
    }

    @GoodTestCase
    @DisplayName("Change default image encoding to BMP")
    public void changeDefaultImageEncodingBMP() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(60);
        cameraCommand.setTimeout(6000);
        cameraCommand.setImageEncoding(CameraCommand.ImageEncoding.BMP);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e bmp --width 0 --height 0 -o "+cameraCommand.getImageFileOutputName()+" -q 60 -t 6000", command);
    }

    @GoodTestCase
    @DisplayName("Change default image encoding to PNG")
    public void changeDefaultImageEncodingPNG() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(70);
        cameraCommand.setTimeout(2000);
        cameraCommand.setImageEncoding(CameraCommand.ImageEncoding.PNG);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e png --width 0 --height 0 -o "+cameraCommand.getImageFileOutputName()+" -q 70 -t 2000", command);
    }

    @GoodTestCase
    @DisplayName("Change default image encoding to RGB")
    public void changeDefaultImageEncodingRGB() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(55);
        cameraCommand.setTimeout(110);
        cameraCommand.setImageEncoding(CameraCommand.ImageEncoding.RGB);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e rgb --width 0 --height 0 -o "+cameraCommand.getImageFileOutputName()+" -q 55 -t 110", command);
    }

    @GoodTestCase
    @DisplayName("Change default image encoding to YUV420")
    public void changeDefaultImageEncodingYUV420() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(25);
        cameraCommand.setTimeout(4000);
        cameraCommand.setImageEncoding(CameraCommand.ImageEncoding.YUV420);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e yuv420 --width 0 --height 0 -o "+cameraCommand.getImageFileOutputName()+" -q 25 -t 4000", command);
    }

    @GoodTestCase
    @DisplayName("Change default image width and height to R_640x480")
    public void changeDefaultWidthAndHeightR_640x480() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(99);
        cameraCommand.setTimeout(101);
        cameraCommand.setImageEncoding(CameraCommand.ImageEncoding.PNG);
        cameraCommand.setWidthAndHeight(CameraCommand.WidthAndHeight.R_640x480);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e png --width 640 --height 480 -o "+cameraCommand.getImageFileOutputName()+" -q 99 -t 101", command);
    }

    @GoodTestCase
    @DisplayName("Change default image width and height to R_1296x972")
    public void changeDefaultWidthAndHeightR_1296x972() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(10);
        cameraCommand.setTimeout(100);
        cameraCommand.setImageEncoding(CameraCommand.ImageEncoding.RGB);
        cameraCommand.setWidthAndHeight(CameraCommand.WidthAndHeight.R_1296x972);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e rgb --width 1296 --height 972 -o "+cameraCommand.getImageFileOutputName()+" -q 10 -t 100", command);
    }

    @GoodTestCase
    @DisplayName("Change default image width and height to R_1920x1080")
    public void changeDefaultWidthAndHeightR_1920x1080() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(99);
        cameraCommand.setTimeout(9999);
        cameraCommand.setImageEncoding(CameraCommand.ImageEncoding.BMP);
        cameraCommand.setWidthAndHeight(CameraCommand.WidthAndHeight.R_1920x1080);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e bmp --width 1920 --height 1080 -o "+cameraCommand.getImageFileOutputName()+" -q 99 -t 9999", command);
    }

    @GoodTestCase
    @DisplayName("Change default image width and height to R_2592X1944")
    public void changeDefaultWidthAndHeightR_2592X1944() {
        cameraCommand.setCameraLib(CameraCommand.CameraLibrary.COMMAND_LIB_CAMERA_JPEG);
        cameraCommand.setQuality(100);
        cameraCommand.setTimeout(10000);
        cameraCommand.setImageEncoding(CameraCommand.ImageEncoding.JPG);
        cameraCommand.setWidthAndHeight(CameraCommand.WidthAndHeight.R_2592X1944);
        String command = cameraCommand.generateCommand();
        assertEquals("libcamera-jpeg -e jpg --width 2592 --height 1944 -o "+cameraCommand.getImageFileOutputName()+" -q 100 -t 10000", command);
    }

}
