package com.blogspot.jesfre.bypassfileuploader.service


import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


@Service
class FileUploadServiceImpl : FileUploadService {

    override fun saveFile(file: MultipartFile, pathString:String) {
        var pathToSave = Paths.get("D:/dev/bypass-file-uploader/outputs/")
        try{
            if(pathString != "") {
                var path:Path = Paths.get(pathString)
                if(null != path.root) {
                   path =  path.subpath(0   , path.nameCount)
                }
                val tempPathToSave = pathToSave.resolve(path)
                if(!Files.isDirectory(tempPathToSave)) {
                    Files.createDirectories(tempPathToSave)
                }
                pathToSave = tempPathToSave
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {

            val byteObjects = arrayOfNulls<Byte>(file.bytes.size)

            var i = 0

            for (b in file.bytes) {
                byteObjects[i++] = b
            }


            val fileName = pathToSave.resolve(file.originalFilename)
            val myFile = File(fileName.toString())

            println("Writing filename:$fileName...")

            Files.write(myFile.toPath(), file.bytes, StandardOpenOption.CREATE_NEW, StandardOpenOption.APPEND)

            println("filename:$fileName written")

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
