#!/usr/bin/env bash

qiime dada2 denoise-paired \
	--i-demultiplexed-seqs $CGM/demux.qza \
	--p-trunc-len-f 0 \
	--p-trunc-len-r 0 \
	--p-trunc-q 20 \
	--p-n-threads 0 \
	--o-table $CGM/table.qza \
	--o-representative-sequences $CGM/rep-seqs.qza \
	--o-denoising-stats $CGM/denoising.qza

qiime feature-table summarize \
	--i-table $CGM/table.qza \
	--m-sample-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/table.qzv

qiime feature-table tabulate-seqs \
	--i-data $CGM/rep-seqs.qza \
	--o-visualization $CGM/rep-seqs.qzv

qiime metadata tabulate \
	--m-input-file $CGM/denoising.qza \
	--o-visualization $CGM/denoising.qzv
