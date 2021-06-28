package com.lintrip.springboot.redis.controller;

import com.lintrip.springboot.redis.domain.Book;
import com.lintrip.springboot.redis.dto.BaseResponseObject;
import com.lintrip.springboot.redis.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/book/get.action")
    public BaseResponseObject<Book> getBook(Long id) {
        logger.info("getBook-book id is {}!",id);
        Book book = bookRepository.getBook(id);
        return BaseResponseObject.success(book);
    }

    @RequestMapping("/book/save.action")
    public BaseResponseObject<Long> saveBook(Book book) {
        logger.info("saveBook-request Param：{}!",book);
        Long id = bookRepository.save(book);
        return BaseResponseObject.success(id);
    }
}
