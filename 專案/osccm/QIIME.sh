#!/usr/bin/env bash

export PATH=~/Library/Conda/bin/:$PATH
export OSCCMPATH=~/Documents/專案/osccm
export DATASETS=~/Downloads/Researches/OSCCM/Datasets

source activate qiime

for FILE in $@
do
    $OSCCMPATH/$FILE.sh
done

source deactivate

unset OSCCMPATH
unset DATASETS
