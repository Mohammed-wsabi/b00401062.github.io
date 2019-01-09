%%
% Correct shaking artifact
% Developed by Changle
% Target: NaN
Mainpath='E:\MBSR_Expert\Control';
pathstr=['cd' ' ' Mainpath];
eval(pathstr)
folderlist=dir;
foldername={folderlist.name};
for people=1:numel(foldername)-2
    pathstr2=['cd' ' ' Mainpath '\' foldername{people+2} '\' 'anatomy'];
    eval(pathstr2);
    Lamda=1;
    iteration=1;
    while Lamda==1
        %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        Data=[]; subdir={};
        subdir=dir;
        subfilename={subdir.name};
        for search=1:numel(subfilename)
            fdind=strfind(subfilename{search},'DSI.nii');
            if fdind~=0
                getit=search;
            end
        end
        condition=['Data=nifti(' '''' char(subfilename(getit)) '''' ');'];
        eval(condition)
        
        Vol=Data.dat;
        window=Vol;
        B0=window(:,:,:,2);
        overallmask=[];
        for slice=1:56
            nB0=[]; dB0=[]; sB0=[]; dim=[]; Line=[]; count=[]; edges=[]; Diff=[]; thresh=[]; rsB0=[];
            nB0=(B0(:,:,slice)-min(min(B0(:,:,slice))))/(max(max(B0(:,:,slice)))-min(min(B0(:,:,slice))));
            dB0=wiener2(nB0,[3 3],6);
            dB02=wiener2(dB0,[3 3],6);
            sB0=imsharpen(dB02,'Radius',1,'Amount',1.5,'Threshold',[0.2]);
            sB0(find(sB0<0.1))=0;
            sB0(find(sB0>0))=1;
            sB0(:,:)=imfill(sB0(:,:));
            
            stepu=1;
            pixel=0;
            while pixel<5
                pixel=sum(sB0(stepu,:));
                stepu=stepu+1;
            end
            stepu=stepu-1;
            stepd=80;
            pixel=0;
            while pixel<5
                pixel=sum(sB0(stepd,:));
                stepd=stepd-1;
            end
            stepd=stepd+1;
            rsB0=imresize(sB0,1.1,'bilinear');
            rsB0(find(rsB0>0))=1;
            rsB0(5:stepu+3,:)=0;
            rsB0(stepd+5:88,:)=0;
            overallmask(:,:,slice)=rsB0(5:84,5:84);
        end
        
        DNRLrecord=[];
        ADNRLrecord=[];
        SDNRLrecord=[];
        for n=1:102
            for m=1:56
                RN=[]; NRL=[]; h=[];
                RN=window(:,:,m,n);
                RN=RN.*overallmask(:,:,m);
                NRL=(RN-min(min(RN)))/(max(max(RN))-min(min(RN)));
                ADNRLrecord(:,:,m,n)=NRL;
                DNRL=wiener2(NRL,[3 3],6);
                DNRL2=wiener2(DNRL,[3 3],6);
                DNRLrecord(:,:,m,n)=DNRL2;
                SH=imsharpen(DNRL2,'Radius',1,'Amount',1.5,'Threshold',[0.2]);
                SDNRLrecord(:,:,m,n)=SH;
            end
        end
        
        threcord=[];
        Countrecord={};
        maskrecord=[];
        Edgerecord={};
        Diffrecord={};
        for i=1:102
            mask=squeeze(SDNRLrecord(:,:,:,i));
            dim=size(mask);
            Line=reshape(mask(:,:,:),1,prod(dim));
            [count,edges]=histcounts(Line);
            Countrecord{i,1}=count;
            Edgerecord{i,1}=edges;
            Diff=diff(count);
            Diffrecord{i,1}=Diff;
            thresh=edges(max(find(Diff<-350)));
            if thresh>0.11
                thresh=0.11;
            elseif thresh<0.06
                thresh=0.06;
            end
            threcord(i)=thresh;
            mask(find(mask<thresh))=0;
            mask(find(mask>0))=1;
            x=[]; y=[];
            for j=1:56
                mask(:,:,j)=imfill(mask(:,:,j));
            end
            maskrecord(:,:,:,i)=mask;
        end
        
        BShift=[]; A=[];  DG=[];
        for k=1:102
            DG=k;
            Series=[29,30,31,32,33,34,35,36,37,38,39,40];
            for n=1:length(Series)
                CoCo=[]; Sample=[]; Baseline=[]; UP3=[]; UP2=[]; UP1=[]; DOWN3=[]; DOWN2=[]; DOWN1=[];
                Sample=maskrecord(:,:,Series(n),DG);
                Baseline=maskrecord(:,:,Series(n),2);
                % up by 3 steps
                UP3(:,1:3)=Sample(:,78:80);
                UP3(:,4:80)=Sample(:,1:77);
                % up by 2 steps
                UP2(:,1:2)=Sample(:,79:80);
                UP2(:,3:80)=Sample(:,1:78);
                % up by 1 step
                UP1(:,1)=Sample(:,80);
                UP1(:,2:80)=Sample(:,1:79);
                % down by 1 step
                DOWN1(:,1:79)=Sample(:,2:80);
                DOWN1(:,80)=Sample(:,1);
                % down by 2 step
                DOWN2(:,1:78)=Sample(:,3:80);
                DOWN2(:,79:80)=Sample(:,1:2);
                % down by 3 step
                DOWN3(:,1:77)=Sample(:,4:80);
                DOWN3(:,78:80)=Sample(:,1:3);
                
                CoCo(1,1)=sum(sum(abs(UP3-Baseline)));
                CoCo(1,2)=sum(sum(abs(UP2-Baseline)));
                CoCo(1,3)=sum(sum(abs(UP1-Baseline)));
                CoCo(1,4)=sum(sum(abs(Sample-Baseline)));
                CoCo(1,5)=sum(sum(abs(DOWN1-Baseline)));
                CoCo(1,6)=sum(sum(abs(DOWN2-Baseline)));
                CoCo(1,7)=sum(sum(abs(DOWN3-Baseline)));
                ind=find(CoCo==min(CoCo));
                
                
                if length(ind)>1
                    ind=round(mean(ind));
                end
                TSS=[3,2,1,0,-1,-2,-3];
                BShift(k,n)=TSS(ind);
            end
        end
        A=round(mode(BShift,2))';
        %%%%%%%%%%%%%%%%%%%%%%%%%%
        BShift2=[]; A2=[];
        for k=1:102
            DG=k;
            Series=[29,30,31,32,33,34,35,36,37,38,39,40];
            for n=1:length(Series)
                CoCo=[]; Sample=[]; Baseline=[]; UP3=[]; UP2=[]; UP1=[]; DOWN3=[]; DOWN2=[]; DOWN1=[];
                Sample=maskrecord(:,:,Series(n),DG);
                Baseline=maskrecord(:,:,Series(n),3);
                % up by 3 steps
                UP3(:,1:3)=Sample(:,78:80);
                UP3(:,4:80)=Sample(:,1:77);
                % up by 2 steps
                UP2(:,1:2)=Sample(:,79:80);
                UP2(:,3:80)=Sample(:,1:78);
                % up by 1 step
                UP1(:,1)=Sample(:,80);
                UP1(:,2:80)=Sample(:,1:79);
                % down by 1 step
                DOWN1(:,1:79)=Sample(:,2:80);
                DOWN1(:,80)=Sample(:,1);
                % down by 2 step
                DOWN2(:,1:78)=Sample(:,3:80);
                DOWN2(:,79:80)=Sample(:,1:2);
                % down by 3 step
                DOWN3(:,1:77)=Sample(:,4:80);
                DOWN3(:,78:80)=Sample(:,1:3);
                
                CoCo(1,1)=sum(sum(abs(UP3-Baseline)));
                CoCo(1,2)=sum(sum(abs(UP2-Baseline)));
                CoCo(1,3)=sum(sum(abs(UP1-Baseline)));
                CoCo(1,4)=sum(sum(abs(Sample-Baseline)));
                CoCo(1,5)=sum(sum(abs(DOWN1-Baseline)));
                CoCo(1,6)=sum(sum(abs(DOWN2-Baseline)));
                CoCo(1,7)=sum(sum(abs(DOWN3-Baseline)));
                ind=find(CoCo==min(CoCo));
                
                if length(ind)>1
                    ind=round(mean(ind));
                end
                TSS=[3,2,1,0,-1,-2,-3];
                BShift2(k,n)=TSS(ind);
            end
        end
        A2=round(mode(BShift2,2))';
        %%%%%%%%%%%%%%%%%%%%%%%%%%
        BShift3=[]; A3=[];
        for k=1:102
            DG=k;
            Series=[29,30,31,32,33,34,35,36,37,38,39,40];
            for n=1:length(Series)
                CoCo=[]; Sample=[]; Baseline=[]; UP3=[]; UP2=[]; UP1=[]; DOWN3=[]; DOWN2=[]; DOWN1=[];
                Sample=maskrecord(:,:,Series(n),DG);
                Baseline=maskrecord(:,:,Series(n),4);
                % up by 3 steps
                UP3(:,1:3)=Sample(:,78:80);
                UP3(:,4:80)=Sample(:,1:77);
                % up by 2 steps
                UP2(:,1:2)=Sample(:,79:80);
                UP2(:,3:80)=Sample(:,1:78);
                % up by 1 step
                UP1(:,1)=Sample(:,80);
                UP1(:,2:80)=Sample(:,1:79);
                % down by 1 step
                DOWN1(:,1:79)=Sample(:,2:80);
                DOWN1(:,80)=Sample(:,1);
                % down by 2 step
                DOWN2(:,1:78)=Sample(:,3:80);
                DOWN2(:,79:80)=Sample(:,1:2);
                % down by 3 step
                DOWN3(:,1:77)=Sample(:,4:80);
                DOWN3(:,78:80)=Sample(:,1:3);
                
                CoCo(1,1)=sum(sum(abs(UP3-Baseline)));
                CoCo(1,2)=sum(sum(abs(UP2-Baseline)));
                CoCo(1,3)=sum(sum(abs(UP1-Baseline)));
                CoCo(1,4)=sum(sum(abs(Sample-Baseline)));
                CoCo(1,5)=sum(sum(abs(DOWN1-Baseline)));
                CoCo(1,6)=sum(sum(abs(DOWN2-Baseline)));
                CoCo(1,7)=sum(sum(abs(DOWN3-Baseline)));
                ind=find(CoCo==min(CoCo));
                
                if length(ind)>1
                    ind=round(mean(ind));
                end
                TSS=[3,2,1,0,-1,-2,-3];
                BShift3(k,n)=TSS(ind);
            end
        end
        A3=round(mode(BShift3,2))';
        %%%%%%%%%%%%%%%%%%%%%%%%%%%%
        BShift4=[]; A4=[];
        for k=1:102
            DG=k;
            Series=[29,30,31,32,33,34,35,36,37,38,39,40];
            for n=1:length(Series)
                CoCo=[]; Sample=[]; Baseline=[]; UP3=[]; UP2=[]; UP1=[]; DOWN3=[]; DOWN2=[]; DOWN1=[];
                Sample=maskrecord(:,:,Series(n),DG);
                Baseline=maskrecord(:,:,Series(n),5);
                % up by 3 steps
                UP3(:,1:3)=Sample(:,78:80);
                UP3(:,4:80)=Sample(:,1:77);
                % up by 2 steps
                UP2(:,1:2)=Sample(:,79:80);
                UP2(:,3:80)=Sample(:,1:78);
                % up by 1 step
                UP1(:,1)=Sample(:,80);
                UP1(:,2:80)=Sample(:,1:79);
                % down by 1 step
                DOWN1(:,1:79)=Sample(:,2:80);
                DOWN1(:,80)=Sample(:,1);
                % down by 2 step
                DOWN2(:,1:78)=Sample(:,3:80);
                DOWN2(:,79:80)=Sample(:,1:2);
                % down by 3 step
                DOWN3(:,1:77)=Sample(:,4:80);
                DOWN3(:,78:80)=Sample(:,1:3);
                
                CoCo(1,1)=sum(sum(abs(UP3-Baseline)));
                CoCo(1,2)=sum(sum(abs(UP2-Baseline)));
                CoCo(1,3)=sum(sum(abs(UP1-Baseline)));
                CoCo(1,4)=sum(sum(abs(Sample-Baseline)));
                CoCo(1,5)=sum(sum(abs(DOWN1-Baseline)));
                CoCo(1,6)=sum(sum(abs(DOWN2-Baseline)));
                CoCo(1,7)=sum(sum(abs(DOWN3-Baseline)));
                ind=find(CoCo==min(CoCo));
                
                if length(ind)>1
                    ind=round(mean(ind));
                end
                TSS=[3,2,1,0,-1,-2,-3];
                BShift4(k,n)=TSS(ind);
            end
        end
        A4=round(mode(BShift4,2))';
        %%%%%%%%%%%%%%%%%%%%%%%%%%%
        BShift5=[]; A5=[];
        for k=1:102
            DG=k;
            Series=[29,30,31,32,33,34,35,36,37,38,39,40];
            for n=1:length(Series)
                CoCo=[]; Sample=[]; Baseline=[]; UP3=[]; UP2=[]; UP1=[]; DOWN3=[]; DOWN2=[]; DOWN1=[];
                Sample=maskrecord(:,:,Series(n),DG);
                Baseline=maskrecord(:,:,Series(n),1);
                % up by 3 steps
                UP3(:,1:3)=Sample(:,78:80);
                UP3(:,4:80)=Sample(:,1:77);
                % up by 2 steps
                UP2(:,1:2)=Sample(:,79:80);
                UP2(:,3:80)=Sample(:,1:78);
                % up by 1 step
                UP1(:,1)=Sample(:,80);
                UP1(:,2:80)=Sample(:,1:79);
                % down by 1 step
                DOWN1(:,1:79)=Sample(:,2:80);
                DOWN1(:,80)=Sample(:,1);
                % down by 2 step
                DOWN2(:,1:78)=Sample(:,3:80);
                DOWN2(:,79:80)=Sample(:,1:2);
                % down by 3 step
                DOWN3(:,1:77)=Sample(:,4:80);
                DOWN3(:,78:80)=Sample(:,1:3);
                
                CoCo(1,1)=sum(sum(abs(UP3-Baseline)));
                CoCo(1,2)=sum(sum(abs(UP2-Baseline)));
                CoCo(1,3)=sum(sum(abs(UP1-Baseline)));
                CoCo(1,4)=sum(sum(abs(Sample-Baseline)));
                CoCo(1,5)=sum(sum(abs(DOWN1-Baseline)));
                CoCo(1,6)=sum(sum(abs(DOWN2-Baseline)));
                CoCo(1,7)=sum(sum(abs(DOWN3-Baseline)));
                ind=find(CoCo==min(CoCo));
                
                if length(ind)>1
                    ind=round(mean(ind));
                end
                TSS=[3,2,1,0,-1,-2,-3];
                BShift5(k,n)=TSS(ind);
            end
        end
        A5=round(mode(BShift5,2))';
        
        Afamily(1,:)=A;
        Afamily(2,:)=A2;
        Afamily(3,:)=A3;
        Afamily(4,:)=A4;
        Afamily(5,:)=A5;
        Coordinate=mode(Afamily);
        DG=[]; slice=[];
        for DG=1:102
            for slice=1:56
                easy=window(:,:,slice,DG);
                if Coordinate(DG)>=3
                    correct(:,1:3,slice,DG)=easy(:,78:80); correct(:,4:80,slice,DG)=easy(:,1:77);
                elseif Coordinate(DG)==2
                    correct(:,1:2,slice,DG)=easy(:,79:80); correct(:,3:80,slice,DG)=easy(:,1:78);
                elseif Coordinate(DG)==1
                    correct(:,1,slice,DG)=easy(:,80); correct(:,2:80,slice,DG)=easy(:,1:79);
                elseif Coordinate(DG)<=-3
                    correct(:,1:77,slice,DG)=easy(:,4:80); correct(:,78:80,slice,DG)=easy(:,1:3);
                elseif Coordinate(DG)==-2
                    correct(:,1:78,slice,DG)=easy(:,3:80); correct(:,79:80,slice,DG)=easy(:,1:2);
                elseif Coordinate(DG)==-1
                    correct(:,1:79,slice,DG)=easy(:,2:80); correct(:,80,slice,DG)=easy(:,1);
                elseif Coordinate(DG)==0
                    correct(:,:,slice,DG)=easy;
                else
                    correct(:,:,slice,DG)=easy;
                end
            end
        end
        Data.dat(:,:,:,:)=correct;
        create(Data)
        
        
        %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        iteration=iteration+1;
        if sum(abs(Coordinate))<2
            Lamda=2;
            fprintf('Done for sub: %g ! %s\n',people,datestr(now));
        elseif iteration>25
            Lamda=2;
            fprintf('Done for sub: %g ! %s\n',people,datestr(now));
        elseif max(abs(Coordinate))>5
            Lamda=2;
            fprintf('Something is wrong ! \n');
        else
            Lamda=1;
        end
        
    end
end
