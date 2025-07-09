# 📚 DemaLibrary (Library Management Backend)

This repository contains the backend implementation for a Library Management System. Built with Spring Boot, this application provides a robust and secure foundation for managing library resources, users, and borrowing activities.

## ✨ Features

This backend application offers the following core functionalities:

- User Management:

  + User Registration: Allows new users to register for an account.

  + User Login: Secure authentication for registered users.

- Authentication & Authorization:

  - Makes use of Spring Security for authentication and role-based authorization, ensuring secure access to resources.

- Book Management:

  - Create Books: Functionality to add new book entries to the library.

  - View Books: Retrieve a list of all available books.

  - Search Books: Search for books based on various criteria (e.g., title, author, published year, etc).

- Borrowing Management:

  - Create Borrowings: Users can borrow available books, creating a borrowing record.

  - Return Books: Functionality to mark borrowed books as returned.

## 🛠️ Technologies Used

- Spring Boot: The core framework for building the RESTful API.

- Spring Security: For authentication (JWT-based) and authorization.

- Spring Data JPA / Hibernate: For database interaction and ORM.

- Maven : For dependency management and build automation.

- Neon (PostgreSQL): Cloud DB solution for storing relevant Data

- Lombok: To reduce boilerplate code (getters, setters, constructors).

- ModelMapper: For efficient DTO-Entity mapping.


## 🌐 API Endpoints

POST /library/auth/registerUser - Register a new user

POST /library/auth/login - Authenticate user and receive JWT

GET /library/books - Get all books (paginated)

GET /library/books/id/{id} - Get book details by ID

GET /library/books/search - Search books (eg: &searchParam=Mistborn)

GET /library/books/available - Get Available Books

GET /library/author/{authorName} - Get Books By Author

POST /library/books - Create a new book (requires authentication/authorization)

POST /library/borrowings/borrow - Borrow a book (requires authentication)

POST /library/borrowings/return - Return a borrowed book (requires authentication)

GET /library/users/me/{username} - Get user profile details (requires authentication)

POST /library/borrowings - Get user's borrowing history (requires authentication)


## 💻 Frontend Application
This backend is designed to work seamlessly with a dedicated frontend application.

Frontend Repository: [Add Link to Frontend Repo Here]

## 🤝 Contributing
Contributions are welcome! Please feel free to open issues or submit pull requests.
