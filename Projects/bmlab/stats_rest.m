function stats_rest(table, main, fname)
	% Constants
	TASKS = { 'Rest', 'Rest1', 'Rest2' 'Epso', 'Self', 'Verb' };
	TASKS_SHORT = { 'R', 'R1', 'R2' 'M', 'S', 'L' };
	ALPHA = .05;
	% Compare rests
	[nrow, ncol] = size(table);
	assert(nrow == 13);
	assert(ncol == 2);
	[~, p] = ttest(table(:,1), table(:,2));
	assert(p < ALPHA/2)
	means = mean(table, 1);
	stderrs = std(table, 1)/sqrt(13);
	clf
	hold on
	bar(1:2, means, 'b', 'BarWidth', .5);
	errorbar(1:2, means, stderrs, 'k.', 'LineWidth', 2);
	plot(1:2, means + stderrs * 2, 'r*', 'MarkerSize', 12);
	title(main);
	xlabel('Rest');
	ylabel('FC (z score)');
	ax = gca;
	ax.XTick = 1:2;
	ax.XTickLabel = TASKS_SHORT(2:3);
	hold off
	saveas(1, fname);
end
