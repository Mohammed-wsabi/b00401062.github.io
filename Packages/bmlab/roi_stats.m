% Constants
ROIS = { ...
	'wAngular_L', 'wAngular_R', ...
	'wCingulum_Post_L', 'wCingulum_Post_R', ...
	'wFrontal_Med_Orb_L', 'wFrontal_Med_Orb_R', ...
	'wFrontal_Sup_Medial_L', 'wFrontal_Sup_Medial_R', ...
	'wHippocampus_L', 'wHippocampus_R', ...
	'wParaHippocampal_L', 'wParaHippocampal_R', ...
	'wPrecuneus_L', 'wPrecuneus_R', ...
	'wSupraMarginal_L', 'wSupraMarginal_R' ...
};
TASKS = { 'Rest', 'Rest1', 'Rest2' 'Epso', 'Self', 'Verb' };
TASKS_SHORT = { 'R', 'R1', 'R1' 'M', 'S', 'L' };
SUBJECTS = 1:60;
SUBJECTS([8 15 21 48]) = [];
ALPHA = .05;

% SUBJECT_TASK_ROIS
SUBJECT_TASK_ROIS = {};
for i = 1:60
	sbj = sprintf('TADZ0%02d', i);
	if i <= 46; tasks = [1 4 5 6]; else; tasks = 2:6; end;
	for j = tasks
		fname = sprintf('./Datasets/ROI_Stats/%s_%s.csv', sbj, TASKS{j});
		SUBJECT_TASK_ROIS{i, j} = csvread(fname, 1, 1);
	end
end
SUBJECT_TASK_ROIS(47:60, 1) = SUBJECT_TASK_ROIS(47:60, 3);

% STRENGTH_TABLE
STRENGTH_TABLE = {};
for i = 1:60
	if i <= 46; tasks = [1 4 5 6]; else; tasks = 1:6; end;
	for j = tasks
		r = corr(SUBJECT_TASK_ROIS{i, j}');
		STRENGTH_TABLE{i, j} = (1 + r) ./ (1 - r) / 2;
	end
end

% P_TABLE_TASK and P_TABLE_REST
P_TABLE_TASK = [];
P_TABLE_REST = [];
for roi1 = 1:16
	for roi2 = 1:(roi1-1)
		display(sprintf('== Session %s and %s ==', ROIS{roi1}, ROIS{roi2}));
		table = [];
		for i = 1:60
			if i <= 46; tasks = [1 4 5 6]; else; tasks = 1:6; end;
			for j = tasks
				table(i, j) = STRENGTH_TABLE{i, j}(roi1, roi2);
			end
		end
		table = table(SUBJECTS, :);
		P_TABLE_TASK(roi1, roi2) = anova1(table(:, [1 4 5 6]), [], 'off');
		[~, P_TABLE_REST(roi1, roi2)] = ttest(table(44:end, 2), table(44:end, 3));
	end
end

csvwrite('./Datasets/ROI_Stats/P_TABLE_TASK.csv', P_TABLE_TASK);
csvwrite('./Datasets/ROI_Stats/P_TABLE_REST.csv', P_TABLE_REST);
P_TABLE_TASK = csvread('./Datasets/ROI_Stats/P_TABLE_TASK.csv');
P_TABLE_REST = csvread('./Datasets/ROI_Stats/P_TABLE_REST.csv');

% Display p tables with Bonferroni correction
for p_table = { P_TABLE_TASK, P_TABLE_REST }
	hold on
	imagesc(-log10(p_table(end:-1:2, :)))
	xlim([1 16] - .5)
	ylim([1 16] - .5)
	caxis([0 -log10(ALPHA/2/120)])
	colormap hot
	h = colorbar;
	set(get(h, 'title'), 'string', '-log10(p)');
	ax = gca;
	ax.XTick = 1:15;
	ax.YTick = 1:15;
	ax.XTickLabel = strrep(strrep(ROIS(1:15), '_', ' '), 'w', '');
	ax.YTickLabel = strrep(strrep(ROIS(end:-1:2), '_', ' '), 'w', '');
	ax.XTickLabelRotation = 45;
	for x = find(p_table(end:-1:2, :) < ALPHA/2/120 & p_table(end:-1:2, :) ~= 0)
		plot(floor((x-1)/15)+1, mod(x-1, 15)+1, 'k*')
	end
	hold off
end

% Post-hoc pairwise comparison
for roi1 = 1:16
	for roi2 = 1:(roi1-1)
		if P_TABLE_TASK(roi1, roi2) > ALPHA/2/120
			continue
		end
		roi1_str = strrep(strrep(ROIS{roi1}, '_', ' '), 'w', '');
		roi2_str = strrep(strrep(ROIS{roi2}, '_', ' '), 'w', '');
		display(sprintf('== Session %s and %s ==', ROIS{roi1}, ROIS{roi2}));
		table = [];
		for i = 1:60
			if i <= 46; tasks = [1 4 5 6]; else; tasks = 1:6; end;
			for j = tasks
				table(i, j) = STRENGTH_TABLE{i, j}(roi1, roi2);
			end
		end
		table = table(SUBJECTS, [1 4 6 5]);
		main = sprintf('FC of %s and %s', roi1_str, roi2_str);
		fname = sprintf('./Datasets/ROI_Stats/%s_and_%s.png', ROIS{roi1}, ROIS{roi2});
		stats_task(table, main, fname);
	end
end
