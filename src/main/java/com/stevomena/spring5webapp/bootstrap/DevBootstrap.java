package com.stevomena.spring5webapp.bootstrap;

import com.stevomena.spring5webapp.model.Author;
import com.stevomena.spring5webapp.model.Book;
import com.stevomena.spring5webapp.model.Publisher;
import com.stevomena.spring5webapp.repositories.AuthorRepository;
import com.stevomena.spring5webapp.repositories.BookRepository;
import com.stevomena.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//When we put component We say to sping context that it is a bean
@Component
// ApplicationListener This approach can be used for running logic after the Spring context has been initialized,
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        //Eric
        Author eric = new Author("Eric","Evans");
        Book   ddd  = new Book("Domain Driven Design","1234",publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        this.authorRepository.save(eric);
        this.bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod","Johnson");
        Book   noEJB  = new Book("J2EE Development","23444",publisher);
        rod.getBooks().add(noEJB);

        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);

    }
}
