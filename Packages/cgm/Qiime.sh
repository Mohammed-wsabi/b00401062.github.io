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
	--o-filtered-sequences $CGM/Filtered.qza \
	--o-filter-stats $CGM/Filter.qza

qiime demux summarize \
	--i-data $CGM/Filtered.qza \
	--o-visualization $CGM/Filtered.qzv

qiime metadata tabulate \
	--m-input-file $CGM/Filter.qza \
	--o-visualization $CGM/Filter.qzv

## Denoise

qiime dada2 denoise-single \
	--i-demultiplexed-seqs $CGM/Demultiplexed.qza \
	--p-trim-left 0 \
	--p-trunc-len 200 \
	--o-representative-sequences $CGM/Representative.qza \
	--o-table $CGM/Features.qza \
	--o-denoising-stats $CGM/Denoise.qza

qiime feature-table tabulate-seqs \
	--i-data $CGM/Representative.qza \
	--o-visualization $CGM/Representative.qzv

qiime feature-table summarize \
	--i-table $CGM/Features.qza \
	--o-visualization $CGM/Features.qzv \

qiime metadata tabulate \
	--m-input-file $CGM/Denoise.qza \
	--o-visualization $CGM/Denoise.qzv

source deactivate

unset CGM
unset PATH
