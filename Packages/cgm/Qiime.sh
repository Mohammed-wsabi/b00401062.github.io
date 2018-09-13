#!/usr/bin/env bash

export PATH="~/Library/Conda/bin/:$PATH"
export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

## Import Data

qiime tools import \
	--type 'SampleData[PairedEndSequencesWithQuality]' \
	--input-path $CGM/Manifest.csv \
	--output-path $CGM/Demux/Demux.qza \
	--input-format PairedEndFastqManifestPhred33

qiime demux summarize \
	--i-data $CGM/Demux/Demux.qza \
	--o-visualization $CGM/Demux/Demux.qzv

## Quality Analysis

qiime quality-filter q-score \
	--i-demux $CGM/Demux/Demux.qza \
	--o-filtered-sequences $CGM/QA/Sequences.qza \
	--o-filter-stats $CGM/QA/Stats.qza

## Denoise: DATA2

qiime dada2 denoise-single \
	--i-demultiplexed-seqs $CGM/Demux/Sequences.qza \
	--p-trim-left 0 \
	--p-trunc-len 200 \
	--o-representative-sequences $CGM/DADA2/Representative.qza \
	--o-table $CGM/DADA2/Table.qza \
	--o-denoising-stats $CGM/DADA2/Stats.qza

qiime metadata tabulate \
	--m-input-file $CGM/DADA2/Stats.qza \
	--o-visualization $CGM/DADA2/Stats.qzv

## Denoise: Deblur

qiime deblur denoise-16S \
	--i-demultiplexed-seqs $CGM/Deblur/Filtered.qza \
	--p-trim-length 200 \
	--o-representative-sequences $CGM/Deblur/Representative.qza \
	--o-table $CGM/Deblur/Table.qza \
	--p-sample-stats \
	--o-stats $CGM/Deblur/Stats.qza

qiime deblur visualize-stats \
	--i-deblur-stats $CGM/Deblur/Stats.qza \
	--o-visualization $CGM/Deblur/Stats.qzv

source deactivate

unset CGM
unset PATH
