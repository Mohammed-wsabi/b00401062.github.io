#!/usr/bin/env bash

qiime feature-classifier classify-sklearn \
	--i-classifier $DATASETS/gg-13-8-99-nb-classifier.qza \
	--i-reads $DATASETS/rep-seqs.qza \
	--o-classification $DATASETS/taxonomy.qza

qiime metadata tabulate \
	--m-input-file $DATASETS/taxonomy.qza \
	--o-visualization $DATASETS/taxonomy.qzv

qiime taxa barplot \
	--i-table $DATASETS/table.qza \
	--i-taxonomy $DATASETS/taxonomy.qza \
	--m-metadata-file $DATASETS/metadata.tsv \
	--o-visualization $DATASETS/taxa-bar-plots.qzv
