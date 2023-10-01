# Git & Github

## Learning Objectives

* Understand the purpose of Git and GitHub, and the difference between the two
* Appreciate the strength in tracking file version changes 
* Be able to initialise a local Git repository
* Be able to use Git to track changes to the files in a Git repository
* Appreciate how `revert` and `rebase` can be used to roll back committed changes
* Be able to set up an SSH key for connecting to GitHub on a new device
* Be able to connect a local Git repository to a remote repository on GitHub
* Understand the common use-case of "pushing" and "pulling" files from/to GitHub

## Introduction

>Before we start, let's set up Git to use VSCode as its default editor: `git config --global core.editor "code --wait"`

As the projects we work on get bigger it will become more and more challenging to manage the code we produce. This problem grows even bigger when we start collaborating with others, each of whom will often need to work on the same bits of code. Keeping track of not only who did what but also *when* they did it can be difficult.

This isn't a new problem, though. **Version Control Systems** were developed to help manage it, enabling us to not only record the complete history of a project but also roll it back to a previoius point if necessary. In this course we will use **Git** for version control alongside **GitHub** to facilitate collaboration and sharing our code. 


## Why Use Version Control?

We've all experienced the problem of keeping track of different versions of our files. How many of us have hard drives which include things like:

- `report.pdf`
- `report_final.pdf`
- `report_final_fixed_typo.pdf`

...and so on? Remembering which version is the latest isn't easy, nor is remembering what changed with each iteration. Of course we could constantly overwrite `report.pdf` and stick with one file but in doing so we are tied to a single version and committed to any changes we make - what happens if we change our minds and want to roll back something we did?

This is a long-running problem in many walks of life, including software engineering. The problem multiplies when many engineers start working together on a project, each concentrating on different features and potentially editing the same files. Trying to reconcile each version before deploying an application would be a nightmare without some sort of automated system to manage the changes.

In the 1970s the software engineering industry moved to address this by developing **version control systems**. The earliest systems provided a record of changes made to a centralised file system. Developers could periodically *check out* any recorded changes and the system would automatically apply them to the developers' own local files. After working on a feature a developer would *check in* the changes they had made, ready for the rest of the team to check them out later. The system kept a complete record of what had changed and when, meaning changes could be rolled back if necessary.

There were downsides to these centralised systems though. A developer could not use them without being connected to the network hosting the files. That doesn't sound like a huge problem today, but in the 70s it meant being in the same building as the server whenever someone wanted to work on the project, if not the same room. There were also issues around reconciling changes when two or more people worked on the same file. As systems developed these issues were addressed, with modern systems able to recreate a lot of functionality without the need for a network connection and better handling conflicting changes.


## Using Git

### Initialisation

