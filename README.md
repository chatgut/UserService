# UserService

### This microservice application offers the following features:
* Create a new user profile
* Update user's own profile
* Get user's own profile
* Get user profile by ID

### The user profile contains the following information:
- Image link
- Name
- Amount of posts


## Endpoints
___
 * `GET /users/{userId}` - Get user profile by ID. <br /><br />
  
 * `GET /users` - Get user's own profile by HEADER userID  <br /><br />

 * `GET /users/all` - Get all users profiles <br /><br />
   
 * `POST /users` - Create a new user profile by HEADER userID
   * Example:
     * { <br />
     "name":"User's name",<br />
     "imageLink":"https://http.cat/201" <br />
     } <br /><br />
 * `PUT /users` - Update user's own profile by HEADER userID 
   * Example:
     * { <br />
      "name":"User's new name",<br />
      "imageLink":"https://http.cat/200" <br />
      } <br /><br />

