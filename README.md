# parkplus-Shubham-Hatkar
Parkplus  Parkplus is a super app for car owners that solves all their daily hassles. It is used to handle the creation, deletion, and updating of parking lots and spots within those parking lots, register, update, and delete users, make reservations, and payments once reservations are completed. The system should have the following functionalities

## Parkplus

Parkplus is a super app for car owners that solves all their daily hassles. It is used to handle the creation, deletion, and updating of parking lots and spots within those parking lots, register, update, and delete users, make reservations, and payments once reservations are completed. The system should have the following functionalities:
<br>

The design of models can be found here.
<br>

### ParkingLotController:<br>
Add a new parking lot to the database with a given name and address.<br>
Add a new spot within a specific parking lot with a given number of wheels and price per hour. The spot type should be determined by the number of wheels (2 or 4 wheels for "car" and more than 4 wheels for "others").<br>
Delete a specific spot from a parking lot.<br>
Update the details of a specific spot within a parking lot, including the price per hour.<br>
Delete a specific parking lot and all spots within it.<br><br>

### UserController:<br>
Registering a user: The application needs a functionality to register a new user with their name, phone number, and password.<br>
Updating user password: The application needs a functionality to update the password of an existing user by providing the user's ID and the new password.<br>
Deleting a user: The application needs functionality to delete a user by providing their ID.<br>
ReservationController: Reserve a spot in a given parking lot for a specific user and vehicle, ensuring that the total price for the reservation is minimized.<br>
The price per hour for each spot is different, and the vehicle can only be parked in a spot with a type that is equal to or larger than the given vehicle.<br>
In the event that the parking lot is not found, the user is not found or no spot is available, the system should throw an exception indicating that the reservation cannot be made.<br>

### PaymentController: <br>
Implement a payment functionality that allows users to make a payment for a specific reservation using different modes of payment (cash, card, or UPI). The system should validate the amount sent by the user and compare it with the bill amount for the reservation
If the amount sent is less than the bill, the system should throw an exception "Insufficient Amount".<br>
Additionally, the system should validate the payment mode entered by the user and only accept "cash", "card", or "UPI" as valid modes of payment. Note that the characters of these strings can be in uppercase or lowercase. For example "cAsH" is a valid mode of payment. If the user enters a mode other than these, the system should throw an exception "Payment mode not detected". The system should also update the payment attributes for the reservation after a successful payment.<br>

#### Note:<br>
findById and deleteById must be used wherever necessary.<br>
findAll should never be used.
Do not change the name of any existing function/class.
Avoid using the lombok library. Define getters and setters manually.

