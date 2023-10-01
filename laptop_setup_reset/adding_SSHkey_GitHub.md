# Adding a new SSH key to your GitHub account

We will be using Git in the Terminal. Please do not use a GUI to interact with Git as it is important you know how to use it from a CLI(Command Line Interface).

In this lesson we are going to add an SSH (Secure Shell) key to your GitHub account. The SSH key allows GitHub to identify you and doesn't require you to enter your username and password everytime you interact with your repository.

## 1. Check for (and remove) existing SSH keys
[GitHub docs for checking for existing SSH keys](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/checking-for-existing-ssh-keys)

```sh
ls -al ~/.ssh
# lists all the files in your .ssh directory (if they exist)
```
Since no one else will be using this laptio, if you see keys listed here (`id_rsa.pub`, `id_ecdsa.pub`, `id_ed25519.pub`), we can remove them. 

> WARNING! Be very careful about typing `rm -rf` in your home directory, please make sure you type in the **whole** of the command below before hitting return.

```sh
rm -rf ~/.ssh/*
# this should remove everything in your `.ssh` folder
```

## 2. Generate a new SSH key and add it to the ssh-agent
[GitHub docs on generating a new SSH key](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)

Generate a new SSH key with the command below, replacing `your_mail@example.com` with your own email address. It is very important that this is the email address you use for your GitHub account.

```sh
ssh-keygen -t ed25519 -C "your_email@example.com"
```

When prompted to "Enter a file in which to save the key", press return (this should default to an `.ssh` directory).

You will then be prompted for a passphrase (twice), please leave this empty (on both prompts) and press return.

If you read the docs, you will see that were we using a passphrase, we could add our SSH key to the SSH agent. We aren't using a passphrase and so can skip this step.

## 3. Adding a new SSH key to your GitHub account
[GitHub docs on adding new SSH key to GitHub account](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account)

Begin by copying your new SSH key to the clipboard.

```sh
pbcopy < ~/.ssh/id_ed25519.pub
```
> if your SSH key has a different name, you will need to modify the command accordingly. Remember, you can check for existing SSH keys using the command in section 1.

In **GitHub** in the upper-right corner of any page, click on your profile photo, then click `settings`.

In the `Access` section of the left-hand side nav bar, click `SSH and GPG keys`.

Click `new SSH key` and paste your key (`ctrl + v`) into the `Key` field. Add a descriptor (something like `BNTA laptop`)for the new key so you can recognise it again in the future.

Click on `Add SSH Key`, and if prompted, confirm your password to continue.
