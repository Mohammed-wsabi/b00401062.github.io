addpath '/usr/local/spm12'

% MNI Cerebrum
fnames = dir('/bml/Data/Bank5/TAD/aal_cerebral/');
fnames = { fnames(3:end).name };
header = spm_vol(['/bml/Data/Bank5/TAD/aal_cerebral/', fnames{1}]);
header.fname = './Datasets/MNI_Collect/MNI_Cerebrum.nii';
vols = zeros(header.dim);
for i = 1:length(fnames)
	display(sprintf('== Session %s ==', fnames{i}));
	vols = vols | spm_read_vols(spm_vol(['/bml/Data/Bank5/TAD/aal_cerebral/', fnames{j}]));
end
spm_write_vol(header, vols)

% MNI DMN
fnames = dir('./Datasets/AAL_Collect/');
fnames = { fnames(3:end).name };
header = spm_vol(['/bml/Data/Bank5/TAD/aal_cerebral/', fnames{1}]);
header.fname = './Datasets/MNI_Collect/MNI_DMN.nii';
vols = zeros(header.dim);
for i = 1:length(fnames)
    display(sprintf('== Session %s ==', fnames{i}));
	vols = vols | spm_read_vols(spm_vol(['./Datasets/AAL_Collect/', fnames{i}]));
end
spm_write_vol(header, vols)

% Template for future image IO
template = spm_vol('./Datasets/Group_ICA/dmn_mean_component_ica_s_all_.nii');
template = template(1);
template.fname = ['./Datasets/MNI_Collect/MNI_TEMPLATE.nii'];
spm_write_vol(template, ones(template.dim));

% Apply DMN mask
spm_mask('./Datasets/MNI_Collect/MNI_DMN.nii', './Datasets/MNI_Collect/MNI_TEMPLATE.nii', 0.7)
