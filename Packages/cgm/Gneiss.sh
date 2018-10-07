#!/usr/bin/env bash

qiime gneiss ilr-phylogenetic \
	--i-table $DATASETS/FeatureTable[Frequency]/table.qza \
	--i-tree $DATASETS/Phylogeny[Rooted]/rooted-tree.qza \
	--o-balances $DATASETS/FeatureTable[Balance]/balances.qza \
	--o-hierarchy $DATASETS/Hierarchy/hierarchy.qza

qiime gneiss ols-regression \
	--p-formula Tissue \
	--i-table $DATASETS/FeatureTable[Balance]/balances.qza \
	--i-tree $DATASETS/Hierarchy/hierarchy.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--o-visualization $DATASETS/gneiss-ols-regression/regression_summary.qzv
