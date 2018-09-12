#!/usr/bin/env bash

export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

qiime tools import \
	--type 'SampleData[PairedEndSequencesWithQuality]' \
	--input-path $CGM/Manifest.csv \
	--output-path $CGM/Subjects.qza \
	--input-format ~/PairedEndFastqManifestPhred33

qiime demux summarize \
	--i-data $CGM/Subjects.qza \
	--o-visualization $CGM/Subjects.qzv

qiime tools view $CGM/Subjects.qzv

qiime dada2 denoise-single \
	--i-demultiplexed-seqs $CGM/Subjects.qza \
	--p-trim-left 0 \
	--p-trunc-len 200 \
	--o-representative-sequences $CGM/DADA/Representatives.qza \
	--o-table $CGM/DADA/Table.qza \
	--o-denoising-stats $CGM/DADA/Stats.qza

source deactivate
