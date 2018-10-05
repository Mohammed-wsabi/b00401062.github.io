#!/usr/bin/env bash

## Alpha Rarefaction

qiime diversity alpha-rarefaction \
	--i-table $CGM/table.qza \
	--i-phylogeny $CGM/rooted-tree.qza \
	--p-max-depth 4778 \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/alpha-rarefaction.qzv

## Preprocessing

qiime diversity core-metrics-phylogenetic \
	--i-phylogeny $CGM/rooted-tree.qza \
	--i-table $CGM/table.qza \
	--p-sampling-depth 2035 \
	--m-metadata-file $CGM/metadata.tsv \
	--output-dir $CGM/core-metrics-results

## Alpha Diversity

qiime diversity alpha-group-significance \
	--i-alpha-diversity $CGM/core-metrics-results/faith_pd_vector.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/core-metrics-results/faith-pd-group-significance.qzv

qiime diversity alpha-group-significance \
	--i-alpha-diversity $CGM/core-metrics-results/evenness_vector.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/core-metrics-results/evenness-group-significance.qzv

## Beta Diversity

qiime diversity beta-group-significance \
	--i-distance-matrix $CGM/core-metrics-results/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization $CGM/core-metrics-results/unweighted-unifrac-subject-significance.qzv \
	--p-pairwise

qiime diversity beta-group-significance \
	--i-distance-matrix $CGM/core-metrics-results/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $CGM/core-metrics-results/unweighted-unifrac-tissue-significance.qzv \
	--p-pairwise
