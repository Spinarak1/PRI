package com.software.rateit.services;

import com.software.rateit.Entity.File;
import com.software.rateit.FileStorageException;
import com.software.rateit.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileStorageService {
    @Autowired
    private FileRepository fileRepository;

    public File storeFile(MultipartFile file){
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try{
        if(fileName.contains("..")){
            throw new FileStorageException("Sorry! Filename contains invalid path sequence" + fileName);
        }
        File dbFile = new File(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(dbFile);
    }catch(IOException ex){
        throw new FileStorageException("Could not store this File" + fileName + ". Please try again", ex);
        }
    }
    public File getFile(String fileId) throws Exception

    {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
