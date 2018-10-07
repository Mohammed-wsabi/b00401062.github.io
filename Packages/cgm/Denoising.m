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
			legend(this.table.Properties.RowNames)
			ylabel("log(#Sequences)")
			xticks(1:width(this.table))
			xticklabels(this.table.Properties.VariableNames)
			legend("Location", "southwest", "Box", "off")
			set(gca, "YScale", "log")
			set(gca, "TickLabelInterpreter", "none")
			set(findobj(gcf, "Type", "Line"), "LineWidth", 1.5);
			saveas(gcf, this.outfile)
			hold("off")
		end
	end
end
