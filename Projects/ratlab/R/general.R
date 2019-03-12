general <- list(
	cd = setwd,
	clearvars = rm,
	dir = dir,
	copyfile = file.copy,
	getenv = Sys.getenv,
	isfolder = dir.exists,
	mkdir = dir.create,
	movefile = file.rename,
	onCleanup = on.exit,
	path = searchpaths,
	pwd = getwd,
	rmdir = unlink,
	setenv = Sys.setenv,
	system = function(command) system(command, intern = TRUE),
	ver = function() version,
	which = environment,
	whos = function() data.frame(
		Name = ls(envir = .GlobalEnv),
		Bytes = ls(envir = .GlobalEnv) %>% Map(f = get) %>% Map(f = object.size) %>% unlist,
		Class = ls(envir = .GlobalEnv) %>% Map(f = get) %>% Map(f = typeof) %>% unlist
	)
)
