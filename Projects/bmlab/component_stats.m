addpath '/usr/local/spm12'

% Constants
TASKS = { 'Rest', 'Rest1', 'Rest2' 'Epso', 'Self', 'Verb' };
TASKS_SHORT = { 'R', 'R1', 'R1' 'M', 'S', 'L' };
SUBJECTS = 1:60;
SUBJECTS([8 15 21 48]) = [];
MAX_SET = [4 10 16];
ALPHA = .05

% STRENGTH_TABLE
STRENGTH_TABLE = zeros(56, length(TASKS));
for i = 1:56
	mask = spm_read_vols(spm_vol(sprintf('./Datasets/DMN_Zmaps/mTADZ0%02d.nii', SUBJECTS(i))));
	mask = reshape(mask, [], length(MAX_SET)) > 2;
	mask = sum(mask, 2) > 0;
	if SUBJECTS(i) <= 46; tasks = [1 4 5 6]; else; tasks = 2:6; end;
	for j = tasks
		display(sprintf('== Session TADZ0%02d > %s ==\n', SUBJECTS(i), TASKS{j}));
		fname = sprintf('./Datasets/Residuals/TADZ0%02d/%s/Residual.nii', SUBJECTS(i), TASKS{j});
		img = reshape(spm_read_vols(spm_vol(fname)), [], 211);
		img = img(mask, :);
		cor_mat = corr(img');
		cor_vec = cor_mat(find(tril(ones(sum(mask)), -1)));
		z_vec = (1 + cor_vec) ./ (1 - cor_vec) / 2;
		STRENGTH_TABLE(i, j) = nanmean(z_vec);
	end
	fname = sprintf('./Datasets/Component_Stats/strength_table_TADZ0%d.csv', SUBJECTS(i));
	csvwrite(fname, STRENGTH_TABLE(i, :));
end

STRENGTH_TABLE(44:end, 1) = STRENGTH_TABLE(44:end, 3);
csvwrite('./Datasets/Component_Stats/strength_table.csv', STRENGTH_TABLE);
STRENGTH_TABLE = csvread('./Datasets/Component_Stats/strength_table.csv');

% Compare tasks
table = STRENGTH_TABLE(:, [1 4 6 5]);
main = 'FC Analysis with Independent Component Analysis';
fname = './Datasets/Component_Stats/component_stats_task.png';
stats_task(table, main, fname);

% Compare rests
table = STRENGTH_TABLE(44:end, 2:3);
main = 'FC Analysis with Independent Component Analysis';
fname = './Datasets/Component_Stats/component_stats_rest.png';
stats_rest(table, main, fname);
