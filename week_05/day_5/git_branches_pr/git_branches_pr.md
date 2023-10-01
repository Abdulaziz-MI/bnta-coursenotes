# Branches & Pull Requests

## Learning Objectives

* Understand what is meant by "branching" of a Git project
* Be able to initialise and move to a new Git branch via the terminal
* Understand what is meant by "merging" Git branches
* Be able to merge two divergent Git branches using `git merge`
* Be able to resolve a merge conflict, optionally through the VSCode interface
* Appreciate the common development lifecycle of a project using GitHub, including pull requests

## Introduction

We have seen that Git can be an incredibly useful tool for managing our projects but we haven't come close to its full potential yet. In this session we will see how to work on different versions of a codebase simultaneously, enabling many developers to work on it at once without treading on each other's toes. We'll also see how to merge the different versions together and handle the issues which arise when two people edit the same piece of code.

## Branches

Until this point our projects have developed in a fairly linear manner. In general we've worked on one thing at a time in a clearly-defined order. With text documents, spreadsheets or simple coding projects this progression works well; it is not often that you are working on multiple parts of a document at the same time. For coding projects however, it is quite often the case that you will be working on new features simultaneously, especially when you are working on a collaborative project. To work in this _parallel_ manner we will need to make use of **branches**.

Branching allows us to use a commit in our project as a starting point for an "alternate version" which can run in a different direction to the main branch. Most often, this direction will eventually loop back and the changes you have made will be merged back into the project. In contrast, there is also the possibility that what you are working on is entirely experimental and that you do not intend for it to be used within your final project. Keeping this code on a separate branch affords you the chance to experiment without worrying about unintended side effects. More importantly, the history of the new feature or experiment is retained when the code is merged back onto the parent branch.

We can use the `git branch` command at any time to see a list of branches which exist locally. The currently selected branch is annotated with an `*` next to its name.

Every repository will have one branch when we initialise it. This is often as the "default" branch and is usually given the name `main`. This is by convention rather than for any technical reason, we can call our branches anything we want to. Older projects may still refer to their default branch as `master` but developers are now using `main` as standard.

When we installed zsh it included some helpful git tools. When we initialise a repository we can see the name of the branch we are currently working on written after the directory name.

![fresh git repo with branch name](../../../assets/git/git_collaboration/default_branch.png)

Usually the `main` branch represents the "current" version of the project. That doesn't mean it is the most up-to-date version, instead it means that it is the public-facing version of the app. That means the code available on `main` should not have any bugs or half-finished features lurking in it.

Obviously that presents some issues - how do we share the code we're currently working on with colleagues, for example? Many organisations have a second parallel version of the app on GitHub where new features can be added. This is achieved by creating a **branch**, often called `develop`. Once the next version of an app has been completed on `develop` the code can be **merged** with `main` to make the new features available to users.

## Creating Branches

Before we start creating branches we need to have some code to work with. Create a new project in IntelliJ, initialise a Git repository in it and write a simple "hello world" program. Commit your code once it's working.

```java
// Main.js

public class Main{

	public static void main(String[] args){
		System.out.println("Hello World!");
	}

}
```

Let's imagine this is a more complex app which we want to add a new feature to. We'll create a `develop` branch in Terminal and switch over to it.

```sh

# create new branch
git branch develop

# switch to the new branch
git checkout develop

```

The text in our terminal has changed:

![terminal on develop branch](../../../assets/git/git_collaboration/develop_branch.png)

We have a different name after the directory name indicating that we are now working on the `develop` branch. Nothing else about our project has changed, our code is exactly as it was before we created the branch. What _has_ changed is what will happen to any future commits we make. They will be recorded on `develop`, keeping them separate from our public-facing app on `main`.

Let's make some changes to our app and commit them.

```java
// Main.js

public class Main{

	public static void main(String[] args){
		System.out.println("Hello World!");
		System.out.println("We're writing this on the develop branch");
	}

}
```

Once that's done we can switch back to the `main` branch.

```sh
git checkout main
```

Our code has disappeared! When we use `git checkout` with a branch like this we are changing the state of our project to reflect how it looked when the most recent commit was made on that branch. When we committed our last change we were on `develop`, so `main` has no idea about what we were working on. When we checked out we moved back to a point before those changes had been made. It's scary, but this is what's meant to happen. By isolating changes like this we ensure that they don't get added to the "finished" app until we're ready for that to happen.

