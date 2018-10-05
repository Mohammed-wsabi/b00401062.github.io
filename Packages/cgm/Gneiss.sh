#!/usr/bin/env bash

qiime gneiss ilr-phylogenetic \
	--i-table $CGM/table.qza \
	--i-tree $CGM/rooted-tree.qza \
	--o-balances $CGM/balances.qza \
	--o-hierarchy $CGM/hierarchy.qza

qiime gneiss ols-regression \
	--p-formula "Tissue" \
	--i-table $CGM/balances.qza \
	--i-tree $CGM/hierarchy.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/regression_summary.qzv
