package com.raym.flitfilemanager.models;

import java.io.File;

public class Method {
    public static void load_Directory_Files(File directory){
        File[] fileList = directory.listFiles();

        if (fileList != null && fileList.length > 0){
            for (int i = 0; i< fileList.length; i++){
                if (fileList[i].isDirectory()){
                    Constant.allFolderList.add(fileList[i]);
                    load_Directory_Files(fileList[i]);

                }else{
                    //load files
                    String name = fileList[i].getName().toLowerCase();
                    for (String videoExtension : Constant.videoExtensions) {
                        if (name.endsWith(videoExtension)){
                            Constant.allVideoList.add(fileList[i]);
                            break;
                        }
                    }
                    for (String audioExtension :
                            Constant.audioExtensions) {
                        if (name.endsWith(audioExtension)){
                            Constant.allAudioList.add(fileList[i]);
                            break;
                        }
                    }
                    for (String imageExtension :
                            Constant.imageExtensions) {
                        if (name.endsWith(imageExtension)){
                            Constant.allImageList.add(fileList[i]);
                            break;
                        }
                    }
                }
            }
        }
    }
}
