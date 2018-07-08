addpath '/usr/local/spm12'

for i = 1:60
	img1 = sprintf('/home/luo82/Databank/TADZ0%02d/CONVERTED_FAA/c1MPRAGESag1mmisog2s003a1001.nii', i);
	if i <= 46
		tasks = { 'REST', 'EPSO', 'SELF', 'VERB' };
	else
		tasks = { 'REST', 'REST1', 'REST2', 'EPSO', 'SELF', 'VERB' };
	end
	for j = 1:length(tasks)
		display(sprintf('== Session TADZ0%02d > %s ==', i, tasks{j}))
		img2 = sprintf('/home/luo82/Datasets/Cluster_Analysis/TADZ0%02d_MFCA%s.nii', i, tasks{j});
		slover_batch({ img1; img2 }, { 'Structural', 'Blobs' });
		hgexport(gcf, sprintf('/home/luo82/Datasets/Cluster_Display/TADZ0%02d_MFCA%s', i, tasks{j}));
	end
end
