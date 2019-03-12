let time_start;
let int_snd;
let mid_snd;
let end_snd;

function clock() {
	let time_elapse = ~~((new Date() - time_start) / 1000);
	let time_min = ('0' + ~~(time_elapse / 60)).slice(-2);
	let time_sec = ('0' + time_elapse % 60).slice(-2);
	document.getElementById("clock").innerHTML = `${time_min}:${time_sec}`;
}

function start() {
	time_start = new Date();
	setInterval(clock, 1000);
	document.getElementById("clock").innerHTML = "00:00";
	document.getElementById("clock").disabled = true;
	document.getElementById("clock").style.cursor = "not-allowed";
	for (let i of [4, 7, 8, 12, 22]) {
		setTimeout(int, i * 60 * 1000);
	}
	setTimeout(mid, 15 * 60 * 1000);
	setTimeout(end, 25 * 60 * 1000);
}

function int() {
	int_snd.play();
}

function mid() {
	mid_snd.play();
}

function end() {
	end_snd.play();
	document.getElementById("clock").style.color = "red";
}

window.onload = (event) => {
	document.querySelector("#clock").onclick = (event) => start();
	int_snd = new Audio("http://soundbible.com/mp3/sms-alert-4-daniel_simon.mp3");
	mid_snd = new Audio("http://soundbible.com/mp3/sms-alert-1-daniel_simon.mp3");
	end_snd = new Audio("http://soundbible.com/mp3/railroad_crossing_bell-Brylon_Terry-1551570865.mp3");
}
