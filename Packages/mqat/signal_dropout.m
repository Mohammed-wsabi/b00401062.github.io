%%
% Signal dropout detection
% Developed by Judith, Changle and Edward
% Target: DropoutNo
str=['cd(' '''' 'F:\MTLE_BrainAge\MTLE_HS\Control' '''' ');' ];
eval(str);
dirdata=dir;
filename={dirdata.name};
filename=filename([dirdata.isdir]==1);
filename(1:2)=[];
old_path=pwd;
DropoutNo = [];
for sub=1:numel(filename)
    str=['cd' ' ' old_path '\' char(filename{sub}) '\anatomy'];
    eval(str)
    Data=[]; subdir={};
    subdir=dir;
    subfilename={subdir.name};
    for search=1:numel(subfilename)
        fdind=strfind(subfilename{search},'DSI.nii');
        if fdind~=0
            getit=search;
        end
    end
    %     b=dir;
    condition=['y=spm_vol(' '''' char(subfilename(getit)) '''' ');'];
    eval(condition)
    nii=y.private;
    p=0;
    list=[];
    for volume=1:102
        wsi=nii.dat(:,:,:,volume);
        Max=max(max(max(wsi)));
        Min=min(min(min(wsi)));
        wsi=wsi./(Max-Min);
        wsi=wsi(30:59,30:59,1:56);%30*30 matrix
        for i=1:56
            ima=wsi(:,:,i);
            value(i)=mean(mean(ima));
        end  
        pro=diff(value);
        for x=1:54
            A=abs(pro(x)-pro(x+1));
            if A>0.1 && pro(x)<pro(x+1)
                p=p+1;
                list(1,p)=volume;
                list(2,p)=x+1;
            end
        end
    end
    y=list;
    em=isempty(list);
    if   em==1
        DropoutNo(sub,1)=0;
    elseif em==0
        DropoutNo(sub,1)=length(list);
    end 
    fprintf('Done for one subject: %g  \n',sub);
end








