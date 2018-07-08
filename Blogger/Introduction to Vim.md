# Introduction to Vim

## Background

[Vim](https://www.vim.org) is nothing more than an editor, but surprisingly, has wide popularity among programmers not only because of its tight connection with Unix-like operating systems but also right because of its simplicity. Terminal in the world of Unix is an interface where programmers can type in commands to directly control the resources in a computer and run programs. Vim dates back to the early history of computer when GUI was not widely available and programmers had to edit their codes directly in terminals. Vim was born in such environment to meet the needs of programmers, which partially explains the popularity of Vim. Vim is essentially an editor that comes with features that you and me can imagine what an editor needs to have, including copying/pasting/deleting text, undoing/redoing an action, and saving a document. And since Vim was designed for programmers in the beginning, no doubt it provides great support for syntax highlighting for a number of programming languages. However, Vim may be unfriendly to general users who are not used to typing commands to operate a computer. Its great dependence on keyboard operation may be daunting for newcommers, but once you get used to basic commands to control it, there are always more powerful functionality for you to discover, like searching/replacing text, indenting, keyboard shortcut, etc.

## Basic concepts

Here, I am going to mention a few basic concepts which should be enough for you to start editing a document with Vim, just like what you can do with any other editors. On typing `vim` in terminal, Vim would pop up with an empty window. Whatever commands you type in would appear in the last line of the window, and anything else that should be in your document would show up in the main page. Two modes are clearly seperated to prevent users from modifying a document inadvertently: **editor mode** and **command mode**. You are always brought to _command mode_ when first launching Vim.
- To go from _command mode_ to _editor mode_:
	- Press `a` (stands for _append_) to start editing from the character next to the current location of the cursor.
	- Press `i` (stands for _insert_) to start editing from the current location of the cursor.
	- Press `o` to insert a new line right below the current line and start editing from the new line.
	- Press `Shift + a` to start editing from the end of the line.
	- Press `Shift + i` to start editing from the start of the line.
	- Press `Shift + o` to insert a new line right above the current line and start editing from the new line.
	- Press `Shift + c` to delete the text from the current location of the cursor to the end of the line and start editing.
- To go from _editor mode_ to _command mode_: Press `Esc`.

## Configuration

Below are some of the most common configurations that you would like to add to the `~/.vimrc` under your home directory. All of the configurations can also be treated as commands to enter in _command mode_. These commands take care of every aspect of an editor and aim to provide the users with a better experience to complete a complex task that would have been much more annoying in other editors.

- `syntax on`: Enable syntax highlighting if the file extension matches any programming languages.
- `set autoindent`: Automatically copy the indentation from previous line to the new line.
- `set background=dark`: Use bright color for syntax highlighting in the case of dark background.
- `set breakindent`: Wrap lines that exceed the width of the window with the same indentation.
- `set magic`: Enable searching with regular expression.
- `set noswapfile`: Stop creating unnecessary swap file as a backup.
- `set number`: Display line number in front of each line.
- `set shiftwidth=4`: The number of spaces that should be applied on indentation/deindentation with tab.
- `set smartindent`: Increase the number of tab by 1 below the line that has any unmatched curly bracket.
- `set tabstop=4`: The size of the tab character in terms of the number of spaces.

## When to use Vim

Nowadays people always pursue high productivity, a pure editor itself hardly caters to the demand of developers. Even Vim, which is arguably the most powerful editor with its own history, has to surrender to such trend and evolve to support IDE features, which is beyond the scope of this article. If you are someone like me who need nothing more than a powerful code editor that comes in handy with your Unix-like operating systems, Vim is definitely the first choice without doubt, and may be even better than any other editors that you can download from the Internet. As almost a pure editor, Vim is, surprisingly or not, more popular than many other IDEs that support complex tools according to the survey of [PYPL](http://pypl.github.io/PYPL.html). Guess it is time for you to dive into the world of Vim!
