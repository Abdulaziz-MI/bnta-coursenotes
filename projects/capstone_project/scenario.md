# Capstone Project - Scenario

The well-known mail-order business RainforestRetail have been expanding a lot recently and they have reached the limit of what their current systems can support. When the company started they only sold books but now they have branched out into electronics, fashion, and pretty much anything else you could buy on the high street.

## The Current Business Model

The business started with a paper catalogue with an order form at the back which cutomers could fill in and post back to head office. As more products were added the catalogue got bulky so a website was commissioned to reduce the amount of paper consumed. Visitors could download an order form, fill it in and post if back in the same way as those in the catalogues. As postage costs went up the option was added to email the order forms, or to place orders over the phone.

When orders come in they are processed manually. A member of staff must check if the items are in stock then process payments using card details provided by the customer. There is an old MS Access database listing the current inventory but it must be manually updated when new stock arrives or a sale is made. If something is out of stock the customer must be notified before processing the rest of the order. Customer details - including name, address, contact information and payment details - are stored in a single table in a separate database.

All stock is held in a central warehouse and order details must be manually sent from head office to the warehouse before they are packaged and loaded onto trucks. Each van covers a specific route on a specific day regardless of how many deliveries there are to be made. On a given day one truck could go out nearly empty while another might not be able to fit all its packages in. If there's no room on a truck the package won't be delivered until the next time a truck is on that route. 

Employee details are kept in paper records at head office. Separate diaries are used to keep track of holidays, changes in assignment (eg. which route a driver is assigned to on a given day) and other important dates such as annual appraisals. The finance team need access to these records to get an employee's bank details for payroll.

## Challenges

- The volume of orders has reached a point where manually processing them is too time-consuming
- The user experience of completing a form and posting it off is terrible when compared to the competition's online systems
- Customers have to wait for multiple days between placing their order and finding out if everything is in stock, then further days before the payment is taken and the order dispatched. 
- Delivery times can't be guaranteed - busy periods will likely see delays
- There are frequently errors in the stock control database when an update is missed or processed incorrectly
- All customer records are handled manually
- A single warehouse creates a bottleneck in the delivery system
- The fleet of delivery vehicles is being poorly utilised
- As more employees are added it becomes harder to get an overview of availabilty and where everyone is working on a given day
- Keeping employee records up to date is time-consuming and error-prone

## Existing Actions

- All existing systems will be digitised
- In order to reduce pressure on the delivery network the single warehouse will be replaced with regional hubs, each responsible for managing its own delivery schedule.