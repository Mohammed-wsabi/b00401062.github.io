for ROI in Angular Cingulum_Post Frontal_Med_Orb Frontal_Sup_Medial Hippocampus ParaHippocampal Precuneus SupraMarginal; do
    for LR in L R; do
        ln -s /bml/Data/Bank5/TAD/aal_cerebral/${ROI}_${LR}.nii ./Datasets/AAL_Collect/
    done
done
