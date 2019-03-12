addpath '/usr/local/spm12'

for i = 1:60
	c1_fname = sprintf('./Databank/TADZ0%02d/CONVERTED_FAA/c1MPRAGESag1mmisog2s003a1001.nii', i);
	if i <= 46
		tasks = { 'Rest', 'Epso', 'Self', 'Verb' };
	else
		tasks = { 'Rest1', 'Rest2', 'Epso', 'Self', 'Verb' };
	end
	for j = 1:length(tasks)
		dest = sprintf('./Databank/TADZ0%02d/RESULT/FCA_%s_detrend_filtered/Filtered_4DVolume.nii', i, tasks{j});
		spm_mask(c1_fname, dest, 0.7);
		mkdir('./Databank/TADZ0%02d/RESULT/FCA_%s_detrend_filtered_masked', i, tasks{j});
		from = sprintf('./Databank/TADZ0%02d/RESULT/FCA_%s_detrend_filtered/mFiltered_4DVolume.nii', i, tasks{j});
		to = sprintf('./Databank/TADZ0%02d/RESULT/FCA_%s_detrend_filtered_masked/mFiltered_4DVolume.nii', i, tasks{j});
		movefile(from, to);
	end
end
