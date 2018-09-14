#!/usr/bin/env bash

export PATH="~/Library/Conda/bin/:$PATH"
export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

## Demultiplex

qiime tools import \
	--type "SampleData[PairedEndSequencesWithQuality]" \
	--input-path $CGM/Manifest.csv \
	--output-path $CGM/Demultiplexed.qza \
	--input-format PairedEndFastqManifestPhred33

qiime demux summarize \
	--i-data $CGM/Demultiplexed.qza \
	--o-visualization $CGM/Demultiplexed.qzv

## Filter

qiime quality-filter q-score \
	--i-demux $CGM/Demultiplexed.qza \
	--o-filtered-sequences $CGM/FilteredDemultiplexed.qza \
	--o-filter-stats $CGM/FilterStats.qza

qiime demux summarize \
	--i-data $CGM/Filtered.qza \
	--o-visualization $CGM/Filtered.qzv

qiime metadata tabulate \
	--m-input-file $CGM/FilterStats.qza \
	--o-visualization $CGM/FilterStats.qzv

## Denoise

qiime dada2 denoise-single \
	--i-demultiplexed-seqs $CGM/Demultiplexed.qza \
	--p-trim-left 0 \
	--p-trunc-len 200 \
	--o-representative-sequences $CGM/Representative.qza \
	--o-table $CGM/Features.qza \
	--o-denoising-stats $CGM/DenoiseStats.qza

qiime feature-table tabulate-seqs \
	--i-data $CGM/Representative.qza \
	--o-visualization $CGM/Representative.qzv

qiime feature-table summarize \
	--i-table $CGM/Features.qza \
	--o-visualization $CGM/Features.qzv \

qiime metadata tabulate \
	--m-input-file $CGM/DenoiseStats.qza \
	--o-visualization $CGM/DenoiseStats.qzv

## Phylogeny

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $CGM/Representative.qza \
	--o-alignment $CGM/Alignement.qza \
	--o-masked-alignment $CGM/MaskedAlignment.qza \
	--o-tree $CGM/Tree.qza \
	--o-rooted-tree $CGM/RootedTree.qza

source deactivate

unset CGM
unset PATH
