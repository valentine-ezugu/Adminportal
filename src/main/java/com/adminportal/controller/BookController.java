package com.adminportal.controller;

import com.adminportal.domain.Book;
import com.adminportal.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    BookService bookService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBook";
    }
    @RequestMapping("/bookInfo")
    public String bookInfo(@RequestParam("id") Long id,Model model ){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);

        return "bookInfo";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("id") Long id,Model model ){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);

        return "updateBook";
    }

    @RequestMapping(value="/updateBook", method=RequestMethod.POST)
    public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
        bookService.save(book);

        MultipartFile bookImage = book.getBookImage();

        if(!bookImage.isEmpty()) {
            try {
                byte[] bytes = bookImage.getBytes();
                String name = book.getId() + ".png";

                Files.delete(Paths.get("src/main/resources/static/image/book/" +name));

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "redirect:/book/bookInfo?id="+book.getId();
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBookPost(
            @ModelAttribute("book") Book book, HttpServletRequest Request) {
        bookService.save(book);

        MultipartFile bookImage = book.getBookImage();
        try {
            byte[] bytes = bookImage.getBytes();
            String name = book.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:bookList";
    }

    @RequestMapping("/bookList")
    public String BookList(Model model) {
       List<Book> bookList =  bookService.findAll();
       model.addAttribute("bookList",bookList);// we add attribute so we can retrieve it
        return "bookList";
    }


    @RequestMapping(value="/remove", method=RequestMethod.POST)
    public String remove(
            @ModelAttribute("id") String id, Model model
    ) {
        bookService.removeOne(Long.parseLong(id.substring(8)));
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);

        return "redirect:/book/bookList";
    }

}


