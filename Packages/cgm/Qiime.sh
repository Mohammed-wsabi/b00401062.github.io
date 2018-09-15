#!/usr/bin/env bash

export PATH="~/Library/Conda/bin/:$PATH"
export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

## Importing Data

qiime tools import \
	--type "SampleData[PairedEndSequencesWithQuality]" \
	--input-path $CGM/Manifest.csv \
	--input-format PairedEndFastqManifestPhred33 \
	--output-path $CGM/Demultiplexed.qza

qiime demux summarize \
	--i-data $CGM/Demultiplexed.qza \
	--o-visualization $CGM/Demultiplexed.qzv

## Denoising

qiime dada2 denoise-paired \
	--i-demultiplexed-seqs $CGM/Demultiplexed.qza \
	--p-trim-left-f 0 \
	--p-trim-left-r 0 \
	--p-trunc-len-f 200 \
	--p-trunc-len-r 200 \
	--p-n-threads 0 \
	--o-representative-sequences $CGM/Representative.qza \
	--o-table $CGM/FeatureTable.qza \
	--o-denoising-stats $CGM/DenoisingStats.qza

qiime feature-table tabulate-seqs \
	--i-data $CGM/Representative.qza \
	--o-visualization $CGM/Representative.qzv

qiime feature-table summarize \
	--i-table $CGM/FeatureTable.qza \
	--o-visualization $CGM/FeatureTable.qzv

qiime metadata tabulate \
	--m-input-file $CGM/DenoisingStats.qza \
	--o-visualization $CGM/DenoisingStats.qzv

## Phylogenetic Diversity Analyses

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $CGM/Representative.qza \
	--o-alignment $CGM/Alignement.qza \
	--o-masked-alignment $CGM/MaskedAlignment.qza \
	--o-tree $CGM/UnrootedTree.qza \
	--o-rooted-tree $CGM/RootedTree.qza

qiime tools export \
	--input-path $CGM/UnrootedTree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/UnrootedTree.nwk

qiime tools export \
	--input-path $CGM/RootedTree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/RootedTree.nwk

## Diversity Analysis

qiime diversity core-metrics-phylogenetic \
	--i-phylogeny $CGM/RootedTree.qza \
	--i-table $CGM/FeatureTable.qza \
	--p-sampling-depth 152 \
	--m-metadata-file $CGM/Metadata.tsv \
	--output-dir $CGM/DiversityMetrics

### Alpha Diversity

qiime diversity alpha-group-significance \
	--i-alpha-diversity $CGM/DiversityMetrics/faith_pd_vector.qza \
	--m-metadata-file $CGM/Metadata.tsv \
	--o-visualization $CGM/DiversityMetrics/faith-pd-group-significance.qzv

qiime diversity alpha-group-significance \
	--i-alpha-diversity $CGM/DiversityMetrics/evenness_vector.qza \
	--m-metadata-file $CGM/Metadata.tsv \
	--o-visualization $CGM/DiversityMetrics/evenness-group-significance.qzv

### Beta Diversity

qiime diversity beta-group-significance \
	--i-distance-matrix $CGM/DiversityMetrics/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $CGM/Metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization $CGM/DiversityMetrics/unweighted-unifrac-subject-significance.qzv \
	--p-pairwise

qiime diversity beta-group-significance \
	--i-distance-matrix $CGM/DiversityMetrics/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $CGM/Metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $CGM/DiversityMetrics/unweighted-unifrac-tissue-significance.qzv \
	--p-pairwise

## Alpha Rarefaction

qiime diversity alpha-rarefaction \
	--i-table $CGM/FeatureTable.qza \
	--i-phylogeny $CGM/RootedTree.qza \
	--p-max-depth 22682 \
	--m-metadata-file $CGM/Metadata.tsv \
	--o-visualization $CGM/AlphaRarefaction.qzv

## Taxonomic Analysis

qiime feature-classifier classify-sklearn \
	--i-classifier $CGM/gg-13-8-99-515-806-nb-classifier.qza \
	--i-reads $CGM/Representative.qza \
	--o-classification $CGM/Taxonomy.qza

qiime metadata tabulate \
	--m-input-file $CGM/Taxonomy.qza \
	--o-visualization $CGM/Taxonomy.qzv

qiime taxa barplot \
	--i-table $CGM/FeatureTable.qza \
	--i-taxonomy $CGM/Taxonomy.qza \
	--m-metadata-file $CGM/Metadata.tsv \
	--o-visualization $CGM/taxa-bar-plots.qzv

## Differential Abundance Testing

qiime feature-table filter-samples \
	--i-table table.qza \
	--m-metadata-file sample-metadata.tsv \
	--p-where "BodySite='gut'" \
	--o-filtered-table gut-table.qza

qiime composition add-pseudocount \
	--i-table gut-table.qza \
	--o-composition-table comp-gut-table.qza

qiime composition ancom \
	--i-table comp-gut-table.qza \
	--m-metadata-file sample-metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization ancom-Subject.qzv

qiime taxa collapse \
	--i-table gut-table.qza \
	--i-taxonomy taxonomy.qza \
	--p-level 6 \
	--o-collapsed-table gut-table-l6.qza

qiime composition add-pseudocount \
	--i-table gut-table-l6.qza \
	--o-composition-table comp-gut-table-l6.qza

qiime composition ancom \
	--i-table comp-gut-table-l6.qza \
	--m-metadata-file sample-metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization l6-ancom-Subject.qzv

## Cleanup

source deactivate

unset CGM
unset PATH
