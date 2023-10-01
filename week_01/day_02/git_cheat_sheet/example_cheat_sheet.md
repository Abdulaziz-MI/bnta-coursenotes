# Terminal & Git Cheat Sheet

## Terminal Commands

### Navigation

- `pwd`: **P**rint **W**orking **D**irectory - shows current location in file system
- `cd path/to/location`: **C**hange **D**irectory - move to the specified directory
- `cd ..`: Move up one directory
- `cd`: Move back to the home directory
- `ls`: List contents of current directory
- `ls -a`: List contents and include hidden files and folders
- `ls -l`: List contents with full details

### Creation

- `mkdir myDirectory`: **M**a**k**e **Dir**ectory - create a directory called `myDirectory`
- `touch myFile.txt`: Create a new file called `myFile.txt`
- `rm myFile.txt`: Delete a file called `myFile.txt`
- `rm -rf myDirectory`: Delete a directory called `myDirectoy` including all of its contents

### Manipulating Files

- `mv file.txt path/to/new/location`: Move `file.txt` to a new location
- `mv file.txt renamed.txt`: Rename `file.txt` to `renamed.txt`
- `cp file.txt path/to/new/location`: Copy `file.txt` to a new location
- `cp -r directory path/to/new/location`: Copy `directory` to a new location



## Git Commands

1. `mkdir newProject` - creates a new directory
2. `cd newProject` - moves into our new directory
3. `git init` - initialises a new Git repository
4. `touch newFile.txt` - creates a new text file
5. `open newFile.txt` - allows us to edit our text file in an editor
6. `git add newFile.txt` / `git add .` - tells Git to track this new file. `.` will select all modified files.
7. `git commit -m "our commit message"` - commits our staged changes
8. Create a repository on GitHub
9. `git remote add origin <GIT URI of new remote repository>` - connects our local and remote repositories
10. `git push origin main` - pushes the local changes to the remote

To commit changes to the local repository and to push changes to a remote once everything is set up:

1. `git add ...` - stage the changes we want to commit (`.` specifies all files, `git status` will show all modified files)
2. `git commit -m "..."` - commits the staged changes to teh local repository
3. `git push origin main` - pushes the changes to the remote