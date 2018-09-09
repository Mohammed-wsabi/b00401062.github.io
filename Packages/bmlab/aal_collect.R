for (ROI in c("Angular", "Cingulum_Post", "Frontal_Med_Orb", "Frontal_Sup_Medial", "Hippocampus", "ParaHippocampal", "Precuneus", "SupraMarginal")) {
    for (LR in c("L", "R")) {
        system(sprintf("ln -s /bml/Data/Bank5/TAD/aal_cerebral/%s_%s.nii ./Datasets/AAL_Collect/", ROI, LR))
    } #=> for
} #=> for
