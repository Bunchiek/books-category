package com.example.books_categories.mapper;

import com.example.books_categories.entity.Book;
import com.example.books_categories.web.model.BookResponse;
import com.example.books_categories.web.model.BooksListResponse;
import com.example.books_categories.web.model.UpsertBookRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@DecoratedWith(BookMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    Book requestIdToBook(Long id);

    Book requestToBook(UpsertBookRequest request);

    @Mapping(source = "bookId", target = "id")
    Book requestToBook(Long bookId, UpsertBookRequest request);

    BookResponse BookToResponse(Book book);

    default BooksListResponse BooksListToBooksListResponse(List<Book> books){
        BooksListResponse booksListResponse = new BooksListResponse();
        List<BookResponse> responseList = new ArrayList<>();
        for(Book booksList : books) {
            responseList.add(new BookResponse(booksList.getId(),booksList.getBookName(),booksList.getAuthor(),booksList.getCategory().getCategoryName()));
        }
        booksListResponse.setBooks(responseList);

        return booksListResponse;
    }
}
