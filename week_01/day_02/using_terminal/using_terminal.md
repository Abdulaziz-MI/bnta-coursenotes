# Using Terminal

## Learning Objectives

* Understand what a Command Terminal is and how we can use written commands to perform actions we are accustomed to performing with the Operating Systems' GUI
* Appreciate the history of UNIX operating systems, and how MacOS hence relates to Linux
* Appreciate the format of common terminal commands and what is meant by "flags"
* Be able to move around a file system in the terminal
* Be able to create and delete files through the terminal

## Introduction

The vast majority of people's interactions with a computer are done by using the mouse to click on something. You probably used a mouse to open this document - either by clicking on a link to find it on GitHub, or by clicking on it in your computer's file explorer to open it in another application. The applications which enable us to do this are called **Graphical User Interfaces**, or **GUI**s. 

They're not the only way of interacting with our systems though. Back in the old days before the hardware existed to render all the fancy graphics we are used to seeing today, everything had to be done by typing commands into an interface. These interfaces still exist, we just have to go looking for them. On a Mac the application we use to access it is called **Terminal**.

## UNIX

The MacOS operating system is part of a much larger family which share a common ancestor: **UNIX**. Originally *Unics*, for *Uniplexed Information and Computing Service*, UNIX was conceived in the late 1960s before development began in earnest in the 70s. Different institutions (both academic and commercial) continually expanded on the system, adding their own features, which led to specifications being drafted which any new UNIX implementation must adhere to. Each distinct system could still be be expanded upon however the specifications required that they shared the same base.

The upshot of this is that we can now guarantee that *any* UNIX system will have certain features and permit us to do certain things. The same set of core commands will hence work on **any** system, as so long as we know how to access the command line to use them. 

Why bother with these commands? Think about the experience of learning a new operating system, or even navigating an update to one you already use. Something as simple as searching for a file can require multiple steps, which are not always obvious, before you can even get to the point of entering the name of the file you're looking for; and this process varies between different systems so you may be stumbling along blindingly throughout these initial steps. In contrast, when we learn how to do these things on the command line then we only need to learn **one process** which will be applicable in any environment. Initially, it may take more effort to learn as you may not be familiar with this kind of process, but once we know how to do it, it's a lot more efficient and powerful than using a GUI.

## Navigating the File System

Before we can start working with the command line we need to open the app to do so. On a Mac we have the built-in **Terminal** app, but there are many others we can install to do the same job.

> The Terminal app isn't usually included in the Applications folder. We can open it using Spotlight instead: press `cmd` + `space` to open the search bar and type "terminal", then click or press the return key to open the app.

Terminal is an example of a **shell** where we can run UNIX commands. When you first open it you will see a tilde symbol (`~`) followed by a cursor waiting for you to type something. See below:

![a new terminal window](../../../assets/programming_fundamentals/using_terminal/new_terminal.png)

When we open Terminal the default behaviour is to start in the current user's directory. Any command we run will hence be executed as if we were doing so in a Finder window open within our current folder. We can check that we're in the correct directory by using the `pwd` command, short for **p**rint **w**orking **d**irectory. We can run this command at any time to find out which folder we're currently in.

```sh
pwd

# /Users/yourusername
```

To find out what's in our current directory we can use the `ls` command, which lists all files and folders.

```sh
ls

# a list of files and folders will appear
```

This command shows us a lot, but not quite everything. If we want to see more we can add a **flag** to the `ls` command to modify it's behaviour slightly. For example, `ls -l` will yield extra information about what's in the directory, including files' most recent modification date and file size: 

```sh
ls -l

# a list of files and folders along with permissions, owner and date last modified
```

By default, the `ls` command doesn't show us all files. There are actually some files currently being hidden from us too. If a file or folder name begins with a dot it indicates that it is a **hidden file** and shouldn't be exposed to the user unless they explicitly ask to see it. Usually they are configuration files which affect the way the system runs if modified, so it makes sense to add a layer of protection to them. This way, only those who know what they're looking for can change these files. We can view these files by adding the `-a` flag to the `ls` command. We can even combine this with the `-l` flag to see all the details for **all** files within our current directory.

```sh
ls -al

# a list of files and folders including hidden files
```

Now that we know what's there in our folder, we can start finding our way around. If we want to move into a different directory we can use the `cd` command to **c**hange **d**irectory. For now we'll move into our `Documents` directory, but we could move anywhere in the file system we like.

```sh
cd Documents
```

Note that this time we don't see anything printed after our command, but the `~` symbol has been replaced with the word `Documents`. Terminal will show the name of the folder we're currently in at the start of the line, with `~` acting as a shortcut for our home directory. We don't have any output displayed because we haven't done anything that *needs* an output. Shell commands are very efficient, they won't tell us anything they don't have to. If something works it will just work without giving us a confirmation, but if something goes wrong it will let us know.

If we ever get lost we can get back to our home directory we can get back there by typing either `cd ~` or just `cd` on its own. If we change directory accidentally we can go back to where we just were using `cd -`.

```sh
cd

# Takes us back to our home directory...

cd -

# ... and now we're back in Documents!
```

If you do get lost, don't forget you can use `pwd` to help you figure out where you are!

