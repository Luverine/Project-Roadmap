# GitHub User Activity CLI

This is a simple command-line interface(CLI) application written in java that fetches the recent activity of a specified GitHub API and display it in terminal.

## Features

- **Fetch User Activity:** Retrieve recent public events (activity) for a given GitHub username.
- **Formatted Output:** Displays human-friendly messages (e.g., "Pushed 3 commits to username/repository","Opened an issue in username/repository").
- **Error Handling:** Gracefully handles errors such as invalid usernames or API failures.
- **No External Libraries:** Uses only Java's build-in classes for HTTP request and basic string processing.

## **Project URL**
 ```bash
   https://roadmap.sh/projects/github-user-activity
```

## Requirements

- Since i have used **Java 23** it would be better if used, but at least it should be higher then **Java 8**.
- Internet connection to access the **GitHub API**.

## Usage

1. **Compile the Code**
   
    Open a terminal in the project directory and compile the java source file :
    ```bash
   javac GitHubUserActivity.java
    ```
2. **Run the Application**
   
    Run the CLI by providing a GitHub username as an argument:
    ```bash
   java GitHubUserActivity <username>
    ```
3. **Output**

   The application will output recent events for the specified GitHub user in a friendly format. 
   ```bash
      - Pushed 3 commits to username/repo_name
      - Opened an issue in username/repo_name
      - Created repository username/repo_name
      - WatchEvent in Username/repo_name
   ```
   
---
## How It Works

1. **Command Line Argument**
   - The username is passed as an argument when running the program.
2. **Fetching Activity**
   - The application constructs the URL ```https://api.github.com/users/<username>/events``` and makes a GET request using ```HttpURLConnectionn```.
3. **Processing Response**
   - The response is received as a JSON string. THe code then does a simple split on ```"},{"``` to separate indivisual event objects.
   - Depending on the event type (e.g. ```PushEvent```, ```IssuesEvent```, ```CreatEvent```), the application formats and prints a summary message to the terminal.
   - For each event, key fields such as ```type``` and ```repo``` are exception occurs, a corresponding error message is displayed.

---
## License

This project is open-source. Feel free to use, modify, and distribute it as needed.

---

### **Final Notes**

This sample project meets the requirements without relying on any external libraries. The JSON processing here is very basic; in a real‑world scenario, consider using a proper JSON parser. Enjoy building and extending your GitHub Activity CLI!
