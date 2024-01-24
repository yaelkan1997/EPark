# Amusement Park Management System

Welcome to the Amusement Park Management System! This Java application is designed to manage and organize various aspects of an amusement park, from child registration to device entries and payments.

## Classes

### 1. Child
The `Child` class represents a child visitor to the amusement park. It includes information such as name, age, guardian details, and whether the child is registered or owns an eTicket.

### 2. CreditCardCompany
The `CreditCardCompany` class simulates a credit card company. It includes methods for charging a credit card and getting approval.

### 3. Devices
The `Devices` class represents devices available in the amusement park. It includes information such as name, minimum age and height requirements, and pricing details.

### 4. ETicket
The `ETicket` class represents an electronic ticket for a child. It includes information about the child, device entries, total payment, and credit card details.

### 5. ExtremeDevices
The `ExtremeDevices` class is a subclass of `Devices` that represents extreme devices in the amusement park.

### 6. Guardian
The `Guardian` class represents the guardian of a child. It includes credit card details and a list of children associated with eTickets.

### 7. Park
The `Park` class manages the devices available in the amusement park. It includes methods for adding devices and getting a list of allowed devices based on age and height.


## Main Class

The `Main` class serves as the entry point for the Amusement Park Management System. 
It includes the main functionality to interact with the system through a console-based menu. Some key features of the `Main` class include:

### 1. User Input Functions
- `CheckInputChildName()`: Gets a valid string input for the child's name.
- `CheckInputIntForAge()`: Gets a valid integer input for the child's age.
- `CheckDateInput()`: Gets a valid date input.
- `CheckIntInput()`: Gets a valid integer input.

### 2. Registration and Management
- `RegisterChild()`: Registers a new child with the system, including guardian details and credit card validation.
- `ManageTicket()`: Allows managing a child's eTicket, including viewing device entries and making modifications.

### 3. Device Entry Functions
- `AddRide(boolean enteredFromManageTicket, Child child)`: Adds entries for a child to ride devices.
- `RemoveRide(boolean enteredFromManageTicket, Child child)`: Removes entries for a child from ride devices.

### 4. Exiting the Park
- `ExitPark()`: Allows a child to exit the park, handling eTicket return and payment.

## How to Use

1. **Launching the Application:**
   - Run the `Main` class to start the Amusement Park Management System.

2. **Interaction through Console Menu:**
   - Use the provided menu options to register a child, manage eTickets, add or remove ride entries, exit the park, or exit the application.

3. **Input Validation:**
   - The application includes input validation functions to ensure correct data entry from the user.

