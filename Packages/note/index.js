function generate() {
	let year = document.getElementById("year").value;
	let month = document.getElementById("month").value;
	let gender = document.querySelector("input[name='gender']:checked").value;
	let pronoun = gender == "female" ? "she" : "he";
	let management = document.getElementById("management").value;
	let complaint = document.getElementById("complaint").value;
	let diagnosis = document.getElementById("diagnosis").value;
	document.getElementById("output").innerHTML = `The patient is a ${year? year + "-year-" : ""}${month? month + "-month-" : ""}old ${gender}. ${capitalize(pronoun)} presents${management? " for " : ""}${management}${complaint || diagnosis? " for " : ""}${complaint}${complaint && diagnosis? " with " : ""}${diagnosis}.`;
}

function capitalize(s) {
	return s.charAt(0).toUpperCase() + s.substr(1);
}
