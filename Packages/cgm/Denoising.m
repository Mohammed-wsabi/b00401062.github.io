classdef Denoising
	properties
		infile
		outfile
		table
	end
	methods
		function this = Denoising(infile, outfile)
			this.infile = infile;
			this.outfile = outfile;
			this.table = readtable( ...
				this.infile, ...
				"ReadRowNames", true, ...
				"Filetype", "text", ...
				"Delimiter", "\t", ...
				"CommentStyle", "#" ...
			);
		end
		function plot(this)
			hold("on")
			for i = 1:height(this.table)
				plot(this.table{i, :})
			end
			set(gca, "YScale", "log")
			set(gca, "TickLabelInterpreter", "none")
			xticks(1:width(this.table))
			xticklabels(this.table.Properties.VariableNames)
			ylabel("log(#Sequences)")
			legend(this.table.Properties.RowNames)
			legend("Location", "southwest", "Box", "off")
			set(findobj(gcf, "Type", "Line"), "LineWidth", 2);
			saveas(gcf, this.outfile)
			hold("off")
		end
	end
end
