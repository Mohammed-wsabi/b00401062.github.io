SUBJECTS = 1:60;
SUBJECTS([8 15 21 48]) = [];
for i = SUBJECTS
	if i <= 46; img_id = 9; else; img_id = 10; end
	system(sprintf('mkdir ./Datasets/Converted_FAA/TADZ0%02d', i));
	from = sprintf('~/Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211RESTs0%02da001.nii_detrend_filtered/Filtered_4DVolume.nii', i, img_id);
	system(['ln -s ', from, sprintf(' ./Datasets/Converted_FAA/TADZ0%02d/', i)]);
end
