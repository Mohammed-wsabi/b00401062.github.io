#!/usr/bin/env bash

export PATH=~/Library/Conda/bin/:$PATH
export CGMPATH=~/Documents/Packages/cgm
export DATASETS=~/Downloads/Researches/CGM/Datasets

source activate qiime

for FILE in $@
do
	$CGMPATH/$FILE.sh
done

source deactivate

unset PATH
unset CGMPATH
unset DATASETS
