const SYMBOL = {
	NUMBER: { rank: 0, regex: /[0-9.]/ },
	PAREN_LT: { rank: 0, regex: /\(/ },
	PAREN_RT: { rank: 0, regex: /\)/ },
	PLUS: { rank: 2, regex: /\+/ },
	MINUS: { rank: 2, regex: /\-/ },
	TIMES: { rank: 3, regex: /\*/ },
	OVER: { rank: 3, regex: /\// },
	ROUND: { rank: 3, regex: /%/ },
	POWER: { rank: 4, regex: /@/ },
	SIGN: { rank: 4, regex: /[PN]/ },
	AND: { rank: 1, regex: /&/ },
	OR: { rank: 1, regex: /\|/ },
	XOR: { rank: 1, regex: /\^/ },
	NOT: { rank: 4, regex: /~/ }
};

class Symbol {
	constructor(symbol) {
		this.symbol = symbol;
	}
	get type() {
		for (let TYPE in SYMBOL) {
			if (SYMBOL[TYPE].regex.test(this.symbol)) {
				return SYMBOL[TYPE];
			}
		}
	}
	get rank() {
		for (let TYPE in SYMBOL) {
			if (SYMBOL[TYPE].regex.test(this.symbol)) {
				return SYMBOL[TYPE].rank;
			}
		}
	}
}

function assert(judgement) {
	if (!judgement) {
		throw new Error("Assertion error");
	}
}

function infix2postfix(line) {
	let stack_operator = [];
	let array_postfix = [];
	for (let symbol of line) {
		let symbol_prev = stack_operator[stack_operator.length - 1];
		let symbol_curr = new Symbol(symbol);
		switch (symbol_curr.type) {
		case SYMBOL.NUMBER:
			array_postfix.push(symbol_curr.symbol);
			break;
		case SYMBOL.PAREN_LT:
			stack_operator.push(symbol_curr);
			break;
		case SYMBOL.PAREN_RT:
			while (symbol_prev.symbol != '(') {
				array_postfix.push(stack_operator.pop());
				symbol_prev = stack_operator[stack_operator.length - 1];
			}
			assert(stack_operator.pop().symbol == '(');
			break;
		case SYMBOL.PLUS:
		case SYMBOL.MINUS:
		case SYMBOL.TIMES:
		case SYMBOL.OVER:
		case SYMBOL.ROUND:
		case SYMBOL.AND:
		case SYMBOL.OR:
		case SYMBOL.XOR:
			while (symbol_prev != undefined && symbol_prev.rank >= symbol_curr.rank) {
				array_postfix.push(stack_operator.pop());
				symbol_prev = stack_operator[stack_operator.length - 1];
			}
			stack_operator.push(symbol_curr);
			break;
		case SYMBOL.POWER:
		case SYMBOL.SIGN:
		case SYMBOL.NOT:
			stack_operator.push(symbol_curr);
			break;
		default:
			throw new Error("Invalid symbol");
		}
	}
	while (stack_operator.length > 0) {
		array_postfix.push(stack_operator.pop());
	}
	return array_postfix;
}

function is_number(str) {
	return !isNaN(str);
}

function parse_number(str) {
	assert(is_number(str));
	return parseFloat(str);
}

function calc_unary(operand, operator) {
	let num = parse_number(operand);
	switch (operator.symbol) {
	case 'P':
		return +num;
	case 'N':
		return -num;
	case '~':
		return ~num;
	default:
		throw new Error("Invalid operator");
	}
}

function calc_binary(operand1, operand2, operator) {
	let num1 = parse_number(operand1);
	let num2 = parse_number(operand2);
	switch (operator.symbol) {
	case '+':
		return num1 + num2;
	case '-':
		return num1 - num2;
	case '*':
		return num1 * num2;
	case '/':
		return num1 / num2;
	case '%':
		return num1 % num2;
	case '@':
		return Math.pow(num1, num2);
	case '&':
		return num1 & num2;
	case '|':
		return num1 | num2;
	case '^':
		return num1 ^ num2;
	default:
		throw new Error("Invalid operator");
	}
}

function process_postfix(array_postfix) {
	let stack_answer = [];
	for (let i in array_postfix) {
		let answer;
		switch (array_postfix[i].type) {
		case SYMBOL.PLUS:
		case SYMBOL.MINUS:
		case SYMBOL.TIMES:
		case SYMBOL.OVER:
		case SYMBOL.ROUND:
		case SYMBOL.POWER:
		case SYMBOL.AND:
		case SYMBOL.OR:
		case SYMBOL.XOR:
			let operand2 = stack_answer.pop();
			let operand1 = stack_answer.pop();
			let operator = array_postfix[i];
			answer = calc_binary(operand1, operand2, operator);
			stack_answer.push(answer);
			break;
		case SYMBOL.SIGN:
		case SYMBOL.NOT:
			let operand = stack_answer[stack_answer.length - 1];
			let sign = array_postfix[i];
			answer = calc_unary(operand, sign);
			stack_answer.pop();
			stack_answer.push(answer);
			break;
		default:
			stack_answer.push(array_postfix[i]);
			break;
		}
	}
	assert(stack_answer.length == 1);
	return parseFloat(stack_answer.pop());
}

function replace_sign(line) {
	let sign_flag = true;
	for (let i in line) {
		if (line[i] == '+' && sign_flag) {
			line[i] = 'P';
		} else if (line[i] == '-' && sign_flag) {
			line[i] = 'N';
		} else {
			sign_flag = /[(+\-*/%@&|^~]/.test(line[i]);
		}
	}
	return line;
}

function click_submit() {
	let line = document.getElementById("line").value;
	let answer, err_flag = false;
	try {
		if (line.search(/[^\s0-9.()+\-*/%&|^~]/) != -1) {
			throw new Error("Invalid symbol");
		}
		let line_new = line.replace(/\*\*/g, '@').match(/[0-9.]+|[()+\-*/%@&|^~]/g);
		line_new = replace_sign(line_new);
		let array_postfix = infix2postfix(line_new);
		answer = process_postfix(array_postfix);
	} catch (err) {
		answer = err.message;
		err_flag = true;
	}
	let li1 = document.createElement("li");
	li1.className = "list-group-item";
	li1.innerHTML = line;
	let li2 = document.createElement("li");
	li2.className = "list-group-item";
	li2.innerHTML = answer;
	if (err_flag) {
		li2.className += " error";
	}
	let show = document.getElementById('show');
	show.insertBefore(li2, show.childNodes[0]);
	show.insertBefore(li1, show.childNodes[0]);
	document.getElementById("line").value = '';
}

const TEST_DATA = [
	{ line: "2 /3 /12341 / 12 * 23", answer: "0.00010353924137248016" },
	{ line: "4+ + 1234", answer: "1238" },
	{ line: "238 * 234/2 ** 4+ -1234", answer: "2246.75" },
	{ line: "(123) * 234 / 3 ** 5 + 4 * 8 / (23 ** 3 ) * 2", answer: "118.4497045743039" },
	{ line: "1234 % 11 / 3+ (4 +123) *9 ** (2-5+7) / 10", answer: "83325.36666666667" },
	{ line: "-2 * -4 * -8", answer: "-64" },
	{ line: "-8/-4/-2", answer: "-1" },
	{ line: "(3 - 3* 2) / -1", answer: "3" },
	{ line: "(123) * 234 / -3 ** 1.5 ** -(2)", answer: "-17663.10610686539" },
	{ line: "27*234/-3+2**1.5**-(2)-2", answer: "-2106.6392099998257" },
	{ line: "2**-2**-3", answer: "0.9170040432046712" },
	{ line: "-(-(-8))", answer: "-8" },
	{ line: "-+2*-+2**-+2", answer: "0.5" },
	{ line: "-2/-00", answer: "-Infinity" },
	{ line: "2&1 ^ 3-1^4-5**~-1", answer: "1" },
	{ line: "~70/5+(12 ^ 42)*(31--- ---4)", answer: "1315.8" }
];

function test() {
	console.log("Test started");
	for (let i in TEST_DATA) {
		let line = TEST_DATA[i].line;
		let line_new = line.replace(/\*\*/g, '@').match(/[0-9.]+|[()+\-*/%@&|^~]/g);
		line_new = replace_sign(line_new);
		answer = process_postfix(infix2postfix(line_new));
		if (answer != TEST_DATA[i].answer) {
			console.error(answer);
			console.error(TEST_DATA[i].answer);
			throw new Error("Unmatched answers");
		}
	}
	console.log("Test finished");
}

window.onload = (event) => {
	document.querySelector("#form").onsubmit = document.querySelector("#submit").onclick = (event) => {
		event.preventDefault();
		click_submit();
	}
	document.querySelector("#paren_lt").onclick = (event) => document.getElementById("line").value += '(';
	document.querySelector("#paren_rt").onclick = (event) => document.getElementById("line").value += ')';
	document.querySelector("#plus").onclick = (event) => document.getElementById("line").value += '+';
	document.querySelector("#minus").onclick = (event) => document.getElementById("line").value += '-';
	document.querySelector("#times").onclick = (event) => document.getElementById("line").value += '*';
	document.querySelector("#over").onclick = (event) => document.getElementById("line").value += '/';
	document.querySelector("#round").onclick = (event) => document.getElementById("line").value += '%';
	document.querySelector("#power").onclick = (event) => document.getElementById("line").value += '**';
	document.querySelector("#and").onclick = (event) => document.getElementById("line").value += '&';
	document.querySelector("#or").onclick = (event) => document.getElementById("line").value += '|';
	document.querySelector("#xor").onclick = (event) => document.getElementById("line").value += '^';
	document.querySelector("#not").onclick = (event) => document.getElementById("line").value += '~';
	try {
		test();
	} catch (err) {
		console.error(err.message);
		confirm("Test error. Please quit.");
	}
}
