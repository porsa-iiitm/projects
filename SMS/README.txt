PROBLEM STATEMENT

- It should provide a way to send and receive SMSs.
- It should display the messages you have received in a format specified below:

- For a given sender, UI should display only one tile showing the latest message received for that sender. If this message is unread, it should be displayed in bold.
- A tile can be colored in two ways based on two situations that can arise, the sender is in your phonebook or it is an unknown number.
- Tiles should be swipable and exhibit different behavior based on its color. If it is an unknown contact, the tile gets deleted. If it is from a contact from your phonebook, the message is just marked as read i.e. the text will not be bold now.

- Show a notification once you receive an SMS.
- The application is expected to run smoothly and should be performant.



SOLUTION

-For Android version Kitkat or onwards, on app launch it will ask to make this app as your default SMS app. So hit Yes. 

-Landing Screen will show all the received messages. If the sender is not in the contacts list then message card color is LIGHT GREY otherwise WHITE. Once the user will scroll the tiles it will behave accordingly as mentioned in the problem statement.

-In ActionBar there is an option for New Message, that will open a New Activity where, user can enter number of the receiver or pick the contacts from the contacts list(contacts list will open by click on right side icon) and after entering text will hit "SEND" to send message.
During send it will also insert the same message in content provider. 
