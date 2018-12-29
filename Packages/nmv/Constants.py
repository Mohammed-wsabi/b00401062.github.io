#!/usr/bin/env python3

from collections import namedtuple
from numpy import arange

Name = namedtuple("Name", ["nickname", "fullname", "index"])
Sex = namedtuple("Sex", ["female", "male"])

ALPHA = .05
INDICES = ["FA", "GFA", "AD", "RD", "MD", "NG", "NGO", "NGP"]
AGES = arange(18, 89)
TRACTS = (
	Name(nickname = "A001_AF", fullname = "Arcuate fasciculus", index = list(range(0, 2))),
	Name(nickname = "A002_CBB", fullname = "Cingulum (body)", index = list(range(2, 4))),
	Name(nickname = "A003_CBH", fullname = "Cingulum (hippocampus)", index = list(range(4, 6))),
	Name(nickname = "A004_FAT", fullname = "Frontal aslant tract", index = list(range(6, 8))),
	Name(nickname = "A005_FX", fullname = "Fornix", index = list(range(8, 10))),
	Name(nickname = "A006_IFOF", fullname = "Inferior fronto-occipital fasciculus", index = list(range(10, 12))),
	Name(nickname = "A007_ILF", fullname = "Inferior longitudinal fasciculus", index = list(range(12, 14))),
	Name(nickname = "A008_PF", fullname = "Perpendicular fasciculus", index = list(range(14, 16))),
	Name(nickname = "A009_SLF", fullname = "Superior longitudinal fasciculus", index = list(range(16, 22))),
	Name(nickname = "A010_ST", fullname = "Stria terminalis", index = list(range(22, 24))),
	Name(nickname = "A011_UF", fullname = "Uncinate fasciculus", index = list(range(24, 26))),
	Name(nickname = "A012_CST", fullname = "Corticospinal tract", index = list(range(26, 34))),
	Name(nickname = "A013_FST", fullname = "Frontostriatal tract", index = list(range(36, 44))),
	Name(nickname = "A014_TRPFC", fullname = "Thalamic radiation (prefrontal cortex)", index = list(range(46, 50))),
	Name(nickname = "A015_TRM", fullname = "Thalamic radiation (precentral gyrus)", index = list(range(50, 52))),
	Name(nickname = "A016_TRS", fullname = "Thalamic radiation (postcentral gyrus)", index = list(range(52, 54))),
	Name(nickname = "A017_AR", fullname = "Auditory radiation", index = list(range(54, 56))),
	Name(nickname = "A018_OR", fullname = "Optic radiation", index = list(range(56, 58))),
	Name(nickname = "A019_CCPFC", fullname = "Corpus callosum (prefrontal cortex)", index = list(range(60, 64))),
	Name(nickname = "A020_CCM", fullname = "Corpus callosum (precentral gyrus)", index = list(range(64, 66))),
	Name(nickname = "A021_CCP", fullname = "Corpus callosum (parietal lobe)", index = list(range(66, 69))),
	Name(nickname = "A022_CCT", fullname = "Corpus callosum (temporal lobe)", index = list(range(69, 74))),
	Name(nickname = "A023_CCS", fullname = "Corpus callosum (splenium)", index = list(range(74, 76))),
)
