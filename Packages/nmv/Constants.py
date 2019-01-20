#!/usr/bin/env python3

from collections import namedtuple
from numpy import arange

Name = namedtuple("Name", ["nickname", "fullname", "indices"])
Sex = namedtuple("Sex", ["female", "male"])

ALPHA = .05
INDICES = ["FA", "GFA", "AD", "RD", "MD", "NG", "NGO", "NGP"]
AGES = arange(18, 89)
TRACTS = (
	Name(nickname = "A001_AF", fullname = "Arcuate Fasciculus", indices = list(range(0, 2))),
	Name(nickname = "A002_CBB", fullname = "Cingulum Bundle (Body)", indices = list(range(2, 4))),
	Name(nickname = "A003_CBH", fullname = "Cingulum Bundle (Hippocampus)", indices = list(range(4, 6))),
	Name(nickname = "A004_FAT", fullname = "Frontal Aslent Tract", indices = list(range(6, 8))),
	Name(nickname = "A005_FX", fullname = "Fornix", indices = list(range(8, 10))),
	Name(nickname = "A006_IFOF", fullname = "Inferior Fronto-Occipital Fasciculus", indices = list(range(10, 12))),
	Name(nickname = "A007_ILF", fullname = "Inferior Longitudinal Fasciculus", indices = list(range(12, 14))),
	Name(nickname = "A008_PF", fullname = "Perpendicular Fasciculus", indices = list(range(14, 16))),
	Name(nickname = "A009_SLF", fullname = "Superior Longitudinal Fasciculus", indices = list(range(16, 22))),
	Name(nickname = "A010_ST", fullname = "Stria Terminalis", indices = list(range(22, 24))),
	Name(nickname = "A011_UF", fullname = "Uncinate Fasciculus", indices = list(range(24, 26))),
	Name(nickname = "A012_CST", fullname = "Corticospinal Tract", indices = list(range(26, 34))),
	Name(nickname = "A013_FSTPF", fullname = "Frontostriatal Tract (Prefrontal)", indices = list(range(36, 42))),
	Name(nickname = "A014_FSTM", fullname = "Frontostriatal Tract (Motor)", indices = list(range(42, 44))),
	Name(nickname = "A014_TRPF", fullname = "Thalamic Radiation (Prefrontal)", indices = list(range(46, 50))),
	Name(nickname = "A015_TRSM", fullname = "Thalamic Radiation (Sensorimotor)", indices = list(range(50, 54))),
	Name(nickname = "A017_AR", fullname = "Auditory Radiation", indices = list(range(54, 56))),
	Name(nickname = "A018_OR", fullname = "Optic Radiation", indices = list(range(56, 58))),
	Name(nickname = "A019_CCPF", fullname = "Corpus Callosum (Prefrontal)", indices = list(range(60, 64))),
	Name(nickname = "A020_CCSM", fullname = "Corpus Callosum (Sensorimotor)", indices = [64, 65, 67]),
	Name(nickname = "A021_CCP", fullname = "Corpus Callosum (Parietal)", indices = [66, 68, 74]),
	Name(nickname = "A022_CCT", fullname = "Corpus Callosum (Temporal)", indices = list(range(69, 74))),
	Name(nickname = "A023_CCO", fullname = "Corpus Callosum (Occipital)", indices = [75]),
)
