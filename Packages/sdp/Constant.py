#!/usr/bin/env python3

from collections import namedtuple

Tract = namedtuple("Tract", ["nickname", "fullname"])
Data = namedtuple("Data", ["X", "y"])
Set = namedtuple("Set", ["training", "test"])

INDICES = ("FA", "GFA", "AD", "RD", "MD", "NG", "NGO", "NGP")
TRACTS = (
	Tract(nickname = "T001_AF_L", fullname = "Left arcuate fasciculus"),
	Tract(nickname = "T002_AF_R", fullname = "Right arcuate fasciculus"),
	Tract(nickname = "T003_CG_body_L", fullname = "Left cingulum main body part"),
	Tract(nickname = "T004_CG_body_R", fullname = "Right cingulum main body part"),
	Tract(nickname = "T005_CG_hippocampal_L", fullname = "Left cingulum hippocampal part"),
	Tract(nickname = "T006_CG_hippocampal_R", fullname = "Right cingulum hippocampal part"),
	Tract(nickname = "T007_Frontal_aslent_tract_L", fullname = "Left frontal aslant tract"),
	Tract(nickname = "T008_Frontal_aslent_tract_R", fullname = "Right frontal aslant tract"),
	Tract(nickname = "T009_FX_L", fullname = "Left fornix"),
	Tract(nickname = "T010_FX_R", fullname = "Right fornix"),
	Tract(nickname = "T011_IFOF_L", fullname = "Left inferior fronto-occipital fasciculus"),
	Tract(nickname = "T012_IFOF_R", fullname = "Right inferior fronto-occipital fasciculus"),
	Tract(nickname = "T013_ILF_L", fullname = "Left inferior longitudinal fasciculus"),
	Tract(nickname = "T014_ILF_R", fullname = "Right inferior longitudinal fasciculus"),
	Tract(nickname = "T015_Perpendicular_fasciculus_L", fullname = "Left perpendicular fasciculus"),
	Tract(nickname = "T016_Perpendicular_fasciculus_R", fullname = "Right perpendicular fasciculus"),
	Tract(nickname = "T017_SLF1_L", fullname = "Left superior longitudinal fasciculus I"),
	Tract(nickname = "T018_SLF1_R", fullname = "Right superior longitudinal fasciculus I"),
	Tract(nickname = "T019_SLF2_L", fullname = "Left superior longitudinal fasciculus II"),
	Tract(nickname = "T020_SLF2_R", fullname = "Right superior longitudinal fasciculus II"),
	Tract(nickname = "T021_SLF3_L", fullname = "Left superior longitudinal fasciculus III"),
	Tract(nickname = "T022_SLF3_R", fullname = "Right superior longitudinal fasciculus III"),
	Tract(nickname = "T023_stria_terminalis_L", fullname = "Left stria terminalis"),
	Tract(nickname = "T024_stria_terminalis_R", fullname = "Right stria terminalis"),
	Tract(nickname = "T025_UF_L", fullname = "Left uncinate fasciculus"),
	Tract(nickname = "T026_UF_R", fullname = "Right uncinate fasciculus"),
	Tract(nickname = "T027_CST_HAND_L", fullname = "Left corticospinal tract of hand"),
	Tract(nickname = "T028_CST_HAND_R", fullname = "Right corticospinal tract of hand"),
	Tract(nickname = "T029_CST_M1_L", fullname = "Left corticospinal tract of trunk"),
	Tract(nickname = "T030_CST_M1_R", fullname = "Right corticospinal tract of trunk"),
	Tract(nickname = "T031_CST_mouth_L", fullname = "Left corticospinal tract of mouth"),
	Tract(nickname = "T032_CST_mouth_R", fullname = "Right corticospinal tract of mouth"),
	Tract(nickname = "T033_CST_toe_L", fullname = "Left corticospinal tract of toe"),
	Tract(nickname = "T034_CST_toe_R", fullname = "Right corticospinal tract of toe"),
	Tract(nickname = "T035_Geniculate_fibers_L", fullname = "Left corticospinal tract of geniculate fibers"),
	Tract(nickname = "T036_Geniculate_fibers_R", fullname = "Right corticospinal tract of geniculate fibers"),
	Tract(nickname = "T037_FS_OFC_L", fullname = "Left frontostriatal tract of orbitofrontal cortex"),
	Tract(nickname = "T038_FS_OFC_R", fullname = "Right frontostriatal tract of orbitofrontal cortex"),
	Tract(nickname = "T039_FS_VLPFC_L", fullname = "Left frontostriatal tract of ventral lateral prefrontal cortex"),
	Tract(nickname = "T040_FS_VLPFC_R", fullname = "Right frontostriatal tract of ventral lateral prefrontal cortex"),
	Tract(nickname = "T041_FS_DLPFC_L", fullname = "Left frontostriatal tract of dorsolateral prefrontal cortex"),
	Tract(nickname = "T042_FS_DLPFC_R", fullname = "Right frontostriatal tract of dorsolateral prefrontal cortex"),
	Tract(nickname = "T043_FS_motor_precentral_L", fullname = "Left frontostriatal tract of precentral gyrus"),
	Tract(nickname = "T044_FS_motor_precentral_R", fullname = "Right frontostriatal tract of precentral gyrus"),
	Tract(nickname = "T045_Medial_lemniscus_L", fullname = "Left medial lemniscus"),
	Tract(nickname = "T046_Medial_lemniscus_R", fullname = "Right medial lemniscus"),
	Tract(nickname = "T047_TR_ventral_L", fullname = "Left thalamic radiation of ventral lateral prefrontal cortex"),
	Tract(nickname = "T048_TR_ventral_R", fullname = "Right thalamic radiation of ventral lateral prefrontal cortex"),
	Tract(nickname = "T049_TR_dorsal_L", fullname = "Left thalamic radiation of dorsolateral prefrontal cortex"),
	Tract(nickname = "T050_TR_dorsal_R", fullname = "Right thalamic radiation of dorsolateral prefrontal cortex"),
	Tract(nickname = "T051_TR_precentral_L", fullname = "Left thalamic radiation of precentral gyrus"),
	Tract(nickname = "T052_TR_precentral_R", fullname = "Right thalamic radiation of precentral gyrus"),
	Tract(nickname = "T053_TR_postcentral_L", fullname = "Left thalamic radiation of postcentral gyrus"),
	Tract(nickname = "T054_TR_postcentral_R", fullname = "Right thalamic radiation of postcentral gyrus"),
	Tract(nickname = "T055_TR_auditory_L", fullname = "Left thalamic radiation of auditory nerve"),
	Tract(nickname = "T056_TR_auditory_R", fullname = "Right thalamic radiation of auditory nerve"),
	Tract(nickname = "T057_TR_optic_L", fullname = "Left thalamic radiation of optic radiation"),
	Tract(nickname = "T058_TR_optic_R", fullname = "Right thalamic radiation of optic radiation"),
	Tract(nickname = "T059_AC", fullname = "Anterior commissure"),
	Tract(nickname = "T060_PC", fullname = "Posterior commissure"),
	Tract(nickname = "T061_CC_gen", fullname = "Corpus callosum of genus"),
	Tract(nickname = "T062_CC_DLPFC", fullname = "Corpus callosum of dorsolateral prefrontal cortex"),
	Tract(nickname = "T063_CC_VLPFC", fullname = "Corpus callosum of ventral lateral prefrontal cortex"),
	Tract(nickname = "T064_CC_SMA", fullname = "Corpus callosum of supplementary motor area"),
	Tract(nickname = "T065_CC_motor_precentral", fullname = "Corpus callosum of precentral gyrus"),
	Tract(nickname = "T066_CC_paracentral", fullname = "Corpus callosum of paracentral lobule"),
	Tract(nickname = "T067_CC_inferior_parietal_lobule", fullname = "Corpus callosum of inferior parietal lobule"),
	Tract(nickname = "T068_CC_postcentral_dorsal", fullname = "Corpus callosum of postcentral gyrus"),
	Tract(nickname = "T069_CC_superior_parietal_lobule", fullname = "Corpus callosum of superior parietal lobule"),
	Tract(nickname = "T070_CC_supeior_temporal", fullname = "Corpus callosum of superior temporal gyrus"),
	Tract(nickname = "T071_CC_middle_temporal", fullname = "Corpus callosum of middle temporal gyrus"),
	Tract(nickname = "T072_CC_temporal_pole", fullname = "Corpus callosum of temporal pole"),
	Tract(nickname = "T073_CC_hippocampus", fullname = "Corpus callosum of hippocampus"),
	Tract(nickname = "T074_CC_amygdala", fullname = "Corpus callosum of amygdala"),
	Tract(nickname = "T075_CC_precuneus", fullname = "Corpus callosum of precuneus"),
	Tract(nickname = "T076_CC_splenium", fullname = "Corpus callosum of splenium"))
