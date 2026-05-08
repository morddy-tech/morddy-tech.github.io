## 📖 Library System – README

# 📚 Library System

A console‑based Java application to manage a small library: add books, borrow, return, view all, and search by title/author.

## ✨ Features

- **Add books** – create new entries or increase quantity of existing ones  
- **Borrow books** – reduces quantity if enough copies exist  
- **Return books** – increases quantity  
- **Display all books** – see title, author, and available copies  
- **Search** – case‑insensitive partial match on title or author  
- **Input validation** – prevents empty titles/authors, negative quantities, invalid numbers  

## 🛠️ Requirements

- Java JDK 11 or later

## 🚀 How to Run

```bash
javac LibrarySystem.java
java LibrarySystem
```

## 📝 Usage

After starting, you see a menu:

```
====== Library Menu ======
1. Add Books
2. Borrow Books
3. Return Books
4. Display All Books
5. Search Books
6. Exit
```

Follow the prompts. All inputs are validated – you will be asked to re‑enter if something is wrong.

## 🔒 Security Notes

- No SQL or shell injection risks (no external databases or commands).  
- Inputs are trimmed and checked for emptiness.  
- Quantities cannot go negative.  
- Book titles/authors are stored as entered but trimmed – no XSS because it's console only.  

## 📂 Project Structure

```
library-system/
├── LibrarySystem.java   (contains Book class as inner or separate)
└── README.md
```

## 🤝 Extension Ideas

- Save library to a file (`serialization` or JSON)  
- Add due dates and fine system  
- Use a database (JDBC)  
- Build a GUI with Swing/JavaFX  

## 📄 License

Educational use – free to modify and share.