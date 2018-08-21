function generate() {
	let year = document.getElementById("year").value;
	let month = document.getElementById("month").value;
	let gender = document.querySelector("input[name='gender']:checked").value;
	let pronoun = gender == "female" ? "she" : "he";
	let management = document.getElementById("management").value;
	let complaint = document.getElementById("complaint").value;
	let diagnosis = document.getElementById("diagnosis").value;
	let out = document.getElementById("out");

	h2 = document.createElement("h2");
	h2.innerText = "## History of Present Illness";
	out.appendChild(h2);

	div = document.createElement("div");
	div.innerText = `The patient is a ${year? year + "-year-" : ""}${month? month + "-month-" : ""}old ${gender}. ${capitalize(pronoun)} presents${management? " for " : ""}${management}${complaint || diagnosis? " for " : ""}${complaint}${complaint && diagnosis? " with " : ""}${diagnosis}.`
	out.appendChild(div);

	div = document.createElement("div");
	div.innerText = `He/She complains of **.`
	out.appendChild(div);

	div = document.createElement("div");
	div.innerText = `He/She denies **.`
	out.appendChild(div);

	div = document.createElement("div");
	div.innerText = `He/She presented to Dr. **'s outpatient clinic on **.`
	out.appendChild(div);

	div = document.createElement("div");
	div.innerText = `Due to the diagnosis/suspicion of/for **, he/she is admitted for **.`
	out.appendChild(div);

	h2 = document.createElement("h2");
	h2.innerText = "## Birth History"
	out.appendChild(h2);

	li = document.createElement("li");
	li.innerText = `Term:`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Delivery route:`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Complications: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Developmental milestones: WNL`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Vaccinations: as schedule`
	out.appendChild(li);

	h2 = document.createElement("h2");
	h2.innerText = "## Past History"
	out.appendChild(h2);

	li = document.createElement("li");
	li.innerText = `Hypertension: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Diabetes: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Malignancy: denied`
	out.appendChild(li);

	h3 = document.createElement("h3");
	h3.innerText = "### Allergy"
	out.appendChild(h3);

	li = document.createElement("li");
	li.innerText = `Medication: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Medical device and materials: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Food: denied`
	out.appendChild(li);

	h3 = document.createElement("h3");
	h3.innerText = "### Treatments"
	out.appendChild(h3);

	li = document.createElement("div");
	li.innerText = `Chinese medicine: denied`
	out.appendChild(li);

	li = document.createElement("div");
	li.innerText = `Supplements: denied`
	out.appendChild(li);

	h2 = document.createElement("h2");
	h2.innerText = "## Social History"
	out.appendChild(h2);

	li = document.createElement("li");
	li.innerText = `Alcohol: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Betel nuts: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Cigarettes: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Drugs: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Occupation:`
	out.appendChild(li);

	h2 = document.createElement("h2");
	h2.innerText = "## Family History"
	out.appendChild(h2);

	li = document.createElement("li");
	li.innerText = `Hypertension: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Diabetes: denied`
	out.appendChild(li);

	li = document.createElement("li");
	li.innerText = `Malignancy: denied`
	out.appendChild(li);
}

function capitalize(s) {
	return s.charAt(0).toUpperCase() + s.substr(1);
}
