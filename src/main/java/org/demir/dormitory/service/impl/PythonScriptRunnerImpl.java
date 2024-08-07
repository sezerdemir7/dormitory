package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.StudentResponse;
import org.demir.dormitory.service.PythonScriptRunner;
import org.demir.dormitory.service.StudentService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;

@Service
public class PythonScriptRunnerImpl implements PythonScriptRunner{

    private final StudentService studentService;

    public PythonScriptRunnerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void runPythonScript(String scriptPath, String... args) throws Exception {
        String pythonExecutable = "C:\\Users\\Demirr\\PycharmProjects\\pythonProject5\\venv\\Scripts\\python.exe";

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(pythonExecutable, scriptPath);
        processBuilder.command().addAll(Arrays.asList(args));

        Process process = processBuilder.start();

        try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
             BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

            String line;
            System.out.println("Standard output:");
            while ((line = stdInput.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("Standard error:");
            while ((line = stdError.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Python script failed with exit code: " + exitCode);
        }
    }

    @Override
    public String exportToWord(Long id) {
        StudentResponse student = studentService.getOneStudentById(id);
        try {

            ImageResponse response = studentService.getOneStudentImage(student.id());
            String imageBase64 = response.base64Data();
            String imagePath = "temp_image.png";

            if (imageBase64 != null) {

                imageBase64 = imageBase64.trim().replaceAll("\\s+", "");


                if (imageBase64.startsWith("data:image")) {
                    int commaIndex = imageBase64.indexOf(',');
                    imageBase64 = imageBase64.substring(commaIndex + 1);
                }

                try {
                    byte[] imageBytes = Base64.getDecoder().decode(imageBase64);
                    try (FileOutputStream fos = new FileOutputStream(imagePath)) {
                        fos.write(imageBytes);
                    }
                } catch (IllegalArgumentException e) {
                    return "Base64 decoding failed: " + e.getMessage();
                }
            }

            String[] args = new String[]{
                    "Student",
                    "image=" + imagePath,
                    "Name=" + student.name(),
                    "Surname=" + student.surname()

            };
            runPythonScript("C:\\Users\\Demirr\\PycharmProjects\\pythonProject5\\wordscript.py", args);


            new File(imagePath).delete();
        } catch (Exception e) {
            e.printStackTrace();
            return "Export failed: " + e.getMessage();
        }

        return "Export completed";
    }
}
