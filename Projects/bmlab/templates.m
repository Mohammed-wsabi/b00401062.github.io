addpath '/usr/local/spm12'

parfor i = 1:60
	sbj = sprintf('TADZ0%02d', i);
    template = spm_vol(['./Databank/', sbj, '/RESULT/FCA_Verb_detrend_filtered_masked/mFiltered_4DVolume.nii']);
    template = template(1);
    template.fname = ['./Datasets/Templates/', sbj, '_Template.nii'];
	spm_write_vol(template, ones(template.dim));
end
