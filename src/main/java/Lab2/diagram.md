```mermaid
---
title: Bookstore Diagram
---
classDiagram

    Editable <|-- Publication
    Editable <|-- Ticket
    Serializable <|.. Ticket
    Serializable <|.. Publication
    SaleableItem <|-- CashTill
    SaleableItem <|.. Ticket
    SaleableItem <|.. Publication
    Publication <|-- Book
    Publication <|-- Magazine
    Magazine <|-- DiscMag
    
    Class SaleableItem
    <<interface>> SaleableItem
    SaleableItem: + sellItem()
    SaleableItem: + getPrice()
    
    Class Serializable
    <<interface>> Serializable
    
    Class Editable
    <<abstract>> Editable
    Editable: - Scanner input
    Editable: + String getInput(String)
    Editable: + int getInput(int)
    Editable: + double getInput(double)
    Editable: + boolean getInput(boolean)
    Editable: + Date getInput(Date)
    Editable: + Enum getInput(Enum)
    Editable: + edit()
    Editable: + initialize()
    
    class Publication
    <<abstract>> Publication
    Publication: - String title
    Publication: - double price
    Publication: - int copies
    Publication: - String author
    Publication: - String isbn
    Publication: - String description
    Publication: + getCopies()
    Publication: + getPrice()
    Publication: + Publication()
    Publication: + Publication(String author, String title, double price. int copies, String isbn, String description)
    Publication: + toString()
    Publication: + equals()
    Publication: + hashCode()
    
    Class CashTill
    CashTill: - double runningTotal
    CashTill: + CashTill()
    CashTill: + showTotal()
    CashTill: + sellItem(SaleableItem item)
    CashTill: + sellItem()
    CashTill: + getPrice()
    
    Class Ticket
    Ticket: + String description
    Ticket: + double price
    Ticket: + toString()
    Ticket: + Ticket()
    Ticket: + Ticket(String description, double price)
    Ticket: + getPrice()
    Ticket: + initialize()
    Ticket: + edit()
    Ticket: + sellItem()
    
    Class Book
    Book: + Book()
    Book: + Book(String author)
    Book: + Book(String author, String title, double price, int copies)
    Book: + getCopies()
    Book: + getPrice()
    Book: + getTitle()
    Book: + initialize()
    Book: + edit()
    Book: + sellItem()
    Book: + toString()
    Book: + equals()
    Book: + hashCode()
    
    Class Magazine
    Magazine: - LocalDate currentIssue
    Magazine: + Magazine()
    Magazine: + Magazine(String author, String title, double price, int copies, String isbn, String description, LocalDate currentIssue)
    Magazine: + getCopies()
    Magazine: + getPrice()
    Magazine: + getTitle()
    Magazine: + initialize()
    Magazine: + edit()
    Magazine: + sellItem()
    Magazine: + toString()
    
    Class DiscMag
    DiscMag: - boolean hasDisc
    DiscMag: + DiscMag()
    DiscMag: + DiscMag(String author, String title, double price, int copies, String isbn, String description, LocalDate currentIssue, boolean hasDisc)
    DiscMag: + initialize()
    DiscMag: + edit()
    DiscMag: + sellItem()
    DiscMag: + toString()

```
