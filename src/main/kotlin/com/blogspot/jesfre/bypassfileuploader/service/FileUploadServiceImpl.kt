package com.blogspot.jesfre.bypassfileuploader.service


import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

import java.io.IOException
import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


@Service
class FileUploadServiceImpl : FileUploadService {

    @Value("\${output.directory}")
    private lateinit var outputPathString:String

    override fun saveFile(file: MultipartFile, pathString:String) {
        println("Files will be stored to base directory $outputPathString")

        if(pathString.contains("../")) {
            throw IllegalArgumentException("Invalid path. Use normalized paths only.")
        }

        var pathToSave = Paths.get(outputPathString)
        try{
            if(pathString != "") {
                var path:Path = Paths.get(pathString)
                println("Input sub-oath $path")

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
