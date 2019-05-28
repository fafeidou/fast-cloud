package com.fast.cloud.sidecarclient.client;

import com.fast.cloud.sidecarclient.entity.Message;
import com.fast.cloud.sidecarclient.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author yangyongjie
 * @create 2017-10-22 20:30
 */
@FeignClient(name = "ace-sidecar-server")
public interface PythonFeignClient {
    //parse param like /message?id=12
    @RequestMapping("/message/{id}")
    List<Message> getMsg(@RequestParam("id") Long id);

    //parse url like /test
    @RequestMapping("/test")
    String getTest();

    //parse url like /test
    @RequestMapping("/getStudent")
    Student getStudent(@RequestParam("id") Long id);

    @RequestMapping("/getStudents")
    List<Student> getStudents();

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    String uploadImg(@RequestParam("the_file") MultipartFile file);
}
