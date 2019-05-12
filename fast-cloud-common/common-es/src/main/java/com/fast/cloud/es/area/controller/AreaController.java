package com.fast.cloud.es.area.controller;

import com.fast.cloud.es.area.entity.Area;
import com.fast.cloud.es.area.service.AreaService;
import com.fast.cloud.es.common.entity.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "area")
public class AreaController {

    @Autowired
    private ElasticsearchTemplate elasticSearchTemplate;
    @Autowired
    private AreaService areaService;

    @GetMapping(value = "index")
    public String index() {
        return "area/index";
    }

    @PostMapping(value = "list")
    public @ResponseBody
    Pages<Area> list(Integer pageNumber, Integer pageSize, String searchContent) {
        return areaService.searchAreaPage(pageNumber, pageSize, searchContent);
    }
}