## Merging Branches

We can add our changes to the published app by **merging** them. By merging one branch into another we copy the commits across and update our code to reflect the changes. We can do this in Terminal:

```sh
git merge develop
```

Now our latest changes are visible on `main`! Now they are part of our public-facing app and available to our users. The `develop` branch still exists (we can check using `git branch`) and we can switch back to it at any point to continue developing new features.

## Handling & Merging Multiple Branches

By introducing the `develop` branch we have seen how we can avoid breaking our code as we add new features, but this still doesn't help us when more than one person is working on a project. We're not limited to a single branch, though, we can have as many as we like. A typical workflow might include a `main` branch for the deployed app, a `develop` branch where the next version is being built and then several other branches coming from `develop`, each of which includes code for a new feature or bug-fix. These branches exist in isolation and can be merged back onto `develop` when the feature is complete then deleted when no longer needed. With multiple branches like this each developer can work on their own code without interfering with anyone else's work.

The difficulties come when merging all those branches together. The algorithms powering Git are very sophisticated and are generally able to reconcile the changes made by two different users but not always. In those cases we are left with a **merge conflict** and we need to manually edit the file(s) to resolve them.

![merge conflict in Main.java](../../../assets/git/git_collaboration/merge_conflict.png)

Making the edit isn't too complicated, the improtant part is figuring out which lines to keep and which to discard. The line of `=` symbols is separating the two conflicting versions of the code. The code between `<<<<<<< HEAD` and that point is the version which is on the currently checked out branch. Between `=======` and `>>>>>>> develop` is the code we are trying to merge. Since we're trying to merge the `develop` branch in this screenshot that's what appears after the `>` symbols, but it could be the name of any branch or indeed a specific commit ID.

To manually resolve the conflict at the very least we need to delete the lines with the `<`, `=` and `>` symbols. Aside from them acting as indicators to Git that there is an unresolved conflict in the file, leaving them in will cause compiler errors if we try to run the code. We can pick and choose the other lines we want to keep; sometimes we'll want everything from one commit or another, sometimes we'll want to mix and match. Once we have decided which version to keep we need to make another commit to mark the conflict(s) as resolved. It's is **extremely important** to test the code before making this commit - we need to make sure we haven't deleted the wrong thing!

Because of this it is good practice when merging branches to merge the target branch onto your working branch first. That way if there are any conflicts, or indeed any bugs arising as a result of someone else's changes, they can be fixed before the code is merged onto a stable branch. Even in a small team it's important that the code on `develop` is in a working state at all times. If someone takes a branch off of `develop` and starts working on a new feature they don't want to have to fix someone else's bugs before they start. By merging `develop` onto `new_feature` and fixing issues there, then merging the result back onto `develop`, we can try to avoid this problem.

## Branches & GitHub

We aren't limited to working with branches on our own laptops. Remember that until we push our work to GitHub the commits we have made only exist on our local machines, and the same applies to branches. Recall the way in which we push our code:

```sh
git push origin main
```

We have a bit more context for this now: we know that the "main" part of this command is referring to the `main` branch. When we type the command above we are only pushing the code on `main` to GitHub. How can we get the code from another branch up there?

The command is almost the same, we just need to change the name of the branch. If, for example, we wanted to push what's on our `develop` branch to GitHub we would checkout the branch then use `git push` again.

```sh
git checkout develop

git push origin develop
```

Now our colleagues can `git pull` to get all of our changes including our new branches. They can checkout those branches and start working on them if they want to. When we view on the code on GitHub we can also switch between the branches which have been push to view the code and even carry out merges using GitHub's built-in tools.

## Pull Requests

A pull request is an event that takes place when you want to merge code from one branch to another (usually into the `main` branch).

In a larger organisation, it can usually involve somebody reviewing your code before approving a branch merge. Generally pull requests are a good habit to get into, as opposed to forcefully and bluntly merging branches and hoping nothing breaks 🤷🏻‍♂️🙃.

<img src="https://media.giphy.com/media/cFkiFMDg3iFoI/giphy.gif" alt="Git merge Gif" height="150" />

So let's try to create a pull request. To start, create a new Git repository.

