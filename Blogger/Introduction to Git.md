# Introduction to Git

Building a project and tracking any changes made to it become overwhelming and formidable when the project is heavy enough. Multiple users may edit the same pieces of code. The integration of works from two programmers is simply impractical if this process is totally done from scratches. Moreover, code itself, although is written by human, is actually unreadable, even if the code that was written weeks ago by the same person.

Given the predicaments that a project team could face, there developed a tool among several other **version control tools**, called Git. [**Git**](https://git-scm.com/) is arguably by far the most widely-adopted tool in academics, open source projects, industries, etc. Other tools are also available, of which the second most popular is [Subversion (SVN)](https://subversion.apache.org/), which is a default program installed in Unix/Linux distributions.

The rationale behind Git as well as all the other version control tools is pretty intuitive. These tools are designed to expedite coordination of codes developed on different computers, by different engineers. Any changes made to original copy are faithfully recorded and any conflicts between two versions is highlighted. Furthermore, comments are allowed to be made for each line of code to facilitate readability. Since teamwork is usually required, syncing a local project from/onto a remote repository is supporeted by several online repository providers, for example, [GitHub](https://github.com/), [GitLab](https://about.gitlab.com/), etc.

Git itself is a binary program that usually comes without GUI. Operations have to be done in a terminal window which takes in commands from end users. Thanksfully, software engineers have encapsulated Git with a GUI or ported it onto exisiting IDEs to allow for direct access and abridge the need for typing verbose commands. The basic commands required for creation of a git project and committing changes are briefed in the following paragraphs. Before jumping into the commands, you have to change the working directory into the top level of your project. By the way, it is always a good habit to include a README file as a manual for your audiences.

## Create

- `git init`: Initiate a new project that is rooted on this currect working directory.
- `git remote add [URL]`: Add a remote repository that you are goind to sync your project with.

## Commit

- `git add .`: Add all of the files that have been created/modified/deleted since the last commit. If certain files/folders are meant to be left out, please refer to the configurations mentioned at the end of this article.
- `git commit -m [comments]`: Commit all changes and leave commments which is required for each commit.
- `git push -u origin master`: Push all commits that have yet to be synced with the remote repository.

## Configurations

- `.gitconfig`: 
- `.git/info/exclude`
