# Make a Command-Line Cheat Sheat

Both Terminal and Git will be important tools in our careers as software engineers, so understanding them and practicing with them is vital. In this lab we will write a cheat-sheet to summarise what we covered in today's lessons which we can use for future reference.

## MVP (Minimum Viable Product)

- Open Terminal and navigate to your `bnta_work/week_01/day_2` folder.
- In your `day_2` folder create another new folder: `mkdir terminal_commands`
- Create a Markdown file to record your research:
`touch terminal_cheatsheat.md`. (Note, the `.md` file extension, which denotes this as a Markdown file.)
- Open `TextEdit` and open your `terminal_cheatsheet.md` (`File` > `Open`). You could also use VSCode if you wish.
- Use markdown to create a Terminal commands cheatsheet. There are many resources on the web to help you get to know Markdown. Here is a Markdown cheatsheet to get you started: [Markdown Cheatsheet](https://www.markdownguide.org/cheat-sheet/). 

## Using Git in Terminal

- Once you have done your research and added your notes to your `terminal_cheatsheet.md` file, return to Terminal. We are going to initialise your folder as a Git project (remember, Git is local - i.e. on your laptop).

- Ensure you have navigated to your `terminal_commands` folder.

- Type `git init` in order to create a `.git` folder (the `.` indicates it is a hidden file). You should now see `git:(main)` after your folder name. Try typing `ls -a` and you should now see `.git` listed.

### Tracking changes in our code
We can now stage our work and make a commit.

- `git add .` will add all changes (in this case everything, as the whole project is new) to be tracked in our `.git` file.
- `git commit -m"first commit"` confirms to Git that we want to save this work (take a snapshot) at this stage.

## GitHub

We are almost ready to push our project to GitHub.

First we need to create a remote repository - a place for your code to sit in GitHub.

- Go to your browser and navigate to GitHub
- Create a new repository. Please remember that this repo must be public in order for the trainers to see it! 
- Copy the three lines of code from the middle block that looks like this:

```
...or push an existing repository from the command line
git remote add origin git@github.com:<your_github_name>/<your_repo>.git
git branch -M main
git push -u origin main
```

## Terminal

We can now push our code to GitHub. 

- Paste the code you copied into the terminal and hit `enter`. This code tells Git where to push your work to on GitHub, and then pushes it up.

Check if it worked! Head back to your browser and GitHub, refresh the page and you should now see your file on GitHub.

Copy the URL of your repo in GitHub and paste it into the lab submission form. The link for the lab submission form is bookmarked at the top of your classroom Slack channel.


## Extensions

- Investigate `.gitignore` files and what they are used for. Try adding one to your repository.
