#!/usr/bin/env bash

export PATH=~/Library/Conda/bin/:$PATH
export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

./Documents/Packages/cgm/Importing.sh
./Documents/Packages/cgm/Denoising.sh
./Documents/Packages/cgm/Phylogeny.sh
./Documents/Packages/cgm/Diversity.sh
./Documents/Packages/cgm/Classifier.sh
./Documents/Packages/cgm/Taxonomy.sh
./Documents/Packages/cgm/ANCOM.sh
./Documents/Packages/cgm/Gneiss.sh

source deactivate

unset CGM
unset PATH
