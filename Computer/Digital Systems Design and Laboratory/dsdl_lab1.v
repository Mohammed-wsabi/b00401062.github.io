module Seg7(input [3:0] DIG_In, output reg [7:0] DIG_Out);
	always @(DIG_In) begin
		case (DIG_In)
			4'b0000: DIG_Out <= 8'b11000000; // 0
			4'b0001: DIG_Out <= 8'b11111001; // 1
			4'b0010: DIG_Out <= 8'b10100100; // 2
			4'b0011: DIG_Out <= 8'b10110000; // 3
			4'b0100: DIG_Out <= 8'b10011001; // 4
			4'b0101: DIG_Out <= 8'b10010010; // 5
			4'b0110: DIG_Out <= 8'b10000011; // 6
			4'b0111: DIG_Out <= 8'b11111000; // 7
			4'b1000: DIG_Out <= 8'b10000000; // 8
			4'b1001: DIG_Out <= 8'b10010000; // 9
			4'b1010: DIG_Out <= 8'b01111111; // dp
			default: DIG_Out <= 8'b11111111; // off
		endcase
	end
endmodule

module Seg7forD3(input  [3:0] DIG_In, input Sign, input [3:0] operator, output reg [7:0] DIG_Out);
	always @(DIG_In) begin
		case (DIG_In)
			4'b0000: DIG_Out <= 8'b11000000; // 0
			4'b0001: DIG_Out <= 8'b11111001; // 1
			4'b0010: DIG_Out <= 8'b10100100; // 2
			4'b0011: DIG_Out <= 8'b10110000; // 3
			4'b0100: DIG_Out <= 8'b10011001; // 4
			4'b0101: DIG_Out <= 8'b10010010; // 5
			4'b0110: DIG_Out <= 8'b10000011; // 6
			4'b0111: DIG_Out <= 8'b11111000; // 7
			4'b1000: DIG_Out <= 8'b10000000; // 8
			4'b1001: DIG_Out <= 8'b10010000; // 9
			4'b1010: DIG_Out <= 8'b01111111; // dp
			default: DIG_Out <= 8'b11111111; // off
		endcase
   		if (Sign == 1 && operator == 4'b0100)
			DIG_Out <= 8'b10111111;
	end
endmodule

module input_clip(input [6:0] iNum, output reg[6:0] oNum);
	always @(iNum) begin
		if (iNum > 7'd99)
			oNum = 7'd99;
		else
			oNum = iNum;
	end
endmodule

module num_split(input [13:0] iNum, output reg [15:0] oNum);
	always @(iNum) begin
		oNum[3:0] = iNum % 10;
		oNum[7:4] = iNum / 10 % 10;
		oNum[11:8] = iNum / 100 % 10;
		oNum[15:12] = iNum / 1000 % 10;
	end
endmodule

module calculate(input [6:0] iA, input [6:0] iB, input [3:0] operator, output reg [13:0] out);
	always @(iA or iB or operator) begin
		case (operator)
			4'b1000: out = iA + iB;
			4'b0100:
				if (iA > iB)
					out = iA - iB;
				else
					out = iB - iA;
			4'b0010: out = iA * iB;
			4'b0001: out = iA / iB;
			4'b1111: out = iA % iB;
		endcase
	end
endmodule

module dsdl_lab1(input [17:0] SW,
	output reg [7:0] HEX0, HEX1, HEX2, HEX3, HEX4, HEX5, HEX6, HEX7);
	wire [3:0] D[7:0];
	wire [13:0] OUT;
	wire [6:0] oSW[1:0];
	input_clip clip1(SW[17:11], oSW[0]);
	input_clip clip2(SW[10:4], oSW[1]);
	calculate calc(oSW[0], oSW[1], SW[3:0], OUT);
	num_split oDigit0(OUT, {D[3], D[2], D[1], D[0]});
	num_split oDigit1(oSW[0], {D[7], D[6]});
	num_split oDigit2(oSW[1], {D[5], D[4]});
	Seg7 h0(D[0], HEX0);
	Seg7 h1(D[1], HEX1);
	Seg7 h2(D[2], HEX2);
	Seg7forD3 h3(D[3], oSW[0] < oSW[1], SW[3:0], HEX3);
	Seg7 h4(D[4], HEX4);
	Seg7 h5(D[5], HEX5);
	Seg7 h6(D[6], HEX6);
	Seg7 h7(D[7], HEX7);
endmodule
