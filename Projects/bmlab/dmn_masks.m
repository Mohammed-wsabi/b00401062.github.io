addpath '/usr/local/spm12'

THRESH <- 0.7
ROIS = { ...
	'Angular_L', 'Angular_R', ...
	'Cingulum_Post_L', 'Cingulum_Post_R', ...
	'Frontal_Med_Orb_L', 'Frontal_Med_Orb_R', ...
	'Frontal_Sup_Medial_L', 'Frontal_Sup_Medial_R', ...
	'Hippocampus_L', 'Hippocampus_R', ...
	'ParaHippocampal_L', 'ParaHippocampal_R', ...
	'Precuneus_L', 'Precuneus_R', ...
	'SupraMarginal_L', 'SupraMarginal_R', ...
};

parfor i = 1:60
	sbj = sprintf('TADZ0%02d', i);
	display(['== Session ', sbj, ' ==']);
	c1 = spm_vol(['./Databank/', sbj, '/CONVERTED_FAA/c1MPRAGESag1mmisog2s003a1001.nii']);
	c1_vols = spm_read_vols(c1);
	mask = c1;
	mask.fname = ['./Datasets/DMN_Masks/', sbj, '_DMN_Mask.nii'];
	mask_vols = zeros(c1.dim);
	for j = 1:length(ROIS)
		roi = spm_vol(['./Databank/', sbj, '/ROIDeformSubj/aalwholebrain/w', ROIS{j}, '.nii']);
		mask_vols = mask_vols | spm_read_vols(roi);
	end
	spm_write_vol(mask, (c1_vols > THRESH) & mask_vols);
end

parfor i = 1:60
	mkdir(sprintf('./Datasets/DMN_Masks/TADZ0%02d', i))
	for j = 1:length(ROIS)
		from = sprintf('/home/luo82/Datasets/Templates/TADZ0%02d_Template.nii', i);
		to = sprintf('./Datasets/DMN_Masks/TADZ0%02d/w%s.nii', i, ROIS{j});
		command = ['ln -s ', from, ' ', to];
		system(command);
	end
end

parfor i = 1:60
	from = sprintf('./Datasets/DMN_Masks/TADZ0%02d_DMN_Mask.nii', i);
	to = sprintf('./Datasets/Templates/TADZ0%02d_Template.nii', i);
	spm_mask(from, to, 0.7);
	for j = 1:length(ROIS)
		from = sprintf('./Databank/TADZ0%02d/ROIDeformSubj/aalwholebrain/w%s.nii', i, ROIS{j});
		to = sprintf('./Datasets/DMN_Masks/TADZ0%02d/w%s.nii', i, ROIS{j});
		spm_mask(from, to, 0.7);
	end
end
