package com.djw.DemaLibrary.services.impl;

import com.djw.DemaLibrary.domain.dto.BorrowingRequest;
import com.djw.DemaLibrary.domain.dto.BorrowingResponse;
import com.djw.DemaLibrary.domain.dto.BorrowingDTO;
import com.djw.DemaLibrary.domain.entities.BookEntity;
import com.djw.DemaLibrary.domain.entities.BorrowingEntity;
import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.exception.BookNotAvailableException;
import com.djw.DemaLibrary.exception.UserExceededBorrowingLimitException;
import com.djw.DemaLibrary.exception.UserHasExistingBorrowingException;
import com.djw.DemaLibrary.exception.UserNotFoundException;
import com.djw.DemaLibrary.mappers.impl.BookMapper;
import com.djw.DemaLibrary.mappers.impl.BorrowingMapper;
import com.djw.DemaLibrary.repositories.BookRepository;
import com.djw.DemaLibrary.repositories.BorrowingRepository;
import com.djw.DemaLibrary.repositories.UserRepository;
import com.djw.DemaLibrary.security.LibraryUserDetails;
import com.djw.DemaLibrary.services.BorrowingService;
import com.djw.DemaLibrary.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BorrowingMapper borrowingMapper;

    @Override
    @Transactional
    public BorrowingResponse checkAndBorrowBook(BorrowingRequest borrowingRequest) {
        Integer availableCount = bookRepository.getAvailableCopiesOfBook(borrowingRequest.getBook_id()).orElse(0);
        LibraryUserDetails userDetails = (LibraryUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookEntity bookToBorrow = getBookForGivenID(borrowingRequest.getBook_id());
        UserEntity currentUser = userDetails.getUser();

        if(availableCount == 0)
            throw new BookNotAvailableException("No available copies for given Book");

        if(checkIfUserHasExistingBorrowing(currentUser, bookToBorrow))
            throw new UserHasExistingBorrowingException("User already has existing borrowing");

        if(getUserCurrentBorrowCount(currentUser.getId()) > Constants.BORROWING_LIMIT)
            throw new UserExceededBorrowingLimitException("User Borrowing Limit Exceeded");

        LocalDateTime borrowDate = LocalDateTime.now();
        LocalDateTime dueDate = borrowDate.plusDays(14);

        BorrowingEntity borrowingEntity = BorrowingEntity.builder()
                .user(userDetails.getUser())
                .book(bookToBorrow)
                .borrowed_at(borrowDate)
                .returnDate(dueDate)
                .build();
        borrowingRepository.save(borrowingEntity);

        updateBookAndUserEntities(bookToBorrow, currentUser);

        return BorrowingResponse.builder()
                .status("Borrowed Successfully")
                .bookTitle(bookToBorrow.getTitle())
                .authorName(bookToBorrow.getAuthor())
                .borrowedAt(borrowDate)
                .returnDate(dueDate)
                .build();
    }

    @Override
    public void checkAndReturnBook(String bookId) {
        LibraryUserDetails userDetails = (LibraryUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        if(!checkIfUserHasExistingBorrowing(userDetails.getUser()))
//            throw new UserHasExistingBorrowingException("Borrowing does not exist for user");

        BookEntity borrowedBook = getBookForGivenID(UUID.fromString(bookId));
        UserEntity currentUser = getUserForGivenId(userDetails.getId());

        BorrowingEntity borrowingEntity = borrowingRepository.getByUserAndBook(currentUser, borrowedBook);
        borrowingEntity.setReturned_at(LocalDateTime.now());
        borrowingRepository.save(borrowingEntity);

        borrowedBook.setAvailable_copies(borrowedBook.getAvailable_copies() + 1);
        bookRepository.save(borrowedBook);
    }

    @Override
    public List<BorrowingDTO> getPastBookings() {
        LibraryUserDetails userDetails = (LibraryUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BorrowingEntity> borrowingEntities = borrowingRepository.getBorrowingsForUser(userDetails.getId());
        return borrowingEntities.stream().map(borrowingMapper::mapTo).toList();

    }

    private boolean checkIfUserHasExistingBorrowing(UserEntity user, BookEntity book){
        return !borrowingRepository.getOngoingBorrowingForUserAndBook(user.getId(), book.getId()).isEmpty();
    }

    private int getUserCurrentBorrowCount(UUID userID){
        return borrowingRepository.getCurrentBorrowCountForUser(userID);
    }

    private BookEntity getBookForGivenID(UUID bookId){
        return bookRepository.getBookById(bookId).orElseThrow(() -> new BookNotAvailableException("Book not found for given ID"));
    }

    private UserEntity getUserForGivenId(UUID userId){
        return userRepository.getUserById(userId).orElseThrow(() -> new UserNotFoundException("User not found with given ID"));
    }

    private void updateBookAndUserEntities(BookEntity bookToBorrow, UserEntity currentUser){
        bookToBorrow.setAvailable_copies(bookToBorrow.getAvailable_copies() - 1);
        bookToBorrow.getBorrowers().add(currentUser);
        bookRepository.save(bookToBorrow);

        currentUser.getBorrowedBooks().add(bookToBorrow);
        userRepository.save(currentUser);
    }

}
