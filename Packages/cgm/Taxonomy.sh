#!/usr/bin/env bash

qiime feature-classifier classify-sklearn \
	--i-classifier $CGM/gg-13-8-99-nb-classifier.qza \
	--i-reads $CGM/rep-seqs.qza \
	--o-classification $CGM/taxonomy.qza

qiime metadata tabulate \
	--m-input-file $CGM/taxonomy.qza \
	--o-visualization $CGM/taxonomy.qzv

qiime taxa barplot \
	--i-table $CGM/table.qza \
	--i-taxonomy $CGM/taxonomy.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/taxa-bar-plots.qzv
