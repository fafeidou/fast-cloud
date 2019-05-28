package com.fast.cloud.sidecarclient.rest;

import com.fast.cloud.sidecarclient.client.PythonFeignClient;
import com.fast.cloud.sidecarclient.entity.Message;
import com.fast.cloud.sidecarclient.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author yangyongjie
 * @create 2017-10-22 20:30
 */
@RestController
@RequestMapping("test")
public class PythonController {
    @Autowired
    private PythonFeignClient pythonFeignClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest() {
        return pythonFeignClient.getTest();
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public List<Message> getMsg(@PathVariable Long id) {
        return pythonFeignClient.getMsg(id);
    }

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    public Student getMsg() {
        return pythonFeignClient.getStudent(123l);
    }

    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return pythonFeignClient.getStudents();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    String uploadImg(@RequestParam("the_file") MultipartFile file){
        return pythonFeignClient.uploadImg(file);
    }
}