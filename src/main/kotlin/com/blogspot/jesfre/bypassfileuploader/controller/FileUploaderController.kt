package com.blogspot.jesfre.bypassfileuploader.controller

import com.blogspot.jesfre.bypassfileuploader.service.FileUploadService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller("/")
class FileUploaderController {

    val fileUploadService: FileUploadService

    constructor(fileUploadService: FileUploadService) {
        this.fileUploadService = fileUploadService
    }

    @GetMapping
    fun showUploadForm(model: Model): String {
        return "fileuploadform"
    }

    @PostMapping
    fun handleImagePost(@RequestParam("file") fileArray: Array<MultipartFile>): String {
        println("Entering handleImagePost method...")
        for(file in fileArray)
            fileUploadService.saveFile(file)

        println("Returning to uploadfile...")
        return "redirect:/uploadfile"
    }
}