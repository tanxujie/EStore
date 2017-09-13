/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.common.controller;

import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estore.utils.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@RestController("downloadController")
@RequestMapping(path = "/download")
@Slf4j
public class DownloadController {

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * Download Video for ios
     * 
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/image/{filename:.+}", produces = "image/*")
    @CrossOrigin
    public Resource downloadImage(@PathVariable("filename") String filename, HttpServletRequest request) {
        try {
            return this.resourceLoader.getResource(
                    "file:" + Paths.get(Constants.PRODUCT_IMAGE_DIR_PATH, filename).toString());
        } catch (Exception ex) {
            log.error("Download Image failed. "+ Paths.get(Constants.PRODUCT_IMAGE_DIR_PATH, filename).toString() +"Caused by ", ex);
            return null;
        }
    }

    /**
     * Download Video for ios
     * 
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/video/{filename:.+}", produces = "video/*")
    @CrossOrigin
    public ResourceRegion downloadVideo(@PathVariable("filename") String filename, HttpServletRequest request) {
        try {
            Resource resource = this.resourceLoader.getResource(
                    "file:" + Paths.get(Constants.MICRO_CLASS_VIDEO_DIR_PATH, filename).toString());
            if (null == resource || !resource.exists() || resource.contentLength() == 0) {
                return new ResourceRegion(null, 0, 0);
            }

            long contentLength = resource.contentLength();
            long start = 0;
            long end = contentLength - 1;

            // request video range
            String range = request.getHeader(HttpHeaders.RANGE);
            if (StringUtils.isNotBlank(range)) {
                range = range.substring("bytes=".length());
                String[] rangeTokens = StringUtils.split(range, "-");
                if (ArrayUtils.isNotEmpty(rangeTokens)) {
                    // calculate start index
                    if (StringUtils.isNotBlank(rangeTokens[0])) {
                        start = NumberUtils.toLong(rangeTokens[0], 0);
                    }
                    // calculate end index
                    if (rangeTokens.length == 2 
                            && StringUtils.isNotBlank(rangeTokens[1])) {
                        end = NumberUtils.toLong(rangeTokens[1], end);
                    }
                }
            }

            // get current request video resource region
            ResourceRegion resourceRegion = new ResourceRegion(
                    this.resourceLoader.getResource("file:" + Paths.get(Constants.MICRO_CLASS_VIDEO_DIR_PATH, filename).toString()), 
                    start,
                    (end - start + 1));

            return resourceRegion;
        } catch (Exception ex) {
            log.error("Download Video failed. Caused by ", ex);
            return new ResourceRegion(null, 0, 0);
        }
    }
}