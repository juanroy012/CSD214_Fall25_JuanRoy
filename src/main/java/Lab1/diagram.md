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
    Editable: + String getInput()
    Editable: + String getInput(String)
    Editable: + int getInput(int)
    Editable: + double getInput(double)
    Editable: + boolean getInput(boolean)
    Editable: + Date getInput(LocalDate)
    Editable: + Enum getInput(Enum)
    Editable: + edit()
    Editable: + initialize()
    
    class Publication
    <<abstract>> Publication
    Publication: - String title
    Publication: - double price
    Publication: - int copies
    Publication: - int id
    Publication: + getCopies()
    Publication: + getPrice()
    Publication: + Publication()
    Publication: + Publication(String title, double price. int copies)
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
    Ticket: + int id
    Ticket: + String description
    Ticket: + double price
    Ticket: + toString
    Ticket: + Ticket()
    Ticket: + Ticket(String description, double price)
    Ticket: + edit()
    Ticket: + getDescription()
    Ticket: + initialize()
    Ticket: + getPrice()
    Ticket: + sellItem()
    
    Class Book
    Book: - String author
    Book: + edit()
    Book: + initialize()
    Book: + sellItem()
    Book: + Book()
    Book: + Book(String author)
    Book: + Book(String author, String title, double price, int copies)
    Book: + toString()
    Book: + equals()
    Book: + hashCode()
    Book: + getCopies()
    Book: + getPrice()
    Book: + getTitle()
    
    Class Magazine
    Magazine: - int orderQty
    Magazine: - java.util.Date currentIssue
    Magazine: + Magazine()
    Magazine: + edit()
    Magazine: + initialize()
    Magazine: + sellItem()
    Magazine: + getPrice()
    Magazine: + getTitle()
    Magazine: + toString()
    
    Class DiscMag
    DiscMag: - boolean hasDisc
    DiscMag: + initialize()
    DiscMag: + sellItem()
    DiscMag: + edit()
    DiscMag: + toString()
    DiscMag: + DiscMag()
    DiscMag: + DiscMag(boolean hasDisc, int orderQty, Date currentIssue, String title, double price, int copies)


```
