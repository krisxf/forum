package com.kris.acg.common;

import java.util.Arrays;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-02 15:03
 **/

public class FileFormatConstants {
    public static List<String> ALLOWED_IMAGE_FORMAT = Arrays.asList("jpeg", "jpg", "png");
    public static List<String> ALLOWED_CAROUSEL_FORMAT = Arrays.asList("jpeg", "jpg", "png");
    public static List<String> ALLOWED_COVER_IMAGE_FORMAT = Arrays.asList("jpeg", "jpg", "png");
    public static final String WEBP="webp";
    public static final String WEBP_CONTENT_TYPE="image/webp";

    public static final String JPG = "jpg";
    public static final String JPG_CONTENT_TYPE="image/jpg";
}
