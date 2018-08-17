function generate() {
	let year = document.getElementById("year").value;
	let month = document.getElementById("month").value;
	let gender = document.querySelector("input[name='gender']:checked").value;
	let pronoun = gender == "female" ? "she" : "he";
	let management = document.getElementById("management").value;
	let problem = document.getElementById("problem").value;
	document.getElementById("output").innerHTML = `This is a ${year}-year-old ${gender}. ${capitalize(pronoun)} presents ${management? "for " + management : ""} ${problem? "for " + problem : ""}.`;
}

function capitalize(s) {
	return s.charAt(0).toUpperCase() + s.substr(1);
}
