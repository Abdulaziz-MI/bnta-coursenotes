# Issue Management

### Learning Objectives
Understand the process of issue management including the identification, recording, prioritisation, resolution and reporting on status of software defects.

## Introduction
Now that we know how to create branches and pull requests, we can turn our attention to issue management (sometimes called bug tracking). Every project of any notable size will need a way to track issues and bugs as they arise, as well as how to manage and resolve them. 

How we do this can vary; in recent years there has been an explosion of productivity software, most of which can be used to do this. While we could opt to use Monday, Asana, Trello, Jira, or Basecamp (to name a few), we will show you how to do this using GitHub's own offering: GitHub Issues.

Later in the course we will introduce you to Trello as another option for issue management. (Tip: install the GitHub power-up.)

### Why track issues/bugs?
It probably comes as no surprise that bugs occur in software development all the time. The multitude of issues can be difficult to manage. Teams typically need:

- somewhere to store issues, usually with some associated detail 
- a way to assign issues to team members
- prioritise issues in some manner

Tracking issues can also help greatly with documentation. An issue management system not only documents 'live' bugs, but also lets us create documentation. Documentation can help onboard new members of the team, understand why something has changed or been done a certain way.

### Why use GitHub Issues?
GitHub Issues is a lightweight option which comes 'out-of-the-box' with every GitHub repository. It has many useful features, such as labels, assignment options, a milestones feature, and - as you would expect - integration with the repository you're working on.

On the downside, it can seem awkward if you want to record any issue NOT related to the code on the repository. We will also have to use a workaround to manage prioritisation, which other software usually provides as standard.

## Demo
> This is a demo, not a codealong (although students may wish to practise creating issues on a GitHub repo of their own).

### Creating Issues
> Head to the cohort's coursenotes on GitHub (or another suitable repository)

Let's begin by creating a couple of issues on our repository.

- Click on `Issues` on the top nav bar
- Click on `New Issue` (green button)
- Give your issue a `Title` (e.g. 'Incorrect dates in course overview')
- Give your issue some detail, specify the problem, give it some context, add checklists and if possible a link to where the issue is in the codebase.
- Assign someone (yourself) to the issue. This means you will be notified if there are any changes/additions
- You may wish to add a label (`documentation` would work for this issue). Notice you can create custom labels by typing your label name into the box - if it doesn't yet exist, GitHub offers to make a new one for you.
- Try creating a new label for your issue (e.g 'Critical', or 'Trivial')
- click on `Submit New Issue`
- Create a second issue to work with, following the steps above (multiple issues will be needed to demonstrate prioritisation)

## Prioritising Issues
Sadly, GitHub Issues no longer has a prioritisation or ordering feature but we can use labels to help us do this. Create enough labels to help sort your issues (e.g. 'Critical', 'Major', 'Minor', 'Trivial').

We can now view issues by label.

## Connecting Issues with Pull Requests
It is possible to link a pull request (PR) to an issue in GitHub. Remember, PRs are meant to represent 'fixes' to an issue (bug/feature).

### Create a pull request
To keep things simple, we will make and commit changes via GitHub.

- In GitHub make a change to `README.md` by clicking on the ✎ (pencil). 
- When it is time to `Commit changes` (green button), describe your commit and select `Create a new branch for this commit and start a pull request`
- Name your branch (e.g `fix/correct_dates`) and click on `Propose changes`.
- You will be taken to the `Open a pull request` page. Add any comments, reviewers, assignees and labels you wish. 
- Click on `Development` and you will see an option to attach an issue (or issues) to this PR. Choose a suitable issue to attach and click on `Create pull request`.


## Some best practises
### Encourage 'search' before reporting an issue
To avoid duplication of issues, encourage everyone on your team to search before creating a new issue.

### Have contributing guidelines
If you want to provide structure on reporting issues you can outline rules/suggestions in a document called `CONTRIBUTING.md` in the root of your repository.

This provides a prompt to read them to new users as well as standard place for others to refer to.

### Don't forget to close issues
Issues will need maintenance. Remember to regularly close issues when they are resolved. It is possible to do this with keywords when describing commits, but you may find it easier, initally, to click on `Close issue` at first.


## Further Reading
[Github Docs: Linking a pull request to an issue](https://docs.github.com/en/issues/tracking-your-work-with-issues/linking-a-pull-request-to-an-issue)