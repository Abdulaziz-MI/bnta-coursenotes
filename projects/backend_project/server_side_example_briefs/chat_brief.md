## Chatroom Project Example Brief
 
We chat over the internet hundreds, sometimes thousands of times per day. Before Slack, Facebook, and Twitter were around, the real-time-communication niche of the internet was dominated by chatrooms. While you can emulate a more interesting and complex messaging platform (Discord, Slack, WhatsApp etc), you need to keep the scope of this project in mind, as a week is not a long time! A good way to approach this project is to start with the very basic “chatroom” MVP—where everyone can use any chat—and build up your extension functionality piece by piece.

### MVP

At its heart, a chatroom is a simple concept. A user joins the chatroom, where they can send messages, which can be seen by other users. The app will need models for the Users, Rooms, and Messages. Bear in mind that each User will need a unique identifier (either a randomly generated or user-specified username, separate to their unique database id). Each message will need assigned to the user who sent it, and the chatroom that it is sent in so think about how you implement this information as properties of your Message. Additionally, you may want extra information attached to your messages, such as the time sent. The Chatroom model may or may not have a “name” (*e.g.* "c7_classroom"), but do remember that it will need to display messages in reverse chronological order!

By the nature of communication apps, the information you send to the client must be carefully chosen, as an email/password leak could be devastating to a user. Don’t worry too much about “real” authentication—auth tokens *etc.*—but keep security in mind when you write your DTOs.

This project doesn’t lend itself to a huge amount of business logic in the backend, but there are all sorts of ways that you could sort which chatrooms show up first to a user. For instance, you may want to display the chatroom the user has most recently frequented or sent the most messages in at the top of the list.

### Key Functionality

Your MVP should be able to:

* Allow for the creation of a new user
* Allow you to update the information of a user
* Delete a user
* Allow a user to post a message in a chatroom
* Allow users to read messages sent by other users in a chatroom

### Extensions

When you think of chatrooms/message board/forums/group chats, there is a ton of extra functionality not considered in the MVP. You could look at adding extra information to the classes: for the user class, you could add “roles”  - user, moderator, admin - that modify the allowed behaviour. The message class can take on a whole host of extra information, for example:

- Different types of media (photos, videos) 
- Slack-esque “reactions”

Also, the chatrooms in the MVP might all be public and available to anyone who has access to the API. You could add functionality that only allows specific people into chatrooms. There are a few ways to do this: whitelisting, blacklisting or password-protection to name a few, and these would require adding a password or lists of allowed/blocked players to the chatroom model.

If you really feel up for a challenge, you could research API authentication to get real login security but have a chat with the trainers ahead of attempting this.

### Flavours

The chatroom was an internet staple for group communication for years, but other flavours of communication have always taken the stage since. Communication platforms like WhatsApp do the job slightly differently, where a user has to be added by another user (often a group admin) if they want to join a group chat. You could look to implement this sort of feature.

In addition to this, you could look at having users first “agreeing” to message each other, in the form of friends (Facebook)—where the one user has to accept another user’s invitation—or it may come in the form of followers (Twitter) where there’s no strict invitation, but both parties have to follow each other to be able to message.

Remember that another group will be building a frontend for this API, and they may decide to take it in a direction that your team may not have thought about. Prioritise the basic functionality of a chatroom first before moving onto any extensions.