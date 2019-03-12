addpath '/usr/local/spm12'

% Constants
TASKS = { 'Epso', 'Self', 'Verb' };

% Regress task HRF out of filtered images
for i = 1:60
	if i <= 46
		fnames = { 'wraep2dmoco4mm211As006a001', 'wraep2dmoco4mm211Cs008a001', 'wraep2dmoco4mm211Bs007a001' };
	else
		fnames = { 'wraep2dmoco4mm211As007a001', 'wraep2dmoco4mm211Cs009a001', 'wraep2dmoco4mm211Bs008a001' };
	end
	for j = 1:length(TASKS)
		load(sprintf('/bml/Data/Bank5/TAD/Subject/TADZ0%02d/BATCH/FAA_%s_auto_1st_est_.mat', i, TASKS{j}))
		matlabbatch{1}.spm.stats.fmri_spec.dir = { sprintf('/home/luo82/Datasets/Residuals/TADZ0%02d/%s/', i, TASKS{j}) };
		for k = 1:211
			matlabbatch{1}.spm.stats.fmri_spec.sess.scans{k, 1} = sprintf('./Datasets/Filtered/TADZ0%02d/%s.nii_detrend_filtered/Filtered_4DVolume.nii,%d', i, fnames{j}, k);
		end
		matlabbatch{2}.spm.stats.fmri_est.write_residuals = 1;
		spm_jobman('run', matlabbatch);
	end
end

% Combine 3D residual volumes to 4D
load('./Datasets/Residuals/Residuals.mat')
for i = 1:60
	for j = 1:length(TASKS)
		matlabbatch{1}.spm.util.cat.name = sprintf('./Datasets/Residuals/TADZ0%02d/%s/Residual.nii', i, TASKS{j});
		for k = 1:211
			matlabbatch{1}.spm.util.cat.vols{k, 1} = sprintf('./Datasets/Residuals/TADZ0%02d/%s/Res_0%03d.nii,1', i, TASKS{j}, k);
		end
		spm_jobman('run', matlabbatch);
	end
end
