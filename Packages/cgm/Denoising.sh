#!/usr/bin/env bash

qiime dada2 denoise-paired \
	--i-demultiplexed-seqs $DATASETS/demux.qza \
	--p-trunc-len-f 0 \
	--p-trunc-len-r 0 \
	--p-trunc-q 20 \
	--p-n-threads 0 \
	--o-table $DATASETS/table.qza \
	--o-representative-sequences $DATASETS/rep-seqs.qza \
	--o-denoising-stats $DATASETS/denoising.qza

qiime feature-table summarize \
	--i-table $DATASETS/table.qza \
	--m-sample-metadata-file $DATASETS/metadata.tsv \
	--o-visualization $DATASETS/table.qzv

qiime feature-table tabulate-seqs \
	--i-data $DATASETS/rep-seqs.qza \
	--o-visualization $DATASETS/rep-seqs.qzv

qiime metadata tabulate \
	--m-input-file $DATASETS/denoising.qza \
	--o-visualization $DATASETS/denoising.qzv
