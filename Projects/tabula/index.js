function convert() {
	let caption = document.getElementById("caption").value;
	let content = document.getElementById("content").value;
	let delimiter = document.getElementById("delimiter").value;
	let hHeader = document.getElementById("hHeader").checked;
	let vHeader = document.getElementById("vHeader").checked;
	tabula(caption, content, delimiter, hHeader, vHeader);
}

function tabula(caption, content, delimiter, hHeader, vHeader) {
	let lines = content.trim().split(/\n/);
	for (let i in lines) {
		let line = lines[i].split(delimiter);
		lines[i] = `<tr><td>${line.join("</td><td>")}</td></tr>`;
	}
	if (hHeader) {
		lines[0] = lines[0].replace(/<td>/g, "<th>").replace(/<\/td>/g, "</th>");
	}
	if (vHeader) {
		lines = lines
			.map((line) => line.replace("<td>", "<th>"))
			.map((line) => line.replace("</td>", "</th>"));
	}
	let table = document.getElementById("table");
	table.caption.innerText = caption;
	table.tBodies[0].innerHTML = lines.join("\n");
}