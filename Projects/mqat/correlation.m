%%
cd F:\MTLE_BrainAge\MTLE_HS\Control

dirdata=dir;
filename = dirdata([dirdata.isdir]);
filename={filename.name};
filename(1:2) = [];
old_path=pwd;
Corr=[];
for sub=1:numel(filename)
    str=[]; str2=[]; str3=[]; str4=[]; Img=[]; temp=[];
    str=['cd' ' ' old_path '\' char(filename{sub})];
    eval(str)
    subdir=dir;
    subfilename={subdir.name};
    getit=[];
    for search=1:numel(subfilename)
        fdind=strfind(subfilename{search},'lddmm');
        if fdind~=0
            getit=search;
        end
    end
    if isempty(getit)==0
        str2=['cd' ' ' old_path '\' char(filename{sub}) '\' char(subfilename(getit))];
        eval(str2)
        dirdatacorr=dir;
        filenamecorr={dirdatacorr.name}';
        getit2=[];
        for search=1:numel(filenamecorr)
            fdind=strfind(filenamecorr{search},'corr');
            if fdind~=0
                getit2=[getit2,search];
            end
        end
        if length(getit2)==1
            Corr=[Corr;str2num(filenamecorr{getit2}(6:11))];
        else
            Corr=[Corr;999]; % if there are more than one corr files, output:999
        end
    elseif isempty(getit)==1
        Corr=[Corr;0.0001]; % iif there is no corr file, output : 0.0001
    end
    
end

%%
% To get age, gender and scan date

cd D:\ForYuHong
dirdata=dir;
dirda = dirdata([dirdata.isdir]);
filename={dirda.name};
filename(1:2)=[];
old_path=pwd;
Age=[]; Gender=[]; Time=[];
for sub=1:numel(filename)
    str=[]; str3=[]; str4=[]; Img=[]; temp=[];
    str=['cd' ' ' old_path '\' char(filename{sub})];
    eval(str)
    subdir=dir;
    subfilename2={subdir.name};

    for search2=1:numel(subfilename2)
    fdind2=strfind(subfilename2{search2},'MPRAGE');
    if fdind2~=0
        getit2=search2;
    end
    end
    str3=['cd(' '''' old_path '\' char(filename{sub}) '\' char(subfilename2(getit2)) '''' ');'];
    eval(str3)
% get dropout numbers of each image in the current folder
        temp3=[];
        temp3=dicominfo('MPRAGE_256_i00001.dcm');
        Age(sub,1)=str2num(temp3.PatientAge(2:3));
        if strcmp(temp3.PatientSex,'M')==1
        Gender(sub,1)=1;     
        elseif strcmp(temp3.PatientSex,'F')==1
        Gender(sub,1)=0;     
        end
        Time(sub,1)=str2num(temp3.StudyDate);
end
filename=filename';
