# UserService

#### This service runs on ``port 8002`` with a MYSQL database on ``port 3306``.

-----------------
### This service is responsible for the user profile management:
* Create new user profile
* Get user's own profile
* Get user profile by ID
* Get all users profiles
* Update user's own profile



### The user profile contains the following information:
- Image link
- Name
- Amount of posts

## How to run
___
Clone the repository and run the following command:
```docker compose up```


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

