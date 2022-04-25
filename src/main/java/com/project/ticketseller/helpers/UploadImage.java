package com.project.ticketseller.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadImage {

  public static void upload(String destFolder, Long id, MultipartFile cover) throws IOException {
    Path path = Paths.get(destFolder + "/" + id + ".jpg");
    Files.copy(cover.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
  }
}
