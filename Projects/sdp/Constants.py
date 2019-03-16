#!/usr/bin/env python3

from collections import namedtuple

Tract = namedtuple("Tract", ["nickname", "fullname", "indices"])
Data = namedtuple("Data", ["X", "y"])
Set = namedtuple("Set", ["training", "test"])

INDICES = ("FA", "GFA", "AD", "RD", "MD", "NG", "NGO", "NGP")
TRACTS = (
	Tract(nickname = "A001_AF", fullname = "Arcuate fasciculus", indices = list(range(0, 2))),
	Tract(nickname = "A002_CBB", fullname = "Cingulum bundle (body)", indices = list(range(2, 4))),
	Tract(nickname = "A003_CBH", fullname = "Cingulum bundle (hippocampus)", indices = list(range(4, 6))),
	Tract(nickname = "A004_FAT", fullname = "Frontal aslant tract", indices = list(range(6, 8))),
	Tract(nickname = "A005_FX", fullname = "Fornix", indices = list(range(8, 10))),
	Tract(nickname = "A006_IFOF", fullname = "Inferior fronto-occipital fasciculus", indices = list(range(10, 12))),
	Tract(nickname = "A007_ILF", fullname = "Inferior longitudinal fasciculus", indices = list(range(12, 14))),
	Tract(nickname = "A008_PF", fullname = "Perpendicular fasciculus", indices = list(range(14, 16))),
	Tract(nickname = "A009_SLF", fullname = "Superior longitudinal fasciculus", indices = list(range(16, 22))),
	Tract(nickname = "A010_ST", fullname = "Stria terminalis", indices = list(range(22, 24))),
	Tract(nickname = "A011_UF", fullname = "Uncinate fasciculus", indices = list(range(24, 26))),
	Tract(nickname = "A012_CST", fullname = "Corticospinal tract", indices = list(range(26, 34))),
	Tract(nickname = "A013_FSTPF", fullname = "Frontostriatal tract (prefrontal)", indices = list(range(36, 42))),
	Tract(nickname = "A014_FSTM", fullname = "Frontostriatal tract (motor)", indices = list(range(42, 44))),
	Tract(nickname = "A015_TRPF", fullname = "Thalamic radiation (prefrontal)", indices = list(range(46, 50))),
	Tract(nickname = "A016_TRSM", fullname = "Thalamic radiation (sensorimotor)", indices = list(range(50, 54))),
	Tract(nickname = "A017_AR", fullname = "Auditory radiation", indices = list(range(54, 56))),
	Tract(nickname = "A018_OR", fullname = "Optic radiation", indices = list(range(56, 58))),
	Tract(nickname = "A019_CCPF", fullname = "Corpus callosum (prefrontal)", indices = list(range(60, 64))),
	Tract(nickname = "A020_CCSM", fullname = "Corpus callosum (sensorimotor)", indices = [64, 65, 67]),
	Tract(nickname = "A021_CCP", fullname = "Corpus callosum (parietal)", indices = [66, 68, 74]),
	Tract(nickname = "A022_CCT", fullname = "Corpus callosum (temporal)", indices = list(range(69, 74))),
	Tract(nickname = "A023_CCO", fullname = "Corpus callosum (occipital)", indices = [75]),
)
