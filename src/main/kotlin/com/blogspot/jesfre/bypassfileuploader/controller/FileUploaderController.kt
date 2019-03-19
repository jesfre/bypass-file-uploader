package com.blogspot.jesfre.bypassfileuploader.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
class FileUploaderController {

    @GetMapping("uploadfile")
    fun showUploadForm(model: Model): String {
        return "fileuploadform"
    }

    @PostMapping("uploadfile")
    fun handleImagePost(@RequestParam("file") file: MultipartFile): String {
        println("Entering handleImagePost method...")
        // TODO call to file service

        println("Returning to uploadfile...")
        return "redirect:/uploadfile"
    }
}