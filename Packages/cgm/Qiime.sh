#!/usr/bin/env bash

export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

qiime tools import \
	--type 'SampleData[PairedEndSequencesWithQuality]' \
	--input-path $CGM/Manifest.csv \
	--output-path $CGM/Demux/Demux.qza \
	--input-format PairedEndFastqManifestPhred33

qiime demux summarize \
	--i-data $CGM/Demux/Demux.qza \
	--o-visualization $CGM/Demux/Demux.qzv

qiime tools view $CGM/Demux/Demux.qzv

qiime dada2 denoise-single \
	--i-demultiplexed-seqs $CGM/Demux/Demux.qza \
	--p-trim-left 0 \
	--p-trunc-len 200 \
	--o-representative-sequences $CGM/DADA/Sequences.qza \
	--o-table $CGM/DADA/Table.qza \
	--o-denoising-stats $CGM/DADA/Stats.qza

source deactivate
