package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller //Burasi bir controller sinifidir, requestlerin karsilandigi sinif
@RequestMapping("/students")
//Tomcat calistirildiginda gelen base url ile birlikte hangi isteklerin bu classta karsilanacagini belirtiyoruz
// (   /students ile gelen istekler karsilanacak.  )
// ---> class level:tum methodlar icin gecerli , method level: sadece o method iicn gecerli
public class StudentController {


    @Autowired
    private StudentService service;

    //  gelen requeste gore sadece goruntuleme islemi  yapacagiz
    //controllerdan response olarak modelandview(data+view name) ya da String tipinde sadece view name doner.
    // read islemi-> get()
    @GetMapping("/hi")      // http://localhost:8080/SpringMvc/students/hi
    public ModelAndView sayHi() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Hi! ");    // .jsp'deki ${message} degiskeni icin
        mav.addObject("messagebody", "I'm a Student Management System"); //  ${messagebody} degiskeni icin
        mav.setViewName("hi");
        //bu dosyanin ismini views icerisinde bulup icerisindeki degerlere
        // mav objemizin degerlerini atayip dosyayi hazir hale getirip
        // View katmanina sunan (bind eden) View Resolver'dir.
        return mav;
    }


    //  1. adim : Student Creation
    //  http://localhost:8080/SpringMvc/students/new
    //  --> AddStudent butonuna tiklaninca bilgileri almak icin form gostermemiz gerekiyor
    //  sadece gosterme-okuma islemi yapacagimiz icin get()
    @GetMapping("/new")                             //model         //obje
    public String sendStudentForm(@ModelAttribute("student") Student student) {    //kullaniciya bos bir form dondurecegimiz icin donus tipi String
        return "studentForm";
        // modelattribute  view katmani ile controller arasinda datanin transferini saglar.
        //form icerisindek bilgiler model ile controllera uygulamamiza tasinmis oluyor
    }

    //---------------> submit botonuna tiklandiginda
    //basarili bir kayit islemi gerceklesmisse tum ogrencileri listeleyelim
    //http://localhost:8080/SpringMvc/students/saveStudent  +POST
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {    //sendStudentForm buraya yonlendirdi
        //servide de student i kaydet
        service.saveStudent(student);

        return "redirect:/students";
        // http://localhost:8080/SpringMvc/students/ ogrenci kaydedildiginde buraya yonlendirilecek
    }


    //  2 - List Students
    //  http://localhost:8080/SpringMvc/students/   +GET
    @GetMapping
    public ModelAndView listAllStudents() {
        List<Student> studentList = service.getAllStudent();
        ModelAndView mav = new ModelAndView();
        mav.addObject("students", studentList);
        // students.jsp->  <c:forEach items="${students}" var="student" varStatus="status">
        // "${students}" bu listeye olusturdugumuz studentList listesini ver demis olduk

        mav.setViewName("students");    // students.jsp

        return mav;
    }


}
