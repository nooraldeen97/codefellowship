# codefellowship
### Labs 16 && lab 17 : CodeFellowship Login, Profiles & Posts
#### Feature Tasks
Build an app that allows users to create their profile on CodeFellowship.

1. The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.
An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
2. The site should allow users to create an ApplicationUser on the “sign up” page.
3. Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.
4. The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
5. This should include a default profile picture, which is the same for every user, and their basic information.
6. Using the above cheat sheet, add the ability for users to log in to your app.
When a user is logged in, the app should display the user’s username on every page (probably in the heading).
7. Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
8. The site should be well-styled and attractive.
9. The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
10. The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
11. Ensure that user registration also logs users into your app automatically.
12. Add a Post entity to your app.
A Post has a body and a createdAt timestamp.
A logged-in user should be able to create a Post, and a post should belong to the user that created it.
hint: this is a relationship between two pieces of data
A user’s posts should be visible on their profile page.

## lab 18
### Feature Tasks
1. Ensure that users can’t perform SQL injection or HTML injection with their posts.
2. Allow users to follow other users. Following a user means that their posts show up in the logged-in user’s feed, where they can see what all of their followed users have posted recently.
3. Ensure there is some way (like a users index page) that a user can discover other users on the service.
4. On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
5. note: this will require a self-join on ApplicationUsers.
6. A user can visit a url (like /feed) to view all of the posts from the users that they follow.
7. Each post should have a link to the user profile of the user who wrote the post.