package com.stevomena.spring5webapp.controllers;

import com.stevomena.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//With this annotation Spring detected it as Spring Bean
// and wire it into a spring context
@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // el objeto model nos sirve para mandarle atributos a la vista
    @RequestMapping("/books")
    public String getBooks(Model model){
        model.addAttribute("books",bookRepository.findAll());

        //cuando retornamos un string, hacemos referencia
        // a una vista que se llame books, y el view Resolver nos ayudara a mapearla renderizarla
        return "books";
    }
}
