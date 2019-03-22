package com.blogspot.jesfre.bypassfileuploader.service


import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardOpenOption


@Service
class FileUploadServiceImpl : FileUploadService {

    override fun saveFile(file: MultipartFile) {

        try {

            val byteObjects = arrayOfNulls<Byte>(file.bytes.size)

            var i = 0

            for (b in file.bytes) {
                byteObjects[i++] = b
            }


            val fileName = "D:/dev/bypass-file-uploader/outputs/" + file.originalFilename
            val myFile = File(fileName)

            println("Writing filename:$fileName...")

            Files.write(myFile.toPath(), file.bytes, StandardOpenOption.CREATE_NEW, StandardOpenOption.APPEND)

            println("filename:$fileName written")

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
