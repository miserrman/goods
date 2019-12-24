package com.ooad.good.service;

import com.ooad.good.util.JacksonUtil;
import jdk.nashorn.internal.objects.Global;
import org.apache.catalina.filters.RemoteIpFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.xml.stream.events.Comment;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Autowired
    CommentService commentService;

    @Value("http://localhost:8018/products/{id}")
    String commentDeleteUrl;

    @Test
    void deleteCommentByProductId() throws Exception {

            commentService.deleteCommentByProductId(512, 1, "cd.cd.cds");
    }

    @Test
    void deleteCommentList() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(515);
        list.add(516);
        commentService.deleteCommentByProductIdList(list, 1, "12.12.1.12");
    }
}
