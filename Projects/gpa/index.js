function handle(files) {
	const MAP = new Map(Object.entries({
		"A+": 4.3, "A": 4, "A-": 3.7,
		"B+": 3.3, "B": 3, "B-": 2.7,
		"C+": 2.3, "C": 2, "C-": 1.7,
		"F": 0
	}));
	file = files[0];
	console.log(file.name);
	var reader = new FileReader();
	reader.onload = (event) => {
		let text = new DOMParser().parseFromString(event.target.result, "text/html");
		let rows = Array.from(text.querySelectorAll("body > table.style2 > tbody > tr"))
			.filter(row =>["#DDDDDD", "#EEEEEE"].includes(row.getAttribute("bgcolor")))
			.filter(row => MAP.has(row.children[7].innerText.trim()));
		let sum = rows
			.map(row => Number(row.children[6].innerText) * MAP.get(row.children[7].innerText.trim()))
			.reduce((a, b) => a + b);
		let credits = rows
			.map(row => Number(row.children[6].innerText))
			.reduce((a, b) => a + b);
		document.getElementById('show').innerText += ` ${sum/credits}.`;
	};
	reader.readAsText(file);
}
