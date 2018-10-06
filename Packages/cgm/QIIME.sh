#!/usr/bin/env bash

export PATH=~/Library/Conda/bin/:$PATH
export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

for step in $@
do
	./Documents/Packages/cgm/$step.sh
done

source deactivate

unset CGM
unset PATH