```sh title="Terminal"
mkdir pr-example
cd pr-example
git init
```

Head over to GitHub and [create a new **empty** repository](https://github.com/new). Same as what we did in the Intro to Git lesson. If you need help with this see [the previous lesson](../intro_to_git/intro_to_git.md#uploading-code).

Link your local repository to the GitHub repository you just created

```sh title="Terminal"
git remote add origin your/url/here
```

Let's add a file with some text in it:

```sh title="Terminal"
echo "# My readme" >> README.md
```

> You mightn't have seen this command before, it's kind of a shortcut as it does two things. It creates the file `README.md` and places the text "# My readme" inside of it.

Commit these new changes

```sh title="Terminal"
git add .
git commit -m "Created README.md"
```

And push them to GitHub

```sh title="Terminal"
git push --set-upstream origin main
```

> If you already ran the `--set-upstream` command before, you just need to run `git push`

Now if you open up the repo on GitHub, you should see something like this:

![Github Profile Example](../../../assets/git/git_collaboration/github_repo.png)

Looking pretty good! Head back to your terminal and create a new branch

```sh title="Terminal"
$ git checkout -b readme_changes
# Switched to a new branch 'readme_changes'
```

And let's make changes to the `README.md` file. Open the file in VSC by running `code README.md`, and change some letters in the title from this:

```md title="README.md before"
# My readme
```

To this:

```md title="README.md after"
# My brilliant readme
```

And let's `add` and `commit` the changes, but this time in one line!

```sh title="Terminal"
git commit -am "Updated README.md"
```

> the `-am` flag allows us to `add` and `commit` in the same command, but only if there are **no new files**

And let's push this new branch to our GitHub repo

```sh title="Terminal"
 git push --set-upstream origin readme_changes
```

Head back to the `main` branch and let's change the `README.md` one more time. This may seem strange, and it is. We are purposely messing with git to create a **merge conflict**.

```sh title="Terminal"
 git checkout main
 echo "# some new content that will cause a conflict" > README.md
 git commit -am "Updated README.md"
 git push
```

Once your new branch has been pushed, head back to the GitHub repo on your browser.

![Github Profile with pull request Example](../../../assets/git/git_collaboration/github_new_branch.png)

GitHub has noticed we have created a new branch with commits, and now we can perform a pull request by clicking the **Compare & Pull Request** button.

GitHub brings you to the **Open a pull request** page

![Github open pull request Example](../../../assets/git/git_collaboration/github_open_pr.png)

But you'll notice it says **Can't automatically merge**. This means there is a merge conflict.

Click **Create Pull Request** and you will be brought to a new page with information on your new Pull Request

![Github pull request Example](../../../assets/git/git_collaboration/github_pull_request.png)

From here, if you were on a team you would assign reviews, labels, milestones and a few other options on the right-hand side. There can also be discussions down the bottom. But in our case, we're developing alone 🥲 so let's just go ahead and **resolve the conflicts**.

Where it says `This branch has conflicts that must be resolved, but we're not going to click **Resolve conflicts**, because the only editor is terrible for this, so let's do it within Visual Studio.

Head back to your Visual Studio, or run `code .` to open the folder in Visual Studio. And in your terminal, run the following:

```sh title="Terminal"
git checkout readme_changes
git merge main
```

Now if you open the `README.md` file (in VSC), you will notice it looks a little different:

![VSC merge example](../../../assets/git/git_collaboration/vsc_merge.png)

Visual Studio is showing us both versions of the file, and we get to choose one or both changes. So we have three options:

1. Accept Current Change (in green) - the change that was made in our current branch
2. Accept Incoming Change (in blue) - the change coming in from the other branch
3. Accept both changes - bring in changes from both lessons

Go ahead and select **Accept Incoming Change** up the top. Save the file, commit it and push it to Github.

```sh title="Terminal"
git commit -am "Resolved merge conflicts"
git push
```

Now if you check the Pull request on Github again, it should say the following:

![Github pull request without conflicts Example](../../../assets/git/git_collaboration/github_no_conflicts.png)

Woohoo! We've managed to resolve a merge conflict. No click the **Merge pull request**, **Commit Merge** and our changes are merged back into the main branch. You can now **Delete the `readme_changes`** branch.
