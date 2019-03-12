function stats_task(table, main, fname)
	% Constants
	TASKS = { 'Rest', 'Rest1', 'Rest2' 'Epso', 'Self', 'Verb' };
	TASKS_SHORT = { 'R', 'R1', 'R2' 'M', 'S', 'L' };
	ALPHA = .05;
	% Compare tasks
	[nrow, ncol] = size(table);
	assert(nrow == 56);
	assert(ncol == 4);
	[p, ~, stats] = anova1(table, [], 'off');
	assert(p < ALPHA/2)
	res = multcompare(stats, 'CType', 'bonferroni');
	sig = find(res(:, 6) < ALPHA/2);
	means = stats.means([2 3 4 1]);
	stderrs = stats.s/sqrt(56);
	clf
	hold on
	bar(1:4, means, 'b', 'BarWidth', .5);
	errorbar(1:4, means, stderrs * ones(1, 4), 'k.', 'LineWidth', 2);
	plot(sig, means(sig) + stderrs * 2, 'r*', 'MarkerSize', 12);
	title(main);
	xlabel('Task');
	ylabel('FC (z score)');
	ax = gca;
	ax.XTick = 1:4;
	ax.XTickLabel = TASKS_SHORT([4 6 5 1]);
	hold off
	saveas(1, fname);
end
