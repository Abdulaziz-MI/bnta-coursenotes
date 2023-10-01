# Laptop Setup Instructions

These instructions will help you set up your MacBook with the tools you will need throughout the course. This is not an exhaustive list, if there are other useful tools you want to install then feel free to do so. If you have any problems contact one of the trainers who can help you resolve it.

If you're new to using a mac [this video](https://www.youtube.com/watch?v=67keaaWOKzE) will give you an overview of the basics.

## Initial Setup

The previous user should have fully reset your MacBook and re-installed MacOS. When you switch it on you should be at the point where you need to create your account. **Please do not create or sign up with your personal Apple account** - none of the software we will install will require one. 

**Please begin by upgrading your mac OS (Operating System) to Monterey.**

You can check which OS your laptop is using by clicking on the Apple icon in the top left corner and choosing `About This Mac` from the menu. If the pop-up window displays `macOS Monterey`, you are good to skip to the next step: *Installing Command Line Tools*.

If your macOS has something else installed (likely macOS Big Sur), please choose `Software Update` (or you may be offered the option of `check for software updates`). 

You can also check for software updates via the `Apple icon` > `System Preferences` > `Software Update`.

After downloading the installer will open automatically. Click `Continue` and follow the onscreen instructions.

> Please note, downloading and installing a new OS can take quite some time (anything from 30mins - 4hours) and it is best if your laptop is plugged into a power source. Please allow the installation to finish without putting your Mac to sleep or closing the lid. Your Mac may restart and display a progress bar/blank screen several times. We reccommend starting the process in the evening so that the installation can be completed overnight.

## Installing Command Line Tools

Open a terminal window using Spotlight. Press `command` + `space` to open the search bar and type "Terminal", then press the return key. 

> Spotlight will be a very useful tool for you going forward, it's worth practicing with.

For each of these tools copy/paste the text here into your terminal then press return. If any command throws an error saying you don't have permission, add "sudo " to the front of it and try again (eg. `sudo brew install git`). This will prompt you for your password any will override any access issues.

### Xcode Command Line Tools

Some of these tools may prompt you to install the *Xcode Command Line Tools*. We won't be using these directly, but they provide some services which are needed for other applications. If you are prompted to install Xcode you can do so using the command:

```sh
xcode-select –-install
```

### Command Line Tools

- **[Oh-My-Zsh](https://ohmyz.sh/)** for a nicer terminal experience

```sh
sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
```

- **[Homebrew](https://brew.sh/)** to make installing/managing packages easier

```sh
# step 1 - download
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# step 2 - installation
echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> /Users/$(whoami)/.zprofile   

eval "$(/opt/homebrew/bin/brew shellenv)"
```

- **[Git](https://formulae.brew.sh/formula/git#default)** for version control. 

```sh
# step 1 - installation
brew install git

# step 2 - configuring default branch
git config --global init.defaultBranch main
``` 

- **[Node](https://formulae.brew.sh/formula/node#default)** for running JavaScript code (includes npm)

```sh
brew install node
```

- **[Postgres](https://www.postgresql.org/)**, the database we will use for all our projects. 🚨 You should NOT be asked to set up a password for Postgresql and doing so will likely cause issues throughout the course. If you are prompted to create a password for Postgresql, please abandon the installation and speak to a trainer.
```sh
brew install postgresql@15
```

## Installing Apps

Follow the links to download each program.

- **[VSCode](https://code.visualstudio.com/)** for code editing, plus any extensions you want to add.
- **[Java 17](https://www.oracle.com/java/technologies/downloads/#jdk17-mac)**. 🚨 Please ensure you have selected the `Java 17` and `macOS` tabs. You should select the `Arm 64 DMG Installer`.
- **[IntelliJ](https://www.jetbrains.com/idea/download/#section=mac)** for Java editing. Make sure you select Community Edition (unless you want to pay for it) and the Apple Silicon version from the dropdown.
- **[Postman](https://www.postman.com/)** for querying APIs. You'll need to create an account, but it's free.
- **[Chrome](https://www.google.co.uk/chrome)** for internet access. You'll also need the [React developer tools](https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi?hl=en) extension. You will be guided to use Chrome and NOT Safari.
- **[Zoom](https://zoom.us/download)** so you can see the lessons.
- **[Slack](https://slack.com/intl/en-gb/downloads/mac?geocode=en-gb)** for everyday communication. We highly reccomend downloading and using the desktop application rather than a browser

