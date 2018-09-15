#!/usr/bin/env bash

export PATH="~/Library/Conda/bin/:$PATH"
export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

## Importing Data

qiime tools import \
	--type "SampleData[PairedEndSequencesWithQuality]" \
	--input-path $CGM/Manifest.csv \
	--input-format PairedEndFastqManifestPhred33
	--output-path $CGM/Demultiplexed.qza \

qiime demux summarize \
	--i-data $CGM/Demultiplexed.qza \
	--o-visualization $CGM/Demultiplexed.qzv

## Denoising

qiime dada2 denoise-paired \
	--i-demultiplexed-seqs $CGM/Demultiplexed.qza \
	--p-trim-left-f 0 \
	--p-trim-left-r 0 \
	--p-trunc-len-f 200 \
	--p-trunc-len-r 200 \
	--p-n-threads 0 \
	--o-representative-sequences $CGM/Representative.qza \
	--o-table $CGM/Features.qza \
	--o-denoising-stats $CGM/DenoisingStats.qza

qiime feature-table tabulate-seqs \
	--i-data $CGM/Representative.qza \
	--o-visualization $CGM/Representative.qzv

qiime feature-table summarize \
	--i-table $CGM/Features.qza \
	--o-visualization $CGM/Features.qzv \

qiime metadata tabulate \
	--m-input-file $CGM/DenoisingStats.qza \
	--o-visualization $CGM/DenoisingStats.qzv

## Phylogeny

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $CGM/Representative.qza \
	--o-alignment $CGM/Alignement.qza \
	--o-masked-alignment $CGM/MaskedAlignment.qza \
	--o-tree $CGM/UnrootedTree.qza \
	--o-rooted-tree $CGM/RootedTree.qza

qiime tools export \
	--input-path $CGM/UnrootedTree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/UnrootedTree.nwk

qiime tools export \
	--input-path $CGM/RootedTree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/RootedTree.nwk

source deactivate

unset CGM
unset PATH
