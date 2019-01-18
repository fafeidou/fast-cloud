package com.fast.cloud.es;

import com.fast.cloud.es.dao.UserDao;
import com.fast.cloud.es.domain.User;
import com.fast.cloud.es.service.UserService;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonEsApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

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

}

