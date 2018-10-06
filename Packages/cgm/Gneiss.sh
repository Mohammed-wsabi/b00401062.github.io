#!/usr/bin/env bash

qiime gneiss ilr-phylogenetic \
	--i-table $DATASETS/table.qza \
	--i-tree $DATASETS/rooted-tree.qza \
	--o-balances $DATASETS/balances.qza \
	--o-hierarchy $DATASETS/hierarchy.qza

qiime gneiss ols-regression \
	--p-formula "Tissue" \
	--i-table $DATASETS/balances.qza \
	--i-tree $DATASETS/hierarchy.qza \
	--m-metadata-file $DATASETS/metadata.tsv \
	--o-visualization $DATASETS/regression_summary.qzv
