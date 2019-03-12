%%
% Calculate SNR from DSI data sets for Quality Assurance
% Input: Main directory
% Object: DSI.nii
% Target: SNRmap
cd F:\MTLE_BrainAge\MTLE_HS\Control
dirdata=dir;
dirdata(1:2)=[];
filename1={dirdata.name};
filename = filename1([dirdata.isdir]==1);
old_path=pwd;
SNRmap = zeros(102,numel(filename)); 
for sub=1:numel(filename)
    cd(fullfile(fullfile(old_path,char(filename{sub})),'anatomy'));
    temp = dir('*DSI.nii');
    DSI = nifti({temp.name});
    DSI_img = double(DSI.dat); clear DSI;
    tempSNR = zeros(1,102);
    for direc = 1:102
        iniSNR = zeros(1,8);
        for sli = 1:8
            Signal = mean(mean(DSI_img(31:50,31:50,40+sli,direc)));
            Noise = zeros(20,20);
            Noise(1:10,1:10) = DSI_img(1:10,1:10,40+sli,direc);
            Noise(11:20,1:10) = DSI_img(71:80,1:10,40+sli,direc);
            Noise(1:10,11:20) = DSI_img(1:10,71:80,40+sli,direc);
            Noise(11:20,11:20) = DSI_img(71:80,71:80,40+sli,direc);
            iniSNR(1,sli) = Signal/(1.5264*std(reshape(Noise,1,400)));
        end
        tempSNR(1,direc) = mean(iniSNR);
    end
    SNRmap(:,sub) = tempSNR';
    fprintf('Done for sub: %g ..... \n',sub);
end
SNR = mean(SNRmap)';