#!/usr/bin/env bash

## Alpha Rarefaction

qiime diversity alpha-rarefaction \
	--i-table $DATASETS/table.qza \
	--i-phylogeny $DATASETS/rooted-tree.qza \
	--p-max-depth 10635 \
	--m-metadata-file $DATASETS/metadata.tsv \
	--o-visualization $DATASETS/alpha-rarefaction.qzv

## Preprocessing

qiime diversity core-metrics-phylogenetic \
	--i-phylogeny $DATASETS/rooted-tree.qza \
	--i-table $DATASETS/table.qza \
	--p-sampling-depth 1006 \
	--m-metadata-file $DATASETS/metadata.tsv \
	--output-dir $DATASETS/core-metrics-results

## Alpha Diversity

qiime diversity alpha-group-significance \
	--i-alpha-diversity $DATASETS/core-metrics-results/faith_pd_vector.qza \
	--m-metadata-file $DATASETS/metadata.tsv \
	--o-visualization $DATASETS/core-metrics-results/faith-pd-group-significance.qzv

qiime diversity alpha-group-significance \
	--i-alpha-diversity $DATASETS/core-metrics-results/evenness_vector.qza \
	--m-metadata-file $DATASETS/metadata.tsv \
	--o-visualization $DATASETS/core-metrics-results/evenness-group-significance.qzv

## Beta Diversity

qiime diversity beta-group-significance \
	--i-distance-matrix $DATASETS/core-metrics-results/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $DATASETS/metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization $DATASETS/core-metrics-results/unweighted-unifrac-subject-significance.qzv \
	--p-pairwise

qiime diversity beta-group-significance \
	--i-distance-matrix $DATASETS/core-metrics-results/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $DATASETS/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $DATASETS/core-metrics-results/unweighted-unifrac-tissue-significance.qzv \
	--p-pairwise
