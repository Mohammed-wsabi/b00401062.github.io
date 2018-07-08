addpath '/usr/local/spm12'

SUBJECTS = 1:60;
SUBJECTS([8 15 21 48]) = [];

parfor i = 1:56
	from = './Datasets/MNI_Collect/MNI_Cerebrum.nii';
	to = sprintf('./Datasets/DMN_Zmaps/TADZ0%02d.nii', SUBJECTS(i));
	spm_mask(from, to, 0.7);
end
