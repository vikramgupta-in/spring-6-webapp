package guru.springframework.spring6webapp.Bootstrap;

import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

   private final AuthorRepository authorRepository;
   private final BookRepository bookRepository;

   private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);


        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        Publisher toi = new Publisher();
        toi.setPublisherName("Times of Indiana");
        toi.setAddress("Ross Street");
        toi.setCity("South West");
        toi.setState("NYK");
        toi.setZip("1234");

        Publisher toiSaved = publisherRepository.save(toi);

        Publisher ht = new Publisher();
        ht.setPublisherName("Hurry Times");
        ht.setAddress("West Street");
        ht.setCity("Okhlahoma");
        ht.setState("PSY");
        ht.setZip("5678");

        Publisher htSaved = publisherRepository.save(ht);

        dddSaved.setPublisher(toiSaved);
        noEJBSaved.setPublisher(htSaved);

        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);




        System.out.println("In Bootstrap");
        System.out.println("Author Count: "+authorRepository.count());
        System.out.println("Book Count: "+bookRepository.count());
        System.out.println("Publisher Count: "+publisherRepository.count());




    }
}
