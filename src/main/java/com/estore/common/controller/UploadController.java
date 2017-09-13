/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.common.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.estore.base.ResponseResult;
import com.estore.utils.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@RestController("uploadController")
@RequestMapping(path = "/upload")
@Slf4j
public class UploadController {

    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping(path = "/image", method = RequestMethod.POST)
    public ResponseResult uploadImage(MultipartHttpServletRequest request) {
        try {
            File dir = new File(Constants.PRODUCT_IMAGE_DIR_PATH);
            if (!dir.exists() || !dir.isDirectory()) {
                FileUtils.forceMkdir(dir);
            }

            List<String> fileNames = new ArrayList<>();
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                String imageFile = itr.next();
                MultipartFile file = request.getFile(imageFile);
                if (null == file || file.isEmpty()) {
                    continue;
                }
                if (file.getSize() > Constants.MAX_UPLOAD_IMAGE_SIZE) {
                    continue;
                }
                String fileName = file.getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                String newFileName = UUID.randomUUID() + suffix;
                fileNames.add(newFileName);
                file.transferTo(new File(Constants.PRODUCT_IMAGE_DIR_PATH + newFileName));
            }

            return new ResponseResult(true, fileNames);
        } catch (Exception ex) {
            log.error("Image Upload failed.", ex);
            return new ResponseResult(false, "None Images was uploaded.");
        }
    }

    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping(path = "/video", method = RequestMethod.POST)
    public ResponseResult uploadVideo(MultipartHttpServletRequest request) {
        try {
            File dir = new File(Constants.MICRO_CLASS_VIDEO_DIR_PATH);
            if (!dir.exists() || !dir.isDirectory()) {
                FileUtils.forceMkdir(dir);
            }

            List<String> filenames = new ArrayList<>();
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                if (null == file || file.isEmpty()) {
                    continue;
                }
                if (file.getSize() > Constants.MAX_UPLOAD_VIDEO_SIZE) {
                    continue;
                }
                String filename = file.getOriginalFilename();
                String suffix = filename.substring(filename.lastIndexOf("."));
                String newfilename = UUID.randomUUID() + suffix;
                filenames.add(newfilename);
                file.transferTo(new File(Constants.MICRO_CLASS_VIDEO_DIR_PATH + newfilename));
            }

            return new ResponseResult(true, filenames);
        } catch (Exception ex) {
            log.error("Video Upload failed. Caused by ", ex);
            return new ResponseResult(false, new ArrayList<String>());
        }
    }
}
