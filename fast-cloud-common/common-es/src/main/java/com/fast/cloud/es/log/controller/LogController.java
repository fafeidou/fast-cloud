package com.fast.cloud.es.log.controller;

import com.fast.cloud.es.common.entity.Pages;
import com.fast.cloud.es.log.entity.SysLogs;
import com.fast.cloud.es.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "")
public class LogController {

    @Autowired
    private ElasticsearchTemplate elasticSearchTemplate;
    @Autowired
    private LogService logService;


    @GetMapping(value = "index")
    public String index() {
        return "log/index";
    }

    @PostMapping(value = "list")
    public @ResponseBody
    Pages<SysLogs> list(Integer pageNumber, Integer pageSize,
                        Integer platFrom, String searchContent) {
        return logService.searchLogPage(pageNumber, pageSize, platFrom, searchContent);
    }


}
