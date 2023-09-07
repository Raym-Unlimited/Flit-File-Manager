package com.raym.flitfilemanager.models;

import java.io.File;
import java.util.ArrayList;

public class Constant {
    public static String[] videoExtensions = {".mp4", ".mkv", ".avi", ".mpeg", ".flv",
            ".mov"};
    public static String[] audioExtensions = {".mp3", ".aac", ".wav"};
    public static String[] imageExtensions = {".png", ".jpeg", ".jpg", ".tiff", ".jfif", ".Webp"
            , ".jpeg 2000", ".gif", ".bmp"};

    public static ArrayList<File> allVideoList = new ArrayList<>();
    public static ArrayList<File> allAudioList = new ArrayList<>();
    public static ArrayList<File> allImageList = new ArrayList<>();
    public static ArrayList<File> allFolderList = new ArrayList<>();
}
