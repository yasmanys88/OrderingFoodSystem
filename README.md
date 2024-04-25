Exercise: Online Food Ordering System

You are tasked with building an Online Food Ordering System using Spring Boot to facilitate ordering food from various restaurants. The system should allow users to browse restaurants, view menus, place orders, and track the status of their orders in real-time. Additionally, it should provide administrative functionality for managing restaurants, menus, and orders.

•	Requirements:
•	Restaurant Management:
◦	Create, read, update, and delete restaurants.
◦	Each restaurant should have attributes like name, address, contact information, and a menu.
•	Menu Management:
◦	Create, read, update, and delete menus for each restaurant.
◦	Each menu should contain items with attributes like name, description, price, and availability status.

User Management:
•	Create, read, update, and delete users.
•	Users should be able to register in the system.
•	Each user should have attributes like name, email, delivery address, and a list of orders.
Order Management:
•	Users should be able to browse restaurants and view their menus.
•	Users should be able to add items from the menu to their cart and place orders.
•	Once an order is placed, users should receive real-time updates on the status of their order (e.g., pending, preparing, out for delivery, delivered).
•	Users should be able to track the location of their delivery in real-time if available.
•	Users should be able to cancel orders if they haven't been processed yet.
Admin Panel:
•	Implement an administrative panel for managing restaurants, menus, and orders.
•	Admins should be able to add, update, or remove restaurants and their menus.
•	Admins should be able to view and manage all orders (e.g., mark orders as completed, cancel orders).
Validation:
•	Implement appropriate validation for input data (e.g., restaurant name should not be empty, item price should be positive, etc.).
•	Ensure that users cannot place orders if they are registered.
•	Ensure that users cannot add items to their cart if they are not available.
Testing:
•	Write comprehensive unit tests to ensure the correctness of your code.
•	Test the RESTful APIs using tools like Postman or curl.
•	Write test with Junit and Mockito

Documentation:
•	Document your API endpoints, with Open Api Swagger, and how to use them.

Instructions:
•	Fork this project on GitHub and work on your solution.
•	Structure your project using appropriate packages and classes.
•	Use a database like MongoDB for storing data.
•	Use Spring Data MongoDB for interacting with the database.
•	Write clean, maintainable code with proper error handling.
•	Provide clear instructions on how to run and test your application.
Once you've completed the exercise, please provide a link to your GitHub repository containing the code. Don't hesitate to reach out if you have any questions or need clarification on the requirements. Best of luck!


