#!/usr/bin/env bash

## Alpha Rarefaction

qiime diversity alpha-rarefaction \
	--i-table $DATASETS/FeatureTable[Frequency]/table.qza \
	--i-phylogeny $DATASETS/Phylogeny[Rooted]/rooted-tree.qza \
	--p-max-depth 10635 \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--o-visualization $DATASETS/diversity-alpha-rarefaction/alpha-rarefaction.qzv

## Preprocessing

qiime diversity core-metrics-phylogenetic \
	--i-phylogeny $DATASETS/Phylogeny[Rooted]/rooted-tree.qza \
	--i-table $DATASETS/FeatureTable[Frequency]/table.qza \
	--p-sampling-depth 1006 \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--output-dir $DATASETS/diversity-core-metrics-phylogenetic

## Alpha Diversity

qiime diversity alpha-group-significance \
	--i-alpha-diversity $DATASETS/diversity-core-metrics-phylogenetic/shannon_vector.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--o-visualization $DATASETS/diversity-alpha-group-significance/shannon-group-significance.qzv

qiime diversity alpha-group-significance \
	--i-alpha-diversity $DATASETS/diversity-core-metrics-phylogenetic/faith_pd_vector.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--o-visualization $DATASETS/diversity-alpha-group-significance/faith-pd-group-significance.qzv

qiime diversity alpha-group-significance \
	--i-alpha-diversity $DATASETS/diversity-core-metrics-phylogenetic/evenness_vector.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--o-visualization $DATASETS/diversity-alpha-group-significance/evenness-group-significance.qzv

## Beta Diversity

qiime diversity beta-group-significance \
	--i-distance-matrix $DATASETS/diversity-core-metrics-phylogenetic/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization $DATASETS/diversity-beta-group-significance/unweighted-unifrac-subject-significance.qzv \
	--p-pairwise

qiime diversity beta-group-significance \
	--i-distance-matrix $DATASETS/diversity-core-metrics-phylogenetic/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization $DATASETS/diversity-beta-group-significance/unweighted-unifrac-subject.qzv \
	--p-pairwise

qiime diversity beta-group-significance \
	--i-distance-matrix $DATASETS/diversity-core-metrics-phylogenetic/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $DATASETS/diversity-beta-group-significance/unweighted-unifrac-tissue.qzv \
	--p-pairwise
