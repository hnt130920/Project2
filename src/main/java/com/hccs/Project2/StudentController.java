package com.hccs.Project2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("/students")
    public List<Student> students() throws IOException {
        return readData();
    }
    @GetMapping("/name/{name}")
    public List<Student> name(@PathVariable String name) throws IOException {
        System.out.println("Search by name:" + name);
        List<Student> studentList = readData();
        List<Student> studentListFound = new ArrayList<>();
        for(Student student : studentList )
        {
            if(student.getFirstName().equalsIgnoreCase(name))
            {
                studentListFound.add(student);
            }
        }
        if(studentListFound.isEmpty())
            System.out.println("Not Found Student has name: " + name);
        return studentListFound;
    }
    @GetMapping("/student")
    public List<Student> student(@RequestParam double gpa, @RequestParam String gender) throws IOException {
        System.out.println("Search by gpa " + gpa + " gender " +gender);
        List<Student> studentList = readData();
        List<Student> studentListFound = new ArrayList<>();
        for(Student student : studentList )
        {
            if(student.getGpa() == gpa & student.getGender().equalsIgnoreCase(gender.trim()))
            {
                studentListFound.add(student);
            }
        }
        if(studentListFound.isEmpty())
            System.out.println("Not Found Student has gpa " + gpa + " gender " + gender);
        return studentListFound;
    }
    @GetMapping("/gpa")
    public double getAverageGPA() throws IOException {
        List<Student> studentList = readData();
        double totalGPA = 0 ;
        for(Student student : studentList )
        {
            totalGPA+=student.getGpa();
        }
        return totalGPA/studentList.size();
    }

    public List<Student> readData() throws IOException {
        FileReader fileReader = new FileReader("student.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Student> studentList = new ArrayList<>();
        String header = bufferedReader.readLine();
        String line = bufferedReader.readLine();
        while (line != null)
        {
            String data[] = line.split(",");
            Student student = new Student(Integer.parseInt(data[0]),data[1],Double.parseDouble(data[2]),data[3],data[4]);
            studentList.add(student);
            line = bufferedReader.readLine();
        }
        return studentList;

    }

}
