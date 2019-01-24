package com.stevomena.spring5webapp.bootstrap;

import com.stevomena.spring5webapp.model.Author;
import com.stevomena.spring5webapp.model.Book;
import com.stevomena.spring5webapp.repositories.AuthorRepository;
import com.stevomena.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//When we put component We say to sping context that it is a bean
@Component
// ApplicationListener This approach can be used for running logic after the Spring context has been initialized,
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;


    public DevBootstrap(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric","Evans");
        Book   ddd  = new Book("Domain Driven Design","1234","Harper Collins");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        this.authorRepository.save(eric);
        this.bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod","Johnson");
        Book   noEJB  = new Book("J2EE Development","23444","Worx");
        rod.getBooks().add(noEJB);

        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);

    }
}
