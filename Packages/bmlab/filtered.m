addpath './Applications/REST_V1.8_130615'

parfor i = 1:46
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211As006a001.nii', i), '_detrend');
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211Bs007a001.nii', i), '_detrend');
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211Cs008a001.nii', i), '_detrend');
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211RESTs009a001.nii', i), '_detrend');
end

parfor i = 47:60
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211As007a001.nii', i), '_detrend');
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211Bs008a001.nii', i), '_detrend');
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211Cs009a001.nii', i), '_detrend');
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211RESTs006a001.nii', i), '_detrend');
	rest_detrend(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211RESTs010a001.nii', i), '_detrend');
end

parfor i = 1:46
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211As006a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211Bs007a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211Cs008a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211RESTs009a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
end

parfor i = 47:60
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211As007a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211Bs008a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211Cs009a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211RESTs006a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
	rest_bandpass(sprintf('./Datasets/Filtered/TADZ0%02d/wraep2dmoco4mm211RESTs010a001.nii_detrend', i), 2 , 0.08 , 0.009 , 'Yes', 0);
end
