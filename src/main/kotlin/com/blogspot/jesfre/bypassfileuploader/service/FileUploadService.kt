package com.blogspot.jesfre.bypassfileuploader.service

import org.springframework.web.multipart.MultipartFile

interface FileUploadService {

    fun saveFile(file: MultipartFile, pathString:String="")
}
