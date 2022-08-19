package com.camera.rbpi.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Random;

/**
 * Default values:
 * private LibCameraCommand cameraLib = LibCameraCommand.COMMAND_LIB_CAMERA_STILL;
 * private ImageEncoding imageEncoding = ImageEncoding.JPG;
 * private WidthAndHeight widthAndHeight = WidthAndHeight.R_DEFAULT;
 * private int quality = 100;
 * private int timeout = 100;
 */
public class CameraCommand {

    private static final int DEFAULT_QUALITY = 100;
    private static final int DEFAULT_TIMEOUT = 100;
    public static final String BASH = "/usr/bin/bash";

    @NotNull(message = "CameraLibrary is mandatory")
    private CameraLibrary cameraLib = CameraLibrary.COMMAND_LIB_CAMERA_STILL;

    @NotNull(message = "ImageEncoding is mandatory")
    private ImageEncoding imageEncoding = ImageEncoding.JPG;

    @NotNull(message = "WidthAndHeight is mandatory")
    private WidthAndHeight widthAndHeight = WidthAndHeight.R_DEFAULT;

    //TODO -FIXME: Only relevant when saving JPEG image, for other ImageEncoding it's not relevant
    @Min(value = 10, message = "Quality minimum value should be 10")
    @Max(value = 100, message = "Quality maximum value should be 100")
    @NotNull(message = "Quality is mandatory, default value is 93")
    private Integer quality = DEFAULT_QUALITY;

    @Min(value =100, message = "Timeout minimum value should be 100")
    @Max(value = 10000, message = "Timeout maximum value should be 10000")
    @NotNull(message = "Timeout is mandatory, default value is 5000, i.e., 5 Seconds. Relevant for timelapse")
    private Integer timeout = DEFAULT_TIMEOUT;

    public CameraLibrary getCameraLib() {
        return cameraLib;
    }

    public ImageEncoding getImageEncoding() {
        return imageEncoding;
    }

    public WidthAndHeight getWidthAndHeight() {
        return widthAndHeight;
    }

    public int getQuality() {
        return this.quality;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setCameraLib(CameraLibrary cameraLib) {
        this.cameraLib = cameraLib;
    }

    public void setImageEncoding(ImageEncoding imageEncoding) {
        this.imageEncoding = imageEncoding;
    }

    public void setWidthAndHeight(WidthAndHeight widthAndHeight) {
        this.widthAndHeight = widthAndHeight;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @JsonIgnore
    private String imageFileOutputName;

    @JsonIgnore
    public String getImageFileOutputName() {
        return imageFileOutputName;
    }

    @JsonIgnore
    public void setImageFileOutputName(String imageFileOutputName) {
        this.imageFileOutputName = imageFileOutputName;
    }

    enum CameraLibrary {
        COMMAND_LIB_CAMERA_JPEG("libcamera-jpeg"),
        COMMAND_LIB_CAMERA_STILL("libcamera-still");

        private final String libCameraCommand;

        CameraLibrary(String libCameraCommand) {
            this.libCameraCommand = libCameraCommand;
        }

        public String getLibCameraCommand() {
            return libCameraCommand;
        }
    }

    enum ImageEncoding {
        JPG("-e jpg"),
        PNG("-e png"),
        RGB("-e rgb"),
        BMP("-e bmp"),
        YUV420("-e yuv420");

        private final String imageEncoding;

        ImageEncoding(String imageEncoding) {
            this.imageEncoding = imageEncoding;
        }

        public String getEncoding() {
            return imageEncoding;
        }
    }

    enum WidthAndHeight {
        R_DEFAULT("--width 0 --height 0"),
        R_640x480("--width 640 --height 480"),
        R_1296x972("--width 1296 --height 972"),
        R_1920x1080("--width 1920 --height 1080"),
        R_2592X1944("--width 2592 --height 1944");

        private final String widthAndHeight;

        WidthAndHeight(String widthAndHeight) {
            this.widthAndHeight = widthAndHeight;
        }

        public String getWidthAndHeight() {
            return widthAndHeight;
        }
    }

    public String generateCommand() {
        setImageFileOutputName(generateImageOutputName(getImageEncoding()));
        return this.getCameraLib().getLibCameraCommand() +
                " " + this.getImageEncoding().getEncoding() +
                " " + this.getWidthAndHeight().getWidthAndHeight() +
                " -o " + this.getImageFileOutputName() +
                " -q " + this.getQuality() +
                " -t " + this.getTimeout();
    }

    private String generateImageOutputName(ImageEncoding imageEncoding) {
        Random r = new Random(System.currentTimeMillis());
        int uniqueFileNumber = 10000 + r.nextInt(20000);
        return uniqueFileNumber + "." + imageEncoding.getEncoding().substring(3);
    }
}
