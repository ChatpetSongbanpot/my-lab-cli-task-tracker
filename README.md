# my-lab-cli-task-tracker

A simple command-line interface (CLI) application written in Java for tracking tasks — including adding, updating, deleting, listing, and marking task statuses. Tasks are stored locally in a `tasks.json` file.

---

## 📌 Features

- Add tasks with descriptions
- Update task descriptions by ID
- Delete tasks by ID
- Mark tasks as:
  - `todo`
  - `in-progress`
  - `done`
- List all tasks or filter by status
- Auto-create JSON file if not found

---

## 🚀 Usage

### 💬 Command Examples
```bash
task-cli > add "Buy groceries"
task-cli > update 1 "Buy groceries and cook dinner"
task-cli > delete 1
task-cli > mark-done 2
task-cli > list
task-cli > list done
task-cli > list todo
task-cli > list in-progress

```
### 📦 Tech Stack
- Language: Java 11+
- File Format: JSON
- Libraries: Jackson (manual import if not using Maven/Gradle)
### 🛠 Dependencies (Manual Import if no Maven)
Ensure the following .jar files are added to the classpath:

`jackson-core-2.15.2.jar`

`jackson-databind-2.15.2.jar`

`jackson-annotations-2.15.2.jar`
