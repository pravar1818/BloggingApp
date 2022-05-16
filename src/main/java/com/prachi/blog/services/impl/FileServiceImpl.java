package com.prachi.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prachi.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		//Get File Name
		String name = file.getOriginalFilename();
				
		//Generate random file name
		String randomID = UUID.randomUUID().toString();
		String newFileName = randomID.concat(name.substring(name.lastIndexOf(".")));
				
		//Make FullPath 
		String filePath = path + File.separator + newFileName;
				
		//Create Folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
				
		//Copy the file
		Files.copy(file.getInputStream(), Paths.get(filePath));
						
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}