Note also that the `cd` command can be used to enter through a series of different folders within one command. For instance, if we had a `Coding` folder within our `Documents` folder above, then we could use:

```sh
cd Documents/Coding
```

Another handy thing to note is that the `tab` key allows us to auto-complete many different commands. For example, pressing `tab` when we had written `cd Doc` will complete the line to `cd Documents`. If you had a second folder which also started with `Doc`, such as `Documentaries`, then pressing `tab` will take the suggestion up to the point where the possible outcomes diverge: `cd Document`. Pressing `tab` again will bring up a temporary list of the contents of the currently referenced directory. Pressing `tab` passed this point will allow us to cycle through the possible directories.

## Creating Files and Folders

Now we can move between directories let's create something for us to move in to. First we'll make a new directory to play about with. Just like the commands we've already seen we can do this from anywhere in the file system. The command we'll use is `mkdir`, a (sort of) abbreviation of **m**a**k**e **dir**ectory.

```sh
# /Users/yourusername/Documents

mkdir my_directory
```

There are no firm restrictions on **how to name directories**, but there are some good conventions to follow which will make life simpler both for you and your fellow developers:

- File names are case-sensitive, but that shouldn't be relied upon. It's better to have a different name than rely on one being capitalised.
- Spaces are allowed, but difficult to work with. It's better to use an underscore (like we have done here) or remove the whitespace and capitalise new words (eg. `myNewDirectory`)
- In fact, almost any character is allowed in a file or directory name. The exceptions are the forward slash `/` which is used as the separator in file paths and the dot `.` which indicates a file extension. The backslash `\` is best avoided as well; it can be used as an escape character in UNIX systems but is the separator character in Windows.
- A directory needs to have a unique name within its parent directory. 

Once we have created our directory we can move into it and create some files. We'll use the `touch` command to create a new file. 

```sh
cd my_directory

touch my_file.txt
```

When we create a file using the command line we need to include the **file extension**. Here the file extension is `.txt` which indicates that this is a text file. We can create files of any type here, for example `.pdf` or `.png`. We can also create files without any kind of extension.

```sh
touch my_picture.png
touch another_file
```

The same rules apply to file names as to directories but with the added consideration of any language-specific requirements. We'll see examples of what these might be as we move through the course.

## Opening Files

We can open files from the command line too. The `open` command will use whatever the default program for that type of file is to open it in, eg. `open my_file.txt` will open a text editor. If we open a directory in this way it will always open with Finder on Mac. We can open files using specific programs by using the appropriate keyword, defined within the program. For example to open our text file using the VSCode editor we use `code my_file.txt`.

## Manipulating Files and Folders

It's unlikely that we'll only want to create things. Sometimes we'll want to reorganise our directory structure or remove unnecessary files. The commands to do these things are a little more complicated than those we've seen so far but are still quicker than using a GUI to do it.

To **move** a file we use the `mv` command. This one needs two pieces of information: the thing we're moving and the place we're moving it to. If we wanted to move our image file back up a level and into our `Documents` folder we'd do it like this:

```sh
mv my_picture.png ..
```

The `..` shortcut means "the directory above this one" and we can use it from anywhere in the system. A single dot `.` means "this directory".

Let's go back to the `Documents` directory and see if our picture made it there.

```sh
cd ..

ls
```

Success, our picture has moved! It's not got the best name though, so we should do something about that. We can use `mv` again here to rename our file with syntax that's almost the same. When we used `mv` before we only had to specify a directory to move the file to. If we had also provided a filename the file would have been renamed at the same time, and if we only provide a filename without a directory the file will be renamed in place.

```sh
mv my_photo.png profile_picture.png
```

**Copying** a file is very similar to moving a file, only using the `cp` command. If we want to make a copy of our picture we first provide the path to the thing we want to copy then the location we want to copy it to.

```sh
cp profile_picture.png my_directory
```

If we provide a filename in the second path we can rename the copy as we move it. If we want to copy an entire directory we need to add the `-r` flag (for **r**ecursive) to the `cp` command.

**Deleting** a file is possible from the command line, but we need to be **extremely careful** when using it. If we delete something using a GUI like finder we aren't *really* deleting it. The record of the file is removed from directory and so it looks like it's gone but we can still restore it from the trash folder—it's not really gone until we click the "empty trash" button. If we delete something from the command line it's gone, there's no restoring it without taking your laptop to a data recovery specialist. 

The command we use to delete a file is `rm`. Let's delete our picture from the `Documents` folder since we've just made a copy:

```sh
rm profile_picture.png
```

Deleting a directory is similar to above, but again we need the `-r` flag. There isn't any data in any of the files we made earlier so we won't lose anything by deleting the directory we made. 

```sh
rm -r my_directory
```

Sometimes we will be asked for confirmation before deleting something, usually if something else might depend on it being there. We can bypass this confirmation by adding the `-f` flag, but only do this if you are absolutely certain that you want to be deleting the folder!

#### Task

- Navigate back to your home directory
- Create a directory called `coursework` where you can store the projects we'll work on in class
- Change directory into `coursework` and create another directory for `week_01`
- Move into `week_01` and create a directory for each day
- Move into today's directory and create a text file which you can use to make a cheat-sheet for all these commands. Open the file and make a list of all the commands you can remember.