package com.tutotial.bootstrapdatatable.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public String index() {
        return "redirect:/home/showStudents";
    }

    @RequestMapping(value = "showStudents", method = RequestMethod.GET)
    public String showStudentsPage() {
        return "StudentListPage";
    }

    @RequestMapping(value = "list")
    public @ResponseBody
    Collection<Student> getStudentCollection() {
        return new ArrayList<Student>();
    }
}
