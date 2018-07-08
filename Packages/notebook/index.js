function get_list() {
	let list = localStorage.getItem("notebook");
	return JSON.parse(list);
}

function set_list(list) {
	localStorage.setItem("notebook", JSON.stringify(list));
}

function load_list() {
	let list = get_list();
	if (list == null) {
		set_list([]);
		return;
	}
	for (let note of list) {
		append_note(note);
	}
}

function push_list(note) {
	let list = get_list();
	list.push(note);
	set_list(list);
}

function clear_list() {
	set_list([]);
}

function append_note(note) {
	let input = document.createElement("input");
	input.type = "checkbox";
	input.id = note;
	let text = document.createTextNode(note);
	let li = document.createElement("li");
	li.className = "list-group-item";
	li.appendChild(input);
	li.appendChild(text);
	let notebook = document.getElementById("notebook");
	notebook.appendChild(li);
}

function click_add() {
	let note = document.getElementById("note").value;
	if (get_list().indexOf(note) != -1 || note == "") {
		return;
	}
	append_note(note);
	push_list(note);
}

function click_del() {
	if (confirm("Delete Selected") == false) {
		return;
	}
	let list = get_list();
	let new_list = [];
	for (let note of list) {
		if (!document.getElementById(note).checked) {
			new_list.push(note);
		}
	}
	document.getElementById("notebook").innerHTML = "";
	set_list(new_list);
	load_list();
}

function click_clear() {
	if (confirm("Clear All") == false) {
		return;
	}
	document.getElementById("notebook").innerHTML = "";
	clear_list();
}

window.onload = function () {
	load_list();
};
