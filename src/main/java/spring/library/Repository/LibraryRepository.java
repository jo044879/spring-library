package spring.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;
import spring.library.domain.Library;
import spring.library.domain.User;

import java.util.List;


@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    List<Library> findByBookAndUserAndReturned(Book book, User user, boolean returned);
}

