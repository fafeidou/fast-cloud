package com.fast.cloud.es;

import com.fast.cloud.es.area.entity.Area;
import com.fast.cloud.es.area.repository.ElasticAreaRepository;
import com.fast.cloud.es.user.dao.UserDao;
import com.fast.cloud.es.user.domain.User;
import com.fast.cloud.es.user.service.UserService;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonEsApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ElasticAreaRepository areaRepository;

    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setAge(19 + i);
            user.setCreatetm("sdfsdf" + i);
            user.setDescription("desc" + i);
            user.setId(123L + i);
            user.setName("sdf" + i);
            userService.insert(user);
        }
    }

    @Test
    public void testQuery() {
        Iterable<User> all = userDao.findAll();
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder("sdf");
        builder.field("name");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
        AggregatedPage<User> search = elasticsearchTemplate.queryForPage(searchQuery, User.class);
        Iterator<User> iterator = search.iterator();
        while (iterator.hasNext()) {
            User book = iterator.next();
            System.out.println("该次获取的book:" + book.getDescription());
        }
        System.out.println();
    }

    @Test
    public void testTemplate01() {
        User user = new User();
        user.setAge(19);
        user.setCreatetm("sdfsdf");
        user.setDescription("desc");
        user.setId(123L);
        user.setName("你好");
        IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(user.getId())).withObject(user).build();
        elasticsearchTemplate.index(indexQuery);
    }

    @Test
    public void testInsertEs() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select  * from sh_area");
        List<Area> areas = Lists.newArrayList();
        for (Map<String, Object> map : maps) {
            Area area = new Area();
            area.setId(Long.parseLong(map.get("id") + ""));
            area.setPid((Long.parseLong(map.get("pid") + "")));
            area.setShortname(map.get("shortname") + "");
            area.setName(map.get("name") + "");
            area.setMergerName(map.get("merger_name") + "");
            area.setLevel((Short.parseShort(map.get("level") + "")));
            area.setPinyin(map.get("pinyin") + "");
            area.setCode(map.get("code") + "");
            area.setZipCode(map.get("zip_code") + "");
            area.setFirst(map.get("first") + "");
//            GeoPoint geoPoint = new GeoPoint();
//            geoPoint.resetLat(Double.parseDouble(map.get("lat") + ""));
//            geoPoint.resetLon(Double.parseDouble(map.get("lng") + ""));
            area.setLocation(map.get("lat") + "," + map.get("lng"));
            areas.add(area);
        }
        areaRepository.saveAll(areas);
    }

    @Test
    public void testDelete() {
        areaRepository.deleteAll();
    }

}

