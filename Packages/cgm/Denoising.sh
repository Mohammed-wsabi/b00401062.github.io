#!/usr/bin/env bash

qiime dada2 denoise-paired \
	--i-demultiplexed-seqs $CGM/demux.qza \
	--p-trim-left-f 0 \
	--p-trim-left-r 0 \
	--p-trunc-len-f 120 \
	--p-trunc-len-r 120 \
	--p-n-threads 0 \
	--o-table $CGM/table-raw.qza \
	--o-representative-sequences $CGM/rep-seqs-raw.qza \
	--o-denoising-stats $CGM/denoising.qza

qiime feature-table filter-features \
	--i-table $CGM/table-raw.qza \
	--p-min-frequency 2 \
	--o-filtered-table $CGM/table.qza

qiime feature-table filter-seqs \
	--i-data $CGM/rep-seqs-raw.qza \
	--i-table $CGM/table.qza \
	--o-filtered-data $CGM/rep-seqs.qza

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