The most widly used version control system today is [**Git**](https://git-scm.com/), with over 90% of developers using it according to the [Stack Overflow developer survey](https://insights.stackoverflow.com/survey). Git differs from older version control systems in that it is designed to run locally rather than on a server, meaning there is no longer any need for developers to be hard-linked to a server in order to use it. Ultimately we still need to connect to the server to share and see any changes, but for now we will focus on working locally. 

We will use Git to monitor and track the changes we make to a file. In this example we will use a single text file but the principle is the same when working with different file types and multiple files at once. We'll begin by creating a directory to work in and navigating to it.

```sh title="Terminal"
# Terminal

mkdir intro_to_git
cd intro_to_git
```

In order to use Git to track the changes made here we need to create a Git **repository**. There are a few ways in which we can do this, including using a [bespoke GUI](https://desktop.github.com/), but in this session we will use the command line.

```sh title="Terminal"
# Terminal

git init
```

Any time we want to use a Git command we prefix it with the `git` keyword. In this case we are using the `init` command, short for *initialise*, to set up Git in this directory. Note that we now have some extra information in the terminal: our directory name is now followed by `git` with the word `main` in brackets after it.

![A newly-initialised git repository in Terminal](../../../assets/git/intro_to_git/initialised_repo.png)

The `git` label indicates that this directory forms part of a Git repository. `main` denotes the active **branch** in the repository. Branches are useful when collaborating with others in the same repository and we'll go into more detail about them in a future lesson. If we check the contents of the directory using the `ls -a` command we will see a new directory `.git` has been created. This directory is what "makes" a Git repository. Records of changes, branches and anything else Git needs to know about are kept here. If we ever want to remove a repository we do so by deleting this directory, but doing so is permanent and will destroy all of the history being kept by Git.


### Adding Files

In order to track changes using Git we need to have a file to change, which we will create now.

```sh title="Terminal"
# terminal

touch facts.txt
```

We will add to this file later, but for now let's see what our repository has to say about things. Although we haven't done much here, as our projects get more complex we will find it harder and harder to keep track of what has changed recently. Git will always be able to give us a ball-park idea of which files have been modified in some way using the `git status` command.

![Initial git status](../../../assets/git/intro_to_git/initial_status.png)

In this case Git is telling us that we have an "untracked file" - the one we just created. One of the benefits of Git compared to some older version control systems is that it doesn't automatically grab everything in the directory, we have to be explicit. We can tell Git to start tracking `facts.txt` using the command `git add facts.txt`. Checking again with `git status` we see that things have updated:

![Git status after adding file](../../../assets/git/intro_to_git/status_after_adding.png)

### Committing Changes

Checking our status has told us that there are changes ready "to be committed". The process of recording a user's changes in a project has two steps. The first is **adding** the changes (also known as **staging** the changes), which we just did. The second is **committing** them, which creates a record of the files which have been staged and the details of what has changed in each. We can add or remove as many files as we want to the staging area before making a commit and we don't have to include every file when we do.

We make a commit using the command `git commit`, but this time we need to provide extra information. We can pass a number of flags to the `commit` command but one of them, `-m` for **message**, is compulsory. This message should provide a high-level description of which files have changed and what has changed in them. This could be a big new feature or a small bug fix, the important thing is that the message gives anyone reading it an idea of what to expect. For example, in our repository we might commit our change like this:

```sh title="Terminal"
# terminal

git commit -m "add facts.txt"
```

Exactly how a message should be formatted will vary depending on an organisation's coding standards, but if in doubt a good approach to take is to think of the message as finishing the sentence "This commit will...". There is a lot of debate around how best to structure a commit message, but there are many articles (such as those on [reflectpring.io](https://reflectoring.io/meaningful-commit-messages/) or [freecodecamp](https://www.freecodecamp.org/news/writing-good-commit-messages-a-practical-guide/)) which give some more in-depth pointers.

The subject of how often to commit is also hotly-debated. Each commit represents a snapshot of a moment in a project's history, any of which could be used as a point to which we could roll back the project. If we don't commit often enough then it becomes difficult to separate out the different stages of a project's evolution. On the other hand, committing too often can lead to a bloated project history which is just as difficult to manage. A good rule of thumb is to avoid a scenario where you need to use the word "and" in a commit message - if you do, it's likely that you waited too long to commit your changes.

### Making Further Changes

Now we have a file to work with we can start working on it safe in the knowledge that Git is there keeping track of what we're doing. We can open the file in our text editor of choice and add our first fact:

> There are more people living in London than in the whole of Scotland (~9 million vs ~5.5 million)

Git is aware that our file has changed since the last commit, but `git status` gives us a slighlty different message this time:

![SGit status after modifying file](../../../assets/git/intro_to_git/status_after_second_add.png)

`facts.txt` is no longer an untracked file, instead we are warned of specific changes which haven't been staged yet. These changes are added and committed in the same way as before, although this time we'll replace the file name with a `.` when staging the changes. This is quite a common practice and tells Git to stage all changes in the current directory.

```sh title="Terminal"
# terminal

git add .
git commit -m "add population fact"
```

Let's add and commit one more fact.

> Penguins can fly at over 50 km/h

```sh title="Terminal"
# terminal

git add .
git commit -m "add penguin fact"
```

We've made a few commits now and it wouldn't be reasonable to expect us to remember what happened in each one. We can, however, use the `git log` command to show us a history of what has happened in our repository so far.

![Git log](../../../assets/git/intro_to_git/logging.png)

Each commit we have made in the project includes the commit message, a time stamp and a unique identifier for the commit. Once we have a lot of commits, potentially including some made by colleagues, we can use the log to figure out what happened and when. We can return to the command line by pressing `q`.


### Rolling Back a Change

Unfortunately we got given some bad information - it turns out penguins can't fly after all! That means we need to get rid of the last fact we added to our file and Git has few options to help us do this.

The least impactful is `git revert`, which enables us to undo a specific commit. Using `git log` we can find the ID of the commit we want to undo and pass it as an argument to the command. We can provide the whole hexadecimal number, but it is only necessary to provide the first seven digits for the command to work.

```sh title="Terminal"
# terminal

git revert ab69211		# replace the ID with one from your git log
```

When we revert a commit like this Git will open a text editor and prompt us to enter a commit message, although one will be suggested for us. Reverting a commit like this creates a new commit to record this happening and so requires a message describing it. Closing the editor tab will make the commit automatically. We can confirm the new commit using `git log`:

![Git log after reverting a commit](../../../assets/git/intro_to_git/logging_post_revert.png)

We can revert any commit in our history, however we need to be careful when reverting older commits. The `git revert` command only reverses changes made in the specified commit, so any code added later which depends on one of those changes will now no longer work. If we want to undo *everything* since a given commit we can use the `git reset` command instead, which will undo the specified commit and every commit after it. Note that we can't pick and choose which bits we want to keep or discard, it's all or nothing. The final option is `git rebase` which not only reverses the commits but removes them from the history entirely.


## Sharing Code with GitHub

Git is a very powerful tool for managing the history of our projects but so far we have only seen part of what it can do. We will cover some of the features which enable collaboration with colleagues in a future lesson but before we get that far we need to see how we can make our code available to others in the first place. To do this we'll need to make use of a service such as **GitHub** which incorporates a lot of functionality to help us manage our projects. GitHub is not the only option here but is the most widely-used by far and is what we will use throughout this course.

### Uploading Code

> Ensure you have completed the "Adding an SSH key" part of the laptop set-up before attempting this

We will create a **repository** on GitHub to upload our code to before sharing it with anyone else. After we have logged in to GitHub we will see an icon in the top-left corner which looks like a plus sign. Clicking it will open a dropdown with a few options in it, including "new repository"

![Creating a new repository on GitHub](../../../assets/git/intro_to_git/new_repo_github.png)

Clicking it will take us to a page where we can do some setup for our repository. There are a few fields we can fill in:

- **Repository Name**: This is mandatory! The name should describe what is in the repository, be all lower-case and not include any spaces.
- **Description**: This is optional but it is good practice to include one. This can be a longer description of the project including details such as languages used.
- **Public/Private**: Determines the visibility of the repository. Private repositories can only be accessed by invitation, so for this course we will make all our repositories public.
- **README**: A README file gives more in-depth documentation for a project. If we don't have our own we can check this box to have GitHub generate some minimal boiler plate.
- **.gitignore**: We can tell Git to ignore certain files, meaning it won't even attempt to track them. We can configure this ourselves or have GitHub generate one from language-specific templates.
- **License**: We can select from a number of templates describing what other developers are allowed to do with our code.

Clicking "create repository" will take us to a screen where we can find the details needed to connect the online repository we have just created with the local repository on our laptops. This process is known as setting up a **remote** and enables us to upload both our code and our Git history to GitHub. We need to ensure we have selected the "SSH" option before clicking the "copy to clipboard" button next to the url.

![Remote url details](../../../assets/git/intro_to_git/remote_url.png)

We link our local repository to GitHub using the `git remote` command.

```sh title="Terminal"
# terminal

git remote add origin your/url/here
```

The `add` keyword indicates that we are making a new link while `origin` is the conventional name given to the primary remote for this repository. We can add more than one remote if needed, for example for deploying a project using a particular service.

Now we have linked the two repositories we can upload from one to the other using the `git push` command. This needs two pieces of information: the place we are sending code to and the branch we are sending it from. For now we only have our `origin` remote and our `main` branch so they are the only options.

```sh title="Terminal"
# terminal

git push origin main
```

If we check GitHub we see that our code is now there, plus our version history is there too!

### Downloading Code

Now the code is online we can share it with others, but they need to be able to download it. Getting a copy of a repository hosted on GitHub is known as **cloning** the repository and can be done by anyone who can access it. The details are found by clicking the "code" button and copying the link, ensuring "SSH" is selected as before.

![Git cloning details](../../../assets/git/intro_to_git/git_clone.png)

The command to copy the files is `git clone` and needs the url passed to it.

```sh title="Terminal"
# terminal

git clone repsoitory/url/here
```

**Do not clone a repository inside another one!** This will cause problems for both unless managed carefully since changing something in one will update both histories, leading to potential conflicts down the line.

Our copy of the repository is linked to GitHub in the same way as if we had set the link up ourselves, meaning any changes we make can be uploaded using `git push` as before. But what happens if someone else uploads something to the repository? We can download their changes at any time using the `git pull` command, which needs the same information as `git push`.

```sh title="Terminal"
# terminal

git pull origin main
```

Now we can track the evolution of a project, restore it to a previous point and share it with our colleagues! In a future lesson we will see how to facilitate better collaboration with those colleagues and how we can avoid some of the potential issues.
