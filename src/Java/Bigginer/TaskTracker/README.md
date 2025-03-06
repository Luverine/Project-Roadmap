# Task Tracker CLI

This is a simple command-line interface (CLI) application built in **Java** to help you track and manage your tasks. You can use this app to add, update, delete, mark tasks as done, and view tasks based on their status.

## Features

- Add new tasks
- Update existing tasks
- Delete tasks
- Mark tasks as "in-progress" or "done"
- List tasks based on their status (`todo`, `in-progress`, `done`)
- CLI-based interface with commands

## Setup

1. **Clone the repository** (or copy the code):

   ```bash
   git clone https://github.com/Luverine/Project-Roadmap.git
   ```

2. **Compile the Java files** (if not using an IDE like IntelliJ or Eclipse):

   ```bash
   javac Task.java TaskTracker.java TaskManager.java
   ```

3. **Run the program**:

   ```bash
   java TaskTracker
   ```

   This will start the command-line interface where you can begin managing your tasks.

---

## Available Commands

The application supports the following commands:

### 1. `add <description>`

Adds a new task with the provided description.

Example:

```bash
add "Buy groceries"
```

### 2. `update <id> <new description>`

Updates the description of the task with the specified ID.

Example:

```bash
update 1 "Buy groceries and cook dinner"
```

### 3. `delete <id>`

Deletes the task with the specified ID.

Example:

```bash
delete 1
```

### 4. `mark-in-progress <id>`

Marks the task with the specified ID as "in-progress".

Example:

```bash
mark-in-progress 1
```

### 5. `mark-done <id>`

Marks the task with the specified ID as "done".

Example:

```bash
mark-done 1
```

### 6. `list <status>`

Lists all tasks. You can optionally specify a status (`todo`, `in-progress`, `done`) to filter the tasks.

Example:

```bash
list           # Lists all tasks
list done      # Lists all tasks marked as "done"
list todo      # Lists all tasks marked as "todo"
list in-progress # Lists all tasks marked as "in-progress"
```

### 7. `exit`

Exits the application.

---

## Task Properties

Each task in the system has the following properties:

- **id**: A unique identifier for each task (automatically generated).
- **description**: A brief description of the task.
- **status**: The status of the task (`todo`, `in-progress`, `done`).
- **createdAt**: Timestamp of when the task was created.
- **updatedAt**: Timestamp of when the task was last updated.

---

## Example Usage

1. Add tasks:

   ```bash
   add "Buy groceries"
   add "Walk the dog"
   ```

2. List all tasks:

   ```bash
   list
   ```

3. Update a task:

   ```bash
   update 1 "Buy groceries and cook dinner"
   ```

4. Mark a task as in-progress:

   ```bash
   mark-in-progress 2
   ```

5. Mark a task as done:

   ```bash
   mark-done 1
   ```

6. List tasks with a specific status:

   ```bash
   list done
   ```

7. Delete a task:

   ```bash
   delete 2
   ```

8. Exit the program:

   ```bash
   exit
   ```

---

## Data Persistence

Currently, tasks are stored **in-memory** and will not persist once the program is closed. For future versions, you could add support for **storing tasks in a file** (such as a text file or serialized format) to ensure data persistence across sessions.

---

## License

This project is open-source and free to use. You can modify and distribute it as per your needs.

---

## Troubleshooting

- If you get errors when running the program, ensure that you have **Java 8 or higher** installed.
- Ensure the `Task.java`, `TodoList.java`, and `TaskManagerCLI.java` files are compiled and available in the same directory.
- If you encounter issues with the commands, ensure you're using the correct syntax as outlined above.

--- 

**Enjoy managing your tasks with the Task Tracker CLI!**